package midproject.server;

import midproject.server.controller.ServerController;
import midproject.server.controller.ServerUpdates;
import midproject.server.views.LibrarianMainFrame;

import javax.swing.*;
import java.rmi.RemoteException;

public class AdminApplication {

    public static void main(String[] args) {

        try {

            ServerController serverController = new ServerController();
            ServerUpdates servant = new ServerUpdates(serverController);
            serverController.setServerMethods(servant);

            SwingUtilities.invokeLater(() -> {
                LibrarianMainFrame mainFrame = new LibrarianMainFrame(serverController);
                serverController.setServerMainView(mainFrame);
            });

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
