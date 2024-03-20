package project.server.views.panels;

import project.client.controller.ClientController;
import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.server.views.components.manageBookPanel.ManageBooksHeader;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.IconButton;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ManageBookPanel extends JPanel {
    private final Book books;
    private final Student students;
    private final ServerObserver serverObserver;
    private final JPanel panel;
    private final ServerController serverController;

    public ManageBookPanel(Book books, Student students, ServerObserver serverObserver, ServerController serverController){
        this.books = books;
        this.students = students;
        this.serverObserver = serverObserver;
        this.serverController = serverController;

        ManageBooksHeader manageBooksHeader = new ManageBooksHeader();
        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setVgap(50);
        gridLayout.setHgap(10);

        Button searchButton = new Button("Search", 100, 50, FontFactory.newPoppinsDefault(13));

        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Color.WHITE);

        header.add(manageBooksHeader);
        header.add(searchButton);

        searchButton.setBackground(ColorFactory.blue());
        searchButton.setForeground(Color.WHITE);

        add(header);

        panel = new JPanel();
        panel.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(920, 400));
        add(scrollPane);

        populateBookList(books, false);

    }

    public void populateBookList(LinkedList<Book> books, boolean isUpdate){
        new SwingWorker<>(){
            @Override
            protected Object doInBackground(){

                if (isUpdate) {
                    panel.removeAll();
                }

                if (books.isEmpty()) {
                    panel.revalidate();
                    panel.repaint();
                }

                for (Book book : books) {
                    panel.add(new BookCardComponent(book, serverController));
                    panel.revalidate();
                    panel.repaint();
                }

                return null;
            }


        }.execute();
    }

    private static class BookCardComponent extends JPanel {

        private BookCardComponent(Book book, ClientController clientController) {

            setSize(new Dimension(100, 200));

            IconButton button = new IconButton(book.getImagePath(), 250, 300);
            JLabel label = new JLabel(book.getBookTitle());
            label.setFont(FontFactory.newPoppinsBold(16));

            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridy = 0;
            constraints.gridx = 0;
            add(button, constraints);
            constraints.gridy = 1;
            add(label, constraints);

            button.addActionListener(e -> clientController.openBook(book));
        }

    }

}
