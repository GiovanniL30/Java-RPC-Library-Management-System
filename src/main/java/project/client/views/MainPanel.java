package project.client.views;

import project.client.controller.ClientController;
import project.client.utility.ClientPanels;
import project.client.views.components.Header;
import project.client.views.components.HomePanel;
import project.client.views.components.Menu;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

import static project.client.views.ClientMainView.FRAME_WIDTH;

public class MainPanel extends JPanel {

    private final Header header;
    private final Menu menu;
    private final JPanel contentHolder = new JPanel();
    private Loading loading = new Loading(null);
    private ClientController clientController;

    public MainPanel(ClientController clientController) {
        this.clientController = clientController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        header = new Header("Giovanni Leo");
        menu = new Menu();
        contentHolder.setPreferredSize(new Dimension(FRAME_WIDTH, 550));
        contentHolder.setBackground(Color.white);


        setContentPanel(new HomePanel(clientController.getBooks()));


        add(header);
        add(menu);
        add(contentHolder);


        menu.getHomeButton().addActionListener(e -> this.clientController.changeFrame(ClientPanels.HOME_PANEL));
        menu.getPendingBooks().addActionListener(e -> this.clientController.changeFrame(ClientPanels.PENDING_PANEL));
        menu.getBorrowedBooks().addActionListener(e -> this.clientController.changeFrame(ClientPanels.BORROWED_PANEL));
        menu.getAccount().addActionListener(e -> this.clientController.changeFrame(ClientPanels.ACCOUNT_PANEL));
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

    public Header getHeader() {
        return header;
    }

    public Menu getMenu() {
        return menu;
    }


}
