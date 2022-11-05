package qbnb;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import qbnb.models.User;
import qbnb.models.daos.UserDao;

@ServerEndpoint(value = "/game")
public class AppServerEndpoint {

  private Logger logger = Logger.getLogger(this.getClass().getName());
  UserDao userDao = new UserDao();

  @OnOpen
  public void onOpen(Session session) {
    logger.info("Connected ... " + session.getId());
  }

  @OnMessage
  public String onMessage(String msg, Session session) {

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
              return "Logged in successfully";
            }
          }
          return "Wrong log in";
        } catch (Exception e) {
          return "Failed";
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
}
