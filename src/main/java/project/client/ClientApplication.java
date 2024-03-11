package project.client;

import project.client.controller.ClientController;
import project.server.Server;

public class ClientApplication {

    public static void main(String[] args) {



        for(int i = 0 ; i < 10; i++) {

            new Thread(() -> {
                ClientController clientController = new ClientController();
                clientController.logIn(null);
            }).start();

        }





    }

}
