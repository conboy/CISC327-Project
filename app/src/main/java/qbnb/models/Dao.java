package qbnb.models;

import com.google.gson.Gson;
import java.util.List;
import java.util.Optional;

/**
 * Allows the creation of consistent data access objects which act as a persistence layer for thier
 * respective domain object.
 */
public interface Dao<T> {
  Gson gson = new Gson(); // JSON Parser and converter

  /** serialize the DAO to JSON. */
  default String serialize() {
        return gson.toJson(this);
  }

  /** deserialize JSON to a Dao */
  Dao<T> deserialize(String json);
  
  Optional<T> get(long id);

  List<T> getAll();

  /** add an object to the persistence layer. */
  void save(T t);

  /** update an existing object in the persistence layer. */
  void update(T t, String[] params);

  /** delete an object from the persistence layer. */
  void delete(T t);
}
