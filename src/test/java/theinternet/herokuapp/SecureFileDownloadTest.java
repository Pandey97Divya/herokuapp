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

public class SecureFileDownloadTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSecureFileDownload() {
        driver.get("https://the-internet.herokuapp.com/download_secure");
        WebElement downloadLink = driver.findElement(By.linkText("secure_download.txt"));
        downloadLink.click();
        // Add logic to verify the file download, this will vary based on your setup.
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
