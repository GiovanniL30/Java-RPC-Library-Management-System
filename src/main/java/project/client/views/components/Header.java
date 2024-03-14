package project.client.views.components;

import project.client.controller.ClientController;
import project.client.views.ClientMainView;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Header extends JPanel {

    private final Button logout;

    public Header(String studentName) {
        setBackground(ColorFactory.blue());

        GridBagConstraints constraints = new GridBagConstraints();

        setPreferredSize(new Dimension(ClientMainView.FRAME_WIDTH, 100));
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(0, 10, 0, 30));

        Picture logo = new Picture("src/main/resources/images/logo/logo_vanni.png", 100, 100);
        logo.setBackground(ColorFactory.blue());
        JLabel label = new JLabel(studentName.toUpperCase());
        label.setForeground(Color.WHITE);
        label.setFont(FontFactory.newPoppinsBold(18));
        logout = new Button("LOGOUT", 150, 50, FontFactory.newPoppinsBold(13));
        logout.setForeground(Color.WHITE);
        logout.setBackground(ColorFactory.red());
        logout.setMaximumSize(new Dimension(150, 50));

        constraints.gridy = 0;
        constraints.gridx = 0;
        add(logo,constraints);

        constraints.fill = 2;
        constraints.weightx = 2.0;
        constraints.gridx = 1;
        add(label, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0.0;
        constraints.fill = 0;
        add(logout, constraints);
    }

    public void addLogoutAction(ClientController clientController) {
        logout.addActionListener(e -> clientController.logout());
    }

}
