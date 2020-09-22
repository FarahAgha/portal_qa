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
    public LoginSteps() { driver = Hooks.driver; }

    //TODO username and password from config file
    String username = "fagha_justin";
    String password = "we1c0me1";
    String invalidPassword = "Invalidpassword1";

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
    @When("the user enters {string} password field in power2patient login page")
    public void the_user_enters_password_field_in_power2patient_login_page(String passwordcheck) {
        LoginPage loginPage= new LoginPage(driver);
        if(passwordcheck.equals("valid")) {
            Log.info("Entering Valid Password");
            loginPage.enterPassword(password);
        }
        else{
            Log.info("Entering Invalid Password");
            loginPage.enterPassword(invalidPassword);
        }
    }
    @When("^the user selects the sign in button in power2patient login page$")
    public void the_user_selects_the_sign_in_button_in_power2patient_login_page(){
        LoginPage loginPage= new LoginPage(driver);
        loginPage.clickSignInButton();
    }

    @Then("^the user should be successfully logged into the power2patient home page$")
    public void the_user_should_be_successfully_logged_into_the_power2patient_home_page() {
        PatientPortalHomePage patientPortalHomePage = new PatientPortalHomePage(driver);
        patientPortalHomePage.HomePageDisplays();
    }

    //Incorrect password
    @Then("the user should see error message and remain on Login page")
    public void the_user_should_see_error_message_and_remain_on_login_page() {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.InvalidLoginErrorDisplays();
    }

    /*
    Navigational Links
     */

    @Given("user can see Power2Practice Logo")
    public void user_can_see_power2practice_logo() {
        // user can see Power2Practice Logo
        LoginPage loginPage= new LoginPage(driver);
        loginPage.power2PracticeLogo();
    }

    @Given("user can see Forgot Username? link")
    public void user_can_see_forgot_username_link() {
        // user can see Forgot Username? link
        LoginPage loginPage= new LoginPage(driver);
        loginPage.forgotUsernameLink();
    }

    @Given("user can see Forgot Password? link")
    public void user_can_see_forgot_password_link() {
        // user can see Forgot Password? link
        LoginPage loginPage= new LoginPage(driver);
        loginPage.forgotPasswordLink();
    }

    @Given("user can see Agreement text")
    public void user_can_see_agreement_text() {
        // user can see Agreement text
        LoginPage loginPage= new LoginPage(driver);
        loginPage.agreementText();
    }

    @Given("user can see Terms of Use link")
    public void user_can_see_terms_of_use_link() {
        // user can see Terms of Use link
        LoginPage loginPage= new LoginPage(driver);
        loginPage.termsOfUseLink();
    }

    @Given("user can see Register Here link")
    public void user_can_see_register_here_link() {
        // user can see Register Here link
        LoginPage loginPage= new LoginPage(driver);
        loginPage.registerHereLink();
    }




}