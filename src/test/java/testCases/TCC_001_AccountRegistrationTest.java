package testCases;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TCC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void AccountRegistrationTest() throws InterruptedException {

		try {
			logger.info("TCC_001_AccountRegistrationTest Execution Started");
			HomePage hp = new HomePage(driver);
			logger.info("My Acount Option Clicked");
			hp.myaccountClick();
			logger.info("Register Account is Clicked");
			hp.registerLinkClick();

			AccountRegistrationPage ar = new AccountRegistrationPage(driver);

			ar.firstName(randomStringGeneration());
			ar.lastName(randomStringGeneration());
			ar.email(randomStringGeneration() + "@gmail.com");
			ar.telePhone(randomNumericGeneration());

			String password = randomalphaNumeric();
			ar.password(password);
			ar.confirmpassword(password);
			ar.agreeCheckBockClick();
			Thread.sleep(1000);
			ar.submit();

			String confirmation_message = ar.confirm_msg();
			logger.info("TCC_001_AccountRegistrationTest Confirmation Message verified");
System.out.println(confirmation_message);
			if (confirmation_message.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.debug("Debug Logs");
				logger.error("Error Logs");
				Assert.assertTrue(false);

			}
			logger.info("TCC_001_AccountRegistrationTest Execution Completed");

		}

		catch (Exception e) {

			Assert.fail();

		}

	}

}
