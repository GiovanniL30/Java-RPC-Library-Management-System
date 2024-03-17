package project.server;

import project.server.controller.ServerController;
import project.utilities.RMI.ServerRemoteMethods;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerApplication {

    public static void main(String[] args) {

        ServerController serverController = new ServerController();

    }

}
