package project.server.views.components.homePanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BroadcastMessage extends JDialog {

    private JComboBox<String> clientDropdown;
    private JTextField messageTextArea;
    LinkedList<Student> students;

    //TODO: DONE
    public BroadcastMessage(Frame frame, ServerObserver serverObserver) {
        super(frame, "Broadcast Message", true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(500, 130));
        setResizable(false);
        setLocationRelativeTo(null);

        students = serverObserver.getStudents();


        clientDropdown = new JComboBox<>();
        clientDropdown.setForeground(ColorFactory.white());
        clientDropdown.setBackground(ColorFactory.blue());
        clientDropdown.setPreferredSize(new Dimension(200, 30));

        LinkedList<String> clients = new LinkedList<>();
        clients.add("All");

        for (Student student : students) {
            clients.addLast(student.getAccount().getUserName());
        }

        for (String clientId : clients) {
            clientDropdown.addItem(clientId);
        }


        JPanel fieldPanel = new JPanel();
        fieldPanel.setPreferredSize(new Dimension(420, 50));
        fieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        messageTextArea = new JTextField();
        messageTextArea.setPreferredSize(new Dimension(400, 30));
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

           if (!message.isEmpty() && selectedClient != null) {
               serverObserver.broadcastMessage(message);
               messageTextArea.setText("");
           } else {
               JOptionPane.showMessageDialog(BroadcastMessage.this, "Please enter a message.");
           }
        });

    }
}
