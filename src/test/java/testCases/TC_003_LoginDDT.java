package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseTest {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void verify_login(String email, String password, String result) {
		
		logger.info("***** Starting TC_003_LoginDDT *****");
		
		try {
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			hp.clickLogin();
			logger.info("Clicked on Login link");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering login details");
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			logger.info("Clicked on Login button");
			
			MyAccountPage mac = new MyAccountPage(driver);
			boolean targetPage = mac.isMyAccountPageExists();
			
			if(result.equalsIgnoreCase("Valid")) {
				if(targetPage == true) {
					
					mac.clickLogout();
					Assert.assertTrue(true);
				} else {
					
					Assert.assertTrue(false);
				}
			}
			
			if(result.equalsIgnoreCase("Invalid")) {
				if(targetPage == true) {
					
					mac.clickLogout();
					Assert.assertTrue(false);
				} else {
					
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			
			logger.info("Test failed");
			Assert.fail("An exception occured: " + e.getMessage());
		}

		logger.info("***** Finished TC_003_LoginDDT *****");
	}
}
