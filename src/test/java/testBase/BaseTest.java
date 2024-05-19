package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	public WebDriver driver;
	public Logger logger;
	public Properties property;

	@BeforeClass(groups = {"sanity", "regression", "master"})
	@Parameters({ "os", "browser" })
	public void setUp(String os, String browser) throws IOException {
		
		//Loading properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		property = new Properties();
		property.load(file);

		//Loading log4j file
		logger = LogManager.getLogger(this.getClass());

		switch (browser.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			default: System.out.println("No matching browser");
					 return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(property.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"sanity", "regression", "master"})
	public void tearDown() {

		driver.quit();
	}

	public String randomString() {

		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomNumber() {

		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphaNumeric() {

		return RandomStringUtils.randomAlphabetic(5) + "_" + RandomStringUtils.randomNumeric(10);
	}
	
	public String captureScreen(String name) {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
