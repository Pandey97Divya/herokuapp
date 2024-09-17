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

public class StatusCodesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testStatusCodes() {
        driver.get("https://the-internet.herokuapp.com/status_codes");
        WebElement code200 = driver.findElement(By.linkText("200"));
        code200.click();
        WebElement status = driver.findElement(By.id("content"));
        Assert.assertTrue(status.getText().contains("200"), "Status code 200 is not displayed.");

        WebElement code404 = driver.findElement(By.linkText("404"));
        code404.click();
        status = driver.findElement(By.id("content"));
        Assert.assertTrue(status.getText().contains("404"), "Status code 404 is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
