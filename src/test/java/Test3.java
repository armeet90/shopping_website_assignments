import assignment3.Assignment3;
import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test3 extends BaseTest {
    String Url = "http://demo.guru99.com/payment-gateway/index.php";

    @Test
    public void Test3() {
        driver.get(Url);//launch on page 1 that is driver
        driver.findElement(By.xpath("//a[contains(text(), 'Generate Card Number')]")).click();// step 1 click on card generate number
        WebDriver page2 = getLatestPage(driver);
        String cardNumber = page2.findElement(By.xpath("//h4[contains(text(), 'Card Number')]")).getText();
        String cvv = page2.findElement(By.xpath("//h4[contains(text(), 'CVV')]")).getText();
        String expiryDate = page2.findElement(By.xpath("//h4[contains(text(), 'Exp')]")).getText();
        String cardLimit = page2.findElement(By.xpath("//h4[contains(text(), 'Credit Limit')]")).getText();

        System.out.println(cardNumber);
        System.out.println(cvv);
        System.out.println(expiryDate);
        System.out.println(cardLimit);

        page2.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
        WebDriver page3 = getLatestPage(page2);
        WebElement selectElement = page3.findElement(By.xpath("//select[@name='quantity']"));
        Select select = new Select(selectElement);
        select.selectByIndex(3);
        page3.findElement(By.xpath("//input[@value = 'Buy Now']")).click();

        WebDriver page4 = getLatestPage(page3);
        page4.findElement(By.xpath("//input[@id = 'card_nmuber']")).sendKeys(parseCardNumber(cardNumber));
        WebElement expiryMonthSelectElement = page4.findElement(By.xpath("//select[@id = 'month']"));
        Select selectExpiryMonth = new Select(expiryMonthSelectElement);
        selectExpiryMonth.selectByVisibleText(month(expiryDate));


        WebElement expiryYearSelectElement = page4.findElement(By.xpath("//select[@id = 'year']"));
        Select selectExpiryYear = new Select(expiryYearSelectElement);
        selectExpiryYear.selectByVisibleText(year(expiryDate));


        page4.findElement(By.xpath("//input[@id = 'cvv_code']")).sendKeys(parseCvv(cvv));

        WebElement payButton = page4.findElement(By.xpath("//input[@value = 'Pay $80.00']"));
        Assert.assertNotNull(payButton);
        payButton.click();
        WebDriver page5 = getLatestPage(page4);

        try {
            page5.findElement(By.xpath("//h2[contains(text(), 'Payment successfull')]"));
        } catch (NoSuchElementException e) {
            Assert.fail("Payment successful tag not found");
        }
// xpath to fetch order id
        page5.findElement(By.xpath("//a[contains(text(),'Check Credit Card Limit')]")).click();
        WebDriver page6 = getLatestPage(page5);
        page6.findElement(By.xpath("//input[@id = 'card_nmuber']")).sendKeys(parseCardNumber(cardNumber));
        page6.findElement(By.xpath("//input[@value = 'submit']")).click();
        WebDriver page7 = getLatestPage(page6);
        //




        payButton = null;
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

    WebDriver getLatestPage(WebDriver driver) {
        WebDriver page = null;//
        for (String wh : driver.getWindowHandles()) {
            page = driver.switchTo().window(wh);// going to next page
        }
        return page;
    }
}