
Cucumber-BDD-Automation-Framework
Behavior Driven Development Cucumber - Selenium based automation framework including Extent reports

This framework contains sample code containing:
      •	2 feature (feature file)
      •	4 Scenarios
      •	Scenario contain searching flight/hotel with filter available and searching flight/hotel with invalid input(using special char)
      •	Parameterization in statement is done as test cases are single data based.
Directory structure:
 ![image](https://user-images.githubusercontent.com/67786129/117158913-752a0d80-addd-11eb-9515-4a1a2272312d.png)


Tools & Technologies
      •	Automation Tools : Selenium Web Driver (3.8.1 Version) & Cucumber
      •	Project Setup : Apache maven (3.5.2 Version)
      •	Object Repository Design Pattern : Page Object Model
      •	Test Execution Report: Extent API Report & Cucumber
      •	Browser Compatibility: Cross Browser Execution
      •	Screenshot: Take screen shot API to cover the flow and capture invalid screenshots
      •	Implicit and explicit wait
      •	Assertions : Hard & Soft
      •	Log4j: Logging
      •	Report: Extent Report
      •	Support: BrowserStack support
      
Project Structure

•	      com.configurationEnvironment:-
      o	This package has BasePage where webdriver initialization, which browser to use for automation, Browserstack configuration.
      o	All activities that we can perform on UI element like select, sendkeys, click etc are maintained in this BasePage.class file.

•	      Pages:-
      o	This Packages include TajalwalFlightBooking.java and TajalwalHotelBooking.java where locator and actions that will be performed on UI are present.

•	     ReportConfiguration:-
      o	This package contain Hook.java where configuration related to extent report are present.
      o	It have @Before to initialize report at start and @After to generate report.
•	     com.TestRunner.TestRunner:- 
      o	This package has class which run the feature files according to the test cases. In this package we can calling the feature files. 
      o	Runner extension should be common for all the java files as we are using in pom.xml to include for running parallel. 
      o	Runner class call the cucumber feature file by adding @CucumberOptions annotation (which is used to configuration for feature file).
      o	TestRunner have report ExtentReportFormatter to generate Extent report and create rerun file for failed test cases.
•	StepDefinition:-
      o	This file is the glue between Feature file and actual code.
      o	For each feature statement had created individual step in stepdefinition.
      o	FlightSearchStepDef contains are step for TajalwalFlight feature which have in turn point to code in TajalwalFlightBooking.java in Pages package.
      o	TajalwalHotelStepDef contains are step for TajalwalHotel feature which have in turn point to code in TajalwalHotelBooking.java in Pages package.
•	Features:- 
      o	In this package you can create all the feature files which has step to be followed in Automation Script.
      o	TajalwalFlight.feature contains 2 scenario where first scenario is to check flight booking with filter search and second is to check passing invalid input with     special char.
      o	TajalwalHotel.feature contains 2 scenario where first scenario is to check hotel booking with filter search and second is to check passing invalid input with special char.
•	Output:
      o	This folder contains Generated report in html format.
•	Properties:
      o	Properties like username , password, OS , Browser , platform ,URL, jdbc connection etc are kept to be loaded from properties file.
      o	Framework contain 2 properties file log4j and test properties for logging and loading URL ,browser and OS.
•	ExtentReport:- 
      o	ExtentReport folder has extent-report.config file which contains all configuration. It has a target folder which contains extent report in form of html. It is used as a report to see information about test cases which are passes/failed.
•	FailedScreenShot:-
      o	 Scenario is used in this project for checking the Login by giving hotel/flight name. In this case, if cases failed then we capture the screen short and save in this folder FailedScreenShot
•	Pom.xml:- 
      o	Pom.xml file is very important. It will include all dependencies and download automatically in its repository. So we don’t need to add external jar files to project. 
      o	Once you completed all the above steps then you have to run the pom.xml file. It will runs all the test cases paralleled. Don’t need to do any change in this file. You just check your jdk 11 version and change in pom.xml which is showed in below screen shot. For example here is 11 and if you have 11 then change it and then run the file.


