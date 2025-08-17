package stepDefinitions;

import base.CommonFunctions;
import drivers.DriverFactory;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.io.ByteArrayInputStream;

public class Hooks {

    private static WebDriver driver;
    private final CommonFunctions commonFunctions;

    public Hooks()
    {
        commonFunctions = new CommonFunctions();
    }

    @Before
    public void setUp() {
        driver = DriverFactory.initDriver(ConfigReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseUrl"));

        System.out.println("Browser launched at URL: " + ConfigReader.getProperty("baseUrl"));
    }

    @BeforeStep
    public void closeExternalAds()
    {
        commonFunctions.handleShadowRoot(driver);
    }

    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Step Screenshot - " + scenario.getName(), new ByteArrayInputStream(screenshot));
        }
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        System.out.println("Browser closed");
    }
}