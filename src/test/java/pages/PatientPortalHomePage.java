package pages;

import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static junit.framework.Assert.assertTrue;

public class PatientPortalHomePage extends BasePage {
    public PatientPortalHomePage(WebDriver driver) {
        super(driver,35);
    }


    public void HomePageDisplays(){
        Log.info("Homepage displays");
        assertTrue(elementIsDisplayed(By.xpath("//*[@href='/pui/patient/dashboard']")));
    }
}
