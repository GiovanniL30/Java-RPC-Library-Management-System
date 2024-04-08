package midproject.server.views;

import com.formdev.flatlaf.FlatLightLaf;
import midproject.server.controller.ServerController;
import midproject.server.views.components.ServerGuiHeader;
import midproject.server.views.panels.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The main frame of the librarian server application.
 */

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
    private AddBooksPanel addBooksPanel;

    /**
     * Constructs the main frame of the librarian server application.
     * @param serverController The server controller instance.
     */
    public LibrarianMainFrame(ServerController serverController) {
        this.serverController = serverController;
        this.serverGuiHeader = new ServerGuiHeader(this.serverController);

        //Set up window closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serverController.setServerMethods(null);
            }

        });

        // Initialize Frame
        initializeFrame();
        this.getContentPane().add(serverGuiHeader, BorderLayout.NORTH);
        homePanel = new HomePanel(this.serverController);
        setCurrentPanel(homePanel);
    }

    /**
     * Initializes the main frame of the librarian server application.
     */
    private void initializeFrame() {
        try {
            // Set FlatLaf look and feel
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Customize UI components
            UIManager.put("Button.arc", 10);
            UIManager.put("TextComponent.arc", 5);
            UIManager.put("ScrollBar.width", 10);
            UIManager.put("ScrollBar.thumbArc", 3);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        // Set frame properties
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        mainPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    } // end initializeFrame method

    /**
     * ----- Getters -----
     */
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
    public AddBooksPanel getAddBooksPanel(){return addBooksPanel; }

    /**
     * Sets the current panel to be displayed.
     * @param panel The panel to set as the current panel.
     */
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
        } else if (panel instanceof AddBooksPanel) {
            addBooksPanel = (AddBooksPanel) panel;
            serverGuiHeader.setCurrentClickableText(serverGuiHeader.getAddBooks());
        }
    } // end setCurrentPanel method
} // end LibrarianMainFrame class
