package project.server;

import project.server.controller.ServerController;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.RMI.ServerRemoteMethods;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerApplication {

    public static void main(String[] args) {
        ServerController serverController;
        new Server();

        try {
            ServerRemoteMethods remoteMethods = (ServerRemoteMethods) LocateRegistry.getRegistry(1099).lookup("ServerRemote");
            serverController = Server.getServerController();
            remoteMethods.registerServer(serverController);
        } catch (NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }

    }

}
