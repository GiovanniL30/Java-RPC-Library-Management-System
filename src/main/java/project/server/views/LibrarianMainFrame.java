package project.server.views;

import com.formdev.flatlaf.FlatLightLaf;
import project.server.controller.ServerController;
import project.server.views.components.ServerGuiHeader;
import project.server.views.components.viewBookPanel.ViewBooksHeader;
import project.server.views.panels.HomePanel;
import project.server.views.panels.ViewBookPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LibrarianMainFrame extends JFrame {

    public static int FRAME_WIDTH = 1000;
    public static int FRAME_HEIGHT = 800;

    private ServerGuiHeader serverGuiHeader;
    private ViewBooksHeader viewBooksHeader;
    private ServerController serverController;
    private JPanel mainPanel = new JPanel();
    private ViewBookPanel viewBookPanel;

    public LibrarianMainFrame(ServerController serverController) {
        this.serverController = serverController;
        this.serverGuiHeader = new ServerGuiHeader(this.serverController);
        this.viewBooksHeader = new ViewBooksHeader(this.serverController);
        this.viewBookPanel = new ViewBookPanel(this.serverController);

        initializeFrame();
       this.getContentPane().add(serverGuiHeader, BorderLayout.NORTH);
      this.getContentPane().add(new HomePanel(this.serverController), BorderLayout.CENTER);
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
        setContentPane(mainPanel);
        mainPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);



        setVisible(true);
    }

    public ServerGuiHeader getServerGuiHeader() {
        return this.serverGuiHeader;
    }
    public ViewBooksHeader getViewBooksHeader() {
        return this.viewBooksHeader;
    }

    public ViewBookPanel getViewBookPanel() {
        return viewBookPanel;
    }
}
