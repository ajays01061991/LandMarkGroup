Feature: Verify Tajalwal Hotel booking with valid and invalid airport input

  Background: Land on Hotel booking page
    Given The user navigate to url "https://www.tajawal.com/en"
    Then Click on Hotel to switch from flight


  @Smoke
  Scenario: Verify Search Filter On Tajalwal Hotel
    And Search hotel name or place "Seminyak" to checkIn
    And Add checkin date "Sun May 30 2021"
    And Add checkout date "Mon Jun 14 2021"
    When Click on Search to list hotel or hotel in place
    Then User land on Filtered List
    And Apply Filter On Popular Filters using "Free cancellation" ,Availability using "Show available hotels only",Star Rating using "5 stars" and District using "Ubud City-Centre"
    And Hotel "Tejaprana Bisma" with selected Filter should get displayed

  @Smoke
  Scenario: Verify Search Filter On Tajalwal Hotel
    And Search hotel name or place "#$#$#$#$#" to checkIn
    When Click on Search to list hotel or hotel in place
    Then Error page get displayed


  Scenario: Verify Search Filter On Tajalwal
    And From Airport "Mumbai" Boarding will happen upto "Dubai" Airport
    And From Boarding Date "Wed May 12 2021" and returning Date "Fri May 21 2021"
    When Click on Search Flight
    Then Check "tajawal" text is present in "Book Cheap flights from BOM to DXB, Save more with tajawal" displayed on Page