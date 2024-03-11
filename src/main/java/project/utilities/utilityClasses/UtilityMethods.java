package project.utilities.utilityClasses;

import java.util.regex.Pattern;

public class UtilityMethods {

    private static boolean validateEmail(String emailAddress) {

        String emailRegex = "[1-9]{7}@slu.edu.ph";
        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(emailAddress).matches();
    }

}
