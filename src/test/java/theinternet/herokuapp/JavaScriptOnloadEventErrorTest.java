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

public class JavaScriptOnloadEventErrorTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testOnloadEventError() {
        driver.get("https://the-internet.herokuapp.com/javascript_error");
        WebElement errorMessage = driver.findElement(By.xpath("//p"));
        Assert.assertTrue(errorMessage.getText().contains("Unable to locate element"), "Error message is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
