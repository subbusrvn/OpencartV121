package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	// Constructor Invoked
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name = 'email']")
	WebElement user_name;

	@FindBy(xpath = "//input[@name = 'password']")
	WebElement pass_word;

	@FindBy(xpath = "//input[@type = 'submit']")
	WebElement login_btn;

	// Action Methods
	public void setUserName(String username) {
		user_name.sendKeys(username);
	}

	public void setPassword(String password) {
		pass_word.sendKeys(password);
	}

	public void loginBttonClick() {
		login_btn.click();
	}

}
