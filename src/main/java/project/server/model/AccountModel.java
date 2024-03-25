package project.server.model;

import com.google.gson.reflect.TypeToken;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Student;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;


public class AccountModel extends DataModel {


    public static void main(String[] args) {
        AccountModel accountModel = new AccountModel();
//        accountModel.getAccounts();
        accountModel.addAccount(new Student(new Account("2232254212", "giovanni12345", "BANI", "LEO", "21234124", "555555", false), 0, new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
//
//        accountModel.getAccounts();

        accountModel.getStudentAccounts();

    }


    public LinkedList<Account> getAccounts() {
        try (FileReader reader = new FileReader(accountJSONfilePath)) {
            Type linkedListType = new TypeToken<LinkedList<Account>>() {
            }.getType();
            return gson.fromJson(reader, linkedListType);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new LinkedList<>();
        }
    }


    public void addAccount(Student account) {
        LinkedList<Student> accounts = getStudentAccounts();
        accounts.addFirst(account);
        saveStudentAccountChanges(accounts);
    }


    public void deleteAccount(Student student) {
        LinkedList<Student> accounts = getStudentAccounts();

        for (int i = 0; i < accounts.size(); i++) {

            if (accounts.get(i).getAccount().getAccountId().equals(student.getAccount().getAccountId())) {
                accounts.remove(i);
                saveStudentAccountChanges(accounts);
                break;
            }

        }
    }


}
