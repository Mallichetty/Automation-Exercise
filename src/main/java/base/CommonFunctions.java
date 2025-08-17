package base;

import drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.LoggerUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonFunctions {

    private WebDriver driver;
    private WebDriverWait wait;

    public CommonFunctions() {
        this.driver = DriverFactory.getDriver();
        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement getElement(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            LoggerUtil.getLogger().error("Element not found: " + locator);
            throw e;
        } catch (TimeoutException e) {
            LoggerUtil.getLogger().error("Timeout waiting for element: " + locator);
            throw e;
        }
    }

    public void click(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            getElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(locator));
            LoggerUtil.getLogger().error("Failed to click element: " + locator);
            e.printStackTrace();
        }
    }

    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Failed to click element: " + element);
            e.printStackTrace();
        }
    }

    public void moveToElement(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Failed to move element: " + locator);
            e.printStackTrace();
        }
    }

    public void moveToElementAction(By locator) {
        try {
            new Actions(driver).moveToElement(getElement(locator)).build().perform();
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Failed to move element: " + locator);
            e.printStackTrace();
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Failed to type in element: " + locator);
            e.printStackTrace();
        }
    }

    public String getText(By locator) {
        try {
            return getElement(locator).getText();
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Failed to get text from element: " + locator);
            return null;
        }
    }

    public boolean isDisplayed(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Element not displayed: " + locator);
            return false;
        }
    }

    public boolean isDisplayed(String xpath) {
        try {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Element not displayed: " + xpath);
            return false;
        }
    }

    public void handleShadowRoot(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll('ins.adsbygoogle').forEach(e => e.style.display='none');"
            );
            LoggerUtil.getLogger().info("Ads hidden successfully.");
        } catch (Exception e) {
            LoggerUtil.getLogger().info("No ads found or unable to hide ads: " + e.getMessage());
        }
    }


    public List<WebElement> getElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error finding elements: " + locator, e);
            return new ArrayList<>();
        }
    }

}
