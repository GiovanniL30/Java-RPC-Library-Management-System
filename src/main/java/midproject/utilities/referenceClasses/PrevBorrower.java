package midproject.utilities.referenceClasses;

import java.io.Serializable;

/**
 * Represents a previous borrower of a book.
 */
public class PrevBorrower implements Serializable {

    private String returnDate;
    private String student;
    private String name;

    /**
     * Constructs a PrevBorrower object with specified parameters.
     *
     * @param returnDate The date the book was returned.
     * @param student    The student ID of the borrower.
     * @param name       The name of the borrower.
     */
    public PrevBorrower(String returnDate, String student, String name) {
        this.returnDate = returnDate;
        this.student = student;
        this.name = name;
    }

    /**
     * Gets the name of the borrower.
     *
     * @return The name of the borrower.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the borrower.
     *
     * @param name The name of the borrower to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the return date of the book.
     *
     * @return The return date of the book.
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date of the book.
     *
     * @param returnDate The return date of the book to set.
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Gets the student ID of the borrower.
     *
     * @return The student ID of the borrower.
     */
    public String getStudent() {
        return student;
    }

    /**
     * Sets the student ID of the borrower.
     *
     * @param student The student ID of the borrower to set.
     */
    public void setStudent(String student) {
        this.student = student;
    }

    /**
     * Returns a string representation of the PrevBorrower object.
     *
     * @return A string representation of the PrevBorrower object.
     */
    @Override
    public String toString() {
        return "PrevBorrower{" +
                "returnDate='" + returnDate + '\'' +
                ", student=" + student +
                '}';
    }
}
