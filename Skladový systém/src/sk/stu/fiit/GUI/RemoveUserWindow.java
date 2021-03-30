/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.event.MouseAdapter;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan Vykopal
 */
public class RemoveUserWindow extends javax.swing.JFrame {

    /**
     * Creates new form RemoveUserWindow
     */
    public RemoveUserWindow() {
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
        btnChooseUser = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        btnRemoveUser = new javax.swing.JButton();
        tfType = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsers = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        tfFilter = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        cbTypeFilter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Odstránenie používateľa");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 1400));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        jLabel1.setText("Výber používateľa zo systému");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 25, 50);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        jLabel2.setText("Informácie o používateľovi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(25, 50, 25, 50);
        jPanel1.add(jLabel2, gridBagConstraints);

        btnChooseUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnChooseUser.setText("Vybrať používateľa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 25, 100);
        jPanel1.add(btnChooseUser, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText("Prihlasovacie meno:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText("Meno používateľa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel6.setText("Pracovný e-mail:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setText("Typ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 250, 25, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        tfUsername.setEditable(false);
        tfUsername.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 0, 50);
        jPanel1.add(tfUsername, gridBagConstraints);

        tfName.setEditable(false);
        tfName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 0, 50);
        jPanel1.add(tfName, gridBagConstraints);

        tfEmail.setEditable(false);
        tfEmail.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 0, 50);
        jPanel1.add(tfEmail, gridBagConstraints);

        btnRemoveUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnRemoveUser.setText("Vymazať vybraného používateľa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 25, 0);
        jPanel1.add(btnRemoveUser, gridBagConstraints);

        tfType.setEditable(false);
        tfType.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 0, 50);
        jPanel1.add(tfType, gridBagConstraints);

        tbUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Použivateľské meno", "Meno používateľa", "E-mail", "Typ"
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
        jScrollPane1.setViewportView(tbUsers);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 1000;
        gridBagConstraints.ipady = 500;
        gridBagConstraints.insets = new java.awt.Insets(25, 50, 25, 50);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText("Filter:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 50, 25, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        tfFilter.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tfFilter.setMinimumSize(new java.awt.Dimension(250, 28));
        tfFilter.setPreferredSize(new java.awt.Dimension(250, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 150, 25, 0);
        jPanel1.add(tfFilter, gridBagConstraints);

        btnFilter.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnFilter.setText("Filtrovať");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 450, 25, 0);
        jPanel1.add(btnFilter, gridBagConstraints);

        btnNext.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnNext.setText(">>");
        btnNext.setFocusTraversalPolicyProvider(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(15, 200, 15, 0);
        jPanel1.add(btnNext, gridBagConstraints);

        btnPrevious.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnPrevious.setText("<<");
        btnPrevious.setFocusTraversalPolicyProvider(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 200);
        jPanel1.add(btnPrevious, gridBagConstraints);

        cbTypeFilter.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cbTypeFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrátor", "Referent", "Skladník" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 25, 0);
        jPanel1.add(cbTypeFilter, gridBagConstraints);

        jScrollPane3.setViewportView(jPanel1);

        getContentPane().add(jScrollPane3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseUser;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnRemoveUser;
    private javax.swing.JComboBox<String> cbTypeFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbUsers;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFilter;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfType;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

    public JTable getTbUsersTable() {
        return tbUsers;
    }

    public DefaultTableModel getTbUsersModel() {
        return (DefaultTableModel) tbUsers.getModel();
    }

    public void setTfEmail(String text) {
        this.tfEmail.setText(text);
    }

    public void setTfName(String text) {
        this.tfName.setText(text);
    }

    public void setTfType(String text) {
        this.tfType.setText(text);
    }

    public void setTfUsername(String text) {
        this.tfUsername.setText(text);
    }

    public JComboBox<String> getCbTypeFilter() {
        return cbTypeFilter;
    }

    public String getTfFilter() {
        return tfFilter.getText().trim();
    }
    
    public void btnRemoveUserAddMouseListener(MouseAdapter mouseAdapter) {
        btnRemoveUser.addMouseListener(mouseAdapter);
    }
    
    public void btnChooseUserAddMouseListener(MouseAdapter mouseAdapter) {
        btnChooseUser.addMouseListener(mouseAdapter);
    }
    
    public void btnPreviousAddMouseListener(MouseAdapter mouseAdapter) {
        btnPrevious.addMouseListener(mouseAdapter);
    }
    
    public void btnNextAddMouseListener(MouseAdapter mouseAdapter) {
        btnNext.addMouseListener(mouseAdapter);
    }
    
    public void btnFilterAddMouseListener(MouseAdapter mouseAdapter) {
        btnFilter.addMouseListener(mouseAdapter);
    }
    
}
