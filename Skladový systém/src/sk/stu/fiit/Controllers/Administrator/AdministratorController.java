/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sk.stu.fiit.Controllers.ChangePasswordController;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.Controllers.LoginController;
import sk.stu.fiit.Controllers.Referent.GoodsOverviewController;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.About;
import sk.stu.fiit.GUI.AddGoodsWindow;
import sk.stu.fiit.GUI.AddStorageWindow;
import sk.stu.fiit.GUI.AddUserWindow;
import sk.stu.fiit.GUI.AdministratorWindow;
import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.GUI.EditGoodsWindow;
import sk.stu.fiit.GUI.EditUserWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.GUI.RemoveGoodsWindow;
import sk.stu.fiit.GUI.RemoveStorageWindow;
import sk.stu.fiit.GUI.RemoveUserWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;
import sk.stu.fiit.Model.User;

/**
 * Trieda reprezentujúca controller pre prihláseného administrátora.
 *
 * @see Controller
 * 
 * @author Ivan Vykopal
 */
public final class AdministratorController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného administrátora. **/
    private final AdministratorWindow window;
    
    /** Atribút user predstavujúci prihláseného administrátora. **/
    private final User user;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code AdministratorController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V tomto konštruktore sa zároveň vypĺňajú informácie o administrátorovi viditeľné
     * na obrazovke ako sú prihlasovacie meno a meno administrátora.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného administrátora
     * 
     * @param user prihlásený administrátor
     */
    private AdministratorController(Database database, AdministratorWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;

        window.setVisible(true);

        window.setLbName(bundle.getString("NAME_LB") + ": " + user.getName());
        window.setLbUsername(bundle.getString("USER_USERNAME") + ": " + user.getUsername());

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code AdministratorController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie tovarov
     * 
     * @param user prihlásený administrátor
     */
    public static void createController(Database database, AdministratorWindow window, User user) {
        new AdministratorController(database, window, user);
    }

    /**
     * Metóda pre pridanie listenerov pre tlačidlá a menu itemy nachádzajúce sa
     * na obrazovke administrátora po prihlásení. 
     */
    @Override
    public void initController() {
        window.addGoodsListener(() -> addGoods());

        window.addStorageListener(() -> addStorage());

        window.addUserListener(() -> addUser());

        window.aboutListener(e -> new About().setVisible(true));

        window.changePasswordListener(() -> changePassword());

        window.editGoodsListener(() -> editGoods());

        window.editUserListener(() -> editUser());

        window.exitListener(e -> System.exit(0));

        window.logoutListener(() -> logout());

        window.removeGoodsListener(() -> removeGoods());

        window.removeStorageListener(() -> removeStorage());

        window.removeUserListener(() -> removeUser());
        
        window.exportGoodsData(e -> exportGoodsData());
        
        window.importGoodsData(e -> importGoodsData());

    }

    /**
     * Metóda, ktorá volá controller pre {@code AddGoodsController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void addGoods() {
        AddGoodsController.createController(database, new AddGoodsWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code AddStorageController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void addStorage() {
        AddStorageController.createController(database, new AddStorageWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code AddUserController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void addUser() {
        AddUserController.createController(database, new AddUserWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code ChangePasswordController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }

    /**
     * Metóda, ktorá volá controller pre {@code EditGoodsController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void editGoods() {
        EditGoodsController.createController(database, new EditGoodsWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code EditUserController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void editUser() {
        EditUserController.createController(database, new EditUserWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code LoginController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void logout() {
        LoginController.createController(database, new LoginWindow());
        window.setVisible(false);
    }

    /**
     * Metóda, ktorá volá controller pre {@code RemoveGoodsController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void removeGoods() {
        RemoveGoodsController.createController(database, new RemoveGoodsWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code RemoveStorageController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void removeStorage() {
        RemoveStorageController.createController(database, new RemoveStorageWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code RemoveUserController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void removeUser() {
        RemoveUserController.createController(database, new RemoveUserWindow());
    }
    
    /**
     * Metóda pre vygenerovanie XML súboru s informáciami o tovaroch v systéme.
     * 
     * <p>
     * Príklad pre formát XML:
     * {@code
     * <warehouse>
     *     <goods>
     *         <name>Text</name>
     *         <code>Text</code>
     *         <description>Text</description>
     *         <prices>
     *             <incomePrice>cena1</incomePrice>
     *             <exportPrice>cena2</exportPrice>
     *         </prices>
     *         <deleted>false</deleted>
     *     </goods>
     * </warehouse>
     * }
     * </p>
     */
    private void exportGoodsData() {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
        
            Document document = documentBuilder.newDocument();
        
            Element root = document.createElement("warehouse");
            document.appendChild(root);

            for (Goods g : database.getGoodsTable()) {
                Element goods = document.createElement("goods");
                root.appendChild(goods);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(g.getName()));
                goods.appendChild(name);

                Element code = document.createElement("code");
                code.appendChild(document.createTextNode(g.getCode()));
                goods.appendChild(code);

                Element description = document.createElement("description");
                description.appendChild(document.createTextNode(g.getDescription()));
                goods.appendChild(description);

                Element prices = document.createElement("prices");
                goods.appendChild(prices);

                Element incomePrice = document.createElement("incomePrice");
                incomePrice.appendChild(document.createTextNode("" + g.getIncomePrice()));
                prices.appendChild(incomePrice);

                Element exportPrice = document.createElement("exportPrice");
                exportPrice.appendChild(document.createTextNode("" + g.getExportPrice()));
                prices.appendChild(exportPrice);

                Element deleted = document.createElement("deleted");
                deleted.appendChild(document.createTextNode("" + g.isDeleted()));
                goods.appendChild(deleted);
            }
        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
            transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("goods.xml"));
            transformer.transform(domSource, streamResult);
            
        } catch (ParserConfigurationException | TransformerException ex) {
            JOptionPane.showMessageDialog(window, bundle.getString("XML_ERROR"));
            CustomLogger.getLogger(GoodsOverviewController.class).warn(bundle.getString("XML_ERROR"), ex);
        } 
    }
    
    /**
     * Metóda pre načítanie XML súboru s informáciami o tovaroch a ich nasledovné
     * pridanie do systému.
     * 
     * <p>
     * Do systému sa nahrajú len tie tovary, ktoré sa v systéme ešte nenachádzajú.
     * </p>
     */
    private void importGoodsData() {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        

        try {
            documentFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder documentBuilder;
            documentBuilder = documentFactory.newDocumentBuilder();


            Document document;

            document = documentBuilder.parse(new File(getFile()));


            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("goods");

            for (int i = 0; i < list.getLength(); i++) {
                Goods goods = new Goods();
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    goods.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    goods.setCode(element.getElementsByTagName("code").item(0).getTextContent());
                    goods.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                    Element el = (Element) element.getElementsByTagName("prices").item(0);
                    goods.setIncomePrice(Double.parseDouble(el.getElementsByTagName("incomePrice").item(0).getTextContent()));
                    goods.setExportPrice(Double.parseDouble(el.getElementsByTagName("exportPrice").item(0).getTextContent()));
                    goods.setDeleted(Boolean.parseBoolean(element.getElementsByTagName("deleted").item(0).getTextContent()));

                    database.addGoods(goods);
                }
            }
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            CustomLogger.getLogger(GoodsOverviewController.class).warn(bundle.getString("XML_IMPORT_ERROR"), ex);
            return;
        }
        
        JOptionPane.showMessageDialog(window, bundle.getString("XML_IMPROT_DONE"));
        CustomLogger.getLogger(GoodsOverviewController.class).warn(bundle.getString("XML_IMPROT_DONE"));
    }
    
    /**
     * Metóda pre získanie umiestnenia xml súboru pre importovanie.
     * 
     * @return absolútna adresa súboru, inak null
     */
    private String getFile() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML", "xml"));
        
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
        
    }

}

