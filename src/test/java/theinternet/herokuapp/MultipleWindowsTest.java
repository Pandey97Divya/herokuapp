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

import java.util.ArrayList;
import java.util.Set;

public class MultipleWindowsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testMultipleWindows() {
        driver.get("https://the-internet.herokuapp.com/windows");
        WebElement clickHere = driver.findElement(By.linkText("Click Here"));
        clickHere.click();
        Set<String> handles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(handles);
        driver.switchTo().window(tabs.get(1));
        WebElement header = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(header.getText().contains("New Window"), "New window header is incorrect.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
