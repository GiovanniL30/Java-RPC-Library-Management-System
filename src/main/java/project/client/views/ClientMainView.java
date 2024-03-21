package project.client.views;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import project.client.controller.ClientController;
import project.client.views.components.ChatView;
import project.client.views.components.Header;
import project.client.views.components.Menu;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

public class ClientMainView extends JFrame {

    public static int FRAME_WIDTH = 1000;

    private final Loading loading = new Loading(this);
    private ClientController clientController;

    private MainPanel mainPanel;


    public ClientMainView(ClientController clientController) {

        this.clientController = clientController;
        initializeFrame();

        Login login = new Login(new Dimension(FRAME_WIDTH, 900));
        login.addClickEvent(this.clientController);
        this.getContentPane().add(login);
    }


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
        }

        setSize(new Dimension(FRAME_WIDTH, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        setVisible(true);
    }

    public Header getHeader() {
        return mainPanel.getHeader();
    }

    public Menu getMenu() {
        return mainPanel.getMenu();
    }

    public void setContentPanel(JPanel panel) {
        mainPanel.setContentPanel(panel);
    }


    public void setClientController(ClientController clientController) {
        this.clientController = clientController;


    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
