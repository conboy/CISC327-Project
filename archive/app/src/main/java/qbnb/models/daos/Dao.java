package qbnb.models.daos;

import com.google.gson.Gson;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;
import qbnb.AppConf;

/**
 * Allows the creation of consistent data access objects which act as a persistence layer for thier
 * respective domain object.
 */
public interface Dao<T> {

  Gson gson = new Gson();

  /** Convert string to path with correct foratting for system. */
  private static Path formatForSystem(String path) {
    String formattedPath;
    if (System.getProperty("os.name").contains("Windows")) {
      formattedPath = AppConf.WIN_PROJECT_PATH + path;
    } else {
      formattedPath = AppConf.PROJECT_PATH + path;
    }
    return Paths.get(formattedPath);
  }

  /**
   * Load JSON string of instance. @Param location the path where the json file to be read is
   * located. @Return if file cannot be read, null; else JSON string.
   */
  static String read(String location) {
    Path path = formatForSystem(location);
    try {
      return Files.readString(path);
    } catch (IOException ex) {
      return null;
    }
  }

  /**
   * Save instance to file in JSON. @Param location The project path (root of project is root) where
   * json may be saved. @Return if the file cannot be written to, false; else true.
   */
  default boolean write(String location) {
    Path path = formatForSystem(location);
    String json = gson.toJson(this);
    try {
      Files.writeString(path, json, StandardCharsets.UTF_8);
      return true;
    } catch (IOException ex) {
      return false;
    }
  }

  /**
   * Overloaded version of the usual write that allows for the use of custom Gson Serializers.
   * Necessary for the serializing of Dates.
   */
  default boolean write(String location, Gson customSerial) {
    Path path = formatForSystem(location);
    String json = customSerial.toJson(this);
    try {
      Files.writeString(path, json, StandardCharsets.UTF_8);
      return true;
    } catch (IOException ex) {
      return false;
    }
  }

  Optional<T> get(long id);

  HashMap<Long, T> getAll();

  /** add an object to the persistence layer. */
  void save(T t);

  /** update an existing object in the persistence layer. */
  void update(T t, String[] params);

  /** delete an object from the persistence layer. */
  void delete(T t);
}
