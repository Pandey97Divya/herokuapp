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

public class DynamicLoadingTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testDynamicLoading() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
        startButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        Assert.assertTrue(text.getText().contains("Hello World!"), "Dynamic loading did not work.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
