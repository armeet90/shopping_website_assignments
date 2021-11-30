package assignment3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Assignment3 {
    /*
     launc()

     */

    String Url = "http://demo.guru99.com/payment-gateway/index.php";
    WebDriver driver;

    @FindBy(xpath = "//a[@href = 'cardnumber.php']")
    WebElement btn_cardnumber;

    @FindBy(xpath = "//section[@id= 'three']")
    WebElement tbx_three;


    public Assignment3(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void launchpaymentwebsite() {
        driver.get(Url);
    }

    public WebElement hitcardnumber() {
        btn_cardnumber.click();
        return btn_cardnumber;
    }
}




