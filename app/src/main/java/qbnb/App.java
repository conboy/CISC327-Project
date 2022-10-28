package qbnb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.glassfish.tyrus.server.Server;

public class App {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
    runServer();
  }

  public static void runServer() {
    Server server = new Server("localhost", 8025, "/websockets", null, AppServerEndpoint.class);

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
}
