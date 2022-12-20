package qbnb.models.daos;

import com.google.gson.*;
import java.util.HashMap;
import java.util.Optional;
import qbnb.models.*;

/**
 * == TEMPORARY USERDAO IMPLEMENTATION == used only to help handle listing requirements full
 * implementation should be in features/327-24
 */
public class ReviewDao implements Dao<Review> {

  private static HashMap<Long, Review> reviews = new HashMap<Long, Review>();

  public boolean serialize(String reviewPath) {
    return write(reviewPath);
  }

  public static ReviewDao deserialize(String reviewPath) {
    String result = Dao.read(reviewPath);
    return gson.fromJson(result, ReviewDao.class);
  }

  @Override
  public Optional<Review> get(long id) {
    return Optional.ofNullable(reviews.get(id));
  }

  @Override
  public HashMap<Long, Review> getAll() {
    return reviews;
  }

  @Override
  public void save(Review review) {
    reviews.put(review.getId(), review);
  }

  @Override
  public void update(Review review, String[] params) {
    // TODO
  }

  @Override
  public void delete(Review review) {
    // TODO
  }

  /** Used to clear the DAO at the end of testing */
  public void deleteAll() {
    reviews = new HashMap<Long, Review>();
  }
}
