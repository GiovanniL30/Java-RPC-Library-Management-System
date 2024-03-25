package project.utilities.referenceClasses;

import java.io.Serializable;

public class PrevBorrower implements Serializable {

    private String returnDate;
    private String student;

    public PrevBorrower(String returnDate, String student) {
        this.returnDate = returnDate;
        this.student = student;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "PrevBorrower{" +
                "returnDate='" + returnDate + '\'' +
                ", student=" + student +
                '}';
    }
}
