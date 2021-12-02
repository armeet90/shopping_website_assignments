package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Util {
    public static WebDriver driver() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver getLatestPage(WebDriver driver) {
        WebDriver page = null;//
        for (String wh : driver.getWindowHandles()) {
            page = driver.switchTo().window(wh);// going to next page
        }
        return page;
    }
}
