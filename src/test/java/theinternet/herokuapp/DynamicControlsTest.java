package theinternet.herokuapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicControlsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement message = driver.findElement(By.id("message"));
        removeButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOf(inputField));
        Assert.assertTrue(message.getText().contains("It's gone!"), "The 'Remove' action did not work.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
