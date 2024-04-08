package midproject.server.views.components.homePanel;

import midproject.server.controller.ServerObserver;
import midproject.utilities.referenceClasses.Student;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Represents a dialog window for broadcasting messages to clients.
 */

public class BroadcastMessage extends JDialog {
    private JComboBox<String> clientDropdown;
    private JTextField messageTextArea;
    LinkedList<Student> students;

    /**
     * Constructs a BroadcastMessage dialog.
     * @param frame          The parent frame for the dialog.
     * @param serverObserver The server observer for interacting with server functionalities.
     */
    public BroadcastMessage(Frame frame, ServerObserver serverObserver) {
        super(frame, "Broadcast Message", true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(640, 130));
        setResizable(false);
        setLocationRelativeTo(null);

        students = serverObserver.getStudents();

        // Create a dropdown list of clients
        clientDropdown = new JComboBox<>();
        clientDropdown.setPreferredSize(new Dimension(200, 30));
        LinkedList<String> clients = new LinkedList<>();
        clients.add("All");

        for (Student student : students) {
            clients.addLast("User name: "+student.getAccount().getUserName()+"-id="+student.getAccount().getAccountId());
        }
        for (String clientId : clients) {
            clientDropdown.addItem(clientId);
        }
        clientDropdown.setSelectedItem(0);

        // Create a text field for entering the message
        JPanel fieldPanel = new JPanel();
        fieldPanel.setPreferredSize(new Dimension(420, 50));
        fieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        messageTextArea = new JTextField();
        messageTextArea.setPreferredSize(new Dimension(420, 30));
        fieldPanel.add(messageTextArea);

        // Create a button for sending the message
        Button sendButton = new Button("Send", 200, 30, FontFactory.newPoppinsDefault(14));
        sendButton.setForeground(ColorFactory.white());
        sendButton.setBackground(ColorFactory.blue());

        // Create a panel for the dropdown list and the send button
        JPanel lowerPart = new JPanel();
        lowerPart.setPreferredSize(new Dimension(100, 50));
        lowerPart.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        lowerPart.add(clientDropdown);
        lowerPart.add(sendButton);

        // Add components to the dialog window
        add(fieldPanel);
        add(lowerPart);

        // Action listener for sending the message
        sendButton.addActionListener(e -> {
           String message = messageTextArea.getText();
           String selectedClient = (String) clientDropdown.getSelectedItem();

           if (!message.isEmpty()) {
               if (selectedClient.equals("All")){
                   serverObserver.broadcastMessageToAll(message);
                   messageTextArea.setText("");
               } else {
                   serverObserver.broadcastMessage(message, selectedClient.split("-id=")[1]);
                   messageTextArea.setText("");
               }
               dispose();
           } else {
               JOptionPane.showMessageDialog(BroadcastMessage.this, "Please enter a message.");
           }
        });
    } // End of constructor
} // End of BroadcastMessage class
