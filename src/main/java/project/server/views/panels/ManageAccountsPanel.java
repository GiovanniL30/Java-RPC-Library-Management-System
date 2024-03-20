package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.ClickableText;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.FontFactory;

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
            // AccountCard accountCard = new AccountCard(account);
            // accountsPanel.add(accountCard);
        }
    }

    public void refresh(LinkedList<Student> accounts){

    }

    private class AccountCard extends JPanel{

    }
}
