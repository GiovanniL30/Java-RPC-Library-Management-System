package project.server.views.components.accountPanel;


import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Picture;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AccountCard extends JPanel {

    private final ServerObserver serverObserver;

    public AccountCard(Student student, ServerObserver serverObserver) {
        this.serverObserver = serverObserver;


        setBorder(new EmptyBorder(20, 0, 0, 0));
        setMaximumSize(new Dimension(500, 400));
        setMaximumSize(getPreferredSize());
        setBackground(Color.white);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.white);
        GridLayout buttonsGrid = new GridLayout(2, 2);
        buttonsGrid.setHgap(10);
        buttonsGrid.setVgap(10);
        buttonsPanel.setLayout(buttonsGrid);

        JPanel accountInfo = new JPanel();
        accountInfo.setBackground(Color.white);
        accountInfo.setLayout(new GridLayout(3, 1));

        Button banButton = new Button("Ban", 100, 50, FontFactory.newPoppinsDefault(13));
        banButton.setForeground(Color.white);
        banButton.setBackground(ColorFactory.purple());

        Button unbanButton = new Button("Unban", 100, 50, FontFactory.newPoppinsDefault(13));
        unbanButton.setForeground(Color.white);
        unbanButton.setBackground(ColorFactory.green());

        Button deleteAccount = new Button("Delete", 100, 50, FontFactory.newPoppinsDefault(13));
        deleteAccount.setForeground(Color.white);
        deleteAccount.setBackground(ColorFactory.red());

        Button editAccount = new Button("Edit Pass", 100, 50, FontFactory.newPoppinsDefault(13));
        editAccount.setForeground(Color.white);
        editAccount.setBackground(ColorFactory.blue());

        // Creating labels for account information
        JLabel userName = new JLabel("Username: " + student.getAccount().getUserName());
        JLabel name = new JLabel("Name: " + student.getAccount().getFirstName() + " " + student.getAccount().getLastName());
        JLabel userPassword = new JLabel("Password: " + student.getAccount().getPassword());

        name.setFont(FontFactory.newPoppinsBold(14));
        userName.setFont(FontFactory.newPoppinsDefault(13));
        userPassword.setFont(FontFactory.newPoppinsDefault(13));

        // Add buttons to buttons panel
        buttonsPanel.add(unbanButton);
        buttonsPanel.add(banButton);
        buttonsPanel.add(editAccount);
        buttonsPanel.add(deleteAccount);
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Add account info to account info panel
        accountInfo.add(name);
        accountInfo.add(userName);
        accountInfo.add(userPassword);

        JPanel rightSide = new JPanel();
        rightSide.setBackground(Color.white);
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
        rightSide.add(accountInfo);
        rightSide.add(buttonsPanel);

        // Creating picture label for account image
        Picture picture = new Picture("src/main/resources/images/icons/profileAccount.jpg", 175, 175);
        // Setting color and button states based on account status
        if (student.getAccount().getIsBanned()) {
            picture.setBackground(Color.red);
            banButton.setEnabled(false);
            banButton.setBackground(Color.white);
        } else {
            picture.setBackground(ColorFactory.green());
            unbanButton.setEnabled(false);
            unbanButton.setBackground(Color.white);
        }


        rightSide.setBorder(new EmptyBorder(0, 10, 0, 0));
        add(picture);

        add(rightSide);

        // Action listeners for banning, unbanning, deleting, and edting account
        banButton.addActionListener(e -> serverObserver.banAccount(student));
        unbanButton.addActionListener(e -> serverObserver.unbanAccount(student));
        deleteAccount.addActionListener(e -> serverObserver.deleteAccount(student));
        editAccount.addActionListener(e -> serverObserver.changeUserPassword(student));

        //clickableText.addActionListener(e -> signup.setVisible(true));

    }


}
