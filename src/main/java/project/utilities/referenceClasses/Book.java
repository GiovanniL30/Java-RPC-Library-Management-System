package project.utilities.referenceClasses;

import java.io.Serializable;
import java.util.LinkedList;

public class Book implements Serializable {

    private String bookId;
    private String bookTitle;
    private String author;
    private String genre;
    private String shortDescription;
    private String imagePath;
    private int copies;
    private LinkedList<String> currentBorrowers;
    private LinkedList<String> previousBorrowers;
    private LinkedList<String> pendingBorrowers;
    private LinkedList<String> pendingBookReturners;

    public Book(String bookId, String bookTitle, String author, String genre, String shortDescription, String imagePath, int copies, LinkedList<String> currentBorrowers, LinkedList<String> previousBorrowers, LinkedList<String> pendingBorrowers, LinkedList<String> pendingBookReturners) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
        this.shortDescription = shortDescription;
        this.imagePath = imagePath;
        this.copies = copies;
        this.currentBorrowers = currentBorrowers;
        this.previousBorrowers = previousBorrowers;
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

    public LinkedList<String> getCurrentBorrowers() {
        return currentBorrowers;
    }

    public void setCurrentBorrowers(LinkedList<String> currentBorrowers) {
        this.currentBorrowers = currentBorrowers;
    }

    public LinkedList<String> getPreviousBorrowers() {
        return previousBorrowers;
    }

    public void setPreviousBorrowers(LinkedList<String> previousBorrowers) {
        this.previousBorrowers = previousBorrowers;
    }

    public LinkedList<String> getPendingBorrowers() {
        return pendingBorrowers;
    }

    public void setPendingBorrowers(LinkedList<String> pendingBorrowers) {
        this.pendingBorrowers = pendingBorrowers;
    }

    public LinkedList<String> getPendingBookReturners() {
        return pendingBookReturners;
    }

    public void setPendingBookReturners(LinkedList<String> pendingBookReturners) {
        this.pendingBookReturners = pendingBookReturners;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", copies=" + copies +
                ", currentBorrowers=" + currentBorrowers +
                ", previousBorrowers=" + previousBorrowers +
                ", pendingBorrowers=" + pendingBorrowers +
                ", pendingBookReturners=" + pendingBookReturners +
                '}';
    }
}
