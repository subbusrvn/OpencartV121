package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id = 'content']//h2[text()='My Account']")
	WebElement myAccount_lbl;

	@FindBy(xpath = "//a[@href='https://tutorialsninja.com/demo/index.php?route=account/logout' and @class = 'list-group-item']")
	WebElement log_out;

	public boolean isMyaccountExist() {

		try {
			return (myAccount_lbl.isDisplayed());
		} catch (Exception e) {
			return (false);

		}

	}

	public void logOutClick() {
		log_out.click();

	}

}