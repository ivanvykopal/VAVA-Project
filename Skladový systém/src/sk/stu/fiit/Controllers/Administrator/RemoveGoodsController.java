/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.RemoveGoodsWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;
import sk.stu.fiit.Model.SerializationClass;

/**
 *
 * @author Ivan Vykopal
 */
public final class RemoveGoodsController implements Controller {

    private final Database database;
    private final RemoveGoodsWindow window;
    private Goods goods = null;
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    private RemoveGoodsController(Database database, RemoveGoodsWindow window) {
        this.database = database;
        this.window = window;

        fillGoodsTable("");
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
    }

    private void chooseGoods() {
        int index = window.getTbGoodsTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveGoodsController.class).warn(bundle.getString("RECORD_ERROR"));
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

    private void removeGoods() {
        if (goods == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveGoodsController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        goods = database.removeGoods(goods);
        if (goods == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_GOODS_ERROR"));
            CustomLogger.getLogger(RemoveGoodsController.class).warn(bundle.getString("REMOVE_GOODS_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_GOODS_INFO"));
            CustomLogger.getLogger(RemoveGoodsController.class).info(goods.getCode() + ": " + bundle.getString("REMOVE_GOODS_INFO"));
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
