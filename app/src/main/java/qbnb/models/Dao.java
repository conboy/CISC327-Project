package qbnb.models;

import com.google.gson.Gson;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * Allows the creation of consistent data access objects which act as a persistence layer for thier
 * respective domain object.
 */
public interface Dao<T> {

  Gson gson = new Gson();

  /** Convert string to path with correct foratting for system. */
  private static Path formatForSystem(String path) {
    String formattedPath = path.replaceAll("[\\\\/]", File.separator);
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
   * Save instance to file in JSON. @Param location the path where the file to be written to is
   * located. @Return if the file cannot be written to, false; else true.
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

  Optional<T> get(long id);

  List<T> getAll();

  /** add an object to the persistence layer. */
  void save(T t);

  /** update an existing object in the persistence layer. */
  void update(T t, String[] params);

  /** delete an object from the persistence layer. */
  void delete(T t);
}
