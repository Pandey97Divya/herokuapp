package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class KeyPressesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testKeyPresses() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement inputField = driver.findElement(By.id("target"));
        inputField.sendKeys(Keys.ENTER);
        WebElement result = driver.findElement(By.id("result"));
        Assert.assertTrue(result.getText().contains("You entered: ENTER"), "Key press result is incorrect.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
