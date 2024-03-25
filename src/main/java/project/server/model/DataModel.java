package project.server.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.PrevBorrower;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.TransformedBook;
import project.utilities.utilityClasses.TransformedStudent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Responsible for managing data related to accounts and books in a library system.
 * It provides features or the system and also handles operations.
 */
public class DataModel {


    protected final String accountJSONfilePath = "src/main/resources/data/account.json";
    protected final String bookJSONPath = "src/main/resources/data/book.json";
    protected Gson gson = new GsonBuilder().setPrettyPrinting().create();


    protected synchronized void saveJSON(String json, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Successfully wrote JSON to file.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public synchronized LinkedList<Book> getBooks() {

        try (FileReader reader = new FileReader("src/main/resources/data/book.json")) {
            Type linkedListType = new TypeToken<LinkedList<TransformedBook>>() {}.getType();
            LinkedList<TransformedBook> transformedBooks = gson.fromJson(reader, linkedListType);

            LinkedList<Book> books = new LinkedList<>();
            LinkedList<Student> students = getStudentAccounts(books);

            for(TransformedBook transformedBook : transformedBooks) {

                LinkedList<String> currentBorrowers = transformedBook.getCurrentBorrowers().stream().map(current -> current.get("id").toString().replace("\"", "")).collect(Collectors.toCollection(LinkedList::new));
                LinkedList<PrevBorrower> prevBorrowers = new LinkedList<>();

                transformedBook.getPrevBookBorrowers().forEach(current -> {

                    for (Student student : students) {

                        System.out.println(student.getAccount().getAccountId());


                        if (student.getAccount().getAccountId().equals(current.get("student").toString().replace("\"", ""))) {
                            prevBorrowers.add(new PrevBorrower(current.get("returnDate").toString().replace("\"", ""), current.get("student").getAsString(), current.get("name").getAsString()));
                        }

                    }
                });

                LinkedList<String> pendingReturn = transformedBook.getPendingBookReturners().stream().map(current -> current.get("id").toString().replace("\"", "")).collect(Collectors.toCollection(LinkedList::new));
                LinkedList<String> pendingBorrow = transformedBook.getPendingBorrowers().stream().map(current -> current.get("id").toString().replace("\"", "")).collect(Collectors.toCollection(LinkedList::new));
                books.add(new Book(transformedBook.getBookId(), transformedBook.getBookTitle(), transformedBook.getAuthor(), transformedBook.getGenre(), transformedBook.getShortDescription(), transformedBook.getImagePath(), transformedBook.getCopies(), currentBorrowers, prevBorrowers, pendingBorrow, pendingReturn));
           }





            return books;
        } catch (IOException e) {
            return new LinkedList<>();
        }

    }

    public boolean addPending(String bookId, Student student) {

        LinkedList<Book> books = getBooks();

        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                book.setCopies(book.getCopies() - 1);
                book.getPendingBorrowers().add(student.getAccount().getAccountId());
                student.getPendingBooks().add(book);

                saveStudentAccountChanges(student);
                saveBookChanges(books);
                return true;
            }
        }

