package backBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by sivagarjala on 03/02/2017.
 */
public class TestScenario extends Identifiers {

    public Identifiers identifiers = new Identifiers();
    public String executeLogIn(String userId, String password, WebDriver driver, String backBaseLoginurl) {

        // And now visit Backbase homepage
        driver.navigate().to(identifiers.url);
        // Wait for the "Log In" button to load and do sign-In
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(identifiers.loginLink));
        driver.findElement(identifiers.loginLink).click();
        driver.findElement(identifiers.username).sendKeys(userId);
        driver.findElement(identifiers.userPassword).sendKeys(password);
        driver.findElement(identifiers.loginButton).click();
        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException ie){
        }
        String currentUrl = driver.getCurrentUrl();
        wait = new WebDriverWait(driver, 20);
        if (driver.findElement(identifiers.username).isDisplayed()) {
            if(backBaseLoginurl.equalsIgnoreCase(currentUrl)){
                System.out.println("User with ID" + "  " + userId + "  " + "Failed to Logged-In");
            }

        }
        else if (wait.until(ExpectedConditions.elementToBeClickable(identifiers.dropDownAfterLogin)).isEnabled()){
            wait.until(ExpectedConditions.elementToBeClickable(identifiers.dropDownAfterLogin));
            driver.findElement(identifiers.dropDownAfterLogin).click();
            if (driver.findElement(identifiers.logout).isDisplayed()) {
                System.out.println("User with ID" + "  " + userId + "  " + "Successfully Logged-In");
            }

        }
        //update result
        if (driver.findElement(identifiers.username).isDisplayed()) {
            currentUrl = driver.getCurrentUrl();
            if (identifiers.backBaseLoginurl.equalsIgnoreCase(currentUrl)) {
                identifiers.result = userId + " " + "Failed to Login";
                System.out.println("User with ID" + "  " + userId + "  " + "Failed to Logged-In");
            }
        }

        else if (driver.findElement(By.cssSelector("a.logout.ng-binding")).isDisplayed()) {
            identifiers.result = userId + " " + "Logged-in successfully";
            driver.findElement(By.cssSelector("a.logout.ng-binding")).click();
            System.out.println("User with ID" + "  " + userId + "  " + "Successfully Logged-out");
        }
        return result;

    }
}
