package project.server.controller;

import project.utilities.utilityClasses.ClientActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerUpdates extends UnicastRemoteObject implements ServerUpdateReceiver {

    private ServerController serverController;

    public ServerUpdates(ServerController serverController) throws RemoteException {
        this.serverController = serverController;
    }


    @Override
    public void receiveUpdate(ClientActions clientActions) throws RemoteException {
        serverController.updateView(clientActions);
    }
}
