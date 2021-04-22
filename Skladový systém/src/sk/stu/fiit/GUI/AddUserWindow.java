/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.event.MouseAdapter;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import sk.stu.fiit.InternationalizationClass;

/**
 * Trieda predstavujúca obrazovku pre pridanie skladovacieho priestoru do systému.
 *
 * @author Ivan Vykopal
 */
public class AddUserWindow extends javax.swing.JFrame {
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Creates new form AddUserWindow
     */
    public AddUserWindow() {
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
        tfUsername = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        cbType = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnAddUser = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("ADD_USER_TITLE"));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText(bundle.getString("USER_USERNAME") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 200, 25, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText(bundle.getString("USER_NAME") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 200, 25, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        tfUsername.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 100, 25, 200);
        jPanel1.add(tfUsername, gridBagConstraints);

        tfName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 25, 200);
        jPanel1.add(tfName, gridBagConstraints);

        cbType.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { bundle.getString("ADMINISTRATOR"), bundle.getString("REFERENT"), bundle.getString("WAREHOUSEMAN") }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 50, 200);
        jPanel1.add(cbType, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText(bundle.getString("USER_TYPE") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 200, 50, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        btnAddUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAddUser.setText(bundle.getString("ADD_USER_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 100, 0);
        jPanel1.add(btnAddUser, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText(bundle.getString("USER_EMAIL") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 200, 25, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        tfEmail.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 100, 25, 200);
        jPanel1.add(tfEmail, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel6.setText(bundle.getString("ADD_USER_TITLE"));
        jLabel6.setPreferredSize(new java.awt.Dimension(325, 41));
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
    private javax.swing.JButton btnAddUser;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

    /**
     * Metóda pre získanie výberového pola.
     * 
     * @return výberové pole pre typ používateľa
     */
    public JComboBox<String> getCbType() {
        return cbType;
    }

    /**
     * Metóda pre získanie e-mailu používateľa.
     * 
     * @return text z textového poľa
     */
    public String getTfEmail() {
        return tfEmail.getText().trim();
    }

    /**
     * Metóda pre získanie mena používateľa.
     * 
     * @return text z textového poľa
     */
    public String getTfName() {
        return tfName.getText().trim();
    }

    /**
     * Metóda pre získanie používateľského mena.
     * 
     * @return text z textového poľa
     */
    public String getTfUsername() {
        return tfUsername.getText().trim();
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo.
     * 
     * @param mouseAdapter listener pre stlačenia tlačidla
     */
    public void btnAddUserAddMouseListener(MouseAdapter mouseAdapter) {
        btnAddUser.addMouseListener(mouseAdapter);
    }

}
