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
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.AddGoodsWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;

/**
 *
 * @author Ivan Vykopal
 */
public final class AddGoodsController extends Controller {
    private AddGoodsWindow window;
    
    private AddGoodsController(Database database, AddGoodsWindow window) {
        super(database);
        this.window = window;
        
        initController();
    }
    
    public static void createController(Database database, AddGoodsWindow window) {
        new AddGoodsController(database, window);
    }

    @Override
    void initController() {
        window.btnAddGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addGoods();
            }
        });
    }
    
    private void addGoods() {
        Goods goods = new Goods();
        goods.setName(window.getTfName());
        goods.setDescription(window.getTaDescription());
        goods.setCode(window.getTfCode());
        
        goods.setIncomePrice(window.getTfIncomePrice());
        goods.setExportPrice(window.getTfExportPrice());
        if (Boolean.logicalOr(goods.getIncomePrice() == -1, goods.getExportPrice() == -1)) {
            JOptionPane.showMessageDialog(window, "Pri cenách sa využíva '.' namiesto ','!");
            return;
        }
        
        if (goods.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia!");
            return;
        }
        
        try {
            String query = "INSERT INTO goods (name, code, description, incomePrice, exportPrice) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, "'" + goods.getName() + "'");
            ps.setString(2, "'" + goods.getCode() + "'");
            ps.setString(3, "'" + goods.getDescription() + "'");
            ps.setDouble(4, goods.getIncomePrice());
            ps.setDouble(5, goods.getExportPrice());
            
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(window, "Tovar bol pridaný!");
            window.setVisible(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!\n Opakujte prihlásenie!");
        } finally {
            database.closeConnection();
        }
        
    }
    
}
