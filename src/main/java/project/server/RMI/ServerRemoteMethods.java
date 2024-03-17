package project.server.RMI;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface ServerRemoteMethods extends Remote {

    void receiveUpdate(ClientActions clientActions) throws RemoteException;

}
