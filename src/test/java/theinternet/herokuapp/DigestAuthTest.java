package theinternet.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DigestAuthTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testDigestAuthWithCorrectCredentials() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/digest_auth");
        String bodyText = driver.getPageSource();
        Assert.assertTrue(bodyText.contains("Digest Auth"), "Digest Auth page content is incorrect.");
    }

    @Test
    public void testDigestAuthWithIncorrectCredentials() {
        driver.get("https://wronguser:wrongpass@the-internet.herokuapp.com/digest_auth");
        String bodyText = driver.getPageSource();
        Assert.assertFalse(bodyText.contains("Digest Auth"), "Unexpected content found with wrong credentials.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
