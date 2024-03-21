package project.server.controller;

import project.utilities.utilityClasses.ClientActions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerUpdateReceiver extends Remote {

    void receiveUpdate(ClientActions clientActions) throws RemoteException;

}