        return false;
    }

    public boolean removePending(String bookId, Student student) {

        LinkedList<Book> books = getBooks();


        for (Book book : books) {

            if (book.getBookId().equals(bookId)) {
                book.setCopies(book.getCopies() + 1);
                book.getPendingBorrowers().remove(student.getAccount().getAccountId());
                removeBookOnStudentPending(bookId, student);

                saveStudentAccountChanges(student);
                saveBookChanges(books);
                return true;
            }
        }

        return false;
    }

    private void removeBookOnStudentPending(String bookId, Student student) {
        for(int i = 0 ; i < student.getPendingBooks().size(); i++) {
            if(student.getPendingBooks().get(i).getBookId().equals(bookId)) {
                student.getPendingBooks().remove(i);
                break;
            }
        }
    }

    public boolean addBorrowed(String bookId, Student student) {

        LinkedList<Book> books = getBooks();

        for (Book book : books) {

            if (book.getBookId().equals(bookId)) {
                book.getCurrentBorrowers().add(student.getAccount().getAccountId());
                book.getPendingBorrowers().remove(student.getAccount().getAccountId());
                student.getBorrowedBooks().add(book);
                removeBookOnStudentPending(bookId, student);

                saveStudentAccountChanges(student);
                saveBookChanges(book);
                return true;
            }
        }

        return false;
    }


    public boolean removeBorrowed(String bookId, Student student, boolean isAdmin) {
        LinkedList<Book> books = getBooks();

        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {

                book.getCurrentBorrowers().remove(student.getAccount().getAccountId());

                if (isAdmin) {
                    book.getPrevBookBorrowers().add(new PrevBorrower(Date.valueOf(LocalDate.now()).toString(), student.getAccount().getAccountId(), student.getAccount().getFirstName() + " " + student.getAccount().getLastName()));
                } else {
                    book.getPendingBookReturners().add(student.getAccount().getAccountId());
                    student.getPendingReturnBook().add(book);
                }

                book.setCopies(book.getCopies() + 1);
                student.getBorrowedBooks().removeIf(b -> b.getBookId().equals(bookId));

                saveStudentAccountChanges(student);
                saveBookChanges(books);
                return true;
            }
        }

        return false;
    }

    public boolean retrivePendingReturnBook(String bookId, Student student) {
        LinkedList<Book> books = getBooks();

        for (Book book : books) {

            if (book.getBookId().equals(bookId)) {

                book.getPendingBookReturners().remove(student.getAccount().getAccountId());
                book.getPrevBookBorrowers().add(new PrevBorrower(Date.valueOf(LocalDate.now()).toString(), student.getAccount().getAccountId(), student.getAccount().getFirstName() + " " + student.getAccount().getLastName()));
                student.getPendingReturnBook().removeIf(b -> b.getBookId().equals(bookId));


                saveStudentAccountChanges(student);
                saveBookChanges(books);
                return true;
            }
        }

        return false;
    }

    public void saveStudentAccountChanges(Student student) {
        LinkedList<Student> accounts = getStudentAccounts(getBooks());

        for (int i = 0; i < accounts.size(); i++) {

            if (accounts.get(i).getAccount().getAccountId().equals(student.getAccount().getAccountId())) {
                accounts.remove(i);
                accounts.add(i, student);
                saveJSON(gson.toJson(transformStudent(accounts)), accountJSONfilePath);
                break;
            }

        }

    }

    public void saveStudentAccountChanges(LinkedList<Student> students) {
        saveJSON(gson.toJson(transformStudent(students)), accountJSONfilePath);
    }

    public void saveBookChanges(LinkedList<Book> books) {
        saveJSON(gson.toJson(transformedBooks(books)), bookJSONPath);
    }


    public boolean saveBookChanges(Book book) {

        LinkedList<Book> books = getBooks();

        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getBookId().equals(book.getBookId())) {
                books.remove(i);
                books.add(i, book);
                saveJSON(gson.toJson(transformedBooks(books)), bookJSONPath);
                return true;
            }

        }

        return false;
    }

    public LinkedList<Student> getStudentAccounts(LinkedList<Book> allBooks) {
        try (FileReader reader = new FileReader(accountJSONfilePath)) {
            Type linkedListType = new TypeToken<LinkedList<TransformedStudent>>() {}.getType();
            LinkedList<TransformedStudent> studentsFromJson = gson.fromJson(reader, linkedListType);


            LinkedList<Student> students = new LinkedList<>();

            studentsFromJson.forEach(transformedStudent -> {

                LinkedList<String> borrowed = transformedStudent.getBorrowedBooks().stream().map(current -> current.get("id").toString().replace("\"", "")).collect(Collectors.toCollection(LinkedList::new));
                LinkedList<String> pending = transformedStudent.getPendingBooks().stream().map(current -> current.get("id").toString().replace("\"", "")).collect(Collectors.toCollection(LinkedList::new));
                LinkedList<String> pendingReturn = transformedStudent.getPendingReturnBook().stream().map(current -> current.get("id").toString().replace("\"", "")).collect(Collectors.toCollection(LinkedList::new));


                LinkedList<Book> currentBorrowedBooks = new LinkedList<>();
                LinkedList<Book> pendingBooks = new LinkedList<>();
                LinkedList<Book> pendingReturnBooks = new LinkedList<>();



                allBooks.forEach(book -> {
                    for (String string : borrowed) {
                        if (book.getBookId().equals(string)) {
                            currentBorrowedBooks.add(book);
                        }
                    }
                });


                allBooks.forEach(book -> {

                    for (String string : pending) {
                        if (book.getBookId().equals(string)) {
                            pendingBooks.add(book);
                        }
                    }
                });

                allBooks.forEach(book -> {

                    for (String string : pendingReturn) {
                        if (book.getBookId().equals(string)) {
                            pendingReturnBooks.add(book);
                        }
                    }
                });

                students.add(new Student(new Account(transformedStudent.getAccountId(), transformedStudent.getUserName(), transformedStudent.getFirstName(), transformedStudent.getLastName(), transformedStudent.getEmail(), transformedStudent.getPassword(), transformedStudent.isBanned()), transformedStudent.getTotalBorrowedBooks(), pendingBooks, currentBorrowedBooks, pendingReturnBooks));
            });


            return students;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new LinkedList<>();
        }
    }


    protected LinkedList<TransformedBook> transformedBooks(LinkedList<Book> books) {

        LinkedList<TransformedBook> transformedBooks = new LinkedList<>();

        for (Book book : books) {

            LinkedList<JsonObject> pendingBorrowers = new LinkedList<>();
            LinkedList<JsonObject> currentBorrowers = new LinkedList<>();
            LinkedList<JsonObject> pendingBookReturner = new LinkedList<>();
            LinkedList<JsonObject> prevBorrowers = new LinkedList<>();

            book.getPendingBorrowers().forEach(student -> {
                JsonObject id = new JsonObject();
                id.add("id", gson.toJsonTree(student));
                pendingBorrowers.add(id);
            });

            book.getCurrentBorrowers().forEach(student -> {
                JsonObject id = new JsonObject();
                id.add("id", gson.toJsonTree(student));
                currentBorrowers.add(id);
            });

            book.getPendingBookReturners().forEach(student -> {
                JsonObject id = new JsonObject();
                id.add("id", gson.toJsonTree(student));
                pendingBookReturner.add(id);
            });

            book.getPrevBookBorrowers().forEach(prevBorrower -> {
                JsonObject prev = new JsonObject();
                prev.add("student", gson.toJsonTree(prevBorrower.getStudent()));
                prev.add("name", gson.toJsonTree(prevBorrower.getName()));
                prev.add("returnDate", gson.toJsonTree(prevBorrower.getReturnDate()));
                prevBorrowers.add(prev);
            });

            transformedBooks.add(new TransformedBook(book.getBookId(), book.getBookTitle(), book.getAuthor(), book.getGenre(), book.getShortDescription(), book.getImagePath(), book.getCopies(), currentBorrowers, prevBorrowers, pendingBorrowers, pendingBookReturner));

        }


        return transformedBooks;
    }

    protected LinkedList<TransformedStudent> transformStudent(LinkedList<Student> accounts) {
        LinkedList<TransformedStudent> transformed = new LinkedList<>();

        for (Student student : accounts) {
            Account studentAccount = student.getAccount();
            LinkedList<JsonObject> borrowed = new LinkedList<>();
            LinkedList<JsonObject> pending = new LinkedList<>();
            LinkedList<JsonObject> pendingReturn = new LinkedList<>();

            student.getBorrowedBooks().forEach(book -> {
                JsonObject id = new JsonObject();
                id.add("id", gson.toJsonTree(book.getBookId()));
                borrowed.add(id);
            });

            student.getPendingBooks().forEach(book -> {
                JsonObject id = new JsonObject();
                id.add("id", gson.toJsonTree(book.getBookId()));
                pending.add(id);
            });

            student.getPendingReturnBook().forEach(book -> {
                JsonObject id = new JsonObject();
                id.add("id", gson.toJsonTree(book.getBookId()));
                pendingReturn.add(id);
            });


            transformed.add(new TransformedStudent(studentAccount.getFirstName(), studentAccount.getUserName(), studentAccount.getFirstName(), studentAccount.getLastName(), studentAccount.getEmail(), studentAccount.getPassword(), studentAccount.getIsBanned(), student.getTotalBorrowedBooks(), pending, borrowed, pendingReturn));
        }
        return transformed;
    }


}
