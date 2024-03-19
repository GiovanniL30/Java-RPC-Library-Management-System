package project.server.views.components.viewBookPanel;

import project.client.controller.ClientController;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

/**
 * Use to store all the list of Book cards
 * */
public class BookViewer extends JPanel {
//
//    public BookViewer(Frame owner, Book book, Student student, ClientController controller) {
//        super(owner, book.getBookTitle(), true);
//
//        setPreferredSize(new Dimension(400, 600));
//        setResizable(false);
//        JPanel panel = new JPanel(new GridBagLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        Picture picture = new Picture(book.getImagePath(), 150, 200);
//
//        JLabel title = new JLabel(book.getBookTitle());
//        JLabel author = new JLabel("by: " + book.getAuthor());
//        JLabel genre = new JLabel("Genre: " + book.getGenre());
//        JLabel copies = new JLabel("Copies Left: " + book.getCopies());
//
//        JTextArea textArea = new JTextArea(book.getShortDescription());
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
//        textArea.setEditable(false);
//        JScrollPane textScrollPane = new JScrollPane(textArea);
//        textScrollPane.setPreferredSize(new Dimension(200, 120));
//
//        JPanel buttonPanel = getjPanel(book, student, controller);
//
//        constraints.gridx = 2;
//        constraints.gridy = 0;
//        constraints.insets = new Insets(5, 5, 5, 70);
//        panel.add(picture, constraints);
//
//        constraints.insets = new Insets(5, 5, 5, 30);
//        constraints.gridx = 2;
//        constraints.gridy = 1;
//        constraints.anchor = GridBagConstraints.WEST;
//        panel.add(title, constraints);
//
//
//        constraints.gridy = 2;
//        panel.add(author, constraints);
//
//        constraints.insets = new Insets(5, 5, 5, 5);
//        constraints.gridy = 3;
//        constraints.gridx = 0;
//        panel.add(genre, constraints);
//
//        constraints.gridx = 2;
//        constraints.gridy = 3;
//        panel.add(copies, constraints);
//
//        constraints.gridx = 0;
//        constraints.gridy = 4;
//        constraints.gridwidth = 3;
//        constraints.fill = GridBagConstraints.HORIZONTAL;
//        panel.add(textScrollPane, constraints);
//
//        constraints.gridy = 5;
//        constraints.weightx = 3.0;
//        panel.add(buttonPanel, constraints);
//
//        add(panel);
//        pack();
//
//        setLocationRelativeTo(null);
//    }
//
//    private static JPanel getjPanel(Book book, Student student, ClientController clientController) {
//        JPanel buttonPanel = new JPanel();
//        JLabel errorMessage = new JLabel();
//
//        project.utilities.viewComponents.Button addBookButton =  new Button("Add Book", 200, 80, FontFactory.newPoppinsDefault(14));
//        addBookButton.setForeground(Color.WHITE);
//        addBookButton.setBackground(ColorFactory.green());
//
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//
//        if(book.getCopies() == 0) {
//            errorMessage.setText("There are no copies of the book already");
//            addBookButton.setEnabled(false);
//        }
//
//        for(String studentId: book.getPendingBorrowers()) {
//
//            if(studentId.equals(student.getAccount().getAccountId())){
//                errorMessage.setText("This book is already added on your pending");
//                addBookButton.setEnabled(false);
//                break;
//            }
//
//        }
//
//        for(String studentId: book.getCurrentBorrowers()) {
//
//            if(studentId.equals(student.getAccount().getAccountId())){
//                errorMessage.setText("You are already borrowing this book");
//                addBookButton.setEnabled(false);
//                break;
//            }
//
//        }
//
//        for(String studentId: book.getPendingBookReturners()) {
//
//            if(studentId.equals(student.getAccount().getAccountId())){
//                errorMessage.setText("This book is pending for return");
//                addBookButton.setEnabled(false);
//                break;
//            }
//
//        }
//
//
//        if(!errorMessage.getText().isEmpty()){
//            buttonPanel.add(errorMessage);
//        }
//
//        buttonPanel.add(addBookButton);
//        addBookButton.addActionListener(e -> clientController.borrowBook(book));
//        return buttonPanel;
//    }

}
