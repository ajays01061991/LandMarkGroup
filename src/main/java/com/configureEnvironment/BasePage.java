package com.configureEnvironment;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import stepDefinitions.Hooks;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class BasePage {
    public static WebDriver driver;
    static Wait<WebDriver> wait;

    public BasePage() {
        this.driver = Hooks.driver;
    }

    WebActions actions = new WebActions();

    public static class PropertiesHandler {
        static String selector;
        private static String properties = "test.properties";
        private static Properties selectorProp = new Properties();
        private static InputStream in = WebDriverFactory.class.getResourceAsStream("test.properties");
        /******** Log Attribute ********/
        private static Logger log = Logger.getLogger(PropertiesHandler.class);

        private PropertiesHandler() {

        }

        public static String getSelectorFromProperties(String key) {
            try {
                log.info("***********************************************************************************************************");
                log.info("[ Properties Configuration ] - Read the selector properties from: " + properties);
                selectorProp.load(in);
                selector = selectorProp.getProperty(key);

            } catch (IOException e) {
                log.error("getSelectorFromProperties Error", e);
            }

            return selector;
        }


    }

    public static void waitforelement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static class WebActions {
        /******** Log Attribute ********/
        private static Logger log = Logger.getLogger(BasePage.class);
        public WebDriver driver;

        public WebActions() {
            this.driver = Hooks.driver;
        }

        public void sendKeys(WebElement element, String value, String elementLogicalName) {
            try {
                log.info("Entering text: " + value + " at: " + elementLogicalName);
                element.sendKeys(value);
            } catch (Exception e) {
                log.error("Error occured while entering text: " + value + " at: " + elementLogicalName + "\nError message: " + e.getMessage());
            }
        }

        public void get(String url) {
            try {
                boolean isEmpty = url == null || url.trim().length() == 0;
                if (isEmpty) {
                    url = PropertiesHandler.getSelectorFromProperties("pageURL");
                }
                log.info("Opening url: " + url);
                driver.get(url);
            } catch (Exception e) {
                log.error("Error occured while opening url: " + url + "\nError message: " + e.getMessage());
            }
        }

        public void click(WebElement element, String elementLogicalName) {
            try {
                log.info("Submitting the form on element: " + elementLogicalName);
                element.click();
            } catch (Exception e) {
                log.error("Error while Submitting the form on element: " + elementLogicalName + "\n Error message: " + e.getMessage());
            }

        }

        public void clear(WebElement element, String elementLogicalName) {
            try {
                log.info("Clearing text of element: " + elementLogicalName);
                element.clear();
            } catch (Exception e) {
                log.error("Error while clearing text of element: " + elementLogicalName + "\n Error message: " + e.getMessage());
            }
        }

        public boolean isSelected(WebElement element, String elementLogicalName) {
            try {
                log.info("Determining whether or not this element: " + elementLogicalName + " is selected or not");
                return element.isSelected();
            } catch (Exception e) {
                log.error("Error while determining whether or not this element:: " + elementLogicalName + " is selected or not" + "\n Error message: " + e.getMessage());
                return false;
            }
        }

        public boolean isEnabled(WebElement element, String elementLogicalName) {
            try {
                log.info("Is the element: " + elementLogicalName + " currently enabled or not?");
                return element.isEnabled();
            } catch (Exception e) {
                log.error("Error while determining Is the element: " + elementLogicalName + " currently enabled or not?" + "\n Error message: " + e.getMessage());
                return false;
            }
        }

        public String getText(WebElement element, String elementLogicalName) {
            try {
                log.info("Trying to get the visible innerText of this element: " + elementLogicalName);
                return element.getText();
            } catch (Exception e) {
                log.error("Error while trying to get the visible innerText of this element: " + elementLogicalName + "\n Error message: " + e.getMessage());
                return null;
            }
        }

        public boolean isDisplayed(WebElement element, String elementLogicalName) {
            try {
                log.info("Validating is the element: " + elementLogicalName + " displayed or not?");
                return element.isDisplayed();
            } catch (Exception e) {
                log.error("Error while validating is the element: " + elementLogicalName + " displayed or not?\n Error message: " + e.getMessage());
                return false;
            }
        }
    }

    public static class WebDriverFactory {
        private static WebDriver driver = null;

        public static WebDriver ChromeBrowserStack() throws MalformedURLException {
            DesiredCapabilities options = new DesiredCapabilities();

            String username = System.getenv("BROWSERSTACK_USERNAME");
            if (username == null) {
                username = "BROWSERSTACK_USERNAME";
            }

            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            if (accessKey == null) {
                accessKey = "BROWSERSTACK_ACCESS_KEY";
            }

            driver = new RemoteWebDriver(
                    new URL("http://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"),options);
            return driver;
        }

        public static WebDriver CreateNewWebDriver(String browser, String os) {

            /******** The driver selected is Local: Firefox  ********/
            if (browser.equalsIgnoreCase("FIREFOX")) {
                if (os.equalsIgnoreCase("WINDOWS")) {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/files/software/" + os + "/geckodriver.exe");
                } else {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/files/software/" + os + "/geckodriver");
                }
                driver = new FirefoxDriver();
            }

            /******** The browser selected is browserStack********/
//            else if(browser.equalsIgnoreCase("BROWSERSTACK")){
//                DesiredCapabilities capability = new DesiredCapabilities();
//                capability.setPlatform(Platform.WINDOWS);
//                capability.setBrowserName("Chrome");
//                capability.setCapability("browserstack.debug", "true");
//                driver = new RemoteWebDriver(, capability);
//            }
            /******** The driver selected is Chrome  ********/

            else if (browser.equalsIgnoreCase("CHROME")) {
                if (os.equalsIgnoreCase("WINDOWS")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/files/software/" + os + "/chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/files/software/" + os + "/chromedriver");
                }
                driver = new ChromeDriver();
            }

            /******** The driver selected is Internet Explorer ********/
            else if (browser.equalsIgnoreCase("INTERNET EXPLORER")) {
                System.setProperty("webdriver.ie.driver", "src/test/resources/files/software/" + os + "/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }
            /******** The driver is not selected  ********/
            else {
                System.out.println("The Driver is not selected properly, invalid name: " + browser + ", " + os);
                return null;
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }
    }

    /**
     * Custom interface for configure  and create the webdriver.
     *
     * @author Ajay Singh
     */
    public static class CreateDriver {
        public static ThreadLocal<WebDriver> drivers;
        private static String browser, os;
        private static String logLevel;
        private static String properties = "test.properties";
        private static Properties prop = new Properties();
        private static String lvsh = "/test.properties";

        private static WebDriver driver;
        /******** Log Attribute ********/
        private static Logger log = Logger.getLogger(CreateDriver.class);

        /**
         * Get the Browser from the POM
         */
        public static WebDriver initConfig() {
            try {
                log.info("***********************************************************************************************************");
                log.info("[ POM Configuration ] - Read the basic properties configuration from: " + properties);
                InputStream in = new FileInputStream("src/main/properties/test.properties");
                prop.load(in);
                browser = prop.getProperty("browser");
                os = prop.getProperty("os");
                logLevel = prop.getProperty("logLevel");

            } catch (IOException e) {
                e.printStackTrace();
            }

            /******** POM Information ********/
            log.info("[ POM Configuration ] - OS: " + os + " | Browser: " + browser + "|");
            log.info("[ POM Configuration ] - Logger Level: " + logLevel);
            log.info("***********************************************************************************************************");

            /****** Load the driver *******/
            driver = WebDriverFactory.CreateNewWebDriver(browser, os);


            /******** Clean Cookies, maxinize and declare Timeout on the Driver *******/
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }
    }
}
