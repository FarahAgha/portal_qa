package pages;

import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage{

    //Logo
    ///html/body/app-root/app-identity/div/identity-login-page/div/div/div/div/div[1]/img

    // Forgot Username
    private final By forgotUsername= By.xpath("//*[contains(text(),'Forgot Username')]");
    // Forgot Password?
    private final By  forgotPassword = By.xpath("//*[contains(text(),'Forgot Password')]");
    //Agreement
    private final By  agreement = By.xpath("//*[contains(text(),'By clicking \"Sign In\" you agree to the Power2Patient ')]");
    //  Terms of Use
    private final By   termsOfUse = By.xpath("//*[contains(text(),'Terms of Use')]");
    //Register Here
    private final By   registerHere = By.xpath("//*[contains(text(),'Register Here')]");

    private final By username = By.id("username");
    private final By password= By.id("password");
    private final By submit= By.xpath("//*[contains(text(),'Sign In')]");

    public LoginPage(WebDriver driver){
        super(driver,30);
    }

    public void enterUsername (String text) {
        Log.info("Entering User Name");
        elementIsClickable(username);
        enterText(username, text);
    }

    public void enterPassword (String text)  {
        Log.info("Entering Password");
        elementIsClickable(password);
        enterText(password,text);
    }

    public void clickSignInButton() {
        Log.info("Clicking Sign In button");
        clickElement(driver, By.xpath("//*[contains(text(),'Sign In')]"), 30);
    }

    public void getVersion()
    {
        elementIsDisplayed(By.cssSelector("html body"));
        String version= find(By.cssSelector("html body")).getText();
        Log.info("Version:"+version+"\n");
    }
}
