package project.server.views.components.homePanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;

public class BroadcastMessage extends JDialog {

    private JComboBox<String> clientDropdown;
    private JTextField messageTextArea;
    LinkedList<Student> students;

    public BroadcastMessage(Frame frame, ServerObserver serverObserver) {
        super(frame, "Broadcast Message", true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(640, 130));
        setResizable(false);
        setLocationRelativeTo(null);

        students = serverObserver.getStudents();


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

        JPanel fieldPanel = new JPanel();
        fieldPanel.setPreferredSize(new Dimension(420, 50));
        fieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        messageTextArea = new JTextField();
        messageTextArea.setPreferredSize(new Dimension(420, 30));
        fieldPanel.add(messageTextArea);

        Button sendButton = new Button("Send", 200, 30, FontFactory.newPoppinsDefault(14));
        sendButton.setForeground(ColorFactory.white());
        sendButton.setBackground(ColorFactory.blue());


        JPanel lowerPart = new JPanel();
        lowerPart.setPreferredSize(new Dimension(100, 50));
        lowerPart.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        lowerPart.add(clientDropdown);
        lowerPart.add(sendButton);

        add(fieldPanel);
        add(lowerPart);

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


    }
}
