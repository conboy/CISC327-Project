package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.lang.Thread.sleep;
import static qbnb.AppConf.WIN_PROJECT_PATH;

public class WebRegisterTest {

    @Test
    void correctRegisterTest() throws InterruptedException {
        boolean isLogin = false;
        System.setProperty("webdriver.chrome.driver", WIN_PROJECT_PATH + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = WIN_PROJECT_PATH + "app\\src\\main\\js\\qbnb\\register.html";
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

    @Test
    void invalidEmailTest() throws InterruptedException {
        boolean isLogin = false;
        System.setProperty("webdriver.chrome.driver", WIN_PROJECT_PATH + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = WIN_PROJECT_PATH + "app\\src\\main\\js\\qbnb\\register.html";
        driver.get(baseUrl);
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
        Assertions.assertFalse(isLogin);
    }

    @Test
    void invalidUserTest() throws InterruptedException {
        boolean isLogin = false;
        System.setProperty("webdriver.chrome.driver", WIN_PROJECT_PATH + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = WIN_PROJECT_PATH + "app\\src\\main\\js\\qbnb\\register.html";
        driver.get(baseUrl);
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
    }

    @Test
    void invalidPassTest() throws InterruptedException {
        boolean isLogin = false;
        System.setProperty("webdriver.chrome.driver", WIN_PROJECT_PATH + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = WIN_PROJECT_PATH + "app\\src\\main\\js\\qbnb\\register.html";
        driver.get(baseUrl);
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
    }
}
