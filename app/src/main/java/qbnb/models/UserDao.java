package qbnb.models;

import qbnb.models.*;
import models.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**  == TEMPORARY USERDAO IMPLEMENTATION ==
 *  used only to help handle listing requirements
 *  full implementation should be in features/327-24 */
public class UserDao implements Dao<User> {

    private static List<User> users = new ArrayList<User>();

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, String[] params) {
        //TODO
    }

    @Override
    public void delete(User user) {
        //TODO
    }
}
