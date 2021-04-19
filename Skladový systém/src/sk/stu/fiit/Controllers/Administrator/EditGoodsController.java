/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.EditGoodsWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;
import sk.stu.fiit.Model.SerializationClass;

/**
 *
 * @author Ivan Vykopal
 */
public final class EditGoodsController implements Controller {

    private final Database database;
    private final EditGoodsWindow window;
    private Goods goods = null;

    private EditGoodsController(Database database, EditGoodsWindow window) {
        this.database = database;
        this.window = window;

        fillGoodsTable("");
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
    }

    private void chooseGoods() {
        int index = window.getTbGoodsTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam!");
            CustomLogger.getLogger(EditGoodsController.class).warn("Nebol vybraný žiaden záznam!");
            return;
        }

        String code = (String) window.getTbGoodsModel().getValueAt(index, 0);

        goods = database.findGoods(code);
        if (goods == null) {
            window.setTfCode("");
            window.setTfName("");
            window.setTaDescription("");
            window.setTfImportPrice("");
            window.setTfExportPrice("");
        } else {
            window.setTfCode(code);
            window.setTfName(goods.getName());
            window.setTaDescription(goods.getDescription());
            window.setTfImportPrice("" + goods.getIncomePrice());
            window.setTfExportPrice("" + goods.getExportPrice());
        }
    }

    private void editGoods() {
        if (goods == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam!");
            CustomLogger.getLogger(EditGoodsController.class).warn("Nebol vybraný žiaden záznam!");
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
            CustomLogger.getLogger(EditGoodsController.class).warn("Neboli vyplnené všetky polia!");
            return;
        }

        goods = database.setGoods(goods);
        if (goods == null) {
            JOptionPane.showMessageDialog(window, "Chyba pri zmene údajov tovaru!");
            CustomLogger.getLogger(EditGoodsController.class).warn("Chyba pri zmene údajov tovaru!");
        } else {
            JOptionPane.showMessageDialog(window, "Tovar bol upravený!");
            CustomLogger.getLogger(EditGoodsController.class).info(goods.getCode() + ": " + "Tovar bol upravený!");
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

    private void filter() {
        String filter = window.getTfFilter();
        fillGoodsTable(filter);
    }

    private void fillGoodsTable(String filter) {
        window.getTbGoodsModel().setRowCount(0);
        Pattern pattern = Pattern.compile("*" + filter + "*", Pattern.CASE_INSENSITIVE);
        for (Goods g : database.getGoodsTable()) {
            if (!g.isDeleted()) {
                if (pattern.matcher(g.getName()).find()) {
                    Object[] row = new Object[4];
                    row[0] = g.getCode();
                    row[1] = g.getName();
                    row[2] = g.getIncomePrice();
                    row[3] = g.getExportPrice();
                    window.getTbGoodsModel().addRow(row);
                }
            }
        }
    }

}
