package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.viewBookPanel.AllBooksPanel;
import project.server.views.components.viewBookPanel.ViewBooksHeader;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

public class ViewBookPanel extends JPanel {

    private ServerController serverController;
    private ViewBooksHeader header;
    private Loading loading = new Loading(null);
    private final JPanel contentHolder = new JPanel();

    public ViewBookPanel(ServerController serverController) {
        this.serverController = serverController;
        this.header = new ViewBooksHeader(this.serverController);
        AllBooksPanel allBooksPanel = new AllBooksPanel(this.serverController);

        setBackground(Color.white);
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        header.setCurrentClickableText(header.getAllBooks());
        contentHolder.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.add(allBooksPanel);
        panel.setBackground(Color.white);
        setContentPanel(panel);

        add(header);
        add(contentHolder);

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

}
