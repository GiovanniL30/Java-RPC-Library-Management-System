package midproject.server.model;

import com.google.gson.reflect.TypeToken;
import midproject.utilities.referenceClasses.Account;
import midproject.utilities.referenceClasses.Student;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * This class is the logic for any account-related actions
 */

public class AccountModel extends DataModel {

    /**
     * Retrieve the list of all accounts from JSON
     */
    public LinkedList<Account> getAccounts() {
        try (FileReader reader = new FileReader(accountJSONfilePath)) {
            Type linkedListType = new TypeToken<LinkedList<Account>>() {}.getType();
            return gson.fromJson(reader, linkedListType);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new LinkedList<>();
        }
    } // end f getAccounts method

    /**
     * Action for adding the newly created account to the list of users accounts
     */
    public void addAccount(Student account) {
        LinkedList<Student> accounts = getStudentAccounts(getBooks());
        accounts.addFirst(account);
        saveStudentAccountChanges(accounts);
    } // end of addAccount method

    /**
     * Action for deleting an existing account
     */
    public void deleteAccount(Student student) {
        LinkedList<Student> accounts = getStudentAccounts(getBooks());
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccount().getAccountId().equals(student.getAccount().getAccountId())) {
                accounts.remove(i);
                saveStudentAccountChanges(accounts);
                break;
            }
        }
    } // end of deleteAccount method
} // end of AccountModel class
