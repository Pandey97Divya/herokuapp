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
import org.openqa.selenium.JavascriptExecutor;

public class WYSIWYGEditorTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testWYSIWYGEditor() {
        driver.get("https://the-internet.herokuapp.com/wysiwyg_editor");
        WebElement editorFrame = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(editorFrame);
        WebElement body = driver.findElement(By.id("tinymce"));
        body.clear();
        body.sendKeys("Testing WYSIWYG Editor.");
        Assert.assertTrue(body.getText().contains("Testing WYSIWYG Editor"), "WYSIWYG editor text is incorrect.");
        driver.switchTo().defaultContent();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
