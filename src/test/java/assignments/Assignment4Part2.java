package assignments;

import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment4Part2 {

    String url = "https://opensource-demo.orangehrmlive.com/";

    @Test
    public void Assignment4Test() {
        WebDriver page1 = Util.driver();
        page1.get(url);
        page1.findElement(By.xpath("//input[@id = 'txtUsername']")).sendKeys("Admin");
        page1.findElement(By.xpath("//input[@id = 'txtPassword']")).sendKeys("admin123");
        page1.findElement(By.xpath("//input[@id = 'btnLogin']")).click();

        WebDriver page2 = Util.getLatestPage(page1);
        String welcomeText = page2.findElement(By.xpath("//a[@id ='welcome']")).getText();
        Assert.assertTrue(welcomeText.contains("Welcome Paul"));

        page2.findElement(By.xpath("//b[contains(text(),'My Info')]")).click();
        Assert.assertTrue(url.contains("https://opensource-demo.orangehrmlive.com/index.php/pim/viewMyDetails"));
        String firstname = page2.findElement(By.xpath("//input[@id ='personal_txtEmpFirstName']")).getAttribute("value");
        Assert.assertTrue(firstname.contains("Paul"));
        String lastname = page2.findElement(By.xpath("//input[@id ='personal_txtEmpLastName']")).getAttribute("value");
        Assert.assertTrue(lastname.contains("Walker"));

        page2.findElement(By.xpath("//a[@id = 'welcome']")).click();
        page2.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }
}









