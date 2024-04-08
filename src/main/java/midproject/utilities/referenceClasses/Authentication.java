package midproject.utilities.referenceClasses;

import java.io.Serializable;

public class Authentication implements Serializable {

    private String userName; //String variable to store Username
    private String password; //String variable to store password

    /**
     * Constructor to initialize the Authentication object with the provided parameters
     * @param userName
     * @param password
     */
    public Authentication(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Getter method to retrieve username
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter method to retrieve password
     * @return
     */
    public String getPassword() {
        return password;
    }
}
