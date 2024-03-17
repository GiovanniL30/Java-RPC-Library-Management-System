package project.client.RMI;

import project.client.controller.ClientController;
import project.client.controller.ClientObserver;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public interface ClientRemoteMethods extends Remote {
   void receiveMessage(String message, Student sender) throws RemoteException;

   void receiveUpdate(ServerActions serverActions) throws RemoteException;

}
