package Day1.tests.Assignment2.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Helper_Class {
    public static void chromedriverSetup() {
        WebDriverManager.chromedriver().setup();
    }
    public static void initializeDriver(ChromeDriver driver, String url){
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public static WebElement getElement(String identifierType, String identifier, ChromeDriver driver){
        WebElement element = null;
        if(identifierType.equalsIgnoreCase("class")){
            element = driver.findElement(By.className(identifier));
        }else if(identifierType.equalsIgnoreCase("id")){
            element = driver.findElement(By.id(identifier));
        }else if(identifierType.equalsIgnoreCase("link-text")){
            element = driver.findElement(By.linkText(identifier));
        }else if(identifierType.equalsIgnoreCase("xpath")){
            element = driver.findElement(By.xpath(identifier));
        }else if(identifierType.equalsIgnoreCase("tag")){
            element = driver.findElement(By.tagName(identifier));
        }
        return element;
    }
    public static List<WebElement> getElements(String identifierType, String identifier, ChromeDriver driver){
        List<WebElement> elements = null;
        if(identifierType.equalsIgnoreCase("class")){
            elements = driver.findElements(By.className(identifier));
        }else if(identifierType.equalsIgnoreCase("tag")){
            elements = driver.findElements(By.tagName(identifier));
        }
        return elements;
    }
    public static void moveOverElement(ChromeDriver driver, String identifierType, String identifier){
        WebElement element = getElement(identifierType, identifier, driver);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void moveToAndClickElement(ChromeDriver driver, String identifierType, String identifier){
        WebElement element = getElement(identifierType, identifier, driver);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        action.click(element).build().perform();
    }
    public static void enterText(ChromeDriver driver,String identifierType,String identifier, String input){
        WebElement textBox = getElement(identifierType, identifier, driver );
        textBox.sendKeys(input);
    }
    public static String getText(ChromeDriver driver,String identifierType,String identifier){
        WebElement textBox = getElement(identifierType, identifier, driver );
        return textBox.getText();
    }

    public static void elementClick(ChromeDriver driver,String identifierType,String identifier){
        WebElement element = getElement(identifierType, identifier, driver );
        element.click();
    }
    public static boolean isFramePresent(ChromeDriver driver){
        int count = getElements("tagName", "iframe", driver).size();
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
    public static void switchToFrame(ChromeDriver driver, String identifierType, String identifier){
        if(identifierType.equalsIgnoreCase("index")){
            driver.switchTo().frame(Integer.parseInt(identifier));
        }else if((identifierType.equalsIgnoreCase("id")) || (identifierType.equalsIgnoreCase("name"))){
            driver.switchTo().frame(identifier);
        }
    }

    public static void selectDropdown(ChromeDriver driver,String identifierType,String identifier, String value){
        Select dropdown = new Select(getElement(identifierType, identifier, driver ));
        dropdown.selectByVisibleText(value);
    }
    public static String getSelectedOption(ChromeDriver driver, String identifierType,String identifier){
        Select dropdown = new Select(getElement(identifierType, identifier, driver ));
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        return selectedOption;
    }

}
