/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.event.MouseAdapter;
import java.util.ResourceBundle;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.InternationalizationClass;

/**
 *
 * @author Ivan Vykopal
 */
public class AddGoodsWindow extends javax.swing.JFrame {

    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Creates new form AddGoodsWindow
     */
    public AddGoodsWindow() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        tfIncomePrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfExportPrice = new javax.swing.JTextField();
        btnAddGoods = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("ADD_GOODS_TITLE"));
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText(bundle.getString("GOODS_NAME") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 200, 25, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        tfName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 100, 25, 200);
        jPanel1.add(tfName, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText(bundle.getString("GOODS_CODE") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 200, 25, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        tfCode.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 25, 200);
        jPanel1.add(tfCode, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText(bundle.getString("GOODS_DESCRIPTION") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 200, 250, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        taDescription.setColumns(20);
        taDescription.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        taDescription.setWrapStyleWord(true);
        taDescription.setPreferredSize(new java.awt.Dimension(300, 107));
        jScrollPane1.setViewportView(taDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 25, 200);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText(bundle.getString("GOODS_INCOME") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 200, 25, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        tfIncomePrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 25, 200);
        jPanel1.add(tfIncomePrice, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText(bundle.getString("GOODS_EXPORT") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 200, 50, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        tfExportPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 50, 200);
        jPanel1.add(tfExportPrice, gridBagConstraints);

        btnAddGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAddGoods.setText(bundle.getString("ADD_GOODS_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 100, 0);
        jPanel1.add(btnAddGoods, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel6.setText(bundle.getString("ADD_GOODS_TITLE"));
        jLabel6.setPreferredSize(new java.awt.Dimension(250, 41));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel1.add(jLabel6, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddGoods;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfCode;
    private javax.swing.JTextField tfExportPrice;
    private javax.swing.JTextField tfIncomePrice;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

    public void btnAddGoodsAddMouseListener(MouseAdapter mouseAdapter) {
        btnAddGoods.addMouseListener(mouseAdapter);
    }

    public String getTaDescription() {
        return taDescription.getText().trim();
    }

    public String getTfCode() {
        return tfCode.getText().trim();
    }

    public double getTfExportPrice() {
        try {
            if (tfExportPrice.getText().equals("")) {
                return 0;
            }
            return Double.parseDouble(tfExportPrice.getText());
        } catch (NumberFormatException ex) {
            CustomLogger.getLogger(AddGoodsWindow.class).warn(bundle.getString("PRICE_ERROR1"), ex);
            return -1;
        }
    }

    public double getTfIncomePrice() {
        try {
            if (tfIncomePrice.getText().equals("")) {
                return 0;
            }
            return Double.parseDouble(tfIncomePrice.getText());
        } catch (NumberFormatException ex) {
            CustomLogger.getLogger(AddGoodsWindow.class).warn(bundle.getString("PRICE_ERROR1"), ex);
            return -1;
        }
    }

    public String getTfName() {
        return tfName.getText().trim();
    }

}
