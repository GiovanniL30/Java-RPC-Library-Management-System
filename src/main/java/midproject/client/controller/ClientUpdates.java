package midproject.client.controller;

import midproject.utilities.referenceClasses.*;
import midproject.utilities.utilityClasses.ServerActions;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation class of the Client Update Receiver
 */

public class ClientUpdates extends UnicastRemoteObject implements ClientUpdateReceiver, Serializable {
    private final ClientController clientController;

    /**
     * Constructor
     * @param clientController
     * @throws RemoteException
     */
    public ClientUpdates(ClientController clientController) throws RemoteException {
        this.clientController = clientController;
    } // end of ClientUpdates

    /**
     * Receive a message from another client
     * @param message message to be sent
     * @param sender who the message came from
     */
    @Override
    public void receiveMessage(String message, Student sender) {
        clientController.receiveMessage(message, sender);
    } // end of receiveMessage

    /**
     * Receives updates and notifications from a server
     * @param serverActions actions that a server can do
     * @throws RemoteException
     */
    @Override
    public void receiveUpdate(ServerActions serverActions) throws RemoteException {
        clientController.updateView(serverActions);
    } // end of receiveUpdate


    /**
     * Receive a broadcast message from the server
     * @param serverActions actions that a server can do
     * @param message message to be sent
     * @throws RemoteException
     */
    @Override
    public void receiveBroadcast(ServerActions serverActions, String message) throws RemoteException {
        clientController.receiveBroadcast(message);
    } // end of receiveBroadcast

    @Override
    public void getFile(String fileName, byte[] bytes) throws RemoteException {
        File file = new File(fileName);
        if(!file.exists()) {
            try ( FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("downloaded image");
        }else {
            System.out.println("image is already downloaded");
        }

    }
}
