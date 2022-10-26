package qbnb;

public class App {
  public static final String PROJECT_PATH =
      System.getProperty("user.home") + "/work/CISC327-Project/CISC327-Project";
  public static final String TRANSACTION_PATH = "/db/TransactionDao.json";

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
  }

  public String getGreeting() {
    return "Hello World!";
  }
}
