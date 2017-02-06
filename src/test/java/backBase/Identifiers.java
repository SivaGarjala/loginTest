package backBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by sivagarjala on 03/02/2017.
 */
public class Identifiers extends HomePage{
    String url = "https://my.backbase.com/";
    String result = null;
    String userId = null;
    String password = null;
    String backBaseLoginurl = "https://my.backbase.com/home//login";
    String backBaseHomeurl = "https://my.backbase.com/home";
    String fPath = "src/test/resources/credentials.xls";
    String chromeDriverpath = "src/test/resources/chromedriver";
    String fResultPath = "src/test/resources/credentials.xls";

    WebDriver driver;
    By username = By.id("j_username");
    By userPassword = By.id("j_password");
    By loginButton = By.cssSelector("button.btn.btn-primary");
    By dropDownAfterLogin = By.xpath("//div[@id='user-profile']/section/div/a/i");
    By logout = By.cssSelector("a.logout.ng-binding");
    By loginLink = By.cssSelector("a.login-link.ng-scope");
}
