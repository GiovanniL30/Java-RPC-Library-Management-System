package project.client.controller;

import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientUpdates extends UnicastRemoteObject implements ClientUpdateReceiver, Serializable {

    private final ClientController clientController;

    public ClientUpdates(ClientController clientController) throws RemoteException {
        this.clientController = clientController;
    }


    @Override
    public void receiveMessage(String message, Student sender) {
        clientController.receiveMessage(message, sender);
    }

    @Override
    public void receiveUpdate(ServerActions serverActions) throws RemoteException {
        clientController.updateView(serverActions);
    }

    @Override
    public void receiveBroadcast(ServerActions serverActions, String message) throws RemoteException {
        clientController.receiveBroadcast(message);
    }


}
