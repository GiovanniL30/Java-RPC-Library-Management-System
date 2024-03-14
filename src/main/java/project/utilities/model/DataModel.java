package project.utilities.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Responsible for managing data related to accounts and books in a library system.
 * It provides features or the system and also handles operations.
 */
public class DataModel {

    /**
     * Retrieves text content from the specified JSON object by key.
     *
     * @param jsonObject the JSON object from which to retrieve the value
     * @param key        the key of the value to retrieve
     * @return the value associated with the specified key
     */
    protected String getValue(JSONObject jsonObject, String key) {
        return (String) jsonObject.get(key);
    }

    /**
     * Get a list of strings from a JSON object based on the provided key.
     *
     * @param  jsonObject  the JSON object to retrieve strings from
     * @param  key         the key to use for retrieving the strings
     * @return             a LinkedList of strings retrieved from the JSON object
     */
    protected LinkedList<String> getStrings(JSONObject jsonObject, String key) {
        LinkedList<String> values = new LinkedList<>();
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        if (jsonArray != null) {
            for (Object obj : jsonArray) {
                values.add((String) obj);
            }
        }
        return values;
    }

}
