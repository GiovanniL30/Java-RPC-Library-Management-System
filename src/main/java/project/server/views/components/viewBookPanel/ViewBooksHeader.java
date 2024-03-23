package project.server.views.components.viewBookPanel;

import project.server.controller.ServerController;
import project.server.views.components.ClickableText;
import project.server.views.components.ServerSearchBar;
import project.server.views.utility.ServerPanels;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ViewBooksHeader extends JPanel {
    private final LinkedList<ClickableText> clickableTexts = new LinkedList<>();
    private final ServerSearchBar searchBar = new ServerSearchBar(new Dimension(350, 50));
    private final Button searchButton = new Button("Search", 70, 50, FontFactory.newPoppinsDefault(13));
    private final ClickableText allBooks = new ClickableText("All Books", 40, 70, FontFactory.newPoppinsBold(17));
    private final ClickableText availableBooks = new ClickableText("Available Books", 40, 70, FontFactory.newPoppinsBold(17));
    private final ClickableText unavailableBooks = new ClickableText("Unavailable Books", 40, 70, FontFactory.newPoppinsBold(17));
    JPanel searchBarPanel = new JPanel();
    public ViewBooksHeader(ServerController serverController) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1000, 65));
        setBackground(Color.white);

        clickableTexts.add(allBooks);
        clickableTexts.add(availableBooks);
        clickableTexts.add(unavailableBooks);

        searchBar.getCancelButton().setBackground(ColorFactory.red());
        searchBar.getCancelButton().setForeground(Color.white);

        searchButton.setBackground(ColorFactory.blue());
        searchButton.setForeground(Color.WHITE);

        JPanel allBooksPanel = new JPanel();
        JPanel availableBooksPanel = new JPanel();
        JPanel unavailableBooksPanel = new JPanel();

        allBooksPanel.add(allBooks);
        allBooksPanel.setBackground(Color.white);

        availableBooksPanel.add(availableBooks);
        availableBooksPanel.setBackground(Color.white);

        unavailableBooksPanel.add(unavailableBooks);
        unavailableBooksPanel.setBackground(Color.white);

        searchBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchBarPanel.add(searchBar);
        searchBarPanel.add(searchButton);
        searchBarPanel.setBackground(Color.white);

        add(allBooksPanel);
        add(availableBooksPanel);
        add(unavailableBooksPanel);
        add(searchBarPanel);

        allBooks.addActionListener( e -> serverController.changeFrame(ServerPanels.All_BOOKS_PANEL));
        availableBooks.addActionListener( e -> serverController.changeFrame(ServerPanels.AVAILABLE_BOOKS_PANEL));
        unavailableBooks.addActionListener(e -> serverController.changeFrame(ServerPanels.UNAVAIILABLE_BOOKS_PANEL));
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
