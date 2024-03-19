package project.server.views.components.viewBookPanel;

import project.server.views.components.BookCard;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;

public class AllBookCard extends JPanel implements BookCard {

    private Picture bookPicture;
    private JLabel bookTitle;
    private JLabel bookCopies;
    private Button deleteBookButton;
    private Button editBookButton;

}
