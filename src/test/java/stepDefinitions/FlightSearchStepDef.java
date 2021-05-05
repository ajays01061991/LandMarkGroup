package stepDefinitions;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import pages.TajalwalFlightBooking;

public class FlightSearchStepDef {

    private static Logger log = Logger.getLogger(FlightSearchStepDef.class);
    private TajalwalFlightBooking TajalwalFlightBooking;


    @Given("^The user navigate to url \"([^\"]*)\"$")
    public void theUserNavigateToUrl(String url) throws Throwable {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.getUrl(url);
    }

    @And("^From Airport \"([^\"]*)\" Boarding will happen upto \"([^\"]*)\" Airport$")
    public void fromAirportBoardingWillHappenUptoAirport(String departure, String Arrival) throws Throwable {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.addDepartureAirport(departure);
        TajalwalFlightBooking.addArrivalAirport(Arrival);
    }


    @And("^From Boarding Date \"([^\"]*)\" and returning Date \"([^\"]*)\"$")
    public void fromBoardingDateAndReturningDate(String departuredate, String arrivaldate) throws Throwable {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.addDepartureDate(departuredate);
        TajalwalFlightBooking.addArrivalDate(arrivaldate);

    }

    @When("^Click on Search Flight$")
    public void clickOnSearchFlight() {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.clickSearchButton();
    }

    @Then("^Check \"([^\"]*)\" text is present in \"([^\"]*)\" displayed on Page$")
    public void checkTextIsPresentInDisplayedOnPage(String present, String title) throws Throwable {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.checkModifySearch(present,title);
    }

    @Then("^Error message should get displayed$")
    public void errorMessageShouldGetDisplayed() {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.checkEntryWithInvalidChar();
    }

//    @And("^Apply filter like Stop as \"([^\"]*)\" ,Any time as \"([^\"]*)\", Baggage as \"([^\"]*)\"$")
//    public void applyFilterLikeStopAsAirlinesAsBaggageAs(String stop, String Anytime, String baggage) throws Throwable {
//        TajalwalFlightBooking = new TajalwalFlightBooking();
//        TajalwalFlightBooking.applyFilter(stop,Anytime,baggage);
//    }

    @Then("^Result count from filter should be correct$")
    public void resultCountFromFilterShouldBeCorrect() {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.checkFilter();
    }

    @And("^Apply filter like Stop as \"([^\"]*)\" ,Any time as \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" , Baggage as \"([^\"]*)\"$")
    public void applyFilterLikeStopAsAnyTimeAsAndAndBaggageAs(String stop, String Anytime1, String Anytime2, String Anytime3, String baggage) throws Throwable {
        TajalwalFlightBooking = new TajalwalFlightBooking();
        TajalwalFlightBooking.applyFilter(stop,Anytime1,Anytime2,Anytime3,baggage);
    }
}
