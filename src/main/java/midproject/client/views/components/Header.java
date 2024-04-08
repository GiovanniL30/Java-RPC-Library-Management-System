package midproject.client.views.components;

import midproject.client.controller.ClientController;
import midproject.client.views.ClientMainView;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Button;
import midproject.utilities.viewComponents.IconButton;
import midproject.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Class that functions as a header
 */
public class Header extends JPanel {

    private final Button logout;
    private final IconButton message;

    /**
     * Constructor
     * @param studentName name of the student
     */
    public Header(String studentName) {
        setBackground(ColorFactory.blue());

        GridBagConstraints constraints = new GridBagConstraints();

        // set the properties of this panel
        setPreferredSize(new Dimension(ClientMainView.FRAME_WIDTH, 100));
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(0, 10, 0, 30));

        // set the components to be added to the panel
        Picture logo = new Picture("src/main/resources/images/logo/logo_vanni.png", 100, 100);
        logo.setBackground(ColorFactory.blue());
        JLabel label = new JLabel(studentName.toUpperCase());
        label.setForeground(Color.WHITE);
        label.setFont(FontFactory.newPoppinsBold(18));

        // initialize the logout button and its properties
        logout = new Button("LOGOUT", 150, 50, FontFactory.newPoppinsBold(13));
        logout.setForeground(Color.WHITE);
        logout.setBackground(ColorFactory.red());
        logout.setMaximumSize(new Dimension(150, 50));

        message = new IconButton("src/main/resources/images/icons/icons8-message-96.png", 65, 65);

        constraints.gridy = 0;
        constraints.gridx = 0;

        // add the logo using the given constraints
        add(logo,constraints);

        constraints.fill = 2;
        constraints.weightx = 2.0;
        constraints.gridx = 1;

        // add the label using the given constraints
        add(label, constraints);


        constraints.gridx = 2;
        constraints.weightx = 0.0;
        constraints.fill = 0;
        constraints.insets = new Insets(0, 0, 0, 15);

        // add the message using the given constraints
        add(message, constraints);

        constraints.gridx = 3;
        constraints.insets = new Insets(0, 0, 0, 0);

        // add the logout button using the given constraints
        add(logout, constraints);
    } // end of constructor

    /**
     * Adds the action for the logout button
     * @param clientController
     */
    public void addLogoutAction(ClientController clientController) {
        logout.addActionListener(e -> clientController.logout());
    } // end of addLogoutAction

    /**
     * Adds the action for the message button
     * @param clientController
     */
    public void addMessageAction(ClientController clientController) {
        message.addActionListener(e -> clientController.openMessageChat());
    } // end of addMessageAction

} // end of class
