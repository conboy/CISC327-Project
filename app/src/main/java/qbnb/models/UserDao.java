package qbnb.models;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Public interface for program when dealing with users
 */
public class UserDao {

  private final Gson gson = new Gson();
  private HashMap<Long, User> users = new HashMap<Long, User>();

  /** 
   * Returns json copy of wanted user
   * @param id - id of user to be returned
   * @return String - json object of user
   */
  public String get(long id) {
    return gson.toJson(users.get(id));
  }
    
  /** 
   * Adds a user to the dao
   * 
   * @param data - user data to be added
   * @return Boolean - returns true if succeeds
   */
  public Boolean add(String data) {
    User user = gson.fromJson(data, User.class);
    this.users.put(user.getUserID(), user);
    return false;
  }

  /** 
   * Returns json copy of all users in dao
   * 
   * @return String - json file as a string
   */
  public String getAll() {
    return gson.toJson(users);
  }

  /** 
   * Saves all users in the dao
   * This will most likely happen at exit
   * 
   * @return Boolean - true if operation succeeds
   * @throws IOException
   */
  public Boolean save() throws IOException {
    FileWriter writer = new FileWriter("/db/users.json");
    try {
      writer.write(gson.toJson(users));
      writer.close();
    } catch (IOException e) {
      return false;
    }

    return true;
  }
  
  /** 
   * Updates a given user
   * 
   * @param id - id of user
   * @param data - json data to be used
   * @return Boolean
   */
  public Boolean update(Long id, String data) {
    if(this.users.get(id) != null) {
      this.users.put(id, gson.fromJson(data, User.class));
      return true;
    }

    return false;
  }
  
  /** 
   * Deletes a given user from the user list
   * 
   * @param id - id of user
   * @return Boolean - true if user is deleted
   */
  public Boolean delete(Long id) {
    if(this.users.get(id) != null) {
      this.users.remove(id);
    }

    return false;
  }
 
  /** 
   * deletes all users kept in dao
   * 
   * @return Boolean - true if operation succeeds
   */
  public Boolean deleteAll() {
    try {
      save();
    } catch (IOException e) {
      return false;
    }

    this.users = new HashMap<Long, User>();

    return true;    
  }

  /** 
   * Converts Json from a string to the working user hashmap data structure
   * 
   * @param data - data to be deserialized
   */
  public void deserialize(String data) {
    this.users = gson.fromJson(data, HashMap.class);
  }
}
