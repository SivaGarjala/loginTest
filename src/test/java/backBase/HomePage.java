package backBase;

import junit.framework.Test;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HomePage {

	public static void main(String[] args) {

		Identifiers identifiers = new Identifiers();
		TestScenario scenario = new TestScenario();
		System.setProperty("webdriver.chrome.driver", identifiers.chromeDriverpath);
		WebDriver driver = new ChromeDriver();
		//Replace the Path of Credentials.xls file with path in your machine
		String filePath = identifiers.fPath;
		FileInputStream fs;
		Workbook wb;
		try {
			
			fs = new FileInputStream(filePath);
			wb = new HSSFWorkbook(fs);

			// TO get the access to the sheet
			Sheet sh = wb.getSheetAt(0);

			// To get the number of rows present in sheet
			int totalNoOfRows = sh.getPhysicalNumberOfRows();

			// Loop goes through the number of Test Cases present in Credentials.xls file
			for (int row = 1; row < totalNoOfRows; row++) {
				identifiers.userId = sh.getRow(row).getCell(2).getStringCellValue();
				identifiers.password = sh.getRow(row).getCell(3).getStringCellValue();
				System.out.println("Executing Test Case: " + row);
				System.out.println("User id: " + identifiers.userId + " Password: " + identifiers.password);
				scenario.executeLogIn(identifiers.userId, identifiers.password, driver, identifiers.backBaseLoginurl);
				//update.updateResult();
				//Update the excel sheet with the result
		        sh.getRow(row).createCell(5).setCellValue(identifiers.result);
			}
			//Replace the path value to a place where you expect to see the output file generated
			FileOutputStream fout = new FileOutputStream(identifiers.fResultPath);
			wb.write(fout);
			fout.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Close the browser
		driver.quit();
	}

}
