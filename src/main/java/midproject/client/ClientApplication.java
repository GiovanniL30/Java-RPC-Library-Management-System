package midproject.client;

import midproject.client.controller.ClientController;
import midproject.client.views.ClientMainView;

import javax.swing.*;

public class ClientApplication {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater( () -> {

            ClientController clientController;
            clientController = new ClientController();

            ClientMainView mainView = new ClientMainView(clientController);
            mainView.setClientController(clientController);
            clientController.setMainView(mainView);
        });

    } // end of main method

} // end of class

