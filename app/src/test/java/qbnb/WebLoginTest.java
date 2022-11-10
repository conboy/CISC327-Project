package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebLoginTest {
    WebDriver driver;
    boolean isLogin = false;
    String baseUrl = WIN_PROJECT_PATH + "\\app\\src\\main\\js\\qbnb\\register.html";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        try {
            driver = new ChromeDriver();
            driver.get(baseUrl);
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
        try {
            // Create user
            WebElement email = driver.findElement(By.id("email"));
            WebElement username = driver.findElement(By.id("user"));
            WebElement password = driver.findElement(By.id("pass"));
            WebElement submit = driver.findElement(By.id("submit"));
            email.sendKeys("johndoe@test.com");
            username.sendKeys("JohnDoe");
            password.sendKeys("JohnDoe123#");
            submit.click();
            sleep(1000);

            // Login
            driver.findElement(By.linkText("Log in")).click();
            email = driver.findElement(By.id("email"));
            password = driver.findElement(By.id("pass"));
            submit = driver.findElement(By.id("submit"));
            email.sendKeys("johndoe@test.com");
            password.sendKeys("JohnDoe123#");
            submit.click();
            sleep(1000);
            String alert = driver.switchTo().alert().getText();
            if (alert.equals("Logged in successfully")) isLogin = true;
            Assertions.assertTrue(isLogin);
        } catch (Exception e) {

        }
    }

    @Test
    void invalidEmailTest() throws InterruptedException {
        try {
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

        try {
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
            Assertions.assertFalse(isLogin);
        } catch (Exception e) {

        }
    }

    @Test
    void invalidPassTest() throws InterruptedException {

        try {
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
            Assertions.assertFalse(isLogin);
        } catch (Exception e) {

        }
    }
}