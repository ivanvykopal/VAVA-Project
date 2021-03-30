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
public final class RemoveGoodsController extends Controller {
    private final RemoveGoodsWindow window;
    private static ArrayList<Goods> goodsList = new ArrayList<>();
    private Goods goods = null;
    
    static {
        try {
            String query = "SELECT (id, name, code, description, incomePrice, exportPrice) FROM goods WHERE deleted = FALSE";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                String description = rs.getString("description");
                double incomePrice = rs.getDouble("incomePrice");
                double exportPrice = rs.getDouble("exportPrice");
                goodsList.add(new Goods(id, name, code, description, incomePrice, exportPrice));
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }

    private RemoveGoodsController(Database database, RemoveGoodsWindow window) {
        super(database);
        this.window = window;
        
        fillGoodsTable();
        
        initController();
        
    }

    @Override
    void initController() {
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
    }
    
    private void chooseGoods() {
        int index = window.getTbGoodsTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        String code = (String) window.getTbGoodsModel().getValueAt(index, 0);
        
        try {
            String query = "SELECT (name, code, description, incomePrice, exportPrice) FROM goods WHERE code = ?";
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
            String query = "UPDATE goods SET deleted = TRUE WHERE id = ?";
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
    
    private void fillGoodsTable() {
        for (Goods g : goodsList) {
            Object[] row = new Object[4];
            row[0] = g.getCode();
            row[1] = g.getName();
            row[2] = g.getIncomePrice();
            row[3] = g.getExportPrice();
            window.getTbGoodsModel().addRow(row);
        }
    }
    
}
