package project.server;

import project.client.controller.ClientController;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.RMI.ServerRemoteMethods;
import project.utilities.referenceClasses.Account;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static LinkedList<Account> loggedInAccounts;
    private static LinkedList<ClientController> clientControllers;

    protected Server(){

        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            ClientRemoteMethods clientRemoteMethods = new ClientServant();
            registry.rebind("ClientRemote", clientRemoteMethods);

            ServerRemoteMethods serverRemoteMethods = new ServerServant();
            registry.rebind("ServerRemote", serverRemoteMethods);

            System.out.println("Server is running...");
        } catch (RuntimeException | RemoteException e) {
            throw  new RuntimeException(e);
        }

    }

    public static void registerClient(ClientController client) {
        if (!clientControllers.contains(client)) {
            clientControllers.add(client);
        }
    }



    public static void unregisterClient(ClientController client) {
        if (clientControllers.contains(client)) {
            clientControllers.remove(client);
        }
    }

    protected static void addLoggedInAccount(Account account) {
        loggedInAccounts.removeIf(acc -> acc.equals(account));
        loggedInAccounts.add(account);
    }

    protected static void removeLoggedInAccount(Account account) {
        loggedInAccounts.removeIf(acc -> acc.equals(account));
    }
    public static void main(String[] args) {
        new Server();
    }

}
