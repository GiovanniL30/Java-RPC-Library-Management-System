package project.server.views;

import com.formdev.flatlaf.FlatLightLaf;
import project.server.controller.ServerController;
import project.server.views.components.ServerGuiHeader;
import project.server.views.panels.HomePanel;
import project.server.views.panels.ManageAccountsPanel;
import project.server.views.panels.ManageBookPanel;
import project.server.views.panels.ViewBookPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LibrarianMainFrame extends JFrame {

    public static int FRAME_WIDTH = 1000;
    public static int FRAME_HEIGHT = 800;

    private final ServerGuiHeader serverGuiHeader;

    private final ServerController serverController;
    private final JPanel mainPanel = new JPanel();
    private ViewBookPanel viewBookPanel;
    private ManageAccountsPanel manageAccountsPanel;
    private ManageBookPanel manageBookPanel;
    private HomePanel homePanel;

    public LibrarianMainFrame(ServerController serverController) {
        this.serverController = serverController;
        this.serverGuiHeader = new ServerGuiHeader(this.serverController);



        initializeFrame();
        this.getContentPane().add(serverGuiHeader, BorderLayout.NORTH);
        homePanel = new HomePanel(this.serverController);
        setCurrentPanel(homePanel);
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

    public ViewBookPanel getViewBookPanel() {
        return viewBookPanel;
    }

    public ManageAccountsPanel getManageAccountsPanel() {
        return manageAccountsPanel;
    }

    public ManageBookPanel getManageBookPanel() {
        return manageBookPanel;
    }

    public HomePanel getHomePanel() {
        return homePanel;
    }

    public void setCurrentPanel(JPanel panel) {

        this.getContentPane().add(panel, BorderLayout.CENTER);

        if (panel instanceof HomePanel) {
            homePanel = (HomePanel) panel;
            serverGuiHeader.setCurrentClickableText(serverGuiHeader.getHome());
        } else if (panel instanceof ManageBookPanel) {
            manageBookPanel = (ManageBookPanel) panel;
            serverGuiHeader.setCurrentClickableText(serverGuiHeader.getManageBooks());
        } else if (panel instanceof ManageAccountsPanel) {
            manageAccountsPanel = (ManageAccountsPanel) panel;
            serverGuiHeader.setCurrentClickableText(serverGuiHeader.getAccounts());
        } else if (panel instanceof ViewBookPanel) {
            viewBookPanel = (ViewBookPanel) panel;
            serverGuiHeader.setCurrentClickableText(serverGuiHeader.getViewBooks());
        }
    }
}