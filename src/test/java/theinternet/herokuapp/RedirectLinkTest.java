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

public class RedirectLinkTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRedirectLink() {
        driver.get("https://the-internet.herokuapp.com/redirector");
        WebElement clickHere = driver.findElement(By.linkText("here"));
        clickHere.click();
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(header.getText().contains("Redirect"), "Redirect page header is incorrect.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
