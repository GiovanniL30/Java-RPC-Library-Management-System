package project.server.views.components.homePanel;

import project.utilities.referenceClasses.Response;
import project.server.GlobalRemoteServant;
import project.utilities.utilityClasses.ColorFactory;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class BroadcastMessage extends JDialog {

    private GlobalRemoteServant server;
    private JComboBox<String> clientDropdown;
    private JTextArea messageTextArea;

    public BroadcastMessage(GlobalRemoteServant server) {
        this.server = server;

        setTitle("Broadcast Message");
        setSize(500, 120);
        setResizable(false);
        setLocationRelativeTo(null);

        broadcastComponents();

    }

    private void broadcastComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("BROADCAST MESSAGE");
        constraints.gridx = 1;
        constraints.gridy = -1;
        panel.add(label, constraints);

        clientDropdown = new JComboBox<>();
        clientDropdown.setForeground(ColorFactory.white());
        clientDropdown.setBackground(ColorFactory.black());
        String[] clientIds = {"All", "Student1", "Student2", "Student3", "Student4"};
        for (String clientId : clientIds) {
            clientDropdown.addItem(clientId);
        }
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(clientDropdown, constraints);

        messageTextArea = new JTextArea(10, 40);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.2;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(messageTextArea, constraints);

        JButton sendButton = new JButton("Send");
        sendButton.setForeground(ColorFactory.white());
        sendButton.setBackground(ColorFactory.black());
        sendButton.addActionListener(e -> {

        });
        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(sendButton, constraints);

        add(panel);
    }


}
