package project.server.views.panels;

import project.server.controller.ServerObserver;
import project.server.views.components.ClickableText;
import project.server.views.components.SubHeader;
import project.server.views.components.viewBookPanel.ViewBooksList;
import project.server.views.utility.ServerPanels;
import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ViewBookPanel extends JPanel {

    private final ServerObserver serverObserver;
    private final SubHeader subHeader;

    private  ViewBooksList viewBooksList;


    public ViewBookPanel(ServerObserver serverObserver) {
        this.serverObserver = serverObserver;
        setLayout(new BorderLayout(0, 20));

        subHeader = new SubHeader(new ClickableText(ServerPanels.All_BOOKS_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)), new ClickableText(ServerPanels.AVAILABLE_BOOKS_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)), new ClickableText(ServerPanels.UNAVAILABLE_BOOKS_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)), serverObserver);
        viewBooksList = new ViewBooksList(serverObserver.getBooks(), this.serverObserver);
        setBackground(Color.white);


        add(subHeader, BorderLayout.NORTH);
        add(viewBooksList, BorderLayout.CENTER);
    }

    public SubHeader getSubHeader() {
        return subHeader;
    }

    public synchronized void setView(LinkedList<Book> books) {
        remove(1);
        viewBooksList = new ViewBooksList(books, this.serverObserver);
        add(viewBooksList);
        revalidate();
        repaint();
    }

}
