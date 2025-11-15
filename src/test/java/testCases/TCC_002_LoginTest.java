package testCases;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.lang.System.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TCC_002_LoginTest extends BaseClass {

// Verify the login test 

	@Test(priority = 1, groups = {"Sanity", "Master"})
	public void verify_home() {
		logger.info("*****TCC_002_LoginTest Execution started");

		try {
			// Home Page
			HomePage hp = new HomePage(driver);
			hp.myaccountClick();
			hp.LoginLinkClick();

			// Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setUserName(pr.getProperty("username"));
			lp.setPassword(pr.getProperty("password"));
			lp.loginBttonClick();

			// My Account Page
			MyAccountPage mp = new MyAccountPage(driver);
			boolean targetPage = mp.isMyaccountExist();

			Assert.assertTrue(targetPage);

		} catch (Exception e) {

			Assert.fail();

		}

	}
}