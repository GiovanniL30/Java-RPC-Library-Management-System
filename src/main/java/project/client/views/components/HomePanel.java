package project.client.views.components;

import project.client.controller.ClientController;
import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.IconButton;
import project.utilities.viewComponents.SearchBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class HomePanel extends JPanel {

    private final LinkedList<Book> books;

    private final SearchBar searchBar;
    private final JPanel panel;
    private boolean haveSearched = false;
    private  ClientController clientController;

    public HomePanel(LinkedList<Book> books, ClientController clientController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.books = books;
        this.clientController = clientController;

        searchBar = new SearchBar(new Dimension(800, 100), false);

        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setVgap(50);
        gridLayout.setHgap(10);

        Button searchButton = new Button("Search", 100, 50, FontFactory.newPoppinsDefault(13));

        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Color.WHITE);

        header.add(searchBar);

        header.add(searchButton);
        searchButton.setBackground(ColorFactory.blue());
        searchButton.setForeground(Color.white);

        add(header);

        panel = new JPanel();
        panel.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(920, 400));
        add(scrollPane);

        populateBookList(books, false);


        searchButton.addActionListener(this::performSearch);
        searchBar.getGenreDropDown().addItemListener(this::performGenreFiltering);
        searchBar.getCancelButton().addActionListener(this::closeSearch);
    }

    public void populateBookList(LinkedList<Book> books, boolean isUpdate) {
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {

                if (isUpdate) {
                    panel.removeAll();
                }

                if(books.isEmpty()) {
                    panel.revalidate();
                    panel.repaint();
                }

                for (Book book : books) {
                    panel.add(new BookCardComponent(book, clientController));
                    panel.revalidate();
                    panel.repaint();
                }

                return null;
            }


        }.execute();
    }

    private void closeSearch(ActionEvent event){

        if(haveSearched) {
            populateBookList(books, true);
            searchBar.getInput().getTextField().setText("");
            haveSearched = false;
        }

    }

    private void performSearch(ActionEvent event) {

        String searchInput = searchBar.getSearch();

        if(searchInput == null){
            searchBar.getInput().enableError("Enter something");
            return;
        }

        if(searchInput.trim().isEmpty()) {
            searchBar.getInput().enableError("Enter something");
            return;
        }

        String filterType = searchBar.getFilterType();

        LinkedList<Book> filteredBooks;

        if(filterType.equals("Author")){
            filteredBooks = books.stream().filter(book -> book.getAuthor().toLowerCase().contains(searchInput.toLowerCase())).collect(Collectors.toCollection(LinkedList::new));
        }else {
            filteredBooks = books.stream().filter(book -> book.getBookTitle().toLowerCase().contains(searchInput.toLowerCase())).collect(Collectors.toCollection(LinkedList::new));
        }


        haveSearched = true;
        populateBookList(filteredBooks, true);
    }

    public void performGenreFiltering(ItemEvent e) {
        LinkedList<Book> filtered = books.stream().filter(book -> book.getGenre().toLowerCase().contains(searchBar.getGenreDropDown().dropDownChoice().toLowerCase())).collect(Collectors.toCollection(LinkedList::new));
        haveSearched = true;
        populateBookList(filtered, true);
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
