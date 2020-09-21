package pages;

import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class LoginPage extends BasePage{

    //Logo
    private final By logo= By.className("identity-logo");
    // Forgot Username
    private final By forgotUsername= By.xpath("//*[contains(text(),'Forgot Username')]");
    // Forgot Password?
    private final By  forgotPassword = By.xpath("//*[contains(text(),'Forgot Password')]");
    //Agreement
    private final By  agreement = By.xpath("//*[contains(text(),'By clicking \"Sign In\" you agree to the Power2Patient')]");
    //  Terms of Use
    private final By   termsOfUse = By.xpath("//*[contains(text(),'Terms of Use')]");
    //Register Here
    private final By   registerHere = By.xpath("//*[contains(text(),'Register Here')]");
    //username
    private final By username = By.id("username");
    //password
    private final By password= By.id("password");
    //submit
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

    public void power2PracticeLogo(){
        Log.info("Verifying power2Practice Logo");
        String path = driver.findElement((logo)).getAttribute("src");
        assertTrue(path.contains("power2patient.net/pui/assets/images/p2p_logo_new.svg"));
    }
    public void forgotUsernameLink(){
        Log.info("Verifying forgot Username Link");
        clickElement(driver,forgotUsername,30);
        assertTrue(driver.getCurrentUrl().contains("power2patient.net/pui/identity/forgot-username"));
        clickElement(driver, By.xpath("//*[contains(text(),'Cancel')]"), 30);
    }
    public void forgotPasswordLink(){
        Log.info("Verifying forgotPasswordLink");
        clickElement(driver,forgotPassword,30);
        assertTrue(driver.getCurrentUrl().contains("power2patient.net/pui/identity/forgot-password"));
        clickElement(driver, By.xpath("//*[contains(text(),'Cancel')]"), 30);
    }
    public void agreementText(){
        Log.info("Verifying agreementText");
        String expected = "By clicking \"Sign In\" you agree to the Power2Patient Terms of Use .";
        String actual = find(agreement).getText();
        assertTrue(expected.contains(actual));
    }
    public void  termsOfUseLink(){
        Log.info("Verifying termsOfUseLink");
        clickElement(driver,termsOfUse,30);
        switchToNextTab();
        assertTrue(driver.getCurrentUrl().contains("power2patient.net/pui/public/terms-of-use"));
    }
    public void registerHereLink(){
        Log.info("Verifying registerHereLink");
        clickElement(driver,registerHere,30);
        assertTrue(driver.getCurrentUrl().contains("power2patient.net/pui/identity/register-here"));
    }

    public void getVersion()
    {
        elementIsDisplayed(By.cssSelector("html body"));
        String version= find(By.cssSelector("html body")).getText();
        Log.info("Version:"+version+"\n");
    }
}
