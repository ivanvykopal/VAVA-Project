/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.event.MouseAdapter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan Vykopal
 */
public class RemoveGoodsWindow extends javax.swing.JFrame {

    /**
     * Creates new form RemoveGoodsWindow
     */
    public RemoveGoodsWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnChooseGoods = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfCode = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        tfImportPrice = new javax.swing.JTextField();
        tfExportPrice = new javax.swing.JTextField();
        btnRemoveGoods = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGoods = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Odstránenie tovaru");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 1500));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        jLabel1.setText("Výber tovaru zo systému");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 25, 50);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        jLabel2.setText("Informácie o tovare");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(25, 50, 25, 50);
        jPanel1.add(jLabel2, gridBagConstraints);

        btnChooseGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnChooseGoods.setText("Vybrať tovar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 25, 100);
        jPanel1.add(btnChooseGoods, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText("Názov tovaru:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText("Kód tovaru:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText("Opis tovaru:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel6.setText("Nákupná cena:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setText("Predajná cena:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        tfName.setEditable(false);
        tfName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 0, 50);
        jPanel1.add(tfName, gridBagConstraints);

        tfCode.setEditable(false);
        tfCode.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 0, 50);
        jPanel1.add(tfCode, gridBagConstraints);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 150));

        taDescription.setEditable(false);
        taDescription.setColumns(20);
        taDescription.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        taDescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(taDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 500, 25, 50);
        jPanel1.add(jScrollPane2, gridBagConstraints);

        tfImportPrice.setEditable(false);
        tfImportPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 0, 50);
        jPanel1.add(tfImportPrice, gridBagConstraints);

        tfExportPrice.setEditable(false);
        tfExportPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 25, 50);
        jPanel1.add(tfExportPrice, gridBagConstraints);

        btnRemoveGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnRemoveGoods.setText("Vymazať vybraný tovar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 25, 0);
        jPanel1.add(btnRemoveGoods, gridBagConstraints);

        tbGoods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kód tovaru", "Názov tovaru", "Nákupná cena", "Predajná cena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbGoods);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 1000;
        gridBagConstraints.ipady = 500;
        gridBagConstraints.insets = new java.awt.Insets(25, 50, 25, 50);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jScrollPane3.setViewportView(jPanel1);

        getContentPane().add(jScrollPane3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseGoods;
    private javax.swing.JButton btnRemoveGoods;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tbGoods;
    private javax.swing.JTextField tfCode;
    private javax.swing.JTextField tfExportPrice;
    private javax.swing.JTextField tfImportPrice;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

    public JTable getTbGoodsTable() {
        return tbGoods;
    }
    
    public DefaultTableModel getTbGoodsModel() {
        return (DefaultTableModel) tbGoods.getModel();
    }

    public void setTaDescription(String text) {
        this.taDescription.setText(text);
    }

    public void setTfCode(String text) {
        this.tfCode.setText(text);
    }

    public void setTfExportPrice(String text) {
        this.tfExportPrice.setText(text);
    }

    public void setTfImportPrice(String text) {
        this.tfImportPrice.setText(text);
    }

    public void setTfName(String text) {
        this.tfName.setText(text);
    }
    
    public void btnRemoveGoodsAddMouseListener(MouseAdapter mouseAdapter) {
        btnRemoveGoods.addMouseListener(mouseAdapter);
    }
    
    public void btnChooseGoodsAddMouseListener(MouseAdapter mouseAdapter) {
        btnChooseGoods.addMouseListener(mouseAdapter);
    }

}
