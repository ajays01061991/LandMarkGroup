package pages;

import com.configureEnvironment.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

public class TajalwalHotelBooking extends BasePage.WebActions {

    private static Logger log = Logger.getLogger(TajalwalHotelBooking.class);
    private static By hotel_tab = By.id("uncontrolled-tab-example-tab-hotels");
    private static By addhotel = By.xpath("//input[contains(@class,'AutoComplete__Input') and @placeholder='Search for hotels or places']");
    private static By checkingDate = By.xpath("//div[@data-testid='HotelsSearchBox__FromDateButton']");
    private static By checkoutDate = By.xpath("//div[@data-testid='HotelsSearchBox__ToDateButton']");
    private static By searchHotel = By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']");
    private static By nextPageExist = By.xpath("//h3[text()='Stay flexible']");
    private static By closePopUp = By.xpath("//span[text()='Close']");
    private static By errorMessage = By.xpath("//h4[text()='Sorry, we do not have any hotels in \"#$#$#$#$#\"']");
    private static By lowestprice = By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']");
    private static By distance = By.xpath("HotelSearchResult__sort__DISTANCE");
    private static By verifyDataPostSorting = By.xpath("//div[@data-testid='HotelSearchResult__Hotel0__address']");

    private WebElement findElementUsingText(String searchText) {
        return driver.findElement(By.xpath("//div[@class='DayPicker-Day' and @aria-label='" + searchText + "']"));
    }

    private WebElement findElementUsingLabel(String searchText) {
        return driver.findElement(By.xpath("//label[text()='" + searchText + "']"));
    }

    private WebElement findElementUsingHotelName(String searchText) {
        return driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__Hotel0__TitleLabel' and text()='" + searchText + "']"));
    }

    public void switchToHotelTab() {
        log.info("Switch From Flight to Hotel Tab");

        Assert.assertTrue("Hotel Tab not present", driver.findElement(hotel_tab).isDisplayed());
        click(driver.findElement(hotel_tab), "Switch to Hotel TAB");
    }

    public void addHotel(String hotel_name) {
        log.info("Select Hotel or Place details ");

        Assert.assertTrue("Hotel details not found", driver.findElement(addhotel).isDisplayed());
        sendKeys(driver.findElement(addhotel), hotel_name, "Search this Hotel");
    }

    public void addCheckInDate(String date) {
        log.info("Select Check in date for Hotel\n");

        Assert.assertTrue("CheckIn date not available", driver.findElement(checkingDate).isDisplayed());
        click(driver.findElement(checkingDate), "Click to select Date");

        Assert.assertTrue("Date not available", findElementUsingText(date).isDisplayed());
        click(findElementUsingText(date), "On this day person will checkIn");
    }

    public void addCheckOutDate(String date) {
        log.info("Select Check Out date for Hotel\n");

        Assert.assertTrue("No check out date present", driver.findElement(checkoutDate).isDisplayed());
        click(driver.findElement(checkoutDate), "Click to select Date");

        Assert.assertTrue("Can't select checkout date", findElementUsingText(date).isDisplayed());
        click(findElementUsingText(date), "On this day person will checkIn");
    }

    public void clickOnSearch() {
        Assert.assertTrue("Search button not present", driver.findElement(searchHotel).isDisplayed());
        click(driver.findElement(searchHotel), "Click to Search with Filter applied");
    }

    public void validSearchResult() {
        Assert.assertTrue("Haven't reached till next page", driver.findElement(nextPageExist).isDisplayed());
    }

    public void invalidInput() {
        click(driver.findElement(closePopUp), "Close pop up");
        Assert.assertTrue("Site is reachable", driver.findElement(errorMessage).isDisplayed());
    }

    public void applyPostSearchFilter() {
        log.info("After Search apply filter to get refined hotel list\n");

        Assert.assertTrue("Lowest price button", driver.findElement(lowestprice).isDisplayed());
        click(driver.findElement(lowestprice), "Search for hotel with lowest price");

        Assert.assertTrue("Check Stop filter is present", driver.findElement(distance).isDisplayed());
        click(driver.findElement(distance), "Filter with distance");
    }

    public void checkHotelName() {
        Assert.assertTrue("Hotel name not present", driver.findElement(verifyDataPostSorting).isDisplayed());
    }

    public void applyPopularFilters(String popular_filter, String availability, String star_rating, String district) {
        log.info("Apply filter to refine hotel search\n");

        Assert.assertTrue("Search for popular filter", findElementUsingLabel(popular_filter).isDisplayed());
        click(findElementUsingLabel(popular_filter), "Filter with popular search");

        Assert.assertTrue("Search for popular filter", findElementUsingLabel(availability).isDisplayed());
        click(findElementUsingLabel(availability), "Filter with popular search");

        Assert.assertTrue("Search for popular filter", findElementUsingLabel(star_rating).isDisplayed());
        click(findElementUsingLabel(star_rating), "Filter with popular search");

        Assert.assertTrue("Search for popular filter", findElementUsingLabel(district).isDisplayed());
        click(findElementUsingLabel(district), "Filter with popular search");
    }

    public void verifyFilterResult(String name) {
        Assert.assertTrue("Hotel name doesn't exist", findElementUsingHotelName(name).isDisplayed());
    }
}
