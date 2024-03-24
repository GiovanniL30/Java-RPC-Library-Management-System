package project.server.views.components.manageBookPanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BorrowedPanel extends JPanel {
    private JPanel borrowedBooks = new JPanel();
    private LinkedList<Book> books = new LinkedList<>();
    private LinkedList<Student> students = new LinkedList<>();
    private ServerObserver serverObserver;
    public BorrowedPanel(ServerObserver serverObserver) {
        JPanel borrowed = new JPanel();
        JLabel borrowedLabel = new JLabel("BORROWED BOOKS");
        borrowedLabel.setFont(FontFactory.newPoppinsBold(15));
        borrowed.setLayout(new BoxLayout(borrowed, BoxLayout.Y_AXIS));
        borrowed.setPreferredSize(new Dimension(1000, 500));
        borrowedBooks.setLayout(new BoxLayout(borrowedBooks, BoxLayout.Y_AXIS));
        addBorrowedBooks();
        borrowed.add(borrowedLabel);

        JScrollPane borrowedScrollPane = new JScrollPane(borrowedBooks);
        borrowedScrollPane.setPreferredSize(new Dimension(450, 800));
        borrowed.add(borrowedScrollPane);
        add(borrowed);
    }

    private void addBorrowedBooks() {
        SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                borrowedBooks.removeAll();

                for (Book book : books) {
                    if (book.getCurrentBorrowers().isEmpty()) continue;
                    for (String studentId : book.getCurrentBorrowers()) {
                        Student student = getStudent(studentId);
                        if (student == null) continue;
                        ManageBookCard card = new ManageBookCard(book, student, true, serverObserver);
                        borrowedBooks.add((Component) card);
                    }
                }
                borrowedBooks.revalidate();
                borrowedBooks.repaint();
                return null;
            }
        };
    }
    private Student getStudent(String studentId) {
        for (Student student : students) {
            if (student.getAccount().getAccountId().equals(studentId)) return student;
        }
        return null;
    }
}