package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC_002_LoginTest extends BaseTest {

	@Test(groups = {"sanity", "master"})
	public void verify_login() {
		
		logger.info("***** Starting TC_002_LoginTest *****");
		
		try {
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			hp.clickLogin();
			logger.info("Clicked on Login link");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering login details");
			lp.setEmail(property.getProperty("email"));
			lp.setPassword(property.getProperty("password"));
			lp.clickLogin();
			logger.info("Clicked on Login button");
			
			MyAccountPage mac = new MyAccountPage(driver);
			boolean targetPage = mac.isMyAccountPageExists();
			Assert.assertEquals(targetPage, true, "Login failed");
		} catch (Exception e) {
			
			logger.info("Test failed");
			Assert.fail();
		}

		logger.info("***** Finished TC_002_LoginTest *****");
	}
}
