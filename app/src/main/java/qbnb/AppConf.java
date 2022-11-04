package qbnb;

// FOR DEV PURPOSES
//
// change these paths based on your system setup.
//
// DO NOT ADD TO COMMITS
//
// the config on the main branch is setup to work on GitHub and cannot be changed.

public class AppConf {
  public static final String PROJECT_PATH =
      System.getProperty("user.home") + "/work/CISC327-Project/CISC327-Project";

  public static final String WIN_PROJECT_PATH = "C:/Users/" + System.getProperty("user.name") + "/CISC327-Project/";
  public static final String TRANSACTION_PATH = "/db/TransactionDao.json";
  public static final String TEST_PATH = "/db/test.json";
}
