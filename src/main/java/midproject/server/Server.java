package midproject.server;

import midproject.utilities.RMI.GlobalRemoteMethods;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(3000);

            GlobalRemoteMethods globalRemoteMethods = new GlobalRemoteServant();
             Naming.bind("rmi://localhost:3000" + "/servermethods", globalRemoteMethods);
            System.out.println("Server is running...");

        } catch (RuntimeException | RemoteException | AlreadyBoundException | MalformedURLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }


    }

}
