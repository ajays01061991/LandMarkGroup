package stepDefinitions;


import com.configureEnvironment.BasePage;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.ITestResult;

import java.io.*;
import java.util.Date;

public class Hooks {
	public static WebDriver driver;
	Logger log = Logger.getLogger(Hooks.class);
	Scenario scenario = null;
	private static boolean isLoaded = true;

	/*
	 * Delete all cookies at the start of each scenario to avoid shared state
	 * between tests
	 */
	@Before
	public void initDriver(Scenario scenario) {
		String fileName = new Date().getTime() + ".html";
		this.scenario = scenario;

		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath("output/STYLI"+fileName);
		log.info(
				"***********************************************************************************************************");
		log.info("[ Configuration ] - Initializing driver configuration");
		log.info(
				"***********************************************************************************************************");
		driver = BasePage.CreateDriver.initConfig();
	}

	/*
	 * Embed a screenshot in test report if test is marked as failed
	 */
	@After
	public void embedScreenshot(Scenario scenario) {
		/*Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", System.getProperty("os.name"));
		Reporter.setTestRunnerOutput("OMS Automation report");*/
		if (scenario.isFailed()) {
			try {

				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("$j(\"a:contains('Logout')\")[0].click()");
				scenario.write("The scenario failed.");
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "properties/screenshot");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}

		log.info(
				"***********************************************************************************************************");
		log.info("[ Driver Status ] - Clean and close the intance of the driver");
		log.info(
				"***********************************************************************************************************");
		driver.quit();
//		String textToAdd="src/test/resources/features";
//		String fileName="target/rerun.txt";

	}

//	public static void writeToFile1(String dataToBeInserted, String fileName) {
//		byte data[] = dataToBeInserted.getBytes();
//		try {
//			RandomAccessFile f = new RandomAccessFile(new File(fileName), "rw");
//			f.getChannel().position(0);
//			f.write(data);
//			f.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static String readFile(String fileName) {
//		final String FILENAME = fileName;
//		String sCurrentLine = null;
//		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
//			while ((sCurrentLine = br.readLine()) != null) {
//				return sCurrentLine;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return sCurrentLine;
//	}
}
