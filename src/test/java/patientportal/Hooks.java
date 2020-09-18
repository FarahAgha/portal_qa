package patientportal;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Hooks {

  public static WebDriver driver;

  @Before
  /**
   * Delete all cookies at the start of each scenario to avoid shared state between tests
   */
  public void openBrowser() throws MalformedURLException {

    String browser = System.getProperty("BROWSER");
    if (browser == null) {
      browser = System.getenv("BROWSER");
      if (browser == null) {
        browser = "chrome";
      }
    }
    System.out.println("openBrowser: browser " + browser);
    switch (browser) {
    case "chrome":

      if (StringUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))){
        System.setProperty("webdriver.chrome.driver","C:/Users/fagha/Documents/faghaGit/resources/chromedriver.exe");
      }
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--disable-extensions");
      options.addArguments("--start-maximized");
      options.addArguments("--always-authorize-plugins=false");
      driver = new ChromeDriver(options);
      driver.manage().deleteAllCookies();
      driver.manage().window().setSize(new Dimension(1280, 1024));
      break;
    default:
      driver = new ChromeDriver();
      break;
    }
    System.out.println("Opening Browser...." + browser);
  }

  @After
  /**
   * Embed a screenshot in test report if test is marked as failed
   */
  public void embedScreenshot(Scenario scenario)  throws Exception {

    int result = 1;
    List<String> testCaseIds = getTestCaseId(scenario);
    String[] testCaseId = new String[testCaseIds.size()];
    for(int i=0; i<testCaseIds.size() ; i++) {
      testCaseId[i] = testCaseIds.get(i);
    }

    if (scenario.isFailed()) {
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png","Failed Screen");
    }
    if (driver != null) {
      driver.quit();
    }

  }

  public List<String>  getTestCaseId(Scenario scenario) throws Exception {
    List<String> testCaseId = new ArrayList<String>();
    for (String tag : scenario.getSourceTagNames()) {
      if(tag.contains("@TCID"))
      {
        testCaseId.add(getTestId(tag));
      }
    }
    return testCaseId;
  }

  public String getTestId(String tag) throws Exception {
    if(!tag.contains("-")) {
      throw new Exception("Test case Id not added in Tag");
    }
    String[] testCaseId = tag.split("--");
    return testCaseId[1];
  }
}
