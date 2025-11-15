package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	// Construction invoked from the BasePage class
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
		//Locators

		@FindBy(xpath = "//input[@name = 'firstname']")
		WebElement first_name;

		@FindBy(xpath = "//input[@name = 'lastname']")
		WebElement last_name;

		@FindBy(xpath = "//input[@name = 'email']")
		WebElement e_mail;
		
		@FindBy(xpath = "//input[@name = 'telephone']")
		WebElement tele_phone;
		
		@FindBy(xpath = "//input[@name = 'password']")
		WebElement pass_word;
		
		@FindBy(xpath = "//input[@name = 'confirm']")
		WebElement confirm_password;

		@FindBy(xpath = "//input[@type = 'checkbox' and @name= 'agree']")
		WebElement agree_check_box;
		
		@FindBy(xpath = "//input[@type = 'submit']")
		WebElement submit_btn;
		
		@FindBy(xpath = "//div[@id='content']//h1")
		WebElement confirm_msg;
		
		// Action Methods
		public void firstName(String firstName) {
			first_name.sendKeys(firstName);
		}

		public void lastName(String lastName) {
			last_name.sendKeys(lastName);
		}
		public void email(String email) {
			e_mail.sendKeys(email);
		}
		
		public void telePhone(String telePhone) {
			tele_phone.sendKeys(telePhone);
		}

		public void password(String password) {
			pass_word.sendKeys(password);
		}
		
		public void confirmpassword(String confirmPassword) {
			confirm_password.sendKeys(confirmPassword);
		}
		
		public void agreeCheckBockClick() {
			agree_check_box.click();
		}
		
		public void submit() {
			submit_btn.click();
		}
		
		public String confirm_msg() {
			String msg= confirm_msg.getText();
			return msg;
			
		}
		
	}
