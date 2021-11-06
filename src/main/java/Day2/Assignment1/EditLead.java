package Day2.Assignment1;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class EditLead extends Base_Class{
	@Test(dataProvider = "fetchEditData")
	public void editLead(String phNo, String companyName) throws InterruptedException {

		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phNo);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys(companyName);
		driver.findElement(By.name("submitButton")).click();
}

	@DataProvider(name = "fetchEditData")
	public Object[][] sendEditData() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("data/EditLead.xlsx");
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		int cellCount = sheet.getRow(0).getLastCellNum();
		String data[][] =  new String[2][2];
		for(int i=0; i<=rowCount; i++ ){
			for(int j=0; j<cellCount; j++){
				String text = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i][j] = text;
			}
		}
		return data;
	}
}






