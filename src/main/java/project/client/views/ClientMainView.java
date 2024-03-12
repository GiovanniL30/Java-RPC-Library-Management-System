package project.client.views;

import com.formdev.flatlaf.FlatLightLaf;
import project.client.controller.ClientController;
import project.client.utility.ClientPanels;
import project.client.views.components.Header;
import project.client.views.components.HomePanel;
import project.client.views.components.Menu;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

public class ClientMainView extends JFrame {

    public static int FRAME_WIDTH = 1000;
    private Header header;
    private Menu menu;
    private ClientController clientController;
    private JPanel contentHolder = new JPanel();
    private Loading loading = new Loading(this);



    public ClientMainView() {
        initializeFrame();

        header = new Header("Giovanni Leo");
        menu = new Menu();
        contentHolder.setPreferredSize(new Dimension(FRAME_WIDTH, 550));
        contentHolder.add(new HomePanel());

        add(header);
        add(menu);
        add(contentHolder);



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

        setSize(new Dimension(FRAME_WIDTH, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        setVisible(true);
    }

    public Header getHeader() {
        return header;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setContentPanel(JPanel panel) {
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                contentHolder.removeAll();
                contentHolder.add(panel);
                contentHolder.revalidate();
                contentHolder.repaint();
                return null;
            }

            @Override
            protected void done() {
                loading.setVisible(false);
            }
        }.execute();

        loading.setVisible(true);
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;

        menu.getHomeButton().addActionListener(e -> this.clientController.changeFrame(ClientPanels.HOME_PANEL));
        menu.getPendingBooks().addActionListener(e -> this.clientController.changeFrame(ClientPanels.PENDING_PANEL));
        menu.getBorrowedBooks().addActionListener(e -> this.clientController.changeFrame(ClientPanels.BORROWED_PANEL));
        menu.getAccount().addActionListener(e -> this.clientController.changeFrame(ClientPanels.ACCOUNT_PANEL));
    }
}
