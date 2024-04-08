package project.server.views.components.accountPanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Student;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Represents a panel for managing a list of student accounts.
 */

public class ManageAccountList extends JPanel {
    private final ServerObserver serverObserver;
    private final JPanel holder = new JPanel();

    /**
     * Constructs a ManageAccountList panel with the specified list of students and server observer.
     * @param students       The list of students to be displayed.
     * @param serverObserver The server observer for handling actions related to the accounts.
     */
    public ManageAccountList(LinkedList<Student> students, ServerObserver serverObserver) {
        this.serverObserver = serverObserver;

        setBackground(Color.white);

        // Set layout for the holder panel
        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(20);
        holder.setLayout(gridLayout);
        holder.setBackground(Color.white);

        // Update the view with the given list of students
        updateView(students);

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setPreferredSize(new Dimension(920, 550));
        add(scrollPane);
    } // end of constructor

    /**
     * Updates the view with the given list of students.
     */
    public void updateView(LinkedList<Student> students) {
        // Use SwingWorker to update the view in the background
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                // Clear the holder panel if the list is empty
                if (students.isEmpty()) {
                    holder.revalidate();
                    holder.repaint();
                }

                // Add account cards for each student to the holder panel
                for(Student student: students) {
                    holder.add(new AccountCard(student, serverObserver));
                    holder.revalidate();
                    holder.repaint();
                }
                return null;
            }
        }.execute();
    } // end of updateView method
} // end of ManageAccountList class
