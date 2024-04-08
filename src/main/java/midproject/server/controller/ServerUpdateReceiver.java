package midproject.server.controller;

import midproject.utilities.utilityClasses.ClientActions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerUpdateReceiver extends Remote {

    void receiveUpdate(ClientActions clientActions) throws RemoteException;

}
