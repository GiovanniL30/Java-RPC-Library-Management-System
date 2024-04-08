package midproject.client.views.components;

import midproject.utilities.referenceClasses.Student;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents a message block
 */
public class MessageBlock extends JPanel {

    private Student sender;
    private String message;
    private Student loggedInAccount;
    private String time;

    /**
     * Constructor
     * @param sender student sender
     * @param loggedInAccount the logged in account
     * @param message message sent
     * @param time time sent
     */
    public MessageBlock(Student sender, Student loggedInAccount, String message, String time){

        // set the properties of the panel
        setPreferredSize(new Dimension(400, 70));
        setLayout(null);

        if(sender == null) return;

        this.sender = sender;
        this.message = message;
        this.time = time;
        this.loggedInAccount = loggedInAccount;

        // instantiate the labels and their properties
        JLabel messageLabel = new JLabel(this.message);
        messageLabel.setFont(FontFactory.newPoppinsDefault(13));
        JLabel timeLabel = new JLabel(this.time.substring(0, 16));
        timeLabel.setFont(FontFactory.newPoppinsDefault(10));
        JLabel senderLabel = new JLabel();

        JPanel messageBlockPanel = new JPanel();
        messageBlockPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        messageBlockPanel.add(messageLabel);


        // differentiate a message sent from you and from another client
        if(isYou()) {
            messageBlockPanel.setBackground(ColorFactory.blue());
            messageLabel.setForeground(Color.WHITE);
            senderLabel.setText("You");
        } else {
            senderLabel.setText(sender.getAccount().getUserName());
            messageBlockPanel.setBackground(new Color(150, 150, 150));
        } // end of if else

        // add labels to panel
        add(senderLabel);
        add(messageBlockPanel);
        add(timeLabel);

        senderLabel.setBounds(0, 0, 100, 20);
        messageBlockPanel.setBounds(10, 20, 400, 30);
        timeLabel.setBounds(310, 50, 200, 20);

    } // end of constructor


    /**
     * Checks if the user is the one that sent the message
     * @return
     */
    public boolean isYou(){
        return sender.getAccount().getAccountId().equals(loggedInAccount.getAccount().getAccountId());
    } // end of isYou

    /**
     * Sets the logged in account
     * @param loggedInAccount
     */
    public void setLoggedInAccount(Student loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    } // end of setLoggedInAccount
} // end of class

