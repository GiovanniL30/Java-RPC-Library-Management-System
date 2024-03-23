package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.ClickableText;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class ManageAccountsPanel extends JPanel{
    private final ServerController serverController;
    private final JPanel accountsPanel = new JPanel();
    private final ClickableText createAccount = new ClickableText("Create account for " +
            "student", 100, 50, FontFactory.newPoppinsBold(15));

    public ManageAccountsPanel(LinkedList<Student> accounts, ServerController serverController){
        this.serverController = serverController;
        GridLayout gridLayout = new GridLayout(0,2 );
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        accountsPanel.setLayout(gridLayout);
        populatePanel(accounts);

        JScrollPane scrollPane = new JScrollPane(accountsPanel);
        scrollPane.setPreferredSize(new Dimension(1000, 700));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollPane);

        createAccount.setBorder(new EmptyBorder(60, 0, 0, 0));
        createAccount.setForeground(Color.BLUE);
        // createAccount.addActionListener(e -> signup.setVisible(true));
    }

    private void populatePanel(LinkedList<Student> accounts){
        for (Student account : accounts){
            AccountCard accountCard = new AccountCard(account);
            // accountsPanel.add(accountCard);
        }
    }

    public void refresh(LinkedList<Student> accounts){
        accountsPanel.removeAll();
        populatePanel(accounts);
        accountsPanel.revalidate();
        accountsPanel.repaint();
    }

    private class AccountCard extends JPanel{
        AccountCard(Student account) {
            setBorder(new EmptyBorder(20, 0, 0, 0));
            setMaximumSize(new Dimension(500, 400));
            setMaximumSize(getPreferredSize());

            JPanel buttonsPanel = new JPanel();
            GridLayout buttonsGrid = new GridLayout(2, 2);
            buttonsGrid.setHgap(10);
            buttonsGrid.setVgap(10);
            buttonsPanel.setLayout(buttonsGrid);

            JPanel accountInfo = new JPanel();
            accountInfo.setLayout(new GridLayout(3, 1));

            Button banButton = new Button("Ban");
            banButton.setForeground(Color.white);
            banButton.setBackground(ColorFactory.purple());

            Button unbanButton = new Button("Unban");
            unbanButton.setForeground(Color.white);
            unbanButton.setBackground(ColorFactory.green());

            Button deleteAccount = new Button("Delete");
            deleteAccount.setForeground(Color.white);
            deleteAccount.setBackground(ColorFactory.red());

            Button editAccount = new Button("Edit Pass");
            editAccount.setForeground(Color.white);
            editAccount.setBackground(ColorFactory.blue());

            // Creating labels for account information
            JLabel userName = new JLabel("Username: " + account.getAccount().getUserName());
            JLabel name = new JLabel("Name: " + account.getAccount().getFirstName() + " " + account.getAccount().getLastName());
            JLabel userPassword = new JLabel("Password: " + account.getAccount().getPassword());

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
            rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
            rightSide.add(accountInfo);
            rightSide.add(buttonsPanel);

            // Creating picture label for account image
            Picture picture = new Picture("res/icons/profileAccount.jpg", 170, 170);
            // Setting color and button states based on account status
            if (account.getAccount().isBanned()) {
                picture.setBackground(Color.red);
                banButton.setEnabled(false);
                banButton.setBackground(Color.white);
            } else {
                picture.setBackground(Color.getColor("57955CFF"));
                unbanButton.setEnabled(false);
                unbanButton.setBackground(Color.white);
            }

            rightSide.setBackground(new Color(238, 238, 238));
            rightSide.setBorder(new EmptyBorder(0, 10, 0, 0));
            add(picture);

            add(rightSide);

            // Action listeners for banning, unbanning, deleting, and edting account
            banButton.addActionListener(e -> serverController.banAccount(account.getAccount()));
            unbanButton.addActionListener(e -> serverController.unbanAccount(account.getAccount()));
            deleteAccount.addActionListener(e -> serverController.deleteAccount(account));
            editAccount.addActionListener(e -> {
                String newPass = JOptionPane.showInputDialog(null, "Enter the new password: ");
                if (newPass == null || newPass.isEmpty()) return;

                if (newPass.length() < 8) {
                    JOptionPane.showMessageDialog(null, "Password Length Invalid [8 and above]");
                    return;
                }

                serverController.changeUserPassword(account.getAccount(), newPass);
            });
            //clickableText.addActionListener(e -> signup.setVisible(true));
        }
    }
}
