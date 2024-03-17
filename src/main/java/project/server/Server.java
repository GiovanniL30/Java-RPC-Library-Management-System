package project.server;

import project.server.RMI.ServerServant;
import project.server.controller.ServerController;
import project.utilities.RMI.GlobalRemoteMethods;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            ServerController serverController = new ServerController();
            ServerServant servant = new ServerServant(serverController);

            GlobalRemoteMethods globalRemoteMethods = new GlobalRemoteServant(servant);
            registry.bind("server", globalRemoteMethods);


            serverController.setServerMethods();

            System.out.println("Server is running...");

        } catch (RuntimeException | RemoteException | AlreadyBoundException e) {
            throw  new RuntimeException(e);
        }





    }

}
