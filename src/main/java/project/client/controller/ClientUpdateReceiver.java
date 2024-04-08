package project.client.controller;

import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Class for receiving updates and notifications for clients
 */
public interface ClientUpdateReceiver extends Remote {
   /**
    * Handle receiving a message from a client
    * @param message message to be sent
    * @param sender who the message came from
    * @throws RemoteException
    */
   void receiveMessage(String message, Student sender) throws RemoteException;

   /**
    * Handles receiving updates from the server
    * @param serverActions actions that a server can do
    * @throws RemoteException
    */
   void receiveUpdate(ServerActions serverActions) throws RemoteException;

   /**
    * Handles receiving a broadcast message from the server
    * @param serverActions actions that a server can do
    * @param message message to be sent
    * @throws RemoteException
    */
   void receiveBroadcast(ServerActions serverActions, String message) throws RemoteException;
} // end of ClientUpdateReceiver
