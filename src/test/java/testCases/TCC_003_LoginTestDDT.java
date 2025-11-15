package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TCC_003_LoginTestDDT extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = {"Regression", "DDT"}) // Getting data from different class
	public void LoginTestDDT(String username, String passWord, String exp) {

		try {
			logger.info("**** TCC_03_LOGINTESTDDT Started*****");
			HomePage hp = new HomePage(driver);
			hp.myaccountClick();
			hp.LoginLinkClick();

			// Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setUserName(username);
			lp.setPassword(passWord);
			lp.loginBttonClick();

			// My Account Page
			MyAccountPage mp = new MyAccountPage(driver);
			boolean targetPage = mp.isMyaccountExist();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage = true) {
					Assert.assertTrue(true);
					mp.logOutClick();
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("InValid")) {
				if (targetPage = true) {
					mp.logOutClick();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);	
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("**** TCC_03_LOGINTESTDDT Finished *****");
	}

}