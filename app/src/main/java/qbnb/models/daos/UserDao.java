package qbnb.models.daos;

import com.google.gson.*;
import java.util.HashMap;
import java.util.Optional;
import qbnb.models.*;

/** Presistence layer for User instances. */
public class UserDao implements Dao<User> {

  private static HashMap<Long, User> users = new HashMap<Long, User>();

  public boolean serialize(String userPath) {
    return write(userPath);
  }

  public static UserDao deserialize(String userPath) {
    String result = Dao.read(userPath);
    UserDao dao = gson.fromJson(result, UserDao.class);
    if (dao == null) return new UserDao();
    else return dao;
  }

  @Override
  public Optional<User> get(long id) {
    return Optional.ofNullable(users.get(id));
  }

  @Override
  public HashMap<Long, User> getAll() {
    return users;
  }

  @Override
  public void save(User user) {
    users.put(user.getUserID(), user);
  }

  @Override
  public void update(User user, String[] params) {
    // TODO
  }

  @Override
  public void delete(User user) {
    // TODO
  }

  /** Used to clear the DAO at the end of testing */
  public void deleteAll() {
    users = new HashMap<Long, User>();
  }
}
