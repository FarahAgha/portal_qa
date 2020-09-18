package patientportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbstractStepDef {
    protected static WebDriver driver;

    public AbstractStepDef() {
    }

    public WebDriver getDriver() {
        if (driver == null)
        {
            driver=new ChromeDriver();
        }
        return getDriver();
    }
}
