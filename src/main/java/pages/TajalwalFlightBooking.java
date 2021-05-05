package pages;

import com.configureEnvironment.BasePage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TajalwalFlightBooking extends BasePage.WebActions {

    private static Logger log = Logger.getLogger(TajalwalFlightBooking.class);
    private static By departAirport = By.xpath("//input[@placeholder='Origin']");
    private static By arivalAirport = By.xpath("//input[@placeholder='Destination']");
    private static By departureDate = By.xpath("//div[contains(@data-testid,'FlightSearchBox__FromDateButton')]");
    private static By arrivalDate = By.xpath("//div[contains(@data-testid,'FlightSearchBox__ToDateButton')]");
    private static By searchFlight = By.xpath("//button[contains(@data-testid,'FlightSearchBox__SearchButton')]");
    private static By errorMessage = By.xpath("//span[text()='Please enter a valid departure city or airport']");
    private static By flightDetails = By.xpath("//button[@data-testid='Group0__SelectFlightButton']");
    private static By waitforLoadingBar = By.xpath("//div[@data-testid='FlightSearchResults__ProgressBar__finished']");

    public void getUrl(String url) {
        get(url);
    }

    private WebElement findElementUsingText(String searchText) {
        log.info("Parameterize xpath to check for specific date in Date Picker\n");
        return driver.findElement(By.xpath("//div[@class='DayPicker-Day' and @aria-label='" + searchText + "']"));
    }

    private WebElement findElementUsingLabel(String searchText) {
        log.info("Parameterize xpath to check for specific text on WebPage\n");
        return driver.findElement(By.xpath("//label[text()='" + searchText + "']"));
    }

    public void addDepartureAirport(String name) {
        log.info("Add Airport from where User will Depart from\n");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assert.assertTrue("Check Stop filter is present", driver.findElement(departAirport).isDisplayed());
        click(driver.findElement(departAirport), "Add departure Airport");

        Assert.assertTrue("Check Stop filter is present", driver.findElement(departAirport).isDisplayed());
        sendKeys(driver.findElement(departAirport), name, "Added departure Airport");
    }

    public void addArrivalAirport(String name) {
        log.info("Add Airport from where User will Depart from\n");

        Assert.assertTrue("Check Stop filter is present", driver.findElement(arivalAirport).isDisplayed());
        click(driver.findElement(arivalAirport), "Add departure Airport");

        Assert.assertTrue("Check Stop filter is present", driver.findElement(arivalAirport).isDisplayed());
        sendKeys(driver.findElement(arivalAirport), name, "Added arrival Airport");
    }

    public void addDepartureDate(String date) {
        log.info("Add Departure date on which booking need to be done\n");

        Assert.assertTrue("Check Stop filter is present", driver.findElement(departureDate).isDisplayed());
        click(driver.findElement(departureDate), "Click to select arrival date");

        Assert.assertTrue("Check Stop filter is present", findElementUsingText(date).isDisplayed());
        click(findElementUsingText(date), "Click on Departure Date");
    }

    public void addArrivalDate(String date) {
        log.info("Add Arriavl date on which booking need to be done\n");

        Assert.assertTrue("Check Stop filter is present", driver.findElement(arrivalDate).isDisplayed());
        click(driver.findElement(arrivalDate), "Click to select departure date");

        Assert.assertTrue("Check Stop filter is present", findElementUsingText(date).isDisplayed());
        click(findElementUsingText(date), "Click on Departure Date");
    }

    public void clickSearchButton() {
        log.info("Search Flight for added details\n");

        Assert.assertTrue("Check Stop filter is present", driver.findElement(searchFlight).isDisplayed());
        click(driver.findElement(searchFlight), "Click on Search Flight");
    }

    public void checkModifySearch(String present, String title) {
        log.info("Check once user hit Search ,he should land on list of flight page \n");
        String pageTitle = driver.getTitle();
        if (present.equals("see")) {
            log.info("The title should be present");
            Assert.assertTrue("The title is not present", pageTitle.equals(title));
        } else if (present.equals("not see")) {
            log.info("The title should not be present");
            Assert.assertFalse("The title is present", pageTitle.equals(title));
        }
    }

    public void checkEntryWithInvalidChar() {
        log.info("Check by searching Departure airport with special char,error should get generated\n");
        Assert.assertTrue("Error message doesn't get displayed", driver.findElement(errorMessage).isDisplayed());
    }

    public void applyFilter(String stop, String Anytime1, String Anytime2, String Anytime3, String baggage) {
        log.info("Check applying multiple filter to refine search for list of flight\n");
        WebDriverWait wait =new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(findElementUsingLabel(stop)));

        Assert.assertTrue("Check Stop filter is present", findElementUsingLabel(stop).isDisplayed());
        click(findElementUsingLabel(stop), "No of stop");

        Assert.assertTrue("Check Stop filter is present", findElementUsingLabel(Anytime1).isDisplayed());
        click(findElementUsingLabel(Anytime1), "No of stop");

        Assert.assertTrue("Check Stop filter is present", findElementUsingLabel(Anytime2).isDisplayed());
        click(findElementUsingLabel(Anytime2), "No of stop");

        Assert.assertTrue("Check Stop filter is present", findElementUsingLabel(Anytime3).isDisplayed());
        click(findElementUsingLabel(Anytime3), "No of stop");

        Assert.assertTrue("Check Stop filter is present", findElementUsingLabel(baggage).isDisplayed());
        click(findElementUsingLabel(baggage), "No of stop");
    }

    public void checkFilter() {
        log.info("Verify after filter condition if user gets correct flight details\n");
        Assert.assertTrue("Count not matched", driver.findElement(flightDetails).isDisplayed());
    }
}
