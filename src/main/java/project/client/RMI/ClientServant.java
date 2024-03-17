package project.client.RMI;

import project.client.controller.ClientController;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientServant extends UnicastRemoteObject implements ClientRemoteMethods, Serializable {

    private final ClientController clientController;


    public ClientServant(ClientController clientController) throws RemoteException {
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


}
