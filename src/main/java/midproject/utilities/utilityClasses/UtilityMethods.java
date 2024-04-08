package midproject.utilities.utilityClasses;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Contains utility methods for common tasks.
 */
public class UtilityMethods {

    /**
     * Validates an email address based on a specific pattern.
     *
     * @param emailAddress The email address to validate.
     * @return True if the email address is valid, false otherwise.
     */
    public static boolean validateEmail(String emailAddress) {

        String emailRegex = "[1-9]{7}@slu.edu.ph";
        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(emailAddress).matches();
    }

    /**
     * Checks if any of the given strings is null or empty.
     *
     * @param strings The strings to check.
     * @return True if any string is null or empty, false otherwise.
     */
    public static boolean haveNullOrEmpty(String... strings){
        for (String str : strings) {
            if (str == null || str.trim().replace(" ", "").isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a random unique ID using UUID.
     *
     * @return A randomly generated unique ID.
     */
    public static String generateRandomID(){
        return UUID.randomUUID().toString();
    }
}
