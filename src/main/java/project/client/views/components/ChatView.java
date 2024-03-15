package project.client.views.components;

import project.client.controller.ClientController;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.FieldInput;

import javax.swing.*;
import java.awt.*;

public class ChatView extends JDialog {

    private JPanel messagesPanel = new JPanel();
    private FieldInput fieldInput = new FieldInput("", new Dimension(700, 50), 50, 1, false);
    private Button sendButton = new Button("Send", 100, 50, FontFactory.newPoppinsDefault(13));
    private GridBagConstraints constraints = new GridBagConstraints();
    private int messagePositionY = 0;
    private JScrollPane scrollPane = new JScrollPane(messagesPanel);

    public ChatView(Frame owner, String title, ClientController clientController) {
        super(owner, title, true);

        messagesPanel.setLayout(new GridBagLayout());

        setSize(new Dimension(900, 500));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);


        scrollPane.setPreferredSize(new Dimension(900, 450));

        JPanel fieldPanel = new JPanel();
        fieldInput.setBackground(UIManager.getDefaults().getColor("Panel.background"));
        fieldPanel.add(fieldInput);
        fieldPanel.add(sendButton);

//        sendButton.addActionListener(e -> {
//            String message = fieldInput.getInput();
//            if(message == null) return;
//            fieldInput.getTextField().setText("");
//            observer.sendMessage(message);
//        });


        add(scrollPane);
        add(fieldPanel);
    }

    public void addMessage(MessageBlock messageBlock) {

        if (messageBlock.isYou()) {
            constraints.gridx = 0;
            messagesPanel.add(new MessageBlock(null, null, null, null), constraints);

            constraints.gridx = 1;
            messagesPanel.add(messageBlock,constraints);
        } else {
            constraints.gridx = 0;
            messagesPanel.add(messageBlock, constraints);

            constraints.gridx = 1;
            messagesPanel.add(new MessageBlock(null, null, null, null), constraints);
        }


        constraints.gridy = messagePositionY++;

        messagesPanel.revalidate();
        messagesPanel.repaint();

        SwingUtilities.invokeLater(() -> {
            JViewport viewport = scrollPane.getViewport();
            viewport.setViewPosition(new Point(0, messagesPanel.getHeight()));
        });
    }

}

