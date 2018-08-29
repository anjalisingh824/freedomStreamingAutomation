package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.BasePage;
import freedom.automation.pages.ForgotPasswordAndSecurityPrivacy;
import freedom.automation.pages.SignUp;
import freedom.automation.utils.Constants;

public class TC_PrivacyAndSecurity extends BasePage {

	private Logger logger = Logger.getLogger(TC_PrivacyAndSecurity.class);
	public SignUp signUp;
	public ForgotPasswordAndSecurityPrivacy forPass;

	@Test(priority = 0)
	public void testLoginFirst() throws Exception {

		logger.info("============Test execution started for Login page==============");

		logger.info("Creating instance for Sign Up and Forgot Password");
		signUp = new SignUp();
		forPass = new ForgotPasswordAndSecurityPrivacy();

		logger.info("Clicking on the Login button");
		signUp.clickOnLoginbtn();
		sleep(3);

		logger.info("Passing username and Password");
		forPass.enterLoginValForChangePass(1);
		sleep(3);

		logger.info("Clicking on the Save button");
		forPass.clickOnSendBtn();
		sleep(3);

		logger.info("Skiping authy");
		signUp.skipingAuthy();
		sleep(5);

		logger.info("============Test execution ended for Login page==============");

	}

	@Test(priority = 1)
	public void testGoToSecurityAndPrivacyTab() {
		logger.info("===========Test execution started for Changing password==============");

		logger.info("Click on the info");
		forPass.clickOnMenu();
		sleep(3);

		logger.info("Click on the Settings Tab");
		forPass.clickOnSettings();
		sleep(3);

		logger.info("Click on the Security and Privacy Tab");
		forPass.clickOnSecurityAndPrivacyTab();
		sleep(3);

		logger.info("===========Test execution ended for Changing password==============");
	}

	@Test(priority = 2)
	public void testNewAndConfirmPasswordMismatch() {
		logger.info("========Test execution started for new and old password mismatch=========");

		logger.info("Passing values for old,new and confirm password");
		forPass.changePasswordAfterLogin(3);
		sleep(3);

		logger.info("Displaying message as Password and Confirm Password do not match");
		Assert.assertEquals(driver.getPageSource().contains(Constants.CONFIRM_PASS_NOT_MATCHING), true);
		sleep(3);

		logger.info("========Test execution ended for new and old password mismatch=========");
	}

	@Test(priority = 3)
	public void testNewPasswordNotSameAsOldPass() {
		logger.info("====Test execution started for testing New Password should not be same as old Password======");

		logger.info("refreshing the page");
		driver.navigate().refresh();
		sleep(5);

		logger.info("Clicking on the Security and Privacy Tab");
		forPass.clickOnSecurityAndPrivacyTab();
		sleep(3);

		logger.info("Passing the values for old ,new and confirm password");
		forPass.changePasswordAfterLogin(4);
		sleep(5);

		logger.info("Click on change password button");
		forPass.clickOnSendBtn();
		sleep(2);

		logger.info("Message displayed as New password could not be same as old password");
		Assert.assertEquals(driver.getPageSource().contains(Constants.NEWPASS_SHDBE_DIFFRM_OLDPASS), true);
		sleep(5);

		logger.info("========Test execution ended for testing new Password should not be same as old Password========");

	}

	@Test(priority = 4)
	public void testWrongOldPassword() {
		logger.info("======Test execution started for testing wrong password========");

		logger.info("refreshing the page");
		driver.navigate().refresh();
		sleep(5);

		logger.info("Clicking on the Security and Privacy Tab");
		forPass.clickOnSecurityAndPrivacyTab();
		sleep(3);

		logger.info("Passing the values for old ,new and confirm password");
		forPass.changePasswordAfterLogin(5);
		sleep(5);

		logger.info("Click on change password button");
		forPass.clickOnSendBtn();
		sleep(2);

		logger.info("Message displayed as the wrong old password is provided");
		Assert.assertEquals(driver.getPageSource().contains(Constants.WRONG_OLD_PASSWORD), true);
		sleep(5);

		logger.info("======Test execution ended for testing wrong password========");

	}

	@Test(priority = 5)
	public void testPasswordChanged() {
		logger.info("======Test execution started for changing password======");

		logger.info("refreshing the page");
		driver.navigate().refresh();
		sleep(5);

		logger.info("Clicking on the Security and Privacy Tab");
		forPass.clickOnSecurityAndPrivacyTab();
		sleep(3);

		logger.info("Passing the values for old ,new and confirm password");
		forPass.changePasswordAfterLogin(6);
		sleep(5);

		logger.info("Click on change password button");
		forPass.clickOnSendBtn();
		sleep(2);

		logger.info("Clicking on the logo to redirect on the home page");
		forPass.clickOnFreedomLogo();
		sleep(3);

		logger.info("clicking on the menu");
		forPass.clickOnMenu();
		sleep(5);

		logger.info("Logging Out");
		forPass.clickOnLogout();
		sleep(3);

		logger.info("======Test execution ended for changing password======");
	}

	@Test(priority = 6)
	public void testLoginAfterChangingPassword() {

		logger.info("======Login started after changing the password=======");

		logger.info("Clicking Login button present on the Home page");
		forPass.clickOnLoginbtn();
		sleep(3);

		logger.info("Logging into the system with new changed password");
		forPass.enterLoginValForChangePass(8);
		sleep(2);

		logger.info("Clicking on the Submit button");
		forPass.clickOnSendBtn();
		sleep(5);

		logger.info("Skiping authy");
		forPass.skipingAuthy();
		sleep(5);

		logger.info("=======Login ended after changing the password=========");

	}

}
