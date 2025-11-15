package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties pr;

	@BeforeClass(groups = { "DDT", "Regression", "Sanity", "Master" })
	@Parameters({ "os", "browser" })
	public void stepUp(String os, String br) throws IOException {
		FileReader fr = new FileReader("./src/test/resources/config.properties");
		pr = new Properties();
		pr.load(fr);

		// If test is launched for remote system in selenium grid
		if (pr.getProperty("execution_env").equalsIgnoreCase("remote"))
		
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			System.out.println("Tests are running in Remote");
			// Operating System Setting
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("Not Matching Operation System");
				return;
			}

			// Browser
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;

			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;

			default:
				System.out.println("No Matching Browser");
				return;
			}
			
			//Webdriver initialized 
			driver = new RemoteWebDriver(new URL("http://192.168.0.103:4444/wd/hub"),capabilities);
		}

		// Test is launched in the local environment
		if (pr.getProperty("execution_env").equalsIgnoreCase("local"))
		
		{
			System.out.println("Tests are running in Local");
			
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
		}

		// Read the URL value from property file.
		driver.get(pr.getProperty("url"));

		// Setup the Logs
		logger = LogManager.getLogger(this.getClass());

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	// This method will give the random string
	public String randomStringGeneration() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}

	// This method will give the random string
	public String randomNumericGeneration() {
		String randomNumeric = RandomStringUtils.randomNumeric(5);
		return randomNumeric;
	}

	// This method will give the random string
	public String randomalphaNumeric() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		String randomNumeric = RandomStringUtils.randomNumeric(5);

		String randomalphaNumeric = (randomString + "@" + randomNumeric);
		return randomalphaNumeric;
	}

//TakeScreenshot
	public String captureScreen(String tname) {

		// take the time stamp
		String dateTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		TakesScreenshot takesScreenShot = (TakesScreenshot) driver;
		File sourceFile = takesScreenShot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + dateTimeStamp
				+ ".png";

		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
