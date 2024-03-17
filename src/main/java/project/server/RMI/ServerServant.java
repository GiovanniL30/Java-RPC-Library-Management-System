package project.server.RMI;

import project.server.controller.ServerController;
import project.utilities.utilityClasses.ClientActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerServant extends UnicastRemoteObject implements ServerRemoteMethods {

    private ServerController serverController;

    public ServerServant(ServerController serverController) throws RemoteException {
        this.serverController = serverController;
    }


    @Override
    public void receiveUpdate(ClientActions clientActions) throws RemoteException {

    }
}
