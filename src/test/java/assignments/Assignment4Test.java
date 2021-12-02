package assignments;


import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment4Test {

    String url = "https://opensource-demo.orangehrmlive.com/";

    @Test
    public void Assignment4Test(){
        WebDriver page1 = Util.driver();
        page1.get(url);
        page1.findElement(By.xpath("//input[@id = 'txtUsername']")).sendKeys("admn");
        page1.findElement(By.xpath("//input[@id = 'txtPassword']")).sendKeys("admin123");
        page1.findElement(By.xpath("//input[@id = 'btnLogin']")).click();

        WebDriver page2 = Util.getLatestPage(page1);
        String welcomeText = page2.findElement(By.xpath("//a[@id ='welcome']")).getText();
        Assert.assertTrue(welcomeText.contains("welcome"));

    }
}
