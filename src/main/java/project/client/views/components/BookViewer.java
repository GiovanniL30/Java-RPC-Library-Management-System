package project.client.views.components;

import project.client.controller.ClientController;
import project.utilities.referenceClasses.Book;
import  project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

public class BookViewer extends JDialog {

    public BookViewer(Frame owner, Book book, ClientController controller) {
        super(owner, book.getBookTitle(), true);

        setPreferredSize(new Dimension(400, 600));
        setResizable(false);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Picture picture = new Picture(book.getImagePath(), 150, 200);

        JLabel title = new JLabel(book.getBookTitle());
        JLabel author = new JLabel("by: " + book.getAuthor());
        JLabel genre = new JLabel("Genre: " + book.getGenre());
        JLabel copies = new JLabel("Copies Left: " + book.getCopies());

        JTextArea textArea = new JTextArea(book.getShortDescription());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setPreferredSize(new Dimension(200, 120));

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 70);
        panel.add(picture, constraints);

        constraints.insets = new Insets(5, 5, 5, 30);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(title, constraints);


        constraints.gridy = 2;
        panel.add(author, constraints);

        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(genre, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        panel.add(copies, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textScrollPane, constraints);

        add(panel);
        pack(); // Adjust the size of the dialog to fit its contents

        // Center the dialog on the screen
        setLocationRelativeTo(null);
    }
}