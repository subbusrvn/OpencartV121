package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// Constructor invoked from the BasePage class
	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md' and text() = 'My Account']")
	WebElement lnk_my_account_loc;

	@FindBy(xpath = "//a[@href = 'https://tutorialsninja.com/demo/index.php?route=account/register' and text()='Register']")
	WebElement lnk_register_loc;
	
	@FindBy(xpath = "//li//a[@href = 'https://tutorialsninja.com/demo/index.php?route=account/login']")
	WebElement login_link_loc;

	// Action Methods
	public void myaccountClick() {
		lnk_my_account_loc.click();

	}

	public void registerLinkClick() {
		lnk_register_loc.click();

	}
	
	public void LoginLinkClick() {
		login_link_loc.click();

	}
	
}
