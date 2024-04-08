package midproject.utilities.referenceClasses;

import java.io.Serializable;

public class Account implements Serializable {

    private String accountId; //String variable to store Account ID
    private String userName; //String variable to store Username
    private String firstName; //String variable to store First Name
    private String lastName; //String variable to store Last Name
    private String email; //String variable to store Email
    private String password; //String variable to store Password
    private boolean isBanned; //Boolean variable to store Is banned

    /**
     * Constructor to initialize the Account object with the provided parametes
     * @param accountId
     * @param userName
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param isBanned
     */
    public Account(String accountId, String userName, String firstName, String lastName, String email, String password, boolean isBanned) {
        this.accountId = accountId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isBanned = isBanned;
    }

    /**
     * Getter method to retrieve account ID
     * @return
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Setter method to set account ID
     * @param accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Getter method to retrieve username
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method to set username
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method to retrieve first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method to set first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method to retrieve last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method to set last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method to retrieve password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method to set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to convert Account object to string representation
     * @return
     */
    @Override
    public String toString() {
        return "Account{" + "accountId='" + accountId + '\'' + ", userName='" + userName + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", isBanned='" + isBanned + '\'' + '}';
    }

    /**
     * Getter method to retrieve email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method to set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method to retrieve banned status
     * @return
     */
    public boolean getIsBanned() {
        return isBanned;
    }

    /**
     * Setter method to set banned status
     * @param isBanned
     */
    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }
}
