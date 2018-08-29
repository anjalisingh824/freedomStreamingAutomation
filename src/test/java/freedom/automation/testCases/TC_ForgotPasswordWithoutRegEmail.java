package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.ForgotPasswordAndSecurityPrivacy;
import freedom.automation.pages.Main;
import freedom.automation.utils.Constants;

public class TC_ForgotPasswordWithoutRegEmail extends Main {

	private Logger logger = Logger.getLogger(TC_ForgotPasswordWithoutRegEmail.class);
	public ForgotPasswordAndSecurityPrivacy forPass;

	@Test(priority = 0)
	public void testForgotPassword() {
		logger.info("===========Test execution started for Forgot password==============");

		logger.info("Creating instances");
		forPass = new ForgotPasswordAndSecurityPrivacy();

		logger.info("Click on the Login Button");
		forPass.clickOnLoginbtn();
		sleep(3);

		logger.info("Click on the Forgot Password");
		forPass.clickOnForgotPassword();
		sleep(3);

		logger.info("Entering Registered Email");
		forPass.enteringRegisteredEmail(15,0);
		sleep(3);

		logger.info("Click on the Send button");
		forPass.clickOnSendBtn();
		sleep(2);

		logger.info("Check for the error message while passing the Email which is not Registered");
		Assert.assertEquals(driver.getPageSource().contains(Constants.INVALID_EMAIL), true);
		sleep(3);

		logger.info("============Test execution ended for Forgot password================");

	}

}
