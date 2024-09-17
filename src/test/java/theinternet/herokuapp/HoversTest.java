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

public class HoversTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testHovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement user1 = driver.findElement(By.xpath("//div[@class='figure'][1]"));
        WebElement user1Text = driver.findElement(By.xpath("//div[@class='figcaption'][1]"));
        user1.click();
        Assert.assertTrue(user1Text.getText().contains("name: user1"), "Hover text for user1 is incorrect.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
