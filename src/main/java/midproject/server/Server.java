package midproject.server;

import midproject.utilities.RMI.GlobalRemoteMethods;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Main class for starting the server.
 */

public class Server {
    /**
     * Main method to start the server.
     */
    public static void main(String[] args) {
        try {
            // Create and start the RMI registry on port 3000
            LocateRegistry.createRegistry(3000);
            // Create an instance of the server's remote methods implementation
            GlobalRemoteMethods globalRemoteMethods = new GlobalRemoteServant();
            // Bind the remote methods object to the RMI registry
            Naming.bind("rmi://localhost:3000" + "/servermethods", globalRemoteMethods);
            // Output a message indicating that the server is running
            System.out.println("Server is running...");
        } catch (RuntimeException | RemoteException | AlreadyBoundException | MalformedURLException e) {
            // Print any exceptions that occur during server startup
            System.err.println(e.getMessage());
            // Exit the server application
            System.exit(0);
        }
    } // End of main method
} // End of Server class
