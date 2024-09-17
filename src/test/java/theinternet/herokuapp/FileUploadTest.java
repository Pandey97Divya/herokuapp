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

import java.nio.file.Paths;

public class FileUploadTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFileUpload() {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement uploadElement = driver.findElement(By.id("file-upload"));
        uploadElement.sendKeys(Paths.get("src/test/resources/testfile.txt").toAbsolutePath().toString());
        WebElement submitButton = driver.findElement(By.id("file-submit"));
        submitButton.click();
        WebElement result = driver.findElement(By.id("uploaded-files"));
        Assert.assertTrue(result.getText().contains("testfile.txt"), "File upload was not successful.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
