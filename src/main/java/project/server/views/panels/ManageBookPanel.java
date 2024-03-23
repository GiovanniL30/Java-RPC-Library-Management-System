package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.server.views.components.ServerSearchBar;
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
    private final JPanel panel;
    private final ServerController serverController;

    private final ManageBooksHeader manageBooksHeader;

    public ManageBookPanel(LinkedList<Book> books, LinkedList<Student> students, ServerObserver serverObserver, ServerController serverController) {
        this.books = books;
        this.students = students;
        this.serverObserver = serverObserver;
        this.serverController = serverController;

        // Initialize ManageBooksHeader
        manageBooksHeader = new ManageBooksHeader(serverController, this); // Pass reference to this ManageBookPanel

        // Initialize ManageBooksSearch and Search Button
        ServerSearchBar search = new ServerSearchBar(new Dimension(350, 50));
        Button searchButton = new Button("Search", 80, 50, FontFactory.newPoppinsDefault(13));
        searchButton.setBackground(ColorFactory.blue());
        searchButton.setForeground(Color.WHITE);

        // Header Panel
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Color.WHITE);
        header.add(manageBooksHeader);
        header.add(search);
        header.add(searchButton);

        // Main Panel with ScrollPane
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // Set GridLayout here
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(920, 400));

        // Set layout for ManageBookPanel to BorderLayout
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Add panel to center

        // By default, show BorrowedPanel
        switchToPanel(ServerPanels.BORROWED_PANEL);
    }

    // Method to switch between panels based on ServerPanels enum
    public void switchToPanel(ServerPanels panel) {
        switch (panel) {
            case BORROWED_PANEL:
                replacePanel(new BorrowedPanel(serverObserver));
                break;
            case PENDING_BORROW_PANEL:
                replacePanel(new PendingBorrowPanel(serverObserver));
                break;
            case PENDING_RETURN_PANEL:
                replacePanel(new PendingReturnPanel());
                break;
        }
    }

    // Method to replace the current panel with a new panel
    private void replacePanel(JPanel newPanel) {
        panel.removeAll();
        panel.add(newPanel);
        revalidate();
        repaint();
    }
}