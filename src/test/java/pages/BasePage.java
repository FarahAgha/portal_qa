package pages;

import helpers.Log;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public abstract class BasePage<T extends WebDriver> {

    // declaring driver
    protected WebDriver driver;
    // declaring time out in seconds
    protected final int waitTimeOutSeconds;

    // Initializing constructor
    public BasePage(WebDriver driver, int waitTimeOutSeconds) {
        this.driver = driver;
        this.waitTimeOutSeconds = waitTimeOutSeconds;

    }
//
//    public WebElement findElement(By by) {
//        return driver.findElement(by);
//    }
//
//    public List<WebElement> findElements(By by) {
//        return driver.findElements(by);
//    }
//
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
//
//    protected List<WebElement> finds(By locator) {
//        return driver.findElements(locator);
//    }
//
//    public boolean elementExists(By by) {
//        try {
//            findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
//    public void elementIsVisible(By e) {
//        wait_until_true_or_timeout(ExpectedConditions.visibilityOfElementLocated(e));
//    }
//
    public void elementIsPresent(By e) {
        wait_until_true_or_timeout(ExpectedConditions.presenceOfElementLocated(e));
    }
    /**
     * wait until element is clickable
     */
    public void elementIsClickable(By e) {
        wait_until_true_or_timeout(ExpectedConditions.elementToBeClickable(e));
    }

    /**
     * wait until condition is true or timeout kicks in
     */
    protected <V> V wait_until_true_or_timeout(ExpectedCondition<V> isTrue) {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, waitTimeOutSeconds)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(isTrue);
    }

    public boolean elementIsDisplayed(By by) {
        return waitUntilElementIsVisible(by).isDisplayed();
    }
    public WebElement waitUntilElementIsVisible(By by) {
        return new WebDriverWait(driver, waitTimeOutSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
//
//    public boolean elementIsSelected(By by) {
//        return waitUntilElementIsVisible(by).isSelected();
//    }
//
//    public void chooseSelectByValue(By by, String value) {
//        new Select(findElement(by)).selectByValue(value);
//    }
//
//    public void chooseSelectByText(By by, String text) {
//        new Select(findElement(by)).selectByVisibleText(text);
//    }
//
    public void enterText(By by, String text) {
        waitUntilElementIsClickable(by).sendKeys(text);
    }
    public WebElement waitUntilElementIsClickable(By by) {
        return new WebDriverWait(driver, waitTimeOutSeconds).until(ExpectedConditions.elementToBeClickable(by));
    }

//    public static void scroll(WebDriver driver) {
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("window.scrollBy(0,250)", "");
//    }
//
    public void clickElement(WebDriver driver, By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            //wait for element to be present in DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            //scroll element into view
            WebElement element = driver.findElement(by);
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            //wait for element to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(by));
            js.executeScript("arguments[0].click();", element);

        } catch (StaleElementReferenceException e) {
            Log.info("StaleElementReferenceException-" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (WebDriverException e) {
            Log.info("WebDriverException getMessage-" + e.getMessage());
            e.printStackTrace();
            Log.info("WebDriverException done");
        }
    }

//    public abstract void HomePageDisplays();
//
//    public boolean click(By by) {
//        boolean result = false;
//        int attempts = 0;
//        while(attempts < 2) {
//            try {
//                clickElement(driver,by,30);
//                result = true;
//                break;
//            } catch(StaleElementReferenceException e) {
//            }
//            attempts++;
//        }
//        return result;
//    }
//
//    public void sleep(long l) {
//        try {
//            Thread.sleep(l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void switchToWindow(int numberWindow) {
//        int i = 1;
//        for (i = 1; i < 10; i++) {
//            if (driver.getWindowHandles().size() >= numberWindow) {
//                driver.switchTo().window(driver.getWindowHandles().toArray()[numberWindow].toString());
//                driver.close();
//                driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
//            }
//            sleep(1000);
//            Log.info("Waiting-" + i);
//        }
//        if (i == 10) {
//            Assert.fail("Window " + numberWindow + " is not available");
//        }
//        waitForPageToLoad();
//    }
//
//    public void waitForPageToLoad() {
//
//        for (int i = 1; i <= 60; i++) {
//            if (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")) {
//                break;
//            }
//            sleep(1000);
//        }
//    }
//
//    public void takeShot()
//
//    {
//        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        try {
//            // now copy the  screenshot to desired location using copyFile //method
//            FileUtils.copyFile(src, new File("D:/Users/gholmes/IdeaProjects/emr_qa/selenium/log/screenshots"));
//        }
//        catch (IOException e)
//        {
//            System.out.println(e.getMessage());
//
//        }
//    }
//
//    public void takeWebElementScreenshot(By by, String fileName) {
//        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        WebElement element = driver.findElement(by);
//        Point p = element.getLocation();
//
//        int width = element.getSize().getWidth();
//        int height = element.getSize().getHeight();
//
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(screen);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width,
//                height);
//
//        File testFile = new File("./log/" + "ActualScreenshots/" + fileName + ".png");
//        try {
//            ImageIO.write(dest, "png", testFile);
//            // ImageIO.write(dest, "png", testFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    void switchToNextTab(){
        Set<String> handles =driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        //iterate through your windows
        while (it.hasNext()) {
            String parent = it.next();
            String newwin = it.next();
            driver.switchTo().window(newwin);
        }
    }
}
