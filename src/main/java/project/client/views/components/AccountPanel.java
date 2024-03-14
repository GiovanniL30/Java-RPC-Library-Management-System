package project.client.views.components;

import project.client.views.ClientMainView;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AccountPanel extends JPanel {

    public AccountPanel(Student student) {

        Account studentAccount = student.getAccount();


        Picture picture = new Picture("src/main/resources/images/icons/profileAccount.jpg", 200, 200);
        JLabel userName = new JLabel("User Name: " + studentAccount.getUserName());
        JLabel name = new JLabel("Full Name: " + studentAccount.getFirstName() + " " + studentAccount.getLastName());
        JLabel email = new JLabel("Email: " + studentAccount.getEmail());
        JLabel totalBorrowed = new JLabel("Number of borrowed books: "+   student.getBorrowedBooks().size());
        JLabel totalPendingBooks = new JLabel("Number of pending books: " + student.getPendingBooks().size());

        increaseFont(userName, name, email, totalBorrowed, totalPendingBooks);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(ClientMainView.FRAME_WIDTH, 500));

        JPanel accountInformation = new JPanel();
        accountInformation.setLayout(new BoxLayout(accountInformation, BoxLayout.Y_AXIS));
        accountInformation.add(userName);
        accountInformation.add(name);
        accountInformation.add(email);
        accountInformation.add(totalBorrowed);
        accountInformation.add(totalPendingBooks);
        accountInformation.setBackground(Color.white);

        picture.setBackground(Color.white);
        add(picture);
        add(accountInformation);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(100, 0, 0, 0));
    }


    private void increaseFont(JLabel ... labels) {

        for(JLabel label: labels) {
            label.setFont(FontFactory.newPoppinsBold(15));
            label.setBorder(new EmptyBorder(0, 0, 10, 0));
        }

    }

}
