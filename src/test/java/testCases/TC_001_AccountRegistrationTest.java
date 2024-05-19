package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountCreationSuccessPage;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TC_001_AccountRegistrationTest extends BaseTest {

	@Test(groups = {"regression", "master"})
	public void verify_account_registration() {
		
		logger.info("***** Starting TC_001_AccountRegistrationTest *****");
		
		try {
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			hp.clickRegister();
			logger.info("Clicked on Registration link");
			
			AccountRegistrationPage arp = new AccountRegistrationPage(driver);
			logger.info("Entering customer details");
			arp.setFirstName(randomString().toUpperCase());
			arp.setLastName(randomString().toUpperCase());
			arp.setEmail(randomAlphaNumeric()+"@gmail.com");
			arp.setTelephone(randomNumber());
			String password = randomAlphaNumeric();
			arp.setPassword(password);
			arp.setPasswordConfirm(password);
			arp.setPrivacyPolicy();
			arp.clickContinue();
			logger.info("Clicked on Continue button");
			
			AccountCreationSuccessPage acsp = new AccountCreationSuccessPage(driver);
			logger.info("Validating expected message");
			String message = acsp.getConfirmationMessage();
			Assert.assertEquals(message, "Your Account Has Been Created!");
		} catch (Exception e) {
			
			logger.info("Test failed");
			Assert.fail();
		}

		logger.info("***** Finished TC_001_AccountRegistrationTest *****");
	}
}
