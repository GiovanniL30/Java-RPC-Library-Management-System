package midproject.server;

import midproject.server.controller.ServerController;
import midproject.server.controller.ServerUpdates;
import midproject.server.views.LibrarianMainFrame;

import javax.swing.*;
import java.rmi.RemoteException;

/**
 * Entry point for the administrator application.
 */

public class AdminApplication {
    /**
     * Main method
     */
    public static void main(String[] args) {
        try {
            // Create a new instance of the server controller
            ServerController serverController = new ServerController();
            // Create a new instance of the server updates
            ServerUpdates servant = new ServerUpdates(serverController);
            // Set the server methods
            serverController.setServerMethods(servant);

            // Create and display the main frame on the event dispatch thread
            SwingUtilities.invokeLater(() -> {
                LibrarianMainFrame mainFrame = new LibrarianMainFrame(serverController);
                serverController.setServerMainView(mainFrame);
            });
        } catch (RemoteException e) {
            // If an exception occurs, throw a runtime exception
            throw new RuntimeException(e);
        }
    } // end of main method
} // end of class
