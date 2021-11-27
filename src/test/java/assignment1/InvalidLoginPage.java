package assignment1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvalidLoginPage {


    String Url = "https://opensource-demo.orangehrmlive.com/";
    WebDriver driver;

    @FindBy(xpath = "//input[@id = 'txtUsername']")
    WebElement tbx_Username;

    @FindBy(xpath = "//input[@id = 'txtPassword']")
    WebElement tbx_Password;

    @FindBy(xpath = "//input[@id = 'btnLogin']")
    WebElement btn_Submit;

    @FindBy(xpath = "//span[@id = 'spanMessage']")
    WebElement wrong;


    public InvalidLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void launchOrangeCRMWebsite() {
        driver.get(Url);
    }

    public void enterUsername(String username) {
        tbx_Username.sendKeys(username);
    }

    public void enterPassword(String password) {
        tbx_Password.sendKeys(password);
    }

    public void hitSubmit() {
        btn_Submit.click();
    }

    public String getinvalidcredentials() {
        return wrong.getText();


    }
}
