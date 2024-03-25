package project.server;

import project.server.controller.ServerUpdates;
import project.server.controller.ServerController;
import project.server.views.LibrarianMainFrame;
import project.utilities.RMI.GlobalRemoteMethods;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(3000);
            ServerController serverController = new ServerController();
            ServerUpdates servant = new ServerUpdates(serverController);

            GlobalRemoteMethods globalRemoteMethods = new GlobalRemoteServant(servant);
             Naming.bind("rmi://localhost:3000" + "/servermethods", globalRemoteMethods);

            serverController.setServerMethods();

            SwingUtilities.invokeLater(() -> {
                LibrarianMainFrame mainFrame = new LibrarianMainFrame(serverController);
                serverController.setServerMainView(mainFrame);
            });

            System.out.println("Server is running...");

        } catch (RuntimeException | RemoteException | AlreadyBoundException | MalformedURLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }


    }

}
