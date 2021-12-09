package assignments;

import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Assignment5 {
    String url = "https://opensource-demo.orangehrmlive.com/";

    @Test
    public void Assignment4Test() {
        WebDriver page1 = Util.driver();
        page1.get(url);
        page1.findElement(By.xpath("//input[@id = 'txtUsername']")).sendKeys("Admin");
        page1.findElement(By.xpath("//input[@id = 'txtPassword']")).sendKeys("admin123");
        page1.findElement(By.xpath("//input[@id = 'btnLogin']")).click();
        page1.findElement(By.xpath("//b[contains(text(),'PIM')]")).click();
        page1.findElement(By.xpath("//a[@id = 'menu_pim_addEmployee']")).click();

    }
}