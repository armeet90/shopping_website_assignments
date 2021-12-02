package assignments;

import common.BaseTest;
import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment3Test extends BaseTest {
    String Url = "http://demo.guru99.com/payment-gateway/index.php";

    @Test
    public void Test3() {
        driver.get(Url);//launch on page 1 that is driver
        driver.findElement(By.xpath("//a[contains(text(), 'Generate Card Number')]")).click();// step 1 click on card generate number
        WebDriver page2 = Util.getLatestPage(driver);
        String cardNumber = parseCardNumber(page2.findElement(By.xpath("//h4[contains(text(), 'Card Number')]")).getText());
        String cvv = parseCvv(page2.findElement(By.xpath("//h4[contains(text(), 'CVV')]")).getText());
        String expiryDate = page2.findElement(By.xpath("//h4[contains(text(), 'Exp')]")).getText();
        String cardLimit = parseCardLimit(page2.findElement(By.xpath("//h4[contains(text(), 'Credit Limit')]")).getText());

        System.out.println(cardNumber);
        System.out.println(cvv);
        System.out.println(expiryDate);
        System.out.println(cardLimit);

        page2.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
        WebDriver page3 = Util.getLatestPage(page2);
        WebElement selectElement = page3.findElement(By.xpath("//select[@name='quantity']"));
        Select select = new Select(selectElement);
        select.selectByIndex(3);
        page3.findElement(By.xpath("//input[@value = 'Buy Now']")).click();

        WebDriver page4 = Util.getLatestPage(page3);
        page4.findElement(By.xpath("//input[@id = 'card_nmuber']")).sendKeys(cardNumber);
        WebElement expiryMonthSelectElement = page4.findElement(By.xpath("//select[@id = 'month']"));
        Select selectExpiryMonth = new Select(expiryMonthSelectElement);
        selectExpiryMonth.selectByVisibleText(month(expiryDate));

        WebElement expiryYearSelectElement = page4.findElement(By.xpath("//select[@id = 'year']"));
        Select selectExpiryYear = new Select(expiryYearSelectElement);
        selectExpiryYear.selectByVisibleText(year(expiryDate));

        page4.findElement(By.xpath("//input[@id = 'cvv_code']")).sendKeys(cvv);
        try {
            WebElement payButton = page4.findElement(By.xpath("//input[@value = 'Pay $80.00']"));
            payButton.click();
        } catch (NoSuchElementException e) {
            Assert.fail("payment not successful");
        }
        WebDriver page5 = Util.getLatestPage(page4);

        try {
            page5.findElement(By.xpath("//h2[contains(text(), 'Payment successfull')]"));
        } catch (NoSuchElementException e) {
            Assert.fail("Payment successful tag not found");
        }
        WebDriver page6 =  Util.getLatestPage(page5);
        String ExpectedId = page6.findElement(By.xpath("//table[@class='alt access']/tbody/tr/td[2]")).getText();// xpath to fetch order id

        page5.findElement(By.xpath("//a[contains(text(),'Check Credit Card Limit')]")).click();
        page6.findElement(By.xpath("//input[@id = 'card_nmuber']")).sendKeys(cardNumber);
        page6.findElement(By.xpath("//input[@value = 'submit']")).click();


        WebDriver page7 = Util.getLatestPage(page6);
        String balance = page7.findElement(By.xpath("//h4[contains(text(), 'Credit Card Balance')]/span")).getText();
        Assert.assertEquals(Double.parseDouble(cardLimit) - 80.0, Double.parseDouble(balance));

        String actualid= page7.findElement(By.xpath("//table[@class='alt']/tbody/tr/td[6]")).getText();
        Assert.assertEquals(ExpectedId,actualid);

    }

    private String parseCardLimit(String cc) {
        return cc.split("\\$")[1];
    }

    private String parseCvv(String cvv) {
        return cvv.split(":- ")[1];
    }

    private String parseCardNumber(String cardNumber) {
        return cardNumber.split(":- ")[1];
    }

    String month(String expDate) {
        String d = expDate.split(":- ")[1];
        String[] r = d.split("/");
        return r[0];
    }

    String year(String expDate) {
        //String y = expDate("")
        return expDate.split("/")[1];
    }


}