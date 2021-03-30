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
import sk.stu.fiit.GUI.RemoveGoodsWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;

/**
 *
 * @author Ivan Vykopal
 */
public final class RemoveGoodsController implements Controller {

    private final Database database;
    private final RemoveGoodsWindow window;
    private Goods goods = null;
    private int offset = 0;

    private RemoveGoodsController(Database database, RemoveGoodsWindow window) {
        this.database = database;
        this.window = window;

        new Thread(() -> fillGoodsTable("", offset)).start();
        window.setVisible(true);

        initController();

    }

    public static void createController(Database database, RemoveGoodsWindow window) {
        new RemoveGoodsController(database, window);
    }

    @Override
    public void initController() {
        window.btnChooseGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseGoods();
            }
        });

        window.btnRemoveGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeGoods();
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

    private void removeGoods() {
        if (goods == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }

        try {
            String query = "UPDATE goods SET deleted = TRUE WHERE id = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setInt(1, goods.getId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(window, "Vybraný tovar bol nastavený ako vymazaný!");
            ps.close();
            window.setVisible(false);
        } catch (SQLException ex) {
            System.out.println("Chyba!");
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
                row[1] = rs.getString("code");
                row[0] = rs.getString("name");
                row[3] = rs.getDouble("incomePrice");
                row[2] = rs.getDouble("exportPrice");
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
