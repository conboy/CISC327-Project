package qbnb;

import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public class AppServerEndpoint {
  private Logger logger = Logger.getLogger(this.getClass().getName());      

  @OnOpen
  public void onOpen(Session session) {
    logger.info("Connected ... " + session.getId());
  }

  @OnMessage
  public String onMessage(String msg, Session session) {
    logger.info("Message ... " + msg);

    return "Server received: " + msg;
  }

  @OnClose
  public void onClose(Session session, CloseReason closeReason) {
    logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
  }
}
