package qbnb;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.logging.Logger;

@ServerEndpoint(value = "/game")
public class AppServerEndpoint {

  private Logger logger = Logger.getLogger(this.getClass().getName());

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
        return "email: " + " pass: ";
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
