package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.PROJECT_PATH;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebRegisterTest {
  //  WebDriver driver;
  public String path;
  public boolean isLogin = false;
  //  boolean isLogin = false;
  //  public String baseURL;
  //
  //
  //  @BeforeEach
  //  void setupTest() {
  //    AppThread thread = new AppThread();
  //    thread.start();
  //    String osCheck = System.getProperty("os.name").split(" ")[0];
  //    if (osCheck.equals("Mac")) {
  //      path = PROJECT_PATH;
  //      System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
  //      baseURL = "file://" + path + "/app/src/main/js/qbnb/register.html";
  //    } else {
  //      path = WIN_PROJECT_PATH;
  //      System.setProperty("webdriver.chrome.driver", path + "/chromedriver.exe");
  //      baseURL = "file://" + path + "\\app\\src\\main\\js\\qbnb\\register.html";
  //    }
  //    WebDriver driver = new ChromeDriver();
  //    driver.get(baseURL);
  //  }
  //
  //  @AfterEach
  //  void teardown() {
  //      driver.quit();
  //  }

  @Test
  void validRegisterTest() throws InterruptedException {
    String osCheck = System.getProperty("os.name").split(" ")[0];
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      AppThread thread = new AppThread();
      thread.start();
      String baseUrl;
      if (osCheck.equals("Mac")) {
        path = PROJECT_PATH;
        System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
        baseUrl = "file://" + path + "/app/src/main/js/qbnb/register.html";
      } else {
        path = WIN_PROJECT_PATH;
        System.setProperty("webdriver.chrome.driver", path + "/chromedriver.exe");
        baseUrl = "file://" + path + "\\app\\src\\main\\js\\qbnb\\register.html";
      }

      WebDriver driver = new ChromeDriver();
      driver.get(baseUrl);
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("JohnDoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertTrue(isLogin);
    }
  }
}

//  @Test
//  void invalidEmailTest() throws InterruptedException {
//    WebElement email = driver.findElement(By.id("email"));
//    WebElement username = driver.findElement(By.id("user"));
//    WebElement password = driver.findElement(By.id("pass"));
//    WebElement submit = driver.findElement(By.id("submit"));
//    email.sendKeys("johndoe");
//    username.sendKeys("JohnDoe");
//    password.sendKeys("JohnDoe123#");
//    submit.click();
//    sleep(1000);
//    String alert = driver.switchTo().alert().getText();
//    if (alert.equals("Success")) isLogin = true;
//    Assertions.assertFalse(isLogin);
//  }
//
//  @Test
//  void invalidUserTest() throws InterruptedException {
//    WebElement email = driver.findElement(By.id("email"));
//    WebElement username = driver.findElement(By.id("user"));
//    WebElement password = driver.findElement(By.id("pass"));
//    WebElement submit = driver.findElement(By.id("submit"));
//    email.sendKeys("johndoe@test.com");
//    username.sendKeys("j");
//    password.sendKeys("JohnDoe123#");
//    submit.click();
//    sleep(1000);
//    String alert = driver.switchTo().alert().getText();
//    if (alert.equals("Success")) isLogin = true;
//    Assertions.assertFalse(isLogin);
//  }
//
//  @Test
//  void invalidPassTest() throws InterruptedException {
//    WebElement email = driver.findElement(By.id("email"));
//    WebElement username = driver.findElement(By.id("user"));
//    WebElement password = driver.findElement(By.id("pass"));
//    WebElement submit = driver.findElement(By.id("submit"));
//    email.sendKeys("johndoe@test.com");
//    username.sendKeys("JohnDoe");
//    password.sendKeys("JohnDoe12");
//    submit.click();
//    sleep(1000);
//    String alert = driver.switchTo().alert().getText();
//    if (alert.equals("Success")) isLogin = true;
//    Assertions.assertFalse(isLogin);
//  }
