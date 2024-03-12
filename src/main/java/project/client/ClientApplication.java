package project.client;

import project.client.controller.ClientController;
import project.client.views.ClientMainView;

public class ClientApplication {

    public static void main(String[] args) {
        ClientMainView mainView = new ClientMainView();
        ClientController clientController = new ClientController(mainView);
        mainView.setClientController(clientController);
    }

}
