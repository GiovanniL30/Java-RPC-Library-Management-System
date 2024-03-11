package project.utilities.referenceClasses;

import java.util.LinkedList;

public class Book {

    private String bookId;
    private String bookTitle;
    private String author;
    private String[] genre;
    private String shortDescription;
    private String imagePath;
    private int copies;
    private LinkedList<Student> currentBorrowers;
    private LinkedList<Student> previousBorrowers;
    private LinkedList<Student> pendingBorrowers;
    private LinkedList<Student> pendingBookReturners;

    public Book(String bookId, String bookTitle, String author, String[] genre, String shortDescription, String imagePath, int copies, LinkedList<Student> currentBorrowers, LinkedList<Student> previousBorrowers, LinkedList<Student> pendingBorrowers, LinkedList<Student> pendingBookReturners) {
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

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
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

    public LinkedList<Student> getCurrentBorrowers() {
        return currentBorrowers;
    }

    public void setCurrentBorrowers(LinkedList<Student> currentBorrowers) {
        this.currentBorrowers = currentBorrowers;
    }

    public LinkedList<Student> getPreviousBorrowers() {
        return previousBorrowers;
    }

    public void setPreviousBorrowers(LinkedList<Student> previousBorrowers) {
        this.previousBorrowers = previousBorrowers;
    }

    public LinkedList<Student> getPendingBorrowers() {
        return pendingBorrowers;
    }

    public void setPendingBorrowers(LinkedList<Student> pendingBorrowers) {
        this.pendingBorrowers = pendingBorrowers;
    }

    public LinkedList<Student> getPendingBookReturners() {
        return pendingBookReturners;
    }

    public void setPendingBookReturners(LinkedList<Student> pendingBookReturners) {
        this.pendingBookReturners = pendingBookReturners;
    }
}
