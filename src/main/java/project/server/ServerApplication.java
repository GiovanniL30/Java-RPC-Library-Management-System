package project.server;

import project.server.controller.ServerController;
import project.utilities.RMI.ClientRemoteMethods;

import java.rmi.registry.LocateRegistry;

public class ServerApplication {

    public static void main(String[] args) {
        ServerController serverController = new ServerController();
        serverController.acceptBook(null, null);

        while (true) {

            try {

                ClientRemoteMethods clientRemoteMethods = (ClientRemoteMethods) LocateRegistry.getRegistry(1099).lookup("ClientRemote");
                System.out.println(clientRemoteMethods.getClients().size());

            } catch (Exception e) {

            }

        }

    }

}
