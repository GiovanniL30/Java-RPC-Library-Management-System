package midproject.client.views;



import midproject.client.controller.ClientController;
import midproject.utilities.referenceClasses.Authentication;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.FieldInput;
import midproject.utilities.viewComponents.Picture;
import midproject.utilities.viewComponents.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Frame for client login
 */
public class Login extends JPanel {

    private FieldInput password;//Input field for the password
    private FieldInput userName;//Input field for the username
    private final Button loginButton;//Button for login
    private final Picture logo = new Picture("src/main/resources/images/logo/login_logo.jpg", 400, 400);//Logo picture
    private final GridBagConstraints layout;//Layout constraints
    private final Dimension frameDimension;//Dimensions for the frame
    private final JPanel leftSide = new JPanel();//Panel for the left side of the login form
    private final JPanel rightSide = new JPanel();//panel for the right side of the login form
    private final JPanel mainContent = new JPanel();//User credentials
    private Authentication credentials;

    /**
     * Constructor
     *
     * @param dimension the dimension of the frame
     */
    public Login(Dimension dimension) {
        frameDimension = dimension;
        setPreferredSize(frameDimension);
        rightSide.setLayout(new GridBagLayout());
        rightSide.setBackground(Color.white);
        leftSide.setBackground(Color.white);
        setBackground(Color.white);
        logo.setBackground(Color.white);

        // fields where text is to be put
        password = new FieldInput("Password:", new Dimension(300, 50), 100, 8, true);
        userName = new FieldInput("Username:", new Dimension(300, 50), 100, 8, false);
        loginButton = new Button("Login", 300, 50, FontFactory.newPoppinsBold(13));
        loginButton.setForeground(Color.white);
        loginButton.setBackground(ColorFactory.blue());


        layout = new GridBagConstraints();

        //Add components to the right side of the panel
        layout.gridx = 0;
        layout.gridy = 1;
        rightSide.add(userName, layout);

        layout.insets = new Insets(0, 0, 0, 0);
        layout.gridy = 2;
        rightSide.add(password, layout);

        layout.gridy = 3;
        rightSide.add(loginButton, layout);

        //Add logo to the left side panel
        leftSide.add(logo);

        //Configure main content panel
        mainContent.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainContent.add(leftSide);
        mainContent.add(rightSide);
        mainContent.setBackground(Color.white);

        //Set layout and border for the login panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mainContent.setBorder(new EmptyBorder(150, 110, 0, 0));
        add(mainContent);
    } // end of constructor

    /**
     * Click event for login button
     *
     * @param controller
     */
    public void addClickEvent(ClientController controller) {
        loginButton.addActionListener((e) -> {

            //Retrieve the entered username and password
            String enteredPassword = password.getInput();
            String enteredUsername = userName.getInput();

            //Check if username or password is null
            if (enteredUsername == null || enteredPassword == null) {
                // check if username or password is null
                return;
            } // end of if statement

            password.getPasswordField().setText("");
            userName.getTextField().setText("");
            //Password authentication to the controller
            controller.logIn(new Authentication(enteredUsername, enteredPassword));
        });
    } // end of addClickEvent


    public FieldInput getPassword() {
        return password;
    }

    public FieldInput getUserName() {
        return userName;
    }
    public void setPassword(FieldInput password) {
        this.password = password;
    }

    public void setUserName(FieldInput userName) {
        this.userName = userName;
    }

    public Button getLoginButton() {
        return loginButton;
    }
} // end of Login

