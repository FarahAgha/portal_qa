package patientportal;

import helpers.Log;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.PatientPortalHomePage;


import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginSteps extends AbstractStepDef {
    //Logger
    private final static Logger log = Logger.getLogger(LoginSteps.class.getName());

    protected WebDriver driver;

    //TODO username and password from config file
    String username = "fagha_justin";
    String password = "we1c0me1";

    public LoginSteps() {
        driver = Hooks.driver;
    }
    @Given("the Power2Patient test Login page is loaded")
    public void the_Power2Patient_test_Login_page_is_loaded() {
        final String __functionName = "the_Power2Practice_Login_is_loaded";
        final String __className = this.getClass().getName();

        String siteName = "stage.power2patient.net";
        if (!StringUtils.isEmpty(System.getProperty("p2p.siteUrl"))) {
            siteName = System.getProperty("p2p.siteUrl");
            Log.info("siteName "+ siteName);
        }
        final String loginUrl = "https://" + siteName +  "/pui/identity/login";
        String verURL= "https://"+siteName+ "/app/version";
        log.logp(Level.INFO, __className, __functionName, "testing " + siteName);
        driver.get(verURL);

        LoginPage login = new LoginPage(driver);
        login.getVersion();
        driver.get(loginUrl);
    }

    @When("the user enters username field in power2patient login page")
    public void the_user_enters_username_field_in_power2patient_login_page(){
        Log.info("Entering User Name");
        LoginPage loginPage= new LoginPage(driver);
        loginPage.enterUsername(username);
    }
    @When("the user enters password field in power2patient login page")
    public void the_user_enters_password_field_in_power2patient_login_page() {
        Log.info("Entering Password");
        LoginPage loginPage= new LoginPage(driver);
        loginPage.enterPassword(password);
    }
    @When("^the user selects the sign in button in power2patient login page$")
    public void the_user_selects_the_sign_in_button_in_power2patient_login_page(){
        LoginPage loginPage= new LoginPage(driver);
        loginPage.clickSignInButton();
    }

    @Then("^the user should be successfully logged into the power2patient home page$")
    public void the_user_should_be_successfully_logged_into_the_power2patient_home_page() throws Throwable {
        PatientPortalHomePage patientPortalHomePage = new PatientPortalHomePage(driver);
        patientPortalHomePage.HomePageDisplays();

    }



}