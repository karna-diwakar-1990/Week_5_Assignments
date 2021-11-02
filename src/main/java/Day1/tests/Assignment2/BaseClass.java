package Day1.tests.Assignment2;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Day1.tests.Assignment2.helpers.Helper_Class;

import java.time.Duration;

public class BaseClass {
    ChromeDriver driver;
    @BeforeMethod
    public void preConditions(){
        Helper_Class.chromedriverSetup();
        driver = new ChromeDriver();
        Helper_Class.initializeDriver(driver, "https://dev113780.service-now.com/");
        Helper_Class.switchToFrame(driver, "index", "0");
        Helper_Class.enterText(driver, "xpath", "//input[@id='user_name']", "admin");
        Helper_Class.enterText(driver, "xpath", "//input[@id='user_password']", "Sujay@2021");
        Helper_Class.elementClick(driver, "xpath", "//button[@id='sysverb_login']");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.enterText(driver, "xpath", "//input[@id='filter']", "incident");
        //Helper_Class.getElement("xpath", "//input[@id='filter']", driver).sendKeys(Keys.ENTER);
        //Send Keys is not working for this element so using All Link
        Helper_Class.elementClick(driver, "xpath", "//ul[@aria-label='Modules for Application: Incident']//div[@class='sn-widget-list-title' and contains(text(), 'All')]");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    public void postConditions(){
        driver.close();
    }
}
