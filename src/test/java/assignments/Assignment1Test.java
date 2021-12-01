package assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Assignment1Test {

    WebDriver driver;
    String URL = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";

    @BeforeMethod
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void testlogin() throws Exception {
        driver.get(URL);
        WebElement tbx_Username = driver.findElement(By.id("txtUsername"));
        tbx_Username.click();
        tbx_Username.sendKeys("Admin");
        WebElement tbx_Password = driver.findElement(By.name("txtPassword"));
        tbx_Password.click();
        tbx_Password.sendKeys("admin123");

        driver.findElement(By.id("btnLogin")).click();
        WebElement WelcomeMsg = driver.findElement(By.id("welcome"));
        String WelcomeText = WelcomeMsg.getText();
        Assert.assertTrue(WelcomeMsg.isDisplayed());
        Assert.assertTrue(WelcomeText.contains("Welcome"));
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("dashboard"));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.close();
    }
}
