package qbnb;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import qbnb.models.Listing;
import qbnb.models.User;
import qbnb.models.daos.ListingDao;
import qbnb.models.daos.UserDao;

@ServerEndpoint(value = "/game")
public class AppServerEndpoint {

  private Logger logger = Logger.getLogger(this.getClass().getName());
  private static User loggedInUser = null;
  UserDao userDao = new UserDao();

  @OnOpen
  public void onOpen(Session session) {
    logger.info("Connected ... " + session.getId());
  }

  @OnMessage
  public String onMessage(String msg, Session session) {

    ListingDao lDao = ListingDao.deserialize();
    logger.info("Message ... " + msg);
    String msgType = getMsgType(msg);
    String arr[] = msg.split(":");

    switch (msgType) {
      case "updateUserProfile":
        String id = arr[1];
        String newName = arr[2];
        String newMail = arr[3];
        String newAddress = arr[4];
        String newPostalCode = arr[5];
        putUpdateProfile(id, newName, newMail, newAddress, newPostalCode);

      case "register":
        // TODO: What happens when register is called
        // Creates user object and shows user what they typed in text fields in alert dialog on web
        // app
        String email = arr[1];
        String username = arr[2];
        String password = arr[3];
        try {
          User user = new User(email, username, password, false);
          userDao.save(user);
        } catch (Exception e) {
          return "Unable to create user";
        }

        return "Created new user\nemail: "
            + email
            + "\nusername: "
            + username
            + "\npass: "
            + password;
      case "login":
        email = arr[1];
        password = arr[2];
        boolean loggedIn = false;
        try {
          List<User> users = new ArrayList<User>(userDao.getAll().values());
          for (User user : users) {
            loggedIn = user.Login(email, password);
            if (loggedIn == true) {
              loggedInUser = user;
              return "Logged in successfully";
            }
          }
          return "Wrong log in";
        } catch (Exception e) {
          return "Failed";
        }
        // TODO: implement an ID generating algorithm that isn't as insecure as this one
      case "create_listing":
        // basic log-in system implemented to help with listing tests - replace if necessary!
        // Note: CreateListingWebTests requires a way to login as a non-registered user for
        // exhaustive testing purposes.
        long ownerID;
        if (loggedInUser == null) ownerID = 404;
        else ownerID = loggedInUser.getUserID();

        Listing l;
        try {
          l =
              new Listing(
                  (long) ((lDao.getAll().size()) + 1),
                  arr[1],
                  arr[2],
                  Double.parseDouble(arr[3]),
                  LocalDate.now(),
                  ownerID);
          return "Listing saved successfully!";
        } catch (Exception e) {
          return "Error occurred and listing was not saved.\nError: " + e.getMessage();
        }
      case "update_listing":
        long owner;
        if (loggedInUser == null) owner = 404;
        else owner = loggedInUser.getUserID();
        try {
          boolean x = lDao.update(owner, arr[1], Arrays.copyOfRange(arr, 2, arr.length));
          if (!x) throw new NullPointerException();
          return "Listing updated successfully!";
        } catch (Exception e) {
          return "Error: " + e.getMessage();
        }
      default:
        return "Failed";
    }
  }

  @OnClose
  public void onClose(Session session, CloseReason closeReason) {
    logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
  }

  // Gets the desired action from the message sent from websocket
  public String getMsgType(String msg) {
    String[] msgArray = msg.split(":");
    return msgArray[0];
  }

  public String putUpdateProfile(
      String strId, String newName, String newMail, String newAddress, String newPostalCode) {
    Long id;
    try {
      id = Long.parseLong(strId);
    } catch (NumberFormatException e) {
      return "Oops ! bad userID";
    }
    User updatedUser = userDao.get(id).get();
    if (updatedUser.update(newName, newMail, newAddress, newPostalCode)) {
      userDao.save(updatedUser);
      return "User profile updated successfully";
    } else {
      return "Unable to update user profile";
    }
  }

  // sets the current user logged in to the server - TESTING ONLY
  public static void setLoggedInUser(User user) {
    loggedInUser = user;
  }
}
