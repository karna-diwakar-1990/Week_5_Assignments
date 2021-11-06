package Day2.Assignment1;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateLead extends Base_Class {

	@Test(dataProvider = "fetchData")
	public void createLead(String companyName, String firstName, String lastName, String phNo){

		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(companyName);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(firstName);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lastName);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phNo);
		driver.findElement(By.name("submitButton")).click();
}

	@DataProvider(name = "fetchData")
	public Object[][] sendData() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("data/CreateLead.xlsx");
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		int cellCount = sheet.getRow(0).getLastCellNum();
		String data[][] =  new String[2][4];
		for(int i=0; i<=rowCount; i++ ){
			for(int j=0; j<cellCount; j++){
				String text = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i][j] = text;
			}
		}
		return data;
	}
}






