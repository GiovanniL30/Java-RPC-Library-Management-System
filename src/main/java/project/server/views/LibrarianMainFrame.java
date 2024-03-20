package project.server.views;

import com.formdev.flatlaf.FlatLightLaf;
import project.server.GlobalRemoteServant;
import project.server.controller.ServerController;
import project.server.views.components.ServerGuiHeader;
import project.server.views.panels.HomePanel;

import javax.swing.*;
import java.awt.*;

public class LibrarianMainFrame extends JFrame {

    public static int FRAME_WIDTH = 1000;
    public static int FRAME_HEIGHT = 800;

    private ServerGuiHeader serverGuiHeader = new ServerGuiHeader();
    private ServerController serverController;
    private ServerMainPanel serverMainPanel;

    public LibrarianMainFrame(ServerController serverController) {
        this.serverController = serverController;
        initializeFrame();
       this.getContentPane().add(serverGuiHeader);
       this.getContentPane().add(serverMainPanel = new ServerMainPanel(serverController));
    }


    private void initializeFrame() {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 10);
            UIManager.put("TextComponent.arc", 5);
            UIManager.put("ScrollBar.width", 10);
            UIManager.put("ScrollBar.thumbArc", 3);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);



        setVisible(true);
    }

    public ServerGuiHeader getServerGuiHeader() {
        return serverMainPanel.getServerGuiHeader();
    }

    public void setServerContentPanel(JPanel panel) {
        serverMainPanel.setServerContentPanel(panel);
    }

}
