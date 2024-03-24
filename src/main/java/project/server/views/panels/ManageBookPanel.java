package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.server.views.components.ClickableText;
import project.server.views.components.ServerSearchBar;
import project.server.views.components.SubHeader;
import project.server.views.components.manageBookPanel.*;
import project.server.views.utility.ServerPanels;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ManageBookPanel extends JPanel {
    private final LinkedList<Book> books;
    private final LinkedList<Student> students;
    private final ServerObserver serverObserver;
    private SubHeader subHeader;
    private final ServerController serverController;

    public ManageBookPanel(LinkedList<Book> books, LinkedList<Student> students, ServerObserver serverObserver, ServerController serverController) {
        this.books = books;
        this.students = students;
        this.serverObserver = serverObserver;
        this.serverController = serverController;

        subHeader = new SubHeader(new ClickableText(ServerPanels.PENDING_BORROW_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)),
                new ClickableText(ServerPanels.PENDING_RETURN_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)),
                new ClickableText(ServerPanels.BORROWED_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)),
                serverObserver);

    }




}