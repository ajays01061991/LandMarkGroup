package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import pages.TajalwalHotelBooking;

public class TajalwalHotelStepDef {

    private static Logger log = Logger.getLogger(TajalwalHotelBooking.class);
    private TajalwalHotelBooking tajalwalHotelBooking;

    @Then("^Click on Hotel to switch from flight$")
    public void clickOnHotelToSwitchFromFlight() {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.switchToHotelTab();
    }

    @And("^Search hotel name or place \"([^\"]*)\" to checkIn$")
    public void searchHotelNameOrPlaceToCheckIn(String addHotel) throws Throwable {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.addHotel(addHotel);
    }

    @And("^Add checkin date \"([^\"]*)\"$")
    public void addCheckinDate(String date) throws Throwable {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.addCheckInDate(date);
    }

    @And("^Add checkout date \"([^\"]*)\"$")
    public void addCheckoutDate(String date) throws Throwable {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.addCheckOutDate(date);
    }

    @When("^Click on Search to list hotel or hotel in place$")
    public void clickOnSearchToListHotelOrHotelInPlace() {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.clickOnSearch();
    }

    @Then("^User land on Filtered List$")
    public void userLandOnFilteredList() {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.validSearchResult();
    }

    @Then("^Error page get displayed$")
    public void errorPageGetDisplayed() {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.invalidInput();
    }

    @And("^Apply sorting using Lowest price and Distance$")
    public void applySortingUsingLowestPriceAndDistance() {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.applyPostSearchFilter();
    }

    @And("^Check Hotel with least distance from location get displayed$")
    public void checkHotelWithLeastDistanceFromLocationGetDisplayed() {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.checkHotelName();
    }

    @And("^Apply Filter On Popular Filters using \"([^\"]*)\" ,Availability using \"([^\"]*)\",Star Rating using \"([^\"]*)\" and District using \"([^\"]*)\"$")
    public void applyFilterOnPopularFiltersUsingAvailabilityUsingStarRatingUsingAndDistrictUsing(String popular_filter, String availability, String star_rating, String district) throws Throwable {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.applyPopularFilters(popular_filter,availability,star_rating,district);
    }

    @And("^Hotel \"([^\"]*)\" with selected Filter should get displayed$")
    public void hotelWithSelectedFilterShouldGetDisplayed(String hotelName) throws Throwable {
        tajalwalHotelBooking = new TajalwalHotelBooking();
        tajalwalHotelBooking.verifyFilterResult(hotelName);
    }
}
