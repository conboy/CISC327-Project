package qbnb;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.logging.Logger;
import qbnb.models.User;
import qbnb.models.UserDao;

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
}
