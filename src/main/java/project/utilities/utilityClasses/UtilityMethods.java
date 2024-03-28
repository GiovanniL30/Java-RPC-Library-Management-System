package project.utilities.utilityClasses;

import java.util.UUID;
import java.util.regex.Pattern;

public class UtilityMethods {

    public static boolean validateEmail(String emailAddress) {

        String emailRegex = "[1-9]{7}@slu.edu.ph";
        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(emailAddress).matches();
    }

    public static boolean haveNullOrEmpty(String... strings){
        for (String str : strings) {
            if (str == null || str.trim().replace(" ", "").isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static String generateRandomID(){
        return UUID.randomUUID().toString();
    }
}
