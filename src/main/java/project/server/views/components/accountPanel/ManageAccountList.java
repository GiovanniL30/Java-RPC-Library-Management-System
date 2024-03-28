package project.server.views.components.accountPanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Student;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ManageAccountList extends JPanel {

    private final ServerObserver serverObserver;
    private final JPanel holder = new JPanel();

    public ManageAccountList(LinkedList<Student> students, ServerObserver serverObserver) {

        this.serverObserver = serverObserver;

        setBackground(Color.white);

        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(20);
        holder.setLayout(gridLayout);
        holder.setBackground(Color.white);

        updateView(students);

        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setPreferredSize(new Dimension(920, 550));

        add(scrollPane);
    }

    public void updateView(LinkedList<Student> students) {

        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {

                if (students.isEmpty()) {
                    holder.revalidate();
                    holder.repaint();
                }

                for(Student student: students) {
                    holder.add(new AccountCard(student, serverObserver));
                    holder.revalidate();
                    holder.repaint();
                }


                return null;
            }


        }.execute();


    }

}
