package patientportal;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;
import pages.PatientPortalRefillsPage;

public class RefillSteps extends AbstractStepDef {
        private final static Logger log = Logger.getLogger(RefillSteps.class.getName());

        protected WebDriver driver;

        public RefillSteps() { driver = Hooks.driver; }


        @Then("^the user selects the Refills link$")
        public void the_user_selects_the_Refills_link() throws Throwable {
            PatientPortalRefillsPage patientPortalRefillsPage= new PatientPortalRefillsPage(driver);
            patientPortalRefillsPage.clickRefillsLink();
        }

        @Then("^the user should be on the Refills page$")
        public void the_user_should_be_on_the_Refills_page() throws Throwable {
            PatientPortalRefillsPage patientPortalRefillsPage= new PatientPortalRefillsPage(driver);
            patientPortalRefillsPage.refillPageDisplays();
        }

        @When("^the user selects the first prescription in the Refills page$")
        public void the_user_selects_the_first_prescription_in_the_Refills_page() {
            PatientPortalRefillsPage patientPortalRefillsPage= new PatientPortalRefillsPage(driver);
            patientPortalRefillsPage.selectFirstPrescription();
        }

        @When("^the user adds \"(.*?)\" to the first prescription Note to Office field in the Refills page$")
        public void the_user_adds_to_the_first_prescription_Note_to_Office_field_in_the_Refills_page(String notes) {
            PatientPortalRefillsPage patientPortalRefillsPage= new PatientPortalRefillsPage(driver);
            patientPortalRefillsPage.enterNoteToOffice(notes);
        }

        @When("^the user selects the submit button in the Refills page$")
        public void the_user_selects_the_submit_button_in_the_Refills_page() {
            PatientPortalRefillsPage patientPortalRefillsPage= new PatientPortalRefillsPage(driver);
            patientPortalRefillsPage.clickSubmit();
        }

        @Then("^the user should be redirected to the Home page$")
        public void the_user_should_be_redirected_to_the_Home_page() {
            PatientPortalRefillsPage patientPortalRefillsPage= new PatientPortalRefillsPage(driver);
            patientPortalRefillsPage.verifySuccessfulRefill();
        }

    }
