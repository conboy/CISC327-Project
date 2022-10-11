package qbnb.models;

/** Is the parent and base for any user on the platform */
public class User {
  private int userID;
  private String username;
  private String password;
  private String email;

<<<<<<< HEAD
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
=======
  // temporary constructor to help with CreateListingTests
  public User(int id, String n, String p, String e) {
    userID = id;
    username = n;
    password = p;
    email = e;
>>>>>>> 6bc926c1a65bac80755be60fa1b5e04651cc86d0
  }
}
