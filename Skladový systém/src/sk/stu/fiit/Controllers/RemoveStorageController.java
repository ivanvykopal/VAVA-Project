/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.RemoveStorageWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Storage;

/**
 *
 * @author Ivan Vykopal
 */
public final class RemoveStorageController implements Controller {

    private final Database database;
    private final RemoveStorageWindow window;
    private Storage storage = null;

    private RemoveStorageController(Database database, RemoveStorageWindow window) {
        this.database = database;
        this.window = window;

        fillStorageTable("");
        window.setVisible(true);

        initController();
    }

    public static void createController(Database database, RemoveStorageWindow window) {
        new RemoveStorageController(database, window);
    }

    @Override
    public void initController() {
        window.btnChooseStorageAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseStorage();
            }
        });

        window.btnRemoveStorageAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeStorage();
            }
        });

        window.btnFilterAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                filter();
            }
        });
    }

    private void chooseStorage() {
        int index = window.getTbStoragesTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }

        String code = (String) window.getTbStoragesModel().getValueAt(index, 0);

        storage = database.findStorage(code);
        if (storage == null) {
            window.setTfBuilding("");
            window.setTfCode("");
            window.setTfShelf("");
            window.setTfContainItem("");
        } else {
            window.setTfBuilding(storage.getBuilding());
            window.setTfCode(storage.getCode());
            window.setTfShelf(storage.getShelf());
            if (storage.getContainsItem()) {
                window.setTfContainItem("Áno");
            } else {
                window.setTfContainItem("Nie");
            }
        }
    }

    private void removeStorage() {
        if (storage == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        storage = database.removeStorage(storage);
        if (storage == null) {
            JOptionPane.showMessageDialog(window, "Chyb pri mazaní skladovacieho priestoru!");
        } else {
            JOptionPane.showMessageDialog(window, "Skladovací priestor bol vymazaný!");
            window.dispose();
        }
    }

    private void filter() {
        String filter = window.getTfFilter();
        fillStorageTable(filter);
    }

    private void fillStorageTable(String filter) {
        window.getTbStoragesModel().setRowCount(0);
        for (Storage s : database.getStorageTable()) {
            if (Pattern.matches("*" + filter + "*", s.getBuilding())) {
                Object[] row = new Object[4];
                row[0] = s.getCode();
                row[1] = s.getBuilding();
                row[2] = s.getShelf();
                if (s.getContainsItem()) {
                    row[3] = "Áno";
                } else {
                    row[3] = "Nie";
                }
                window.getTbStoragesModel().addRow(row);
            }
        }
    }

}
