package project.server.views.components.viewBookPanel;

import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.server.views.components.ServerSearchBar;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.SearchBar;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ViewBooksHeader extends JPanel {
    private final LinkedList<ClickableText> clickableTexts = new LinkedList<>();
    private final ServerSearchBar searchBar;
    private final Button searchButton = new Button("Search", 50, 50, FontFactory.newPoppinsDefault(13));
    private final ClickableText allBooks = new ClickableText("All Books", 50, 50, FontFactory.newPoppinsBold(15));
    private final ClickableText availableBooks = new ClickableText("Available Books", 50, 50, FontFactory.newPoppinsBold(15));
    private final ClickableText unavailableBooks = new ClickableText("Unavailable Books", 50, 50, FontFactory.newPoppinsBold(15));
    private final JPanel textHeaderPanel = new JPanel();
    private final JPanel searchHeaderPanel = new JPanel();
    public ViewBooksHeader() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1000, 100));

        setBackground(Color.white);

        clickableTexts.add(allBooks);
        clickableTexts.add(availableBooks);
        clickableTexts.add(unavailableBooks);

        searchBar = new ServerSearchBar(new Dimension(500, 200), false);

        searchButton.setBackground(ColorFactory.blue());
        searchButton.setForeground(Color.white);


        JPanel searchBarPanel = new JPanel();
        JPanel allBooksPanel = new JPanel();
        JPanel availableBooksPanel = new JPanel();
        JPanel unavailableBooksPanel = new JPanel();

        textHeaderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        allBooksPanel.add(allBooks);
        availableBooksPanel.add(availableBooks);
        unavailableBooksPanel.add(unavailableBooks);

        textHeaderPanel.add(allBooksPanel);
        textHeaderPanel.add(availableBooksPanel);
        textHeaderPanel.add(unavailableBooksPanel);

        searchBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchBarPanel.add(searchBar);
        searchBarPanel.add(searchButton);

        searchHeaderPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchHeaderPanel.add(searchBarPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 20, 0, 20);
        setLayout(new GridBagLayout());

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.fill = 2;

        add(textHeaderPanel, constraints);

        constraints.gridx = 1;
        add(searchHeaderPanel, constraints);
    }

    public void setCurrentClickableText(ClickableText currentText) {

        currentText.setEnabled(false);


        for(ClickableText clickableText: clickableTexts) {

            if(!clickableText.equals(currentText)){
                clickableText.setEnabled(true);
            }

        }
    }

    public ClickableText getAllBooks() {
        return allBooks;
    }

    public ClickableText getAvailableBooks() {
        return availableBooks;
    }

    public ClickableText getUnavailableBooks() {
        return unavailableBooks;
    }
    public JPanel getTextHeaderPanel() { return textHeaderPanel; }
    public JPanel getSearchHeaderPanel(){return searchHeaderPanel; }
}
