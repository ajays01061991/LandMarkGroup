Feature: Verify Tajalwal Flight booking with valid and invalid airport input

  Background: Land on Flight booking page
    Given The user navigate to url "https://www.tajawal.com/en"

  @Smoke
  Scenario: Verify Search Filter On Tajalwal
    And From Airport "Mumbai" Boarding will happen upto "Dubai" Airport
    And From Boarding Date "Wed May 12 2021" and returning Date "Fri May 21 2021"
    When Click on Search Flight
    And Apply filter like Stop as "Any number of stops" ,Any time as "Before 06:00" and "06:00 — 11:59" and "12:00 — 18:00" , Baggage as "Checked baggage included"
    Then Result count from filter should be correct


  Scenario: Verify Search Filter On Tajalwal Hotel
    And Search hotel name or place "Seminyak" to checkIn
    And Add checkin date "Sun May 30 2021"
    And Add checkout date "Mon Jun 14 2021"
    When Click on Search to list hotel or hotel in place
    Then User land on Filtered List
   # And Apply sorting using Lowest price and Distance
    And Check Hotel with least distance from location get displayed

  @Smoke
  Scenario: Verify Invalid Search Filter On Tajalwal
    When From Airport "**&&^^%" Boarding will happen upto "" Airport
    Then Error message should get displayed

