package theinternet.herokuapp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptAlertsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testJavaScriptAlert() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement alertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        alertButton.click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert", "Alert text is incorrect.");
        alert.accept();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
