package project.utilities.referenceClasses;

import java.io.Serializable;

public class PrevBorrower implements Serializable {

    private String returnDate;
    private String student;
    private String name;

    public PrevBorrower(String returnDate, String student, String name) {
        this.returnDate = returnDate;
        this.student = student;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
