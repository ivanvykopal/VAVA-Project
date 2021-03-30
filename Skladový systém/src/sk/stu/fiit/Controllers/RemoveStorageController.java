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
import sk.stu.fiit.GUI.RemoveStorageWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Storage;

/**
 *
 * @author Ivan Vykopal
 */
public final class RemoveStorageController implements Controller {

    private static Database database;
    private final RemoveStorageWindow window;
    private Storage storage = null;
    private int offset = 0;

    private RemoveStorageController(Database database, RemoveStorageWindow window) {
        this.database = database;
        this.window = window;

        new Thread(() -> fillStorageTable("", offset)).start();
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

    private void chooseStorage() {
        int index = window.getTbStoragesTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }

        String code = (String) window.getTbStoragesModel().getValueAt(index, 0);

        try {
            String query = "SELECT id, code, building, shelf, containsItem FROM storage WHERE code = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                storage = new Storage();
                storage.setId(rs.getInt("id"));
                storage.setBuilding(rs.getString("building"));
                storage.setContainsItem(rs.getBoolean("containsItem"));
                storage.setShelf(rs.getString("shelf"));
            }

            window.setTfBuilding(storage.getBuilding());
            window.setTfCode(storage.getCode());
            window.setTfShelf(storage.getShelf());
            if (storage.isContainsItem()) {
                window.setTfContainItem("Áno");
            } else {
                window.setTfContainItem("Nie");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }

    private void removeStorage() {
        if (storage == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }

        try {
            String query = "DELETE FROM storage WHERE id = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setInt(1, storage.getId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(window, "Vybraný skladovací priestor bol vymazaný!");
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
        fillStorageTable(filter, offset);
    }

    private void previous() {
        if (offset == 0) {
            JOptionPane.showMessageDialog(window, "Ste na začiatku zoznamu.");
            return;
        }
        offset -= 100;
        String filter = window.getTfFilter();
        fillStorageTable(filter, offset);
    }

    private void filter() {
        offset = 0;
        String filter = window.getTfFilter();
        fillStorageTable(filter, offset);
    }

    private void fillStorageTable(String filter, int offset) {
        window.getTbStoragesModel().setRowCount(0);
        try {
            String query = "SELECT id, code, building, shelf, containsItem FROM storage"
                    + " WHERE UPPER(building) LIKE UPPER('%" + filter + "%')"
                    + " ORDER BY building LIMIT 100 OFFSET " + offset + ";";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            int pocet = 0;
            Object[] row = new Object[4];
            while (rs.next()) {
                row[0] = rs.getString("code");
                row[1] = rs.getString("building");
                row[2] = rs.getDouble("shelf");
                if (rs.getBoolean("containsItem")) {
                    row[3] = rs.getDouble("Áno");
                } else {
                    row[3] = rs.getDouble("Nie");
                }
                window.getTbStoragesModel().addRow(row);
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
