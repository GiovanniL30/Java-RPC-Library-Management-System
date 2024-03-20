package project.server.views;

import com.formdev.flatlaf.FlatLightLaf;
import project.server.GlobalRemoteServant;
import project.server.controller.ServerController;
import project.server.views.components.ServerGuiHeader;

import javax.swing.*;
import java.awt.*;

public class LibrarianMainFrame extends JFrame {

    public static int WIDTH = 1000;
    public static int HEIGHT = 800;

    private ServerGuiHeader serverGuiHeader = new ServerGuiHeader();
    private ServerController serverController;

    public LibrarianMainFrame(ServerController serverController) {
        this.serverController = serverController;
        initializeFrame();
       this.getContentPane().add(serverGuiHeader);
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

        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);



        setVisible(true);
    }

}
