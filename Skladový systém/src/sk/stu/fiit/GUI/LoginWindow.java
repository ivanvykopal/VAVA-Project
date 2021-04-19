/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.event.MouseAdapter;
import javax.swing.JComboBox;

/**
 *
 * @author Acer
 */
public class LoginWindow extends javax.swing.JFrame{

    /**
     * Creates new form LoginWindow
     */
    public LoginWindow() {
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
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfLoginField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pfPasswordField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        cbLanguage = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Skladový systém");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 800));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(800, 800));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel1.setText("Prihlasovacie meno:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        jPanel8.add(jLabel1, gridBagConstraints);

        tfLoginField.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 250;
        jPanel8.add(tfLoginField, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel2.setText("Prihlasovacie heslo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        jPanel8.add(jLabel2, gridBagConstraints);

        pfPasswordField.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        jPanel8.add(pfPasswordField, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel3.setText("Prihlásenie do systému");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipady = 100;
        jPanel8.add(jLabel3, gridBagConstraints);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 67;
        gridBagConstraints.ipady = 67;
        jPanel8.add(jLabel4, gridBagConstraints);

        btnLogin.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnLogin.setText("Prihlásiť sa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 75;
        jPanel8.add(btnLogin, gridBagConstraints);

        cbLanguage.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cbLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SK", "EN" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 25, 100);
        jPanel8.add(cbLanguage, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText("Jazyk:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 25, 200);
        jPanel8.add(jLabel5, gridBagConstraints);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox<String> cbLanguage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField pfPasswordField;
    private javax.swing.JTextField tfLoginField;
    // End of variables declaration//GEN-END:variables

   
    public void btnLoginAddMouseListener(MouseAdapter mouseAdapter) {
        btnLogin.addMouseListener(mouseAdapter);
    }

    public String getPfPasswordField() {
        return new String(pfPasswordField.getPassword());
    }

    public String getTfLoginField() {
        return tfLoginField.getText();
    }

    public JComboBox<String> getCbLanguage() {
        return cbLanguage;
    }
    
}
