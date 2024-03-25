package project.server.model;


import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;

import java.util.LinkedList;

public class BookModel extends DataModel {


    public void addBook(Book book) {
        LinkedList<Book> books = getBooks();
        books.addFirst(book);
        saveBookChanges(books);
    }


    public void deleteBook(Book book) {
        LinkedList<Book> books = getBooks();



        for (Book currentBook : books) {

            if (currentBook.getBookId().equals(book.getBookId())) {
                books.remove(currentBook);

                LinkedList<Student> students = getStudentAccounts(books);

                for(Student student : students) {
                    student.getPendingReturnBook().removeIf(b -> b.getBookId().equals(book.getBookId()));
                    student.getBorrowedBooks().removeIf(b -> b.getBookId().equals(book.getBookId()));
                    student.getPendingBooks().removeIf(b -> b.getBookId().equals(book.getBookId()));
                    student.setTotalBorrowedBooks(student.getBorrowedBooks().size());
                }

                saveStudentAccountChanges(students);
                saveBookChanges(books);
                break;
            }

        }

    }

    public LinkedList<Book> getBooksWithCurrentBorrowers() {
        LinkedList<Book> currentBorrowedBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getCurrentBorrowers().isEmpty()) {
                    currentBorrowedBooks.add(book);
                }
            }
        }
        return currentBorrowedBooks;
    }

    public LinkedList<Book> getBooksWithPendingBorrowers() {
        LinkedList<Book> pendingBorrowedBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getPendingBorrowers().isEmpty()) {
                    pendingBorrowedBooks.add(book);
                }
            }
        }
        return pendingBorrowedBooks;
    }

    public LinkedList<Book> getBooksWithPreviousBorrowers() {
        LinkedList<Book> previousBorrowedBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getPrevBookBorrowers().isEmpty()) {
                    previousBorrowedBooks.add(book);
                }
            }
        }
        // Return the list of Unavailable Books
        return previousBorrowedBooks;
    }

    public LinkedList<Book> getBooksWithPendingBookReturners() {
        LinkedList<Book> pendingBookReturners = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getPendingBookReturners().isEmpty()) {
                    pendingBookReturners.add(book);
                }
            }
        }
        return pendingBookReturners;
    }

    public LinkedList<Book> getAvailableBooks() {
        LinkedList<Book> availableBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getCopies() > 0) {
                    availableBooks.add(book);
                }
            }
        }
        // Return the list of Unavailable Books
        return availableBooks;
    }

    public LinkedList<Book> getUnavailableBooks() {
        LinkedList<Book> unavailableBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getCopies() == 0) {
                    unavailableBooks.add(book);
                }
            }
        }
        // Return the list of Unavailable Books
        return unavailableBooks;
    }
}
