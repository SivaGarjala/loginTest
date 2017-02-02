package backBase;

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

		//Replace the Path of Credentials.xls file with path in your machine
		String filePath = "src/test/resources/credentials.xls";
		FileInputStream fs;
		Workbook wb;
		try {
			// Variable declaration
			String url = "https://my.backbase.com/";
			String userId = null;
			String password = null;
			String result = null;
			String backBaseLoginurl = "https://my.backbase.com/home//login";
			String backBaseHomeurl = "https://my.backbase.com/home";
			
			fs = new FileInputStream(filePath);
			wb = new HSSFWorkbook(fs);

			// TO get the access to the sheet
			Sheet sh = wb.getSheetAt(0);

			// To get the number of rows present in sheet
			int totalNoOfRows = sh.getPhysicalNumberOfRows();

			// Loop goes through the number of Test Cases present in Credentials.xls file

			for (int row = 1; row < totalNoOfRows; row++) {

				userId = sh.getRow(row).getCell(2).getStringCellValue();
				password = sh.getRow(row).getCell(3).getStringCellValue();
				System.out.println("Executing Test Case: " + row);
				System.out.println("User id: " + userId + " Password: " + password);

				// Create a new instance of the Chrome driver to execute the tests in the browser

				//Replace the path of the chromedriver with path downloaded present in your machine
		        System.setProperty("webdriver.chrome.driver","/Users/sivagarjala/Desktop/chromedriver");
		        WebDriver driver = new ChromeDriver();

		        // And now visit Backbase homepage
		        HomePage hPage = new HomePage();
		        driver.navigate().to(url);

		        // Wait for the "Log In" button to load and do sign-In
		        WebDriverWait wait = new WebDriverWait(driver, 15);
		        wait.until(ExpectedConditions.elementToBeClickable(By.className("login-button")));
		        driver.findElement(By.cssSelector("a.login-link.ng-scope")).click();

		        //Call the method to Execute the Log-in
		        if(driver.findElement(By.id("j_username")).isDisplayed()) {
		            System.out.println ("Executing the Log-in");
		            hPage.executeLogIn(userId, password, driver, backBaseHomeurl);
		        }

				if(driver.findElement(By.id("j_username")).isDisplayed()) {
					String currentUrl = driver.getCurrentUrl();
					if (backBaseLoginurl.equalsIgnoreCase(currentUrl)) {
						result = userId + " " + "Failed to Login";
						System.out.println("User with ID" + "  " + userId + "  " + "Failed to Logged-In");
					}
				}


				else if(driver.findElement(By.cssSelector("a.logout.ng-binding")).isDisplayed()) {
					result = userId + " " + "Logged-in successfully";
					driver.findElement(By.cssSelector("a.logout.ng-binding")).click();
					System.out.println("User with ID" + "  " + userId + "  " +  "Successfully Logged-out");
				}

				// Close the browser
				driver.quit();

				//Update the excel sheet with the result
		        sh.getRow(row).createCell(5).setCellValue(result);



			}

			//Replace the path value to a place where you expect to see the output file generated
			FileOutputStream fout = new FileOutputStream("src/test/resources/credentials-updated.xls");
			wb.write(fout);
			fout.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executeLogIn(String userId, String password, WebDriver driver, String backBaseLoginurl) {

		driver.findElement(By.id("j_username")).sendKeys(userId);
		driver.findElement(By.id("j_password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException ie){
		}
		String currentUrl = driver.getCurrentUrl();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if (driver.findElement(By.id("j_username")).isDisplayed()) {
			 if(backBaseLoginurl.equalsIgnoreCase(currentUrl)){
				System.out.println("User with ID" + "  " + userId + "  " + "Failed to Logged-In");
			}

		}
		else if (wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='user-profile']/section/div/a/i"))).isEnabled()){
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='user-profile']/section/div/a/i")));
			driver.findElement(By.xpath("//div[@id='user-profile']/section/div/a/i")).click();
			if (driver.findElement(By.cssSelector("a.logout.ng-binding")).isDisplayed()) {
				System.out.println("User with ID" + "  " + userId + "  " + "Successfully Logged-In");
			}

		}

	}

}
