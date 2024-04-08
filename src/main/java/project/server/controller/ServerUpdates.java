package project.server.controller;

import project.utilities.utilityClasses.ClientActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementations for the ServerUpdateReceiver
 */

public class ServerUpdates extends UnicastRemoteObject implements ServerUpdateReceiver {

    private ServerController serverController;

    public ServerUpdates(ServerController serverController) throws RemoteException {
        this.serverController = serverController;
    }

    /**
     * Updates the view for any changes or actions made by the admin
     */
    @Override
    public void receiveUpdate(ClientActions clientActions) throws RemoteException {
        serverController.updateView(clientActions);
    }
} // end of ServerUpdates class
