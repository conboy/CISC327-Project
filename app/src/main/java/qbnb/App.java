package qbnb;

import java.io.*;
import java.net.*;
import javax.websocket.*;

public class App {
  public static final String PROJECT_PATH =
      System.getProperty("user.home") + "/work/CISC327-Project/CISC327-Project";
  public static final String TRANSACTION_PATH = "/db/TransactionDao.json";

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
    runServer();
  }

  public static void runServer() {
    Server server = new Server("localhost", 8025, "/websockets", AppServerEndpoint.class);

    try {
      server.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Please press a key to stop the server.");
      reader.readLine();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      server.stop();
    }
  }

  public String getGreeting() {
    return "Hello World!";
  }
}
