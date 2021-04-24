/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.JMenuItem;
import sk.stu.fiit.Controllers.IMethod;
import sk.stu.fiit.InternationalizationClass;

/**
 * Trieda predstavujúca obrazovku pre prihláseného administrátora.
 *
 * @author Ivan Vykopal
 */
public class AdministratorWindow extends javax.swing.JFrame {
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Creates new form AdministratorWindow
     */
    public AdministratorWindow() {
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        btnAddGoods = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        btnAddStorage = new javax.swing.JButton();
        btnEditGoods = new javax.swing.JButton();
        btnEditUser = new javax.swing.JButton();
        btnRemoveGoods = new javax.swing.JButton();
        btnRemoveUser = new javax.swing.JButton();
        btnRemoveStorage = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        lbUsername = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miChangePassword = new javax.swing.JMenuItem();
        miLogout = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        miExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miAddGoods = new javax.swing.JMenuItem();
        miAddUser = new javax.swing.JMenuItem();
        miAddStorage = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miEditGoods = new javax.swing.JMenuItem();
        miEditUser = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        miRemoveGoods = new javax.swing.JMenuItem();
        miRemoveUser = new javax.swing.JMenuItem();
        miRemoveStorage = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        miExportGoodsData = new javax.swing.JMenuItem();
        miImportGoodsData = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("ADMINISTRATOR_TITLE"));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnAddGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAddGoods.setText(bundle.getString("ADD_GOODS_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnAddGoods, gridBagConstraints);

        btnAddUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAddUser.setText(bundle.getString("ADD_USER_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnAddUser, gridBagConstraints);

        btnAddStorage.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAddStorage.setText(bundle.getString("ADD_STORAGE_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnAddStorage, gridBagConstraints);

        btnEditGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEditGoods.setText(bundle.getString("EDIT_GOODS_BTN1"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnEditGoods, gridBagConstraints);

        btnEditUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEditUser.setText(bundle.getString("EDIT_USER_BTN1"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnEditUser, gridBagConstraints);

        btnRemoveGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnRemoveGoods.setText(bundle.getString("REMOVE_GOODS_BTN1"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnRemoveGoods, gridBagConstraints);

        btnRemoveUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnRemoveUser.setText(bundle.getString("REMOVE_USER_BTN1"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnRemoveUser, gridBagConstraints);

        btnRemoveStorage.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnRemoveStorage.setText(bundle.getString("REMOVE_STORAGE_BTN1"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel1.add(btnRemoveStorage, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        lbName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbName.setText(bundle.getString("NAME_LB") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel2.add(lbName, gridBagConstraints);

        btnLogout.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnLogout.setText(bundle.getString("LOGOUT_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel2.add(btnLogout, gridBagConstraints);

        btnChangePassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnChangePassword.setText(bundle.getString("CHANGE_PASSWORD_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel2.add(btnChangePassword, gridBagConstraints);

        lbUsername.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbUsername.setText(bundle.getString("USER_USERNAME") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel2.add(lbUsername, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel6.setText(bundle.getString("ADMINISTRATOR"));
        jLabel6.setPreferredSize(new java.awt.Dimension(210, 41));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel2.add(jLabel6, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jMenuBar1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jMenu1.setText(bundle.getString("FILE_MENU"));
        jMenu1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        miChangePassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miChangePassword.setText(bundle.getString("CHANGE_PASSWORD_BTN"));
        jMenu1.add(miChangePassword);

        miLogout.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miLogout.setText(bundle.getString("LOGOUT_BTN"));
        jMenu1.add(miLogout);
        jMenu1.add(jSeparator3);

        miExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        miExit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miExit.setText(bundle.getString("EXIT_MENU"));
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("EDIT_MENU"));
        jMenu2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        miAddGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miAddGoods.setText(bundle.getString("ADD_GOODS_BTN"));
        jMenu2.add(miAddGoods);

        miAddUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miAddUser.setText(bundle.getString("ADD_USER_BTN"));
        jMenu2.add(miAddUser);

        miAddStorage.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miAddStorage.setText(bundle.getString("ADD_STORAGE_BTN"));
        jMenu2.add(miAddStorage);
        jMenu2.add(jSeparator1);

        miEditGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miEditGoods.setText(bundle.getString("EDIT_GOODS_BTN1"));
        jMenu2.add(miEditGoods);

        miEditUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miEditUser.setText(bundle.getString("EDIT_USER_BTN1"));
        jMenu2.add(miEditUser);
        jMenu2.add(jSeparator2);

        miRemoveGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miRemoveGoods.setText(bundle.getString("REMOVE_GOODS_BTN1"));
        jMenu2.add(miRemoveGoods);

        miRemoveUser.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miRemoveUser.setText(bundle.getString("REMOVE_USER_BTN1"));
        jMenu2.add(miRemoveUser);

        miRemoveStorage.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miRemoveStorage.setText(bundle.getString("REMOVE_STORAGE_BTN1"));
        jMenu2.add(miRemoveStorage);
        jMenu2.add(jSeparator4);

        miExportGoodsData.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miExportGoodsData.setText(bundle.getString("XML_EXPORT_TITLE"));
        jMenu2.add(miExportGoodsData);

        miImportGoodsData.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miImportGoodsData.setText(bundle.getString("XML_IMPORT_TITLE"));
        jMenu2.add(miImportGoodsData);

        jMenuBar1.add(jMenu2);

        jMenu5.setText(bundle.getString("ABOUT_MENU"));
        jMenu5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        miAbout.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miAbout.setText(bundle.getString("ABOUT"));
        jMenu5.add(miAbout);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddGoods;
    private javax.swing.JButton btnAddStorage;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnEditGoods;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRemoveGoods;
    private javax.swing.JButton btnRemoveStorage;
    private javax.swing.JButton btnRemoveUser;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miAddGoods;
    private javax.swing.JMenuItem miAddStorage;
    private javax.swing.JMenuItem miAddUser;
    private javax.swing.JMenuItem miChangePassword;
    private javax.swing.JMenuItem miEditGoods;
    private javax.swing.JMenuItem miEditUser;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miExportGoodsData;
    private javax.swing.JMenuItem miImportGoodsData;
    private javax.swing.JMenuItem miLogout;
    private javax.swing.JMenuItem miRemoveGoods;
    private javax.swing.JMenuItem miRemoveStorage;
    private javax.swing.JMenuItem miRemoveUser;
    // End of variables declaration//GEN-END:variables

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void addGoodsListener(IMethod iMethod) {
        btnAddGoods.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miAddGoods.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void addStorageListener(IMethod iMethod) {
        btnAddStorage.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miAddStorage.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void addUserListener(IMethod iMethod) {
        btnAddUser.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miAddUser.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void changePasswordListener(IMethod iMethod) {
        btnChangePassword.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miChangePassword.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void editGoodsListener(IMethod iMethod) {
        btnEditGoods.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miEditGoods.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void editUserListener(IMethod iMethod) {
        btnEditUser.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miEditUser.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void logoutListener(IMethod iMethod) {
        btnLogout.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miLogout.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void removeGoodsListener(IMethod iMethod) {
        btnRemoveGoods.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miRemoveGoods.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void removeStorageListener(IMethod iMethod) {
        btnRemoveStorage.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miRemoveStorage.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void removeUserListener(IMethod iMethod) {
        btnRemoveUser.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miRemoveUser.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Actionlistenera pre menu item.
     * 
     * @param actionListener listener pre stlačenia menu item-u
     */
    public void aboutListener(ActionListener actionListener) {
        miAbout.addActionListener(actionListener);
    }

    /**
     * Metóda pre pridanie Actionlistenera pre menu item.
     * 
     * @param actionListener listener pre stlačenia menu item-u
     */
    public void exportGoodsData(ActionListener actionListener) {
        this.miExportGoodsData.addActionListener(actionListener);
    }
    
    /**
     * Metóda pre pridanie Actionlistenera pre menu item.
     * 
     * @param actionListener listener pre stlačenia menu item-u
     */
    public void importGoodsData(ActionListener actionListener) {
        this.miImportGoodsData.addActionListener(actionListener);
    }

    /**
     * Metóda pre pridanie Actionlistenera pre menu item.
     * 
     * @param actionListener listener pre stlačenia menu item-u
     */
    public void exitListener(ActionListener actionListener) {
        miExit.addActionListener(actionListener);
    }

    /**
     * Metóda pre nastavenie textu pre label.
     * 
     * @param text text pre label
     */
    public void setLbName(String text) {
        this.lbName.setText(text);
    }

    /**
     * Metóda pre nastavenie textu pre label.
     * 
     * @param text text pre label
     */
    public void setLbUsername(String text) {
        this.lbUsername.setText(text);
    }
    
}
