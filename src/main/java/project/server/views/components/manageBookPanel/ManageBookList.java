package project.server.views.components.manageBookPanel;

import project.server.controller.ServerObserver;
import project.server.views.components.viewBookPanel.ViewBooksCard;
import project.server.views.utility.ServerPanels;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ManageBookList extends JPanel {

    private final ServerObserver serverObserver;
    private final JPanel holder = new JPanel();

    public ManageBookList(LinkedList<Student> students, ServerObserver serverObserver,ServerPanels serverPanels) {

        this.serverObserver = serverObserver;

        setBackground(Color.white);

        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(20);
        holder.setLayout(gridLayout);
        holder.setBackground(Color.white);

        updateView(students, serverPanels);

        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setPreferredSize(new Dimension(920, 550));

        add(scrollPane);
    }

    public void updateView(LinkedList<Student> students, ServerPanels serverPanels) {
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {


                if (students.isEmpty()) {
                    holder.revalidate();
                    holder.repaint();
                }

                for (Student student : students) {

                    switch (serverPanels) {
                        case PENDING_BORROW_PANEL -> {

                            for(Book book : student.getPendingBooks()) {
                                holder.add(new ManageBookCard(book, student, serverObserver, ServerPanels.PENDING_BORROW_PANEL));
                                holder.revalidate();
                                holder.repaint();
                            }


                        }case PENDING_RETURN_PANEL -> {
                            for(Book book : student.getPendingReturnBook()) {
                                holder.add(new ManageBookCard(book, student, serverObserver, ServerPanels.PENDING_RETURN_PANEL));
                                holder.revalidate();
                                holder.repaint();
                            }
                        }

                        case BORROWED_PANEL->  {

                            for(Book book : student.getBorrowedBooks()) {
                                System.out.println(book);
                                holder.add(new ManageBookCard(book, student,  serverObserver, ServerPanels.BORROWED_PANEL));
                                holder.revalidate();
                                holder.repaint();
                            }

                        }
                    }

                }


                return null;
            }


        }.execute();


    }
}
