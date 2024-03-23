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

    }
}
