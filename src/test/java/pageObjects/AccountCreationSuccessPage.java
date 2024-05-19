package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreationSuccessPage extends BasePage{

	public AccountCreationSuccessPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement successMessage;
	
	public String getConfirmationMessage() {
		try {
			return successMessage.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
}
