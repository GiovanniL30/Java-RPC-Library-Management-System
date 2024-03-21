package project.server.views.components.viewBookPanel;

import project.server.views.components.ClickableText;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.SearchBar;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ViewBooksHeader extends JPanel {
    private final LinkedList<ClickableText> clickableTexts = new LinkedList<>();
    private final SearchBar searchBar;
    private Button searchButton = new Button("Search", 100, 50, FontFactory.newPoppinsDefault(13));
    private final ClickableText allBooks = new ClickableText("All Books", 100, 50, FontFactory.newPoppinsBold(18));
    private final ClickableText availableBooks = new ClickableText("Available Books", 100, 50, FontFactory.newPoppinsBold(18));
    private final ClickableText unavailableBooks = new ClickableText("Unavailable Books", 100, 50, FontFactory.newPoppinsBold(18));
    public ViewBooksHeader() {
        setBackground(ColorFactory.blue());

        clickableTexts.add(allBooks);
        clickableTexts.add(availableBooks);
        clickableTexts.add(unavailableBooks);

        searchBar = new SearchBar(new Dimension(400, 100), false);

        searchButton.setBackground(ColorFactory.blue());
        searchButton.setForeground(Color.white);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 20, 0, 20);
        setLayout(new GridBagLayout());

        constraints.gridy = 0;
        constraints.gridx = 0;

        add(allBooks, constraints);

        constraints.gridx = 1;
        add(availableBooks, constraints);

        constraints.gridx = 2;
        add(unavailableBooks, constraints);

        constraints.gridx = 3;
        add(searchBar, constraints);
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
}
