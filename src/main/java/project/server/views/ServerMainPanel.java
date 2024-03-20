package project.server.views;

import project.server.views.panels.HomePanel;
import project.server.views.utility.ServerPanels;
import project.server.controller.ServerController;
import project.server.views.components.ServerGuiHeader;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

import static project.server.views.LibrarianMainFrame.FRAME_WIDTH;

public class ServerMainPanel extends JPanel {
    private final ServerGuiHeader serverGuiHeader;
    private final JPanel serverContentHolder = new JPanel();
    private Loading loading = new Loading(null);
    private HomePanel homePanel;
    private final ServerController serverController;

    public ServerMainPanel(ServerController serverController) {
        this.serverController = serverController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        serverContentHolder.setPreferredSize(new Dimension(FRAME_WIDTH, 550));
        serverContentHolder.setBackground(Color.white);
        serverGuiHeader = new ServerGuiHeader();
        add(serverGuiHeader);
        add(serverContentHolder);
        homePanel = new HomePanel(serverController);
        setContentPanel(homePanel);

        serverGuiHeader.getHome().addActionListener(e -> this.serverController.changeFrame(ServerPanels.HOME_PANEL));
        serverGuiHeader.getManageBooks().addActionListener(e -> this.serverController.changeFrame(ServerPanels.MANAGE_PANEL));
        serverGuiHeader.getAccounts().addActionListener(e -> this.serverController.changeFrame(ServerPanels.ACCOUNTS_PANEL));
        serverGuiHeader.getViewBooks().addActionListener(e -> this.serverController.changeFrame(ServerPanels.VIEW_PANEL));

    }

    public void setContentPanel(JPanel panel) {

        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                serverContentHolder.removeAll();
                serverContentHolder.add(panel);
                serverContentHolder.revalidate();
                serverContentHolder.repaint();
                return null;
            }

            @Override
            protected void done() {
                loading.setVisible(false);
            }
        }.execute();

        loading.setVisible(true);
    }

    public ServerGuiHeader getServerGuiHeader() {
        return serverGuiHeader;
    }
}
