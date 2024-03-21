package project.server.views.components.viewBookPanel;

import project.server.controller.ServerController;
import project.utilities.referenceClasses.Book;

import javax.swing.*;
import java.awt.*;

public class EditBookViewer extends JDialog {


    public EditBookViewer(Frame frame, Book book, ServerController controller) {
        super(frame, book.getBookTitle(), true);
    }

}
