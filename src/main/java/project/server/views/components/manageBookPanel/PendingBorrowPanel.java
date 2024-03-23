package project.server.views.components.manageBookPanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PendingBorrowPanel extends JPanel {
    private JPanel pendingBooks = new JPanel();
    private LinkedList<Book> books = new LinkedList<>();
    private LinkedList<Student> students = new LinkedList<>();
    private ServerObserver serverObserver;
    public PendingBorrowPanel(ServerObserver serverObserver) {
        this.serverObserver = serverObserver;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel pendingLabel = new JLabel("PENDING BOOKS");
        pendingLabel.setFont(FontFactory.newPoppinsBold(15));
        JPanel pending = new JPanel();
        pending.setLayout(new BoxLayout(pending, BoxLayout.Y_AXIS));
        pendingBooks.setLayout(new BoxLayout(pendingBooks, BoxLayout.Y_AXIS));
        pending.setPreferredSize(new Dimension(1000, 800));
        addPendingBooks();
        pending.add(pendingLabel);

        JScrollPane scrollPane = new JScrollPane(pendingBooks);
        scrollPane.setPreferredSize(new Dimension(450, 800));
        pending.add(scrollPane);
        add(pending);
    }
    private void addPendingBooks() {
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                pendingBooks.removeAll();
                for (Book book : books) {
                    if (book.getPendingBorrowers().isEmpty()) continue;
                    for (String studentId : book.getPendingBorrowers()) {
                        Student student = getStudent(studentId);
                        if (student == null) continue;
                        ManageBookCard card = new ManageBookCard(book, student, false, serverObserver);
                        pendingBooks.add(card);
                    }
                }
                pendingBooks.revalidate();
                pendingBooks.repaint();
                return null;
            } // end of doInBackground
        }.execute();
    } // end of addPendingBooks method
    private Student getStudent(String studentId) {
        for (Student student : students) {
            if (student.getAccount().getAccountId().equals(studentId)) return student;
        }
        return null;
    }
}

