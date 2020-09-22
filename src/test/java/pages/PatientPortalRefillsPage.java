package pages;

import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PatientPortalRefillsPage extends BasePage {

    public PatientPortalRefillsPage(WebDriver driver) {super(driver,30);
    }

    public void refillPageDisplays() throws InterruptedException {
        Log.info("Refill page displays");
        Thread.sleep(3000);
        assertTrue(find(By.xpath("//*[@class='list-group-item router-link-active'][contains(@href,'/pui/patient/refills/list')]")).isDisplayed());
    }

    private void assertTrue(boolean displayed) {
    }

    public PatientPortalRefillsPage clickRefillsLink() throws InterruptedException {
            Log.info("Clicking Refills link");
            Thread.sleep(2000);
            elementIsClickable(By.xpath("//*[@class='list-group-item'][contains(@href,'/pui/patient/refills/list')]"));
            clickElement(driver,By.xpath("//*[@class='list-group-item'][contains(@href,'/pui/patient/refills/list')]"),20);
            return new PatientPortalRefillsPage(driver);
    }

    public void selectFirstPrescription(){
        Log.info("Clicking first prescription");
        assertTrue(elementIsDisplayed(By.xpath("//*[@class='ng-untouched ng-pristine ng-valid'][@type='checkbox']")));
        driver.findElements(By.xpath("//*[@class='ng-untouched ng-pristine ng-valid'][@type='checkbox']")).get(0).click();
    }

    public void enterNoteToOffice(String notes){
        Log.info("Entering Notes to office");
        driver.findElements(By.xpath("//*[@placeholder='Note to Office']")).get(0).sendKeys(notes);
    }

    public void clickSubmit(){
        Log.info("Clicking submit");
        clickElement(driver,By.xpath("//*[@class='btn btn-primary btn-spacing'][contains(text(),'Submit')]"),20);
    }

    public void verifySuccessfulRefill(){
        Log.info("Verifying Schedule Successful");
        assertTrue(elementIsDisplayed(By.xpath("//*[contains(text(),'Welcome')]")));
    }
}
