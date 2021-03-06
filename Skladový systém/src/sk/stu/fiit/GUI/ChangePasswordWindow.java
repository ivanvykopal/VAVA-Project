/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.ResourceBundle;
import javax.swing.event.DocumentListener;
import sk.stu.fiit.InternationalizationClass;

/**
 * Trieda predstavujúca obrazovku pre zmenu hesla používateľa.
 *
 * @author Ivan Vykopal
 */
public class ChangePasswordWindow extends javax.swing.JFrame {
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Creates new form ChangePasswordWindow
     */
    public ChangePasswordWindow() {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pfOldPassword = new javax.swing.JPasswordField();
        pfNewPassword = new javax.swing.JPasswordField();
        pfConfirmPassword = new javax.swing.JPasswordField();
        lbInfoMessage = new javax.swing.JLabel();
        btnChangePassword = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("CHANGE_PASSWORD_TITLE"));
        setPreferredSize(new java.awt.Dimension(700, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText(bundle.getString("OLD_PASSWORD") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 25, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText(bundle.getString("NEW_PASSWORD") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 50, 25, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText(bundle.getString("CHECK_PASSWORD") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 50, 25, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        pfOldPassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        pfOldPassword.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(50, 100, 25, 50);
        jPanel1.add(pfOldPassword, gridBagConstraints);

        pfNewPassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        pfNewPassword.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 25, 50);
        jPanel1.add(pfNewPassword, gridBagConstraints);

        pfConfirmPassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        pfConfirmPassword.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 25, 50);
        jPanel1.add(pfConfirmPassword, gridBagConstraints);

        lbInfoMessage.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 25, 0);
        jPanel1.add(lbInfoMessage, gridBagConstraints);

        btnChangePassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnChangePassword.setText(bundle.getString("CHANGE_PASSWORD_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 50, 0);
        jPanel1.add(btnChangePassword, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel6.setText(bundle.getString("CHANGE_PASSWORD_TITLE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel1.add(jLabel6, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbInfoMessage;
    private javax.swing.JPasswordField pfConfirmPassword;
    private javax.swing.JPasswordField pfNewPassword;
    private javax.swing.JPasswordField pfOldPassword;
    // End of variables declaration//GEN-END:variables

    /**
     * Metóda pre získanie potvrdzujúceho hesla.
     * 
     * @return text z textového poľa
     */
    public String getPfConfirmPassword() {
        return new String(pfConfirmPassword.getPassword());
    }

    /**
     * Metóda pre získanie nového hesla.
     * 
     * @return text z textového poľa
     */
    public String getPfNewPassword() {
        return new String(pfNewPassword.getPassword());
    }

    /**
     * Metóda pre získanie pôvodného hesla.
     * 
     * @return text z textového poľa
     */
    public String getPfOldPassword() {
        return new String(pfOldPassword.getPassword());
    }

    /**
     * Metóda pre nastavenie informačnej správy.
     * 
     * @param text text správy
     * 
     * @param color farba textu
     */
    public void setLbInfoMessage(String text, Color color) {
        this.lbInfoMessage.setText(text);
        this.lbInfoMessage.setForeground(color);
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo.
     * 
     * @param mouseAdapter listener pre stlačenia tlačidla
     */
    public void btnChangePasswordAddMouseListener(MouseAdapter mouseAdapter) {
        btnChangePassword.addMouseListener(mouseAdapter);
    }
    
    /**
     * Metóda pre pridanie DocumentListenera pre zmenu hodnoty v textovom poli.
     * 
     * @param listener listener pre zmenu hodnoty v textovom poli
     */
    public void passwordAddListener(DocumentListener listener) {
        pfConfirmPassword.getDocument().addDocumentListener(listener);
        pfNewPassword.getDocument().addDocumentListener(listener);
    }

}
