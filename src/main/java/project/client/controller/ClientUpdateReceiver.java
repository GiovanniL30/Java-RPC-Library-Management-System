package project.client.controller;

import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientUpdateReceiver extends Remote {
   void receiveMessage(String message, Student sender) throws RemoteException;
   void receiveUpdate(ServerActions serverActions) throws RemoteException;
   void receiveBroadcast(ServerActions serverActions, String message) throws RemoteException;
}
