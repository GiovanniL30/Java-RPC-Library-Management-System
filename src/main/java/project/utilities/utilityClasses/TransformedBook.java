package project.utilities.utilityClasses;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Represents a book in transformed format.
 */
public class TransformedBook implements Serializable {

    private String bookId;
    private String bookTitle;
    private String author;
    private String genre;
    private String shortDescription;
    private String imagePath;
    private int copies;
    private LinkedList<JsonObject> currentBorrowers;
    private LinkedList<JsonObject> prevBookBorrowers;
    private LinkedList<JsonObject> pendingBorrowers;
    private LinkedList<JsonObject> pendingBookReturners;

    /**
     * Constructs a TransformedBook object with the specified parameters.
     */
    public TransformedBook(String bookId, String bookTitle, String author, String genre, String shortDescription, String imagePath, int copies, LinkedList<JsonObject> currentBorrowers, LinkedList<JsonObject> prevBookBorrowers, LinkedList<JsonObject> pendingBorrowers, LinkedList<JsonObject> pendingBookReturners) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
        this.shortDescription = shortDescription;
        this.imagePath = imagePath;
        this.copies = copies;
        this.currentBorrowers = currentBorrowers;
        this.prevBookBorrowers = prevBookBorrowers;
        this.pendingBorrowers = pendingBorrowers;
        this.pendingBookReturners = pendingBookReturners;
    }

    /**
     * Gets the unique identifier of the book.
     *
     * @return The unique identifier of the book.
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Sets the unique identifier of the book.
     *
     * @param bookId The unique identifier to set.
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * Sets the title of the book.
     *
     * @param bookTitle The title of the book to set.
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The author of the book to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the genre of the book.
     *
     * @return The genre of the book.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre The genre of the book to set.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the short description of the book.
     *
     * @return The short description of the book.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the short description of the book.
     *
     * @param shortDescription The short description of the book to set.
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets the file path of the book's cover image.
     *
     * @return The file path of the book's cover image.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the file path of the book's cover image.
     *
     * @param imagePath The file path of the book's cover image to set.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets the number of copies available for this book.
     *
     * @return The number of copies available for this book.
     */
    public int getCopies() {
        return copies;
    }

    /**
     * Sets the number of copies available for this book.
     *
     * @param copies The number of copies to set.
     */
    public void setCopies(int copies) {
        this.copies = copies;
    }

    /**
     * Gets a list of current borrowers of the book.
     *
     * @return A list of current borrowers of the book.
     */
    public LinkedList<JsonObject> getCurrentBorrowers() {
        return currentBorrowers;
    }

    /**
     * Sets the list of current borrowers of the book.
     *
     * @param currentBorrowers A list of current borrowers to set.
     */
    public void setCurrentBorrowers(LinkedList<JsonObject> currentBorrowers) {
        this.currentBorrowers = currentBorrowers;
    }

    /**
     * Gets a list of previous borrowers of the book.
     *
     * @return A list of previous borrowers of the book.
     */
    public LinkedList<JsonObject> getPrevBookBorrowers() {
        return prevBookBorrowers;
    }

    /**
     * Sets the list of previous borrowers of the book.
     *
     * @param prevBookBorrowers A list of previous borrowers to set.
     */
    public void setPrevBookBorrowers(LinkedList<JsonObject> prevBookBorrowers) {
        this.prevBookBorrowers = prevBookBorrowers;
    }

    /**
     * Gets a list of pending borrowers for the book.
     *
     * @return A list of pending borrowers for the book.
     */
    public LinkedList<JsonObject> getPendingBorrowers() {
        return pendingBorrowers;
    }

    /**
     * Sets the list of pending borrowers for the book.
     *
     * @param pendingBorrowers A list of pending borrowers to set.
     */
    public void setPendingBorrowers(LinkedList<JsonObject> pendingBorrowers) {
        this.pendingBorrowers = pendingBorrowers;
    }

    /**
     * Gets a list of pending book returners.
     *
     * @return A list of pending book returners.
     */
    public LinkedList<JsonObject> getPendingBookReturners() {
        return pendingBookReturners;
    }

    /**
     * Sets the list of pending book returners.
     *
     * @param pendingBookReturners A list of pending book returners to set.
     */
    public void setPendingBookReturners(LinkedList<JsonObject> pendingBookReturners) {
        this.pendingBookReturners = pendingBookReturners;
    }

    /**
     * Returns a string representation of the TransformedBook object.
     *
     * @return A string representation of the TransformedBook object.
     */
    @Override
    public String toString() {
        return "TransformedBook{" +
                "bookId='" + bookId + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", copies=" + copies +
                ", currentBorrowers=" + currentBorrowers +
                ", prevBookBorrowers=" + prevBookBorrowers +
                ", pendingBorrowers=" + pendingBorrowers +
                ", pendingBookReturners=" + pendingBookReturners +
                '}';
    }
}
