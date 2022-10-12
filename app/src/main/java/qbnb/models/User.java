package qbnb.models;

/** Is the parent and base for any user on the platform */
public class User {
  private int userID;
  private String username;
  private String password;
  private String email;

  // TEMPORARY CONSTRUCTOR - USED ONLY FOR LISTING IMPLEMENTATION PURPOSES
  public User(int ID, String user, String password, String email) {
    this.userID = ID;
    this.username = user;
    this.password = password;
    this.email = email;

    UserDao dao = new UserDao();
    dao.save(this);
  }

  // TEMPORARY GETTER - returns userID.
  public int getUserID() {
    return userID;
  }
}
