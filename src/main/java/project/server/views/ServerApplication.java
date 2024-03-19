package project.server.views;
import project.server.controller.ServerController;
import javax.swing.*;
import project.server.views.LibrarianMainFrame;
public class ServerApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServerController serverController = new ServerController();
            LibrarianMainFrame mainFrame = new LibrarianMainFrame(serverController);
            mainFrame.setServerController(serverController);
            serverController.setMainView(mainFrame);
        });
    }
}
