package project.utilities.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class AccountModel extends DataModel {

    private final String accountJSONfilePath = "src/main/resources/data/account.json";

    public static void main(String[] args) {
        AccountModel accountModel = new AccountModel();
        accountModel.addAccount(new Student(new Account("2232254", "Eugene123", "Eugene", "Patano", "2232254@slu.edu.ph", "12345678", false),

                0, new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
    }

    private static Account getAccount(JSONObject o) {
        String id = (String) o.get("id");
        String userName = (String) o.get("userName");
        String firstName = (String) o.get("firstName");
        String lastName = (String) o.get("lastName");
        String email = (String) o.get("email");
        String password = (String) o.get("password");
        boolean isBanned = (boolean) o.get("isBanned");
        return new Account(id, userName, firstName, lastName, email, password, isBanned);
    }

    public LinkedList<Account> getAccounts() {

        LinkedList<Account> accounts = new LinkedList<>();
        JSONParser parser = new JSONParser();

        try {

            JSONObject json = (JSONObject) parser.parse(new FileReader(accountJSONfilePath));
            JSONArray jsonArray = (JSONArray) json.get("accounts");

            for (Object o : jsonArray) {
                Account account = getAccount((JSONObject) o);
                accounts.add(account);
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    public void addAccount(Student account) {
        LinkedList<Student> accounts = getStudentAccounts();
        accounts.addFirst(account);

        saveStudentAccountData(accounts);

    }

    public void deleteAccount(Student student) {
        LinkedList<Student> accounts = getStudentAccounts();

        for (int i = 0; i < accounts.size(); i++) {

            if (accounts.get(i).getAccount().getAccountId().equals(student.getAccount().getAccountId())) {
                accounts.remove(i);
                saveStudentAccountData(accounts);
                break;
            }

        }


    }

    public void editAccount(Student student) {
        LinkedList<Student> accounts = getStudentAccounts();

        for (int i = 0; i < accounts.size(); i++) {

            if (accounts.get(i).getAccount().getAccountId().equals(student.getAccount().getAccountId())) {
                accounts.remove(i);
                accounts.add(i, student);
                saveStudentAccountData(accounts);
                break;
            }

        }


    }

    public LinkedList<Student> getStudentAccounts() {
        LinkedList<Student> studentAccounts = new LinkedList<>();

        LinkedList<Account> accounts = getAccounts();
        LinkedList<Book> books = getBooks();

        for (Account account : accounts) {
            LinkedList<Book> studentBorrowedBooks = new LinkedList<>();
            LinkedList<Book> studentPendingBooks = new LinkedList<>();
            LinkedList<Book> studentPendingBookReturns = new LinkedList<>();


            books.stream().filter(book -> {

                LinkedList<String> borrowers = book.getCurrentBorrowers();
                if (borrowers.isEmpty()) return false;

                for (String id : borrowers) {
                    if (id.equals(account.getAccountId())) return true;
                }

                return false;

            }).forEach(studentBorrowedBooks::add);

            books.stream().filter(book -> {

                LinkedList<String> pendingBorrowers = book.getPendingBorrowers();
                if (pendingBorrowers.isEmpty()) return false;

                for (String id : pendingBorrowers) {
                    if (id.equals(account.getAccountId())) return true;
                }

                return false;
            }).forEach(studentPendingBooks::add);

            books.stream().filter(book -> {

                LinkedList<String> pendingReturns = book.getPendingBookReturners();
                if (pendingReturns.isEmpty()) return false;

                for (String id : pendingReturns) {
                    if (id.equals(account.getAccountId())) return true;
                }

                return false;
            }).forEach(studentPendingBookReturns::add);

            studentAccounts.add(new Student(account, studentBorrowedBooks.size(), studentPendingBooks, studentBorrowedBooks, studentPendingBookReturns));
        }

        return studentAccounts;
    }

    public void saveStudentAccountData(LinkedList<Student> students) {
        JSONObject jsonObject = readJSON(accountJSONfilePath);
        JSONArray studentsArray = new JSONArray();

        for (Student account : students) {
            studentsArray.add(account.toJSON());
        }

        jsonObject.put("accounts", studentsArray);
        saveJSON(jsonObject, accountJSONfilePath);
    }


}
