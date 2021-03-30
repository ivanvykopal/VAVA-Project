/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.EditGoodsWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;

/**
 *
 * @author Ivan Vykopal
 */
public final class EditGoodsController implements Controller {
    private static Database database;
    private final EditGoodsWindow window;
    private Goods goods = null;
    private int offset = 0;

    private EditGoodsController(Database database, EditGoodsWindow window) {
        this.database = database;
        this.window = window;
        
        new Thread(() -> fillGoodsTable("", offset)).start();
        window.setVisible(true);
        
        initController();
    }
    
    public static void createController(Database database, EditGoodsWindow window) {
        new EditGoodsController(database, window);
    }

    @Override
    public void initController() {
        window.btnChooseGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseGoods();
            }
        });
        
        window.btnEditGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                editGoods();
            }
        });
        
        window.btnFilterAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                filter();
            }
        });
        
        window.btnNextAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
               next();
            }
        });
        
        window.btnPreviousAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                previous();
            }
        });
    }
    
    private void chooseGoods() {
        int index = window.getTbGoodsTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        String code = (String) window.getTbGoodsModel().getValueAt(index, 0);
        
        try {
            String query = "SELECT name, code, description, incomePrice, exportPrice FROM goods WHERE code = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                goods = new Goods();
                goods.setCode(code);
                goods.setDescription(rs.getString("description"));
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setIncomePrice(rs.getDouble("incomePrice"));
                goods.setExportPrice(rs.getDouble("exportPrice"));
            }
            
            window.setTfCode(code);
            window.setTfName(goods.getName());
            window.setTaDescription(goods.getDescription());
            window.setTfImportPrice("" + goods.getIncomePrice());
            window.setTfExportPrice("" + goods.getExportPrice());
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
    private void editGoods() {
        if (goods == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        goods.setCode(window.getTfCode());
        goods.setDescription(window.getTaDescription());
        goods.setExportPrice(window.getTfExportPrice());
        goods.setIncomePrice(window.getTfImportPrice());
        goods.setName(window.getTfName());
        
        if (Boolean.logicalOr(goods.getIncomePrice() == -1, goods.getExportPrice() == -1)) {
            JOptionPane.showMessageDialog(window, "Pri cenách sa využíva '.' namiesto ','!");
            return;
        }
        
        if (goods.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia!");
            return;
        }
        
        try {
            String query = "UPDATE goods SET name = ?, code = ?, description = ?, incomePrice = ?, exportPrice = ? WHERE id = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, goods.getName());
            ps.setString(2, goods.getCode());
            ps.setString(3, goods.getDescription());
            ps.setDouble(4, goods.getIncomePrice());
            ps.setDouble(5, goods.getExportPrice());
            ps.setInt(6, goods.getId());
            
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(window, "Tovar bol upravený!");
            window.setVisible(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!\n Opakujte prihlásenie!");
        } finally {
            database.closeConnection();
        }
    }
    
    private void next() {
        offset += 100;
        String filter = window.getTfFilter();
        fillGoodsTable(filter, offset);
    }
    
    private void previous() {
        if (offset == 0) {
            JOptionPane.showMessageDialog(window, "Ste na začiatku zoznamu.");
            return;
        }
        offset -= 100;
        String filter = window.getTfFilter();
        fillGoodsTable(filter, offset);
    }
    
    private void filter() {
        offset = 0;
        String filter = window.getTfFilter();
        fillGoodsTable(filter, offset);
    }
    
    private void fillGoodsTable(String filter, int offset) {
        window.getTbGoodsModel().setRowCount(0);
        try {
            String query = "SELECT id, name, code, description, incomePrice, exportPrice FROM goods"
                    + " WHERE deleted = FALSE"
                    + " AND UPPER(name) LIKE UPPER('%" + filter + "%')"
                    + " ORDER BY name LIMIT 100 OFFSET " + offset +";";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            int pocet = 0;
            Object[] row = new Object[4];
            while(rs.next()) {
                row[0] = rs.getString("code");
                row[1] = rs.getString("name");
                row[2] = rs.getDouble("incomePrice");
                row[3] = rs.getDouble("exportPrice");
                window.getTbGoodsModel().addRow(row);
                pocet++;
            }
            
            rs.close();
            ps.close();
            if (pocet == 0) {
                JOptionPane.showMessageDialog(window, "Ste na konci zoznamu.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
}
