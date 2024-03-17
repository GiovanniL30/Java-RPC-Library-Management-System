package project.server;

import project.client.controller.ClientController;
import project.server.controller.ServerController;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.RMI.ServerRemoteMethods;
import project.utilities.model.AccountModel;
import project.utilities.model.BookModel;
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


    protected Server(){

        BookModel bookModel = new BookModel();
        AccountModel accountModel = new AccountModel();

        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            ClientRemoteMethods clientRemoteMethods = new ClientServant(bookModel, accountModel);
            registry.rebind("ClientRemote", clientRemoteMethods);

            ServerRemoteMethods serverRemoteMethods = new ServerServant(bookModel, accountModel);
            registry.rebind("ServerRemote", serverRemoteMethods);


            System.out.println("Server is running...");
        } catch (RuntimeException | RemoteException e) {
            throw  new RuntimeException(e);
        }

    }



    public static void main(String[] args) {
        new Server();
    }

}
