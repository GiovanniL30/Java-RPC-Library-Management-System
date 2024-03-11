package project.utilities.referenceClasses;

import java.io.Serializable;

public class Authentication implements Serializable {

    private String userName;
    private String password;

    public Authentication(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
