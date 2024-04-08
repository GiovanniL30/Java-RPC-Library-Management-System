package midproject.client.views;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import midproject.client.controller.ClientController;
import midproject.client.views.components.Header;
import midproject.client.views.components.Menu;
import midproject.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

 /**
  *  Class that represents the main view of the client using a JFrame
 */
public class ClientMainView extends JFrame {

    public static int FRAME_WIDTH = 1000;

    private final Loading loading = new Loading(this);
    private ClientController clientController;

    private MainPanel mainPanel;
    private JPanel currentPanel;

    /**
     * Constructor
     * @param clientController controller
     */
    public ClientMainView(ClientController clientController) {

        this.clientController = clientController;
        initializeFrame();

        Login login = new Login(new Dimension(FRAME_WIDTH, 900));
        login.addClickEvent(this.clientController);
        this.getContentPane().add(login);
    } // end of constructor

    /**
     * Initializes the properties of the frame
     */
    private void initializeFrame() {

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("Button.arc", 10);
            UIManager.put("TextComponent.arc", 5);
            UIManager.put("ScrollBar.width", 10);
            UIManager.put("ScrollBar.thumbArc", 3);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        } // end of try catch

        setSize(new Dimension(FRAME_WIDTH, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        setVisible(true);
    } // end of initializeFrame

    /**
     * Gets the header
     * @return
     */
    public Header getHeader() {
        return mainPanel.getHeader();
    } // end of getHeader

    /**
     * Gets the menu
     * @return
     */
    public Menu getMenu() {
        return mainPanel.getMenu();
    } // end of getMenu

    /**
     * Sets the content panel
     * @param panel
     */
    public void setContentPanel(JPanel panel) {
        this.currentPanel = panel;
        mainPanel.setContentPanel(panel);
    } // end of setContentPanel


    /**
     * Sets the client controller
     * @param clientController
     */
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    } // end of setClientController

    /**
     * Sets the main panel
     * @param mainPanel
     */
    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    } // end of setMainPanel

    /**
     * Gets the current panel
     * @return
     */
    public JPanel getCurrentPanel() {
        return currentPanel;
    } // end of getCurrentPanel
} // end of class

