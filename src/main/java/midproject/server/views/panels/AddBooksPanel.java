package midproject.server.views.panels;

import midproject.server.controller.ServerObserver;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Button;
import midproject.utilities.viewComponents.*;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.utilityClasses.ColorFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import static midproject.utilities.utilityClasses.UtilityMethods.generateRandomID;
import static midproject.utilities.utilityClasses.UtilityMethods.haveNullOrEmpty;

/**
 * The AddBooksPanel class represents the panel for adding new books to the library system.
 */

public class AddBooksPanel extends JPanel {
    // components
    private final Picture picture;
    private final JPanel imageArea = new JPanel();
    private final FieldInput bookTitle;
    private final FieldInput author;
    private final FieldInput shortDescription;
    private final FieldInput copies;
    private final DropDown dropDown;
    private final Button addBook;
    private final ServerObserver serverObserver;
    private String filePath = "src/main/resources/images/bookImages/imageNotAvailable.png";

    public AddBooksPanel(ServerObserver serverObserver) {
        this.serverObserver = serverObserver;
        setBackground(Color.white);

        setLayout(new GridLayout(1, 2, 50, 0));

        // Initialize components
        bookTitle = new FieldInput("Book Title", new Dimension(400, 50), 200, 1, false);
        author = new FieldInput("Author", new Dimension(400, 50), 200, 1, false);
        shortDescription = new FieldInput("Short Description", new Dimension(400, 50), 200, 1, false);
        copies = new FieldInput("Copies", new Dimension(400, 50), 200, 1, false);
        dropDown = new DropDown(new Dimension(400, 48), true, "Comedy", "Horror", "Fantasy", "Fiction", "Novel", "Sci-Fi",
                "Young", "Adult", "Historical", "Thriller", "Fantasy", "Science", "Romance", "Mystery");
        addBook = new Button("Add Book", 400, 50, FontFactory.newPoppinsDefault(20));
        addBook.setForeground(Color.white);
        addBook.setBackground(ColorFactory.blue());

        // Configure layout and settings

        JPanel contentArea = new JPanel();
        contentArea.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        contentArea.setBackground(Color.white);
        contentArea.add(bookTitle);
        contentArea.add(author);
        contentArea.add(shortDescription);
        contentArea.add(copies);
        contentArea.add(dropDown);
        contentArea.add(addBook);

        // Image Area settings
        Button button = new Button("Upload Image", 400, 50, FontFactory.newPoppinsDefault(13));
        picture = new Picture("", 400, 550);
        picture.setBackground(Color.white);
        imageArea.setBackground(Color.WHITE);
        imageArea.setPreferredSize(new Dimension(400, 800));
        imageArea.add(picture);
        imageArea.add(button);

        // Action listeners for upload image
        button.addActionListener(e -> {
            filePath = uploadImage();
            if (filePath.isEmpty()) return;
            picture.updatePicture(filePath);
            imageArea.revalidate();
            imageArea.repaint();
        });




        // Add components to panel
        add(imageArea);
        add(contentArea);
        createNewBook();
    }

    /**
     * Uploads an image using a file chooser dialog.
     *
     * @return the path of the saved image file
     */
    private String uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Choose Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));

        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return saveImage(selectedFile);
        }
        return "src/main/resources/images/bookImages/imageNotAvailable.png";
    } // end of uploadImage

    private String saveImage(File imageFile) {
        Path targetPath = null;
        try {
            Path sourcePath = imageFile.toPath();
            targetPath = Paths.get("src/main/resources/images/bookImages/", imageFile.getName());
            Files.copy(sourcePath, targetPath);
            JOptionPane.showMessageDialog(this, "Image saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return targetPath.toString();
    } // end of saveImage method

    private void createNewBook() {
        // action listener for add book
        addBook.addActionListener(e -> {
            // Retrieve entered information from fields
            String enteredBookTitle = bookTitle.getInput();
            String enteredAuthor = author.getInput();
            String enteredDescription = shortDescription.getInput();
            String enteredCopies = copies.getInput();

            //Check for empty or null inputs
            if (haveNullOrEmpty(enteredAuthor, enteredCopies, enteredBookTitle, enteredDescription)) {
                return;
            }

            int copies;
            try {
                // Parse the entered number of copies
                copies = Integer.parseInt(enteredCopies);

                // Validate if copies is a non-negative value
                if (copies < 0) {
                    this.copies.enableError("Negative value not allowed");
                    this.copies.getTextField().setText("");
                    return;
                }
            } catch (NumberFormatException ex) {
                // Display error message if the entered value is not a valid integer
                this.copies.enableError("Please enter a valid Integer value");
                return;
            }

            // Check if a genre is selected
            if (dropDown.dropDownChoice().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please choose a genre");
                return;
            }

            // check if an image is uploaded
            if (filePath.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Upload a Book Image Cover");
                return;
            }

            // Create a new book
            Book book = new Book(generateRandomID(),enteredBookTitle, enteredAuthor, dropDown.dropDownChoice(), enteredDescription, filePath, copies, new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
            // Call the server observer to add the book
            serverObserver.createNewBook(book);

            // Show confirmation message
            JOptionPane.showMessageDialog(this, "Book added");
            clearAll(); // Clear the text fields
            // reset file path to default image
            filePath = "src/main/resources/images/bookImages/imageNotAvailable.png";
            picture.updatePicture(filePath);

        });
    } // end of createNewBook method

    public void clearAll() {
        bookTitle.getTextField().setText("");
        author.getTextField().setText("");
        shortDescription.getTextField().setText("");
        copies.getTextField().setText("");
    } // end of clearAll method
} // end of AddBooksPanel class