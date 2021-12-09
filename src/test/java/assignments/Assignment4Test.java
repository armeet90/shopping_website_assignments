package assignments;


import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment4Test {

    String url = "https://opensource-demo.orangehrmlive.com/";

    @Test
    public void Assignment4Test(){
        WebDriver page1 = Util.driver();
        page1.get(url);
        page1.findElement(By.xpath("//input[@id = 'txtUsername']")).sendKeys("admin");
        page1.findElement(By.xpath("//input[@id = 'txtPassword']")).sendKeys("admin123");
        page1.findElement(By.xpath("//input[@id = 'btnLogin']")).click();

        WebDriver page2 = Util.getLatestPage(page1);
        String welcomeText = page2.findElement(By.xpath("//a[@id ='welcome']")).getText();
        Assert.assertTrue(welcomeText.contains("Welcome"));
        page2.findElement(By.xpath("//b[contains(text(),'Directory')]")).click();

        WebElement jobTittle = page2.findElement(By.xpath("//select[@id = 'searchDirectory_job_title']"));
        Select selectjobTittle = new Select(jobTittle);
        selectjobTittle.selectByVisibleText("Sales Representative");

        WebElement location = page2.findElement(By.xpath("//select[@id = 'searchDirectory_location']"));
        Select selectlocation = new Select(location);
        selectlocation.selectByVisibleText("  Canada");

        page2.findElement(By.xpath("//input[@id = 'searchDirectory_emp_name_empName']")).sendKeys("Anthony Nolan");
        page2.findElement(By.xpath("//input[@id = 'searchBtn']")).click();

        try {
        page2.findElement(By.xpath("//b[contains(text(), 'Anthony Nolan')]"));
        } catch (NoSuchElementException e) {
            Assert.fail("Anthony Nolan tag not found");
        }
        page2.findElement(By.xpath("//a[@id = 'welcome']")).click();
        page2.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        Assert.assertEquals(page1.getCurrentUrl(),Util.getLatestPage(page2).getCurrentUrl());
    }
}
