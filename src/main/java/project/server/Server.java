package project.server;

import project.server.controller.ServerUpdates;
import project.server.controller.ServerController;
import project.server.views.LibrarianMainFrame;
import project.utilities.RMI.GlobalRemoteMethods;

import javax.swing.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            ServerController serverController = new ServerController();
            ServerUpdates servant = new ServerUpdates(serverController);

            GlobalRemoteMethods globalRemoteMethods = new GlobalRemoteServant(servant);
            registry.bind("server", globalRemoteMethods);

            serverController.setServerMethods();

            SwingUtilities.invokeLater(() -> {
                LibrarianMainFrame mainFrame = new LibrarianMainFrame(serverController);
                serverController.setServerMainView(mainFrame);
            });

            System.out.println("Server is running...");

        } catch (RuntimeException | RemoteException | AlreadyBoundException e) {
            throw  new RuntimeException(e);
        }





    }

}
