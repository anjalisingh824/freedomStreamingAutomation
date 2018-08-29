package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.BasePage;
import freedom.automation.pages.ForgotPasswordAndSecurityPrivacy;
import freedom.automation.pages.SignUp;
import freedom.automation.utils.Constants;

public class TC_ForgotPasswordWithRegisteredEmail extends BasePage {

	private Logger logger = Logger.getLogger(TC_ForgotPasswordWithoutRegEmail.class);
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
		forPass.enteringValuesForLogin(9);
		sleep(3);

		logger.info("Clicking on the Save button");
		signUp.saveBtn();
		sleep(3);

		logger.info("Skiping authy");
		signUp.skipingAuthy();
		sleep(5);

		logger.info("============Test execution ended for Login page==============");

	}

	@Test(priority = 1)
	public void testLogout() {
		logger.info("============Test execution started for Logging Out============");

		logger.info("Clicking on the menu");
		forPass.clickOnMenu();
		sleep(3);

		logger.info("Doing logout");
		forPass.clickOnLogout();
		sleep(3);

		logger.info("============Test execution ended for Logging Out============");
	}

	@Test(priority = 2)
	public void testForgotPassword() {
		logger.info("===========Test execution started for Forgot password==============");

		logger.info("Click on the Login Button");
		signUp.clickOnLoginbtn();
		sleep(3);

		logger.info("Click on the Forgot Password");
		forPass.clickOnForgotPassword();
		sleep(3);

		logger.info("Entering Registered Email");
		forPass.enteringRegisteredEmail(9,2);
		sleep(3);

		logger.info("Click on the Send button");
		forPass.clickOnSendBtn();
		sleep(2);

		logger.info("OTP send successfully  to the email");
		Assert.assertEquals(driver.getPageSource().contains(Constants.NEW_OTP_SEND), true);
		sleep(5);

		logger.info("============Test execution ended for Forgot password================");

	}

	@Test(priority = 3)
	public void testReadOTP() {
		logger.info("===========Test execution started for reading OTP through Email=============");

		logger.info("=====Reading Email started=======");
		forPass.readEmail(1, 35);
		sleep(5);

		logger.info("============Test execution ended for reading OTP through Email===============");

	}

	@Test(priority = 4)
	public void verifyOTP() {

		logger.info("==========Test execution started for verifying OTP===========");

		logger.info("Entering OTP by fetching through Email");
		forPass.enteringOTP();
		sleep(5);

		logger.info("Clicking on the Send button");
		forPass.clickOnSendBtn();
		sleep(2);

		logger.info("Successful message for OTP send");
		Assert.assertEquals(driver.getPageSource().contains(Constants.OTP_VERIFIED), true);
		sleep(5);

		logger.info("==========Test execution ended for verifying OTP===========");
	}

	@Test(priority = 5)
	public void testChangePasswordPage() {
		logger.info("========Test execution started for Change Password Page==========");

		logger.info("Passing values to New and Confirm Password");
		forPass.changePassword(5);
		sleep(3);

		logger.info("Click on the Submit button");
		forPass.clickOnSendBtn();
		sleep(2);

		logger.info("Password changed successfully");
		Assert.assertEquals(driver.getPageSource().contains(Constants.PASSWORD_RESET_DONE), true);
		sleep(3);

		logger.info("========Test execution ended for Change Password Page==========");
	}

	@Test(priority = 6)
	public void loginWithChangedPassword() {

		logger.info("===========Login started by using Changed Password=============");

		logger.info("Entering username and changed/new password");
		forPass.enteringValuesForLogin(7);
		sleep(3);

		logger.info("Clicking on the save button");
		signUp.saveBtn();
		sleep(3);

		logger.info("Skiping the Authy");
		signUp.skipingAuthy();
		sleep(3);

		logger.info("===========Login ended by using Changed Password=============");

	}

}
