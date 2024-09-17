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

public class TyposTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testTypos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        WebElement typo = driver.findElement(By.tagName("p"));
        Assert.assertTrue(typo.getText().contains("typos"), "Typos text is not correct.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
