package project.utilities.utilityClasses;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.LinkedList;

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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LinkedList<JsonObject> getCurrentBorrowers() {
        return currentBorrowers;
    }

    public void setCurrentBorrowers(LinkedList<JsonObject> currentBorrowers) {
        this.currentBorrowers = currentBorrowers;
    }

    public LinkedList<JsonObject> getPrevBookBorrowers() {
        return prevBookBorrowers;
    }

    public void setPrevBookBorrowers(LinkedList<JsonObject> prevBookBorrowers) {
        this.prevBookBorrowers = prevBookBorrowers;
    }

    public LinkedList<JsonObject> getPendingBorrowers() {
        return pendingBorrowers;
    }

    public void setPendingBorrowers(LinkedList<JsonObject> pendingBorrowers) {
        this.pendingBorrowers = pendingBorrowers;
    }

    public LinkedList<JsonObject> getPendingBookReturners() {
        return pendingBookReturners;
    }

    public void setPendingBookReturners(LinkedList<JsonObject> pendingBookReturners) {
        this.pendingBookReturners = pendingBookReturners;
    }

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
