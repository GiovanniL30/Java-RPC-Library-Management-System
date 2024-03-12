package project.client;

import project.client.controller.ClientController;
import project.client.views.ClientMainView;

import javax.swing.*;

public class ClientApplication {

    public static void main(String[] args) {

        SwingUtilities.invokeLater( () -> {
            ClientController clientController = new ClientController();
            ClientMainView mainView = new ClientMainView(clientController);
            clientController.setMainView(mainView);

            mainView.setClientController(clientController);
        });

    }

}
