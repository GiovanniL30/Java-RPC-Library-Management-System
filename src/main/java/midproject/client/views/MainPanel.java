package midproject.client.views;

import midproject.client.controller.ClientController;
import midproject.client.utility.ClientPanels;
import midproject.client.views.components.Header;
import midproject.client.views.components.HomePanel;
import midproject.client.views.components.Menu;
import midproject.utilities.referenceClasses.Account;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

import static midproject.client.views.ClientMainView.FRAME_WIDTH;

/**
 * Class that represents the main panel
 */
public class MainPanel extends JPanel {

    private final Header header;
    private final Menu menu;
    private final JPanel contentHolder = new JPanel();
    private Loading loading = new Loading(null);
    private ClientController clientController;

    /**
     * Constructor
     * @param clientController controller
     */
    public MainPanel(ClientController clientController) {
        this.clientController = clientController;

        // sets the layout of the panel to BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // set and add the components of the panel
        Account account = clientController.getLoggedInAccount().getAccount();
        header = new Header(account.getFirstName() + " " + account.getLastName());
        menu = new Menu();
        contentHolder.setPreferredSize(new Dimension(FRAME_WIDTH, 550));
        contentHolder.setBackground(Color.white);


        HomePanel homePanel = new HomePanel(clientController.getBooks(), clientController);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(homePanel, BorderLayout.CENTER);
        panel.setBackground(Color.white);
        setContentPanel(panel);


        // add the components of the panel
        add(header);
        add(menu);
        add(contentHolder);


        menu.getHomeButton().addActionListener(e -> this.clientController.changeFrame(ClientPanels.HOME_PANEL));
        menu.getPendingBooks().addActionListener(e -> this.clientController.changeFrame(ClientPanels.PENDING_PANEL));
        menu.getBorrowedBooks().addActionListener(e -> this.clientController.changeFrame(ClientPanels.BORROWED_PANEL));
        menu.getAccount().addActionListener(e -> this.clientController.changeFrame(ClientPanels.ACCOUNT_PANEL));
        header.addLogoutAction(this.clientController);
        menu.setCurrentButton(menu.getHomeButton());
    } // end of constructor

    /**
     * Sets the content panel
     * @param panel
     */
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
            } // end of donde
        }.execute();

        loading.setVisible(true);


    } // end of setContentPanel

    /**
     * Gets the header
     * @return
     */
    public Header getHeader() {
        return header;
    } // end of getHeader

    /**
     * Gets the menu
     * @return
     */
    public Menu getMenu() {
        return menu;
    } // end of getMenu


} // end of class

