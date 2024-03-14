package project.utilities.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.utilities.referenceClasses.Account;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class AccountModel {
    
    public LinkedList<Account> getAccounts() {
        
        LinkedList<Account> accounts = new LinkedList<>();
        JSONParser parser = new JSONParser();
        
        try {

            JSONObject json = (JSONObject) parser.parse(new FileReader("src/main/resources/data/account.json"));
            JSONArray jsonArray = (JSONArray) json.get("accounts");
            
            for(Object o: jsonArray) {

                Account account = getAccount((JSONObject) o);
                accounts.add(account);
                
            }
              
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    private static Account getAccount(JSONObject o) {
        String id = (String) o.get("id");
        String userName =  (String) o.get("userName");
        String firstName = (String) o.get("firstName");
        String lastName = (String) o.get("lastName");
        String password = (String) o.get("password");
        return new Account(id, userName, firstName ,lastName, password);
    }

}
