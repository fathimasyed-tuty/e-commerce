package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtPasswordConfirm;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPrivacy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	public void setFirstName(String fname) {

		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {

		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {

		txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {

		txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {

		txtPassword.sendKeys(password);
	}

	public void setPasswordConfirm(String cpassword) {

		txtPasswordConfirm.sendKeys(cpassword);
	}

	public void setPrivacyPolicy() {

		chkPrivacy.click();
	}

	public void clickContinue() {

		//sol1 
		btnContinue.click();
		
		//sol2 
		//btnContinue.submit();
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
					
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
		
		//sol5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol6  
		//WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
}
