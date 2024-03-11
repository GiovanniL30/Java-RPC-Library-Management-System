package project.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servant extends UnicastRemoteObject {

    protected Servant() throws RemoteException {
        super();
    }

}
