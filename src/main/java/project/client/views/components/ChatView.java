package project.client.views.components;

import project.client.controller.ClientController;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.FieldInput;

import javax.swing.*;
import java.awt.*;

/**
 * Class that shows the chats in a JDialog
 */
public class ChatView extends JDialog {

    private JPanel messagesPanel = new JPanel();
    private FieldInput fieldInput = new FieldInput("", new Dimension(700, 50), 50, 1, false);
    private Button sendButton = new Button("Send", 100, 50, FontFactory.newPoppinsDefault(13));
    private GridBagConstraints constraints = new GridBagConstraints();
    private int messagePositionY = 0;
    private JScrollPane scrollPane = new JScrollPane(messagesPanel);

    /**
     * Constructor
     * @param owner owner frame
     * @param title title of the JDialog
     * @param clientController controller
     */
    public ChatView(Frame owner, String title, ClientController clientController) {
        super(owner, title, true);

        // set the layout of the messagesPanel as GridBagLayout
        messagesPanel.setLayout(new GridBagLayout());

        setSize(new Dimension(900, 500));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);

        scrollPane.setPreferredSize(new Dimension(900, 450));

        // instantiate fieldPanel and set its components
        JPanel fieldPanel = new JPanel();
        fieldInput.setBackground(UIManager.getDefaults().getColor("Panel.background"));
        fieldPanel.add(fieldInput);
        fieldPanel.add(sendButton);

        sendButton.addActionListener(e -> {
            // send the message if button is pressed
            String message = fieldInput.getInput();
            if(message == null) return;
            fieldInput.getTextField().setText("");
            clientController.sendMessage(message);
        });

        add(scrollPane);
        add(fieldPanel);
    } // end of constructor

    /**
     * Adds a new message
     * @param messageBlock
     */
    public void addMessage(MessageBlock messageBlock) {

        if (messageBlock.isYou()) {
            // puts the message on one side if the sender is you
            constraints.gridx = 0;
            messagesPanel.add(new MessageBlock(null, null, null, null), constraints);

            constraints.gridx = 1;
            messagesPanel.add(messageBlock,constraints);
        } else {
            // puts the message on the other side
            constraints.gridx = 0;
            messagesPanel.add(messageBlock, constraints);

            constraints.gridx = 1;
            messagesPanel.add(new MessageBlock(null, null, null, null), constraints);
        } // end of if else statement


        constraints.gridy = messagePositionY++;

        messagesPanel.revalidate();
        messagesPanel.repaint();

        SwingUtilities.invokeLater(() -> {
            JViewport viewport = scrollPane.getViewport();
            viewport.setViewPosition(new Point(0, messagesPanel.getHeight()));
        });
    } // end of addMessage

} // end of class

