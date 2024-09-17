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

import java.io.File;

public class FileDownloadTest {
    WebDriver driver;
    String downloadPath = System.getProperty("user.home") + "/Downloads/";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFileDownload() {
        driver.get("https://the-internet.herokuapp.com/download");
        WebElement downloadLink = driver.findElement(By.linkText("some-file.txt"));
        downloadLink.click();
        File file = new File(downloadPath + "some-file.txt");
        Assert.assertTrue(file.exists(), "File was not downloaded.");
        file.delete();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
