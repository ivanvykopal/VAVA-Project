/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import sk.stu.fiit.Controllers.IMethod;
import sk.stu.fiit.InternationalizationClass;

/**
 * Trieda predstavujúca obrazovku pre prihláseného referenta.
 *
 * @author Ivan Vykopal
 */
public class ReferentWindow extends javax.swing.JFrame {
    
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Creates new form ReferentWindow
     */
    public ReferentWindow() {
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
        pLogin = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnGoodsOverview = new javax.swing.JButton();
        btnCosts = new javax.swing.JButton();
        btnProfits = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        lbUsername = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        spGoodsOverview = new javax.swing.JScrollPane();
        pGoodsOverview = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOverviewGoods = new javax.swing.JTable();
        lbTotalPrice = new javax.swing.JLabel();
        btnExportData = new javax.swing.JButton();
        spGoodsCosts = new javax.swing.JScrollPane();
        pGoodsCosts = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnViewCosts = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbCosts = new javax.swing.JLabel();
        ftfCostsFrom = new javax.swing.JFormattedTextField();
        ftfCostsTo = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbGoodsCosts = new javax.swing.JTable();
        spGoodsProfits = new javax.swing.JScrollPane();
        pGoodsProfits = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnViewProfits = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbProfits = new javax.swing.JLabel();
        ftfProfitsFrom = new javax.swing.JFormattedTextField();
        ftfProfitsTo = new javax.swing.JFormattedTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbGoodsProfits = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        JMenu3 = new javax.swing.JMenu();
        miLoginPage = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        miChangePassword = new javax.swing.JMenuItem();
        miLogout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miExit = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        miGoodsOverview = new javax.swing.JMenuItem();
        miCosts = new javax.swing.JMenuItem();
        miProfits = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("REFERENT_TITLE"));
        setPreferredSize(new java.awt.Dimension(1400, 900));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.CardLayout());

        pLogin.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnGoodsOverview.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnGoodsOverview.setText(bundle.getString("GOODS_OVERVIEW"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel4.add(btnGoodsOverview, gridBagConstraints);

        btnCosts.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnCosts.setText(bundle.getString("COSTS_OVERVIEW"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel4.add(btnCosts, gridBagConstraints);

        btnProfits.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnProfits.setText(bundle.getString("PROFITS_OVERVIEW"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel4.add(btnProfits, gridBagConstraints);

        pLogin.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        lbName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbName.setText(bundle.getString("NAME_LB") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel5.add(lbName, gridBagConstraints);

        btnLogout.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnLogout.setText(bundle.getString("LOGOUT_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel5.add(btnLogout, gridBagConstraints);

        btnChangePassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnChangePassword.setText(bundle.getString("CHANGE_PASSWORD_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel5.add(btnChangePassword, gridBagConstraints);

        lbUsername.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbUsername.setText(bundle.getString("USER_USERNAME") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel5.add(lbUsername, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel8.setText(bundle.getString("REFERENT"));
        jLabel8.setPreferredSize(new java.awt.Dimension(210, 41));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel5.add(jLabel8, gridBagConstraints);

        pLogin.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.add(pLogin, "card2");

        pGoodsOverview.setBackground(new java.awt.Color(255, 255, 255));
        pGoodsOverview.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel1.setText(bundle.getString("GOODS_OVERVIEW"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsOverview.add(jLabel1, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 500));

        tbOverviewGoods.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbOverviewGoods.getTableHeader().setFont(new java.awt.Font("Arial", 1, 15));
        tbOverviewGoods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                bundle.getString("GOODS_CODE"), bundle.getString("GOODS_NAME"), bundle.getString("QUANTITY"), bundle.getString("GOODS_INCOME"), bundle.getString("GOODS_EXPORT")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbOverviewGoods);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsOverview.add(jScrollPane1, gridBagConstraints);

        lbTotalPrice.setFont(new java.awt.Font("Arial", 0, 25)); // NOI18N
        lbTotalPrice.setText(bundle.getString("TOTAL_PRICE_LB") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsOverview.add(lbTotalPrice, gridBagConstraints);

        btnExportData.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnExportData.setText(bundle.getString("EXPORT"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsOverview.add(btnExportData, gridBagConstraints);

        spGoodsOverview.setViewportView(pGoodsOverview);

        jPanel1.add(spGoodsOverview, "card6");

        pGoodsCosts.setBackground(new java.awt.Color(255, 255, 255));
        pGoodsCosts.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText(bundle.getString("DATE_FROM") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 300);
        pGoodsCosts.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText(bundle.getString("DATE_TO") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 300);
        pGoodsCosts.add(jLabel3, gridBagConstraints);

        btnViewCosts.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnViewCosts.setText(bundle.getString("VIEW_COSTS_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsCosts.add(btnViewCosts, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel4.setText(bundle.getString("COSTS_OVERVIEW_TITLE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsCosts.add(jLabel4, gridBagConstraints);

        lbCosts.setFont(new java.awt.Font("Arial", 0, 25)); // NOI18N
        lbCosts.setText(bundle.getString("COSTS_OVERVIEW_LB") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsCosts.add(lbCosts, gridBagConstraints);

        ftfCostsFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(bundle.getString("DATE_FORMAT")))));
        ftfCostsFrom.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ftfCostsFrom.setMinimumSize(new java.awt.Dimension(250, 28));
        ftfCostsFrom.setPreferredSize(new java.awt.Dimension(250, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(25, 300, 25, 25);
        pGoodsCosts.add(ftfCostsFrom, gridBagConstraints);

        ftfCostsTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(bundle.getString("DATE_FORMAT")))));
        ftfCostsTo.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ftfCostsTo.setMinimumSize(new java.awt.Dimension(250, 28));
        ftfCostsTo.setPreferredSize(new java.awt.Dimension(250, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 300, 25, 25);
        pGoodsCosts.add(ftfCostsTo, gridBagConstraints);

        jScrollPane3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jScrollPane3.setMinimumSize(new java.awt.Dimension(1000, 500));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(1000, 500));

        tbGoodsCosts.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbGoodsCosts.getTableHeader().setFont(new java.awt.Font("Arial", 1, 15));
        tbGoodsCosts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                bundle.getString("GOODS_CODE"), bundle.getString("GOODS_NAME"), bundle.getString("QUANTITY"), bundle.getString("GOODS_INCOME")
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
        jScrollPane3.setViewportView(tbGoodsCosts);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsCosts.add(jScrollPane3, gridBagConstraints);

        spGoodsCosts.setViewportView(pGoodsCosts);

        jPanel1.add(spGoodsCosts, "card6");

        pGoodsProfits.setBackground(new java.awt.Color(255, 255, 255));
        pGoodsProfits.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText(bundle.getString("DATE_FROM") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 300);
        pGoodsProfits.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel6.setText(bundle.getString("DATE_TO") + ":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 300);
        pGoodsProfits.add(jLabel6, gridBagConstraints);

        btnViewProfits.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnViewProfits.setText(bundle.getString("VIEW_PROFITS_BTN"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsProfits.add(btnViewProfits, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel7.setText(bundle.getString("PROFITS_OVERVIEW_TITLE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsProfits.add(jLabel7, gridBagConstraints);

        lbProfits.setFont(new java.awt.Font("Arial", 0, 25)); // NOI18N
        lbProfits.setText(bundle.getString("PROFITS_OVERVIEW_LB"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsProfits.add(lbProfits, gridBagConstraints);

        ftfProfitsFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(bundle.getString("DATE_FORMAT")))));
        ftfProfitsFrom.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ftfProfitsFrom.setMinimumSize(new java.awt.Dimension(250, 28));
        ftfProfitsFrom.setPreferredSize(new java.awt.Dimension(250, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(25, 300, 25, 25);
        pGoodsProfits.add(ftfProfitsFrom, gridBagConstraints);

        ftfProfitsTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(bundle.getString("DATE_FORMAT")))));
        ftfProfitsTo.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ftfProfitsTo.setMinimumSize(new java.awt.Dimension(250, 28));
        ftfProfitsTo.setPreferredSize(new java.awt.Dimension(250, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(25, 300, 25, 25);
        pGoodsProfits.add(ftfProfitsTo, gridBagConstraints);

        jScrollPane4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jScrollPane4.setMinimumSize(new java.awt.Dimension(1000, 500));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(1000, 500));

        tbGoodsProfits.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbGoodsProfits.getTableHeader().setFont(new java.awt.Font("Arial", 1, 15));
        tbGoodsProfits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                bundle.getString("GOODS_CODE"), bundle.getString("GOODS_NAME"), bundle.getString("QUANTITY"), bundle.getString("GOODS_INCOME"), bundle.getString("GOODS_EXPORT")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbGoodsProfits);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        pGoodsProfits.add(jScrollPane4, gridBagConstraints);

        spGoodsProfits.setViewportView(pGoodsProfits);

        jPanel1.add(spGoodsProfits, "card6");

        getContentPane().add(jPanel1);

        JMenu3.setText(bundle.getString("ACCOUNT_MENU"));
        JMenu3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        miLoginPage.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miLoginPage.setText(bundle.getString("VIEW_HOMEPAGE_MENU"));
        JMenu3.add(miLoginPage);
        JMenu3.add(jSeparator2);

        miChangePassword.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miChangePassword.setText(bundle.getString("CHANGE_PASSWORD_BTN"));
        JMenu3.add(miChangePassword);

        miLogout.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miLogout.setText(bundle.getString("LOGOUT_BTN"));
        JMenu3.add(miLogout);
        JMenu3.add(jSeparator1);

        miExit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miExit.setText(bundle.getString("EXIT_MENU"));
        JMenu3.add(miExit);

        jMenuBar1.add(JMenu3);

        jMenu1.setText(bundle.getString("VIEW_MENU"));
        jMenu1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        miGoodsOverview.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miGoodsOverview.setText(bundle.getString("GOODS_OVERVIEW"));
        jMenu1.add(miGoodsOverview);

        miCosts.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miCosts.setText(bundle.getString("VIEW_COSTS_BTN"));
        jMenu1.add(miCosts);

        miProfits.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miProfits.setText(bundle.getString("VIEW_PROFITS_BTN"));
        jMenu1.add(miProfits);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("ABOUT_MENU"));
        jMenu2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        miAbout.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        miAbout.setText(bundle.getString("ABOUT"));
        jMenu2.add(miAbout);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JMenu3;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnCosts;
    private javax.swing.JButton btnExportData;
    private javax.swing.JButton btnGoodsOverview;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnProfits;
    private javax.swing.JButton btnViewCosts;
    private javax.swing.JButton btnViewProfits;
    private javax.swing.JFormattedTextField ftfCostsFrom;
    private javax.swing.JFormattedTextField ftfCostsTo;
    private javax.swing.JFormattedTextField ftfProfitsFrom;
    private javax.swing.JFormattedTextField ftfProfitsTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lbCosts;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbProfits;
    private javax.swing.JLabel lbTotalPrice;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miChangePassword;
    private javax.swing.JMenuItem miCosts;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miGoodsOverview;
    private javax.swing.JMenuItem miLoginPage;
    private javax.swing.JMenuItem miLogout;
    private javax.swing.JMenuItem miProfits;
    private javax.swing.JPanel pGoodsCosts;
    private javax.swing.JPanel pGoodsOverview;
    private javax.swing.JPanel pGoodsProfits;
    private javax.swing.JPanel pLogin;
    private javax.swing.JScrollPane spGoodsCosts;
    private javax.swing.JScrollPane spGoodsOverview;
    private javax.swing.JScrollPane spGoodsProfits;
    private javax.swing.JTable tbGoodsCosts;
    private javax.swing.JTable tbGoodsProfits;
    private javax.swing.JTable tbOverviewGoods;
    // End of variables declaration//GEN-END:variables

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void changePasswordListener(IMethod iMethod) {
        btnChangePassword.addMouseListener(new MouseAdapter() {
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
    public void costsListener(IMethod iMethod) {
        btnCosts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miCosts.addActionListener(e -> iMethod.method());
    }
    
    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void profitsListener(IMethod iMethod) {
        btnProfits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miProfits.addActionListener(e -> iMethod.method());
    }
    
    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void goodsOverviewListener(IMethod iMethod) {
        btnGoodsOverview.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miGoodsOverview.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo a Actionlistenera pre
     * menu item.
     * 
     * @param iMethod listener pre stlačenia tlačidla a menu item
     */
    public void logoutListener(IMethod iMethod) {
        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                iMethod.method();
            }
        });
        miLogout.addActionListener(e -> iMethod.method());
    }

    /**
     * Metóda pre pridanie Actionlistenera pre menu item.
     * 
     * @param listener listener pre stlačenia menu item-u
     */
    public void exitListener(ActionListener listener) {
        miExit.addActionListener(listener);
    }
    
    /**
     * Metóda pre pridanie Actionlistenera pre menu item.
     * 
     * @param listener listener pre stlačenia menu item-u
     */
    public void loginListener(ActionListener listener) {
        miLoginPage.addActionListener(listener);
    }
    
    /**
     * Metóda pre pridanie Actionlistenera pre menu item.
     * 
     * @param listener listener pre stlačenia menu item-u
     */
    public void aboutListener(ActionListener listener) {
        miAbout.addActionListener(listener);
    }

    /**
     * Metóda pre zíksanie panelu domovskej stránky.
     * 
     * @return panel s domovskou stránkou
     */
    public JPanel getpLogin() {
        return pLogin;
    }

    /**
     * Metóda pre získanie modelu tabuľky informácií o tovaroch.
     * 
     * @return model tabuľky informácií o tovaroch
     */
    public DefaultTableModel getTbOverviewGoodsModel() {
        return (DefaultTableModel) tbOverviewGoods.getModel();
    }

    /**
     * Metóda pre nastavenie textu pre label.
     * 
     * @param text dodatočný text pre label
     */
    public void setLbTotalPrice(String text) {
        this.lbTotalPrice.setText(bundle.getString("TOTAL_PRICE_LB") + ": " + text);
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo.
     * 
     * @param adapter listener pre stlačenia tlačidla
     */
    public void btnViewCostsAddListener(MouseAdapter adapter) {
        btnViewCosts.addMouseListener(adapter);
    }

    /**
     * Metóda pre získanie dátumu, od ktorého zisťujeme náklady.
     * 
     * @return dátum
     */
    public String getFtfCostsFrom() {
        return ftfCostsFrom.getText().trim();
    }

    /**
     * Metóda pre nastavenie hodnoty v textovom poli.
     * 
     * @param text hodnota pre textové pole
     */
    public void setFtfCostsFrom(String text) {
        ftfCostsFrom.setText(text);
    }
    
    /**
     * Metóda pre získanie dátumu, do ktorého zisťujeme náklady.
     * 
     * @return dátum
     */
    public String getFtfCostsTo() {
        return ftfCostsTo.getText().trim();
    }
    
    /**
     * Metóda pre nastavenie hodnoty v textovom poli.
     * 
     * @param text hodnota pre textové pole
     */
    public void setFtfCostsTo(String text) {
        ftfCostsTo.setText(text);
    }

    /**
     * Metóda pre nastavenie textu pre label.
     * 
     * @param text dodatočný text pre label
     */
    public void setLbCosts(String text) {
        lbCosts.setText(bundle.getString("COSTS_OVERVIEW_LB") + ": " + text);
    }

    /**
     * Metóda pre získanie modelu tabuľky informácií o nákladoch za tovary.
     * 
     * @return model tabuľky informácií o nákladoch za tovary
     */
    public DefaultTableModel getTbGoodsCosts() {
        return (DefaultTableModel) tbGoodsCosts.getModel();
    }

    /**
     * Metóda pre získanie scroll panelu okna pre informácie o nákladoch.
     * 
     * @return scroll panel s informáciami o nákladoch
     */
    public JScrollPane getspGoodsCosts() {
        return spGoodsCosts;
    }

    /**
     * Metóda pre získanie scroll panelu okna pre informácie o tovaroch.
     * 
     * @return scroll panel s informáciami o tovaroch
     */
    public JScrollPane getspGoodsOverview() {
        return spGoodsOverview;
    }

    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo.
     * 
     * @param adapter listener pre stlačenia tlačidla
     */
    public void btnViewProfitsAddListener(MouseAdapter adapter) {
        btnViewProfits.addMouseListener(adapter);
    }

    /**
     * Metóda pre získanie dátumu, od ktorého zisťujeme zisky.
     * 
     * @return dátum
     */
    public String getFtfProfitsFrom() {
        return ftfProfitsFrom.getText().trim();
    }
    
    /**
     * Metóda pre nastavenie hodnoty v textovom poli.
     * 
     * @param text hodnota pre textové pole
     */
    public void setFtfProfitsFrom(String text) {
        ftfProfitsFrom.setText(text);
    }

    /**
     * Metóda pre získanie dátumu, do ktorého zisťujeme zisky.
     * 
     * @return dátum
     */
    public String getFtfProfitsTo() {
        return ftfProfitsTo.getText().trim();
    }
    
    /**
     * Metóda pre nastavenie hodnoty v textovom poli.
     * 
     * @param text hodnota pre textové pole
     */
    public void setFtfProfitsTo(String text) {
        ftfProfitsTo.setText(text);
    }

    /**
     * Metóda pre nastavenie textu pre label.
     * 
     * @param text dodatočný text pre label
     */
    public void setLbProfits(String text) {
        lbProfits.setText(bundle.getString("PROFITS_OVERVIEW_LB") + ": " + text);
    }

     /**
     * Metóda pre získanie scroll panelu okna pre informácie o ziskoch.
     * 
     * @return scroll panel s informáciami o ziskoch
     */
    public JScrollPane getspGoodsProfits() {
        return spGoodsProfits;
    }

    /**
     * Metóda pre získanie modelu tabuľky informácií o ziskoch za tovary.
     * 
     * @return model tabuľky informácií o ziskoch za tovary
     */
    public DefaultTableModel getTbGoodsProfits() {
        return (DefaultTableModel) tbGoodsProfits.getModel();
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
    
    /**
     * Metóda pre pridanie Mouselistenera pre tlačidlo.
     * 
     * @param adapter listener pre stlačenia tlačidla
     */
    public void btnExportDataAddListener(MouseAdapter adapter) {
        btnExportData.addMouseListener(adapter);
    }
 
    /**
     * Metóda pre odstránenie Mouselistenerov z tlačidiel z jednotlivých panelov.
     */
    public void removeListeners() {
        //GoodsCosts panel
        for (MouseListener ml : btnViewCosts.getMouseListeners()) {
            btnViewCosts.removeMouseListener(ml);
        }
        
        //GoodsProfits panel
        for (MouseListener ml : btnViewProfits.getMouseListeners()) {
            btnViewProfits.removeMouseListener(ml);
        }
        
        //GoodsOverview panel
        for (MouseListener ml : btnExportData.getMouseListeners()) {
            btnExportData.removeMouseListener(ml);
        }
    }
    
}
