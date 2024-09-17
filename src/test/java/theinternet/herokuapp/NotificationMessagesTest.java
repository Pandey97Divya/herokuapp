package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NotificationMessagesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testNotificationMessages() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        WebElement clickHere = driver.findElement(By.linkText("Click here"));
        clickHere.click();
        WebElement notification = driver.findElement(By.id("flash"));
        Assert.assertTrue(notification.getText().contains("Action successful"), "Notification message is incorrect.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
