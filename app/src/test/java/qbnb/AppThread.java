package qbnb;

/**
 * A helper class. Executes a thread on AppThread.start() which begins running App.main(), enabling
 * socket connection functionality.
 */
public class AppThread extends Thread {

  public AppThread() {}

  public void run() {
    String[] s = new String[1];
    App.main(s);
  }
}
