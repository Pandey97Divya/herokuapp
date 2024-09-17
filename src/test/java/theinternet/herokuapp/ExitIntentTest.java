package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExitIntentTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testExitIntent() {
        driver.get("https://the-internet.herokuapp.com/exit_intent");
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).perform();
        WebElement modal = driver.findElement(By.xpath("//div[@class='modal']"));
        Assert.assertTrue(modal.isDisplayed(), "Exit intent modal is not displayed.");
        WebElement closeButton = modal.findElement(By.xpath("//a[text()='Close']"));
        closeButton.click();
        WebElement content = driver.findElement(By.id("content"));
        Assert.assertTrue(content.isDisplayed(), "Content is not displayed after closing the modal.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
