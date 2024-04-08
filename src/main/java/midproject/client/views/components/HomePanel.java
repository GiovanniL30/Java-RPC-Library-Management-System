package midproject.client.views.components;

import midproject.client.controller.ClientController;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Button;
import midproject.utilities.viewComponents.IconButton;
import midproject.utilities.viewComponents.SearchBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Class that shows the home panel
 */
public class HomePanel extends JPanel {

    private final LinkedList<Book> books;

    private final SearchBar searchBar;
    private final JPanel panel;
    private boolean haveSearched = false;
    private  ClientController clientController;

    /**
     * Constructor
     * @param books books to be put in the book list
     * @param clientController controller
     */
    public HomePanel(LinkedList<Book> books, ClientController clientController) {
        // sets the layout of the panel into BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.books = books;
        this.clientController = clientController;

        // instantiate the components in the panel
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

        // add the header into the panel
        add(header);

        panel = new JPanel();
        panel.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(920, 400));
        add(scrollPane);

        // populate the book list using the entered parameter
        populateBookList(books, false);

        // add action listeners for buttons
        searchButton.addActionListener(this::performSearch);
        searchBar.getGenreDropDown().addItemListener(this::performGenreFiltering);
        searchBar.getCancelButton().addActionListener(this::closeSearch);
    } // end of constructor

    /**
     * Populates the book list
     * @param books books to be put in the book list
     * @param isUpdate value to check if it is used for updates
     */
    public void populateBookList(LinkedList<Book> books, boolean isUpdate) {
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {

                if (isUpdate) {
                    panel.removeAll();
                } // end of if statement

                if(books.isEmpty()) {
                    panel.revalidate();
                    panel.repaint();
                } // end of if statement

                for (Book book : books) {
                    panel.add(new BookCardComponent(book, clientController));
                    panel.revalidate();
                    panel.repaint();
                } // end of for each

                return null;
            } // end of doInBackground


        }.execute();
    } // end of populateBookList

    /**
     * Closes the search function
     * @param event
     */
    private void closeSearch(ActionEvent event){

        if(haveSearched) {
            populateBookList(books, true);
            searchBar.getInput().getTextField().setText("");
            haveSearched = false;
        } // end of if statement

    } // end of closeSearch

    /**
     * Searches the books using the input
     * @param event
     */
    private void performSearch(ActionEvent event) {

        String searchInput = searchBar.getSearch();

        // handle errors
        if(searchInput == null){
            searchBar.getInput().enableError("Enter something");
            return;
        } // end of if statement

        if(searchInput.trim().isEmpty()) {
            searchBar.getInput().enableError("Enter something");
            return;
        } // end of if statement

        String filterType = searchBar.getFilterType();

        LinkedList<Book> filteredBooks;

        // search the books with the author filter
        if(filterType.equals("Author")){
            filteredBooks = books.stream().filter(book -> book.getAuthor().toLowerCase().contains(searchInput.toLowerCase())).collect(Collectors.toCollection(LinkedList::new));
        }else {
            filteredBooks = books.stream().filter(book -> book.getBookTitle().toLowerCase().contains(searchInput.toLowerCase())).collect(Collectors.toCollection(LinkedList::new));
        } // end of if else


        haveSearched = true;
        populateBookList(filteredBooks, true);
    } // end of performSearch

    /**
     * Filter books according to their genres
     * @param e
     */
    public void performGenreFiltering(ItemEvent e) {
        LinkedList<Book> filtered = books.stream().filter(book -> book.getGenre().toLowerCase().contains(searchBar.getGenreDropDown().dropDownChoice().toLowerCase())).collect(Collectors.toCollection(LinkedList::new));
        haveSearched = true;
        populateBookList(filtered, true);
    } // end of performGenreFiltering

    /**
     * Class for book card
     */
    private static class BookCardComponent extends JPanel {

        /**
         * Constructor
         * @param book
         * @param clientController
         */
        private BookCardComponent(Book book, ClientController clientController) {

            setSize(new Dimension(100, 200));

            // set the components' properties
            IconButton button = new IconButton(book.getImagePath(), 250, 300);
            JLabel label = new JLabel(book.getBookTitle());
            label.setFont(FontFactory.newPoppinsBold(16));

            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridy = 0;
            constraints.gridx = 0;

            // add the button using the given constraints
            add(button, constraints);
            constraints.gridy = 1;

            // add the label using the given constraints
            add(label, constraints);

            button.addActionListener(e -> clientController.openBook(book));
        } // end of constructor

    } // end of BookCardComponent

} // end of class
