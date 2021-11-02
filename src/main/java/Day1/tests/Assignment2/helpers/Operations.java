package Day1.tests.Assignment2.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class Operations {
    public static void create_Incident(ChromeDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.switchToFrame(driver, "id", "gsft_main");
        Helper_Class.elementClick(driver, "xpath", "//button[@type='submit' and @id='sysverb_new']");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.elementClick(driver, "xpath", "//button[@id='lookup.incident.caller_id']");
        String parent=driver.getWindowHandle();

        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        while(I1.hasNext()) {

            String child_window = I1.next();


            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Helper_Class.enterText(driver, "xpath", "//div[@class='input-group']/input", "System");
        Helper_Class.getElement("xpath", "//div[@class='input-group']/input", driver).sendKeys("System Administrator", Keys.ENTER);
        Helper_Class.elementClick(driver, "xpath", "//div[@aria-label='Users']/table/tbody/tr//table//tbody//a");
        driver.switchTo().window(parent);
        Helper_Class.switchToFrame(driver, "id", "gsft_main");
        Helper_Class.enterText(driver, "xpath", "//input[@id='incident.short_description']", "Lore Ipsum");
        String incidentNumber = Helper_Class.getText(driver, "xpath", "//input[@id='incident.number']");
        Helper_Class.elementClick(driver, "xpath", "//button[@id='sysverb_insert_bottom']");
        Helper_Class.selectDropdown(driver, "xpath", "//div[@data-list_id='incident']//select","Number");
        Helper_Class.enterText(driver,"xpath","//div[@data-list_id='incident']//input[@placeholder='Search']", incidentNumber);
        Helper_Class.getElement("xpath", "//div[@data-list_id='incident']//input[@placeholder='Search']", driver).sendKeys(Keys.ENTER);
        String testIncidentNumber = Helper_Class.getElement("xpath","((//table[@id='incident_table']//tbody//tr)[1]//td[@class='vt'])[1]", driver).getText();
        Assert.assertEquals(testIncidentNumber, incidentNumber, "Test Successfull");
    }

    public static void updateIncident(ChromeDriver driver, String incidentNumber){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.switchToFrame(driver, "id", "gsft_main");
        Helper_Class.selectDropdown(driver, "xpath", "//span[@id='incident_hide_search']/div/div/span//select", "Number");
        Helper_Class.enterText(driver, "xpath", "//span[@id='incident_hide_search']/div/div/input", incidentNumber);
        Helper_Class.getElement("xpath", "//span[@id='incident_hide_search']/div/div/input", driver).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.elementClick(driver, "xpath","//table[@id='incident_table']/tbody/tr/td/a[@class='linked formlink']");
        Helper_Class.selectDropdown(driver, "xpath", "//select[@id='incident.urgency']", "1 - High");
        Helper_Class.selectDropdown(driver, "xpath", "//select[@id='incident.state']", "In Progress");
        Helper_Class.enterText(driver, "xpath", "//textarea[@id='activity-stream-work_notes-textarea']", "Lorem");
        String state = Helper_Class.getSelectedOption(driver, "xpath", "//select[@id='incident.state']");
        String priority = Helper_Class.getSelectedOption(driver, "xpath", "//select[@id='incident.priority']");
        Helper_Class.elementClick(driver, "xpath", "//button[@id='sysverb_update_bottom']");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String itemPriority = Helper_Class.getText(driver, "xpath", "(//table[@id='incident_table']/tbody/tr/td)[7]");
        String itemStatus = Helper_Class.getText(driver,"xpath", "(//table[@id='incident_table']/tbody/tr/td)[8]");
        Assert.assertEquals(itemPriority, priority);
        Assert.assertEquals(itemStatus, state);
    }

    public static void assignIncident(ChromeDriver driver, String incidentNumber){
        Helper_Class.switchToFrame(driver, "id", "gsft_main");
        Helper_Class.selectDropdown(driver, "xpath", "//span[@id='incident_hide_search']/div/div/span//select", "Number");
        Helper_Class.enterText(driver, "xpath", "//span[@id='incident_hide_search']/div/div/input", incidentNumber);
        Helper_Class.getElement("xpath", "//span[@id='incident_hide_search']/div/div/input", driver).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.elementClick(driver, "xpath","//table[@id='incident_table']/tbody/tr/td/a[@class='linked formlink']");
        Helper_Class.elementClick(driver, "xpath", "//button[@id='lookup.incident.assignment_group']");
        String parent=driver.getWindowHandle();

        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        while(I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.getElement("xpath", "//div[@class='input-group']/input", driver).sendKeys("Software", Keys.ENTER);
        Helper_Class.elementClick(driver, "xpath", "//div[@id='sys_user_group']/table/tbody/tr/td/a");
        driver.switchTo().window(parent);
        Helper_Class.switchToFrame(driver, "id", "gsft_main");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
// set the text
        String assignedGroup = (String) jsExecutor.executeScript("document.getElementById('sys_display.incident.assignment_group').value;");
        //String assignedGroup = Helper_Class.getText(driver, "xpath", "//input[@id='sys_display.incident.assignment_group']");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.enterText(driver, "xpath", "//textarea[@id='activity-stream-textarea']", "Lorem");
        Helper_Class.elementClick(driver, "xpath", "//button[@id='sysverb_update_bottom']");
        String actualGroup = Helper_Class.getText(driver, "xpath", "(//table[@id='incident_table']/tbody/tr/td)[10]");
        Assert.assertEquals(actualGroup, assignedGroup);
    }

    public static void deleteIncident(ChromeDriver driver, String incidentNumber){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.switchToFrame(driver, "id", "gsft_main");
        Helper_Class.selectDropdown(driver, "xpath", "//span[@id='incident_hide_search']/div/div/span//select", "Number");
        Helper_Class.enterText(driver, "xpath", "//span[@id='incident_hide_search']/div/div/input", incidentNumber);
        Helper_Class.getElement("xpath", "//span[@id='incident_hide_search']/div/div/input", driver).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.elementClick(driver, "xpath","//table[@id='incident_table']/tbody/tr/td/a[@class='linked formlink']");
        Helper_Class.elementClick(driver, "xpath", "//button[@id='sysverb_delete']");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helper_Class.elementClick(driver, "xpath", "//div[@id='delete_confirm_form']//rendered_body/div/div[@class='modal-footer']/button[@id='ok_button']");
    }
}
