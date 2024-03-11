package project.server;

import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.referenceClasses.Account;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private LinkedList<Account> loggedInAccounts;


    protected Server(){

        try {

            ClientRemoteMethods clientRemoteMethods = new ClientServant();
            LocateRegistry.createRegistry(1099).bind("client", clientRemoteMethods);

            System.out.println("Server is running...");
        } catch (RuntimeException | AlreadyBoundException | RemoteException e) {
            throw  new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        new Server();
    }

}
