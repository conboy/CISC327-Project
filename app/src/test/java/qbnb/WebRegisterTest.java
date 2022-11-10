package qbnb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class WebRegisterTest {
    WebDriver driver;
    boolean isLogin = false;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        try {
            driver = new ChromeDriver();
        } catch (Exception e) {

        }
    }

    @AfterEach
    void teardown() {

        try {
            driver.quit();
        } catch (Exception e) {

        }
    }

    @Test
    void validRegisterTest() throws InterruptedException {

      String baseURL = null;
      try {
        baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
        System.out.println(baseURL);
        driver.get(baseURL);
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
      } catch (Exception e) {

      }

    }

    @Test
    void invalidEmailTest() throws InterruptedException {

      String baseURL = null;
      try {
        baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
        System.out.println(baseURL);
        driver.get(baseURL);
        WebElement email = driver.findElement(By.id("email"));
        WebElement username = driver.findElement(By.id("user"));
        WebElement password = driver.findElement(By.id("pass"));
        WebElement submit = driver.findElement(By.id("submit"));
        email.sendKeys("johndoe");
        username.sendKeys("JohnDoe");
        password.sendKeys("JohnDoe123#");
        submit.click();
        sleep(1000);
        String alert = driver.switchTo().alert().getText();
        if (alert.equals("Success")) isLogin = true;
      } catch (Exception e) {

      }

        Assertions.assertFalse(isLogin);
    }

    @Test
    void invalidUserTest() throws InterruptedException {

      String baseURL = null;
      try {
        baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
        System.out.println(baseURL);
        driver.get(baseURL);
        WebElement email = driver.findElement(By.id("email"));
        WebElement username = driver.findElement(By.id("user"));
        WebElement password = driver.findElement(By.id("pass"));
        WebElement submit = driver.findElement(By.id("submit"));
        email.sendKeys("johndoe@test.com");
        username.sendKeys("j");
        password.sendKeys("JohnDoe123#");
        submit.click();
        sleep(1000);
        String alert = driver.switchTo().alert().getText();
        if (alert.equals("Success")) isLogin = true;
      } catch (Exception e) {

      }

        Assertions.assertFalse(isLogin);
    }

    @Test
    void invalidPassTest() throws InterruptedException {

      String baseURL = null;
      try {
        baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
        System.out.println(baseURL);
        driver.get(baseURL);
        WebElement email = driver.findElement(By.id("email"));
        WebElement username = driver.findElement(By.id("user"));
        WebElement password = driver.findElement(By.id("pass"));
        WebElement submit = driver.findElement(By.id("submit"));
        email.sendKeys("johndoe@test.com");
        username.sendKeys("JohnDoe");
        password.sendKeys("JohnDoe12");
        submit.click();
        sleep(1000);
        String alert = driver.switchTo().alert().getText();
        if (alert.equals("Success")) isLogin = true;
      } catch (Exception e) {

      }
        Assertions.assertFalse(isLogin);
    }
}
