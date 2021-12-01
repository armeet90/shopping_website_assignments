package assignments;


import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment2Test extends BaseTest {

    @Test
    public void testLogin() {

        InvalidLoginPage invalidLoginpage = new InvalidLoginPage(driver);
        invalidLoginpage.launchOrangeCRMWebsite();
        invalidLoginpage.enterUsername("Asmi");
        invalidLoginpage.enterPassword("admin321");
        invalidLoginpage.hitSubmit();
        invalidLoginpage. getinvalidcredentials();
        String text = invalidLoginpage.getinvalidcredentials();
        Assert.assertTrue(text.toLowerCase().contains("invalid credentials"));


//       Assert.assertTrue(text.contains("invalid credentials"));


    }
}

