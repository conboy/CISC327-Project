package qbnb;

public class AppThread extends Thread {

  public AppThread() {}

  public void run() {
    String[] s = new String[1];
    App.main(s);
  }
}
