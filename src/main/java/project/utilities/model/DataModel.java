package project.utilities.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * Responsible for managing data related to accounts and books in a library system.
 * It provides features or the system and also handles operations.
 */
public class DataModel {

    /**
     * A method to check if the given JSONObject is empty.
     *
     * @param jsonObject the JSONObject to be checked
     * @return true if the JSONObject is empty, false otherwise
     */
    protected boolean isEmpty(JSONObject jsonObject) {
        return jsonObject.isEmpty();
    }

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

    /**
     * Method for saving a JSON file
     *
     * @param jsonObject the JSON object
     * @param filePath   the path of the file
     * @return true if the file is saved successfully, false otherwise
     */
    protected synchronized boolean saveJSON(JSONObject jsonObject, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * A method for reading a JSON file
     *
     * @param filePath the path of the file
     * @return the JSON object
     */
    protected synchronized JSONObject readJSON(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            return (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method for creating a JSON file
     *
     * @param filePath the path of the file
     * @param root     the root object
     */
    private void createJSONFile(String filePath, JSONObject root) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(root.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addPending(String bookId, Student studentId){
        return pending(bookId, studentId.getAccount().getAccountId(), "src/main/resources/data/book.json", true, false) &&
        pending(bookId, studentId.getAccount().getAccountId(), "src/main/resources/data/account.json", false, false);
    }

    public boolean removePending(String bookId, Student studentId){
        return pending(bookId, studentId.getAccount().getAccountId(), "src/main/resources/data/book.json", true, pending(bookId, studentId.getAccount().getAccountId(), "src/main/resources/data/account.json", false, true));
    }

    public boolean addBorrowed(String bookId, Student studentId){
        return removePending(bookId, studentId) &&
        borrow(bookId, studentId,"src/main/resources/data/book.json", true, false, false) &&
        borrow(bookId, studentId,"src/main/resources/data/account.json", false, false, false);
    }

    public boolean removeBorrowed(String bookId, Student studentId, boolean isClient){
      return borrow(bookId, studentId,"src/main/resources/data/book.json", true, true, isClient) &&
       borrow(bookId, studentId,"src/main/resources/data/account.json", false, true, isClient);


    }


    private boolean pending(String bookId, String studentId, String filePath, boolean isBookTarget, boolean isRemove) {

        JSONObject json = readJSON(filePath);
        JSONArray jsonArray = (JSONArray) json.get((isBookTarget ? "book": "accounts"));

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            if (jsonObject.get((isBookTarget ?  "bookId" : "id")).equals((isBookTarget ?  bookId : studentId))) {

                if(isBookTarget) {
                    long copies = (long) jsonObject.get("copies") - 1;
                    jsonObject.put("copies", copies);
                }

                JSONArray pendings = (JSONArray) jsonObject.get((isBookTarget ? "pendingBorrowers": "pendingBooks"));

                if(isRemove) {

                    for(Object o : pendings) {
                        JSONObject object = (JSONObject) o;
                        if (object.get("id").equals(isBookTarget ? studentId : bookId)) {
                            pendings.remove(object);
                            break;
                        }
                    }

                } else {
                    JSONObject newId = new JSONObject();
                    newId.put("id", (isBookTarget? studentId : bookId));
                    pendings.add(newId);
                }



                JSONArray updatedJsonArray = new JSONArray();
                for (int j = 0; j < jsonArray.size(); j++) {
                    if (j == i) {
                        updatedJsonArray.add(jsonObject);
                    } else {
                        updatedJsonArray.add(jsonArray.get(j));
                    }
                }

                json.put((isBookTarget ? "book": "accounts"), updatedJsonArray);
                return saveJSON(json, filePath);
            }
        }

        return false;
    }


    private boolean borrow(String bookId, Student studentId, String filePath, boolean isBookTarget, boolean isRemove , boolean isClient) {

        JSONObject json = readJSON(filePath);
        JSONArray jsonArray = (JSONArray) json.get((isBookTarget ? "book": "accounts"));

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            if (jsonObject.get((isBookTarget ?  "bookId" : "id")).equals((isBookTarget ?  bookId : studentId.getAccount().getAccountId()))) {

                JSONArray borrowers = (JSONArray) jsonObject.get((isBookTarget ? "currentBorrowers": "borrowedBooks"));

                if(isRemove) {

                    JSONArray prevBookBorrowers = null;

                    if(isBookTarget) {

                        if(!isClient){
                            prevBookBorrowers = (JSONArray) jsonObject.get("prevBookBorrowers");
                        }else {
                            prevBookBorrowers = (JSONArray) jsonObject.get("pendingBookReturners");
                        }

                    }


                    for(Object o : borrowers) {
                        JSONObject object = (JSONObject) o;
                        if (object.get("id").equals(isBookTarget ? studentId.getAccount().getAccountId() : bookId)) {

                            if(prevBookBorrowers != null) {

                                if(!isClient){
                                    JSONObject prevBorrower = new JSONObject();
                                    prevBorrower.put("id", object.get("id"));
                                    prevBorrower.put("dateRetrieved", Calendar.getInstance().getTime().toString());
                                    prevBookBorrowers.add(prevBorrower);
                                    //TODO: add more
                                }else {
                                    JSONObject prevBorrower = new JSONObject();
                                    prevBorrower.put("id", object.get("id"));
                                    prevBookBorrowers.add(prevBorrower);
                                }

                            }

                            borrowers.remove(object);
                            break;
                        }
                    }

                } else {
                    JSONObject newId = new JSONObject();
                    newId.put("id", (isBookTarget? studentId.getAccount().getAccountId() : bookId));
                    borrowers.add(newId);
                }

                JSONArray updatedJsonArray = new JSONArray();
                for (int j = 0; j < jsonArray.size(); j++) {
                    if (j == i) {
                        updatedJsonArray.add(jsonObject);
                    } else {
                        updatedJsonArray.add(jsonArray.get(j));
                    }
                }

                json.put((isBookTarget ? "book": "accounts"), updatedJsonArray);
                return saveJSON(json, filePath);
            }
        }

        return false;
    }


}
