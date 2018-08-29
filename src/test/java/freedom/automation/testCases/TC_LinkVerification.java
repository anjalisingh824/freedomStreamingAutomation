package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.BasePage;
import freedom.automation.pages.SignUp;
import freedom.automation.utils.Constants;

public class TC_LinkVerification extends BasePage {

	private Logger logger = Logger.getLogger(TC_LinkVerification.class);
	public SignUp signUp;

	@Test(priority = 0)
	public void testSignUp() throws Exception {

		logger.info("============Test execution started for Sign Up page==============");

		logger.info("Creating instance for the SignUp Page Factory");
		signUp = new SignUp();

		logger.info("Clicking on the SIGN UP button");
		signUp.clickOnSignUpBtn();
		sleep(5);

		logger.info("Entering values for signup");
		signUp.enteringValuesForSignup(11);
		sleep(2);

		logger.info("Click on the SignUp button");
		signUp.saveBtn();
		sleep(5);

		logger.info("Click on the Okay button");
		signUp.clickOnOkayBtn();
		sleep(3);

		logger.info("============Test execution ended for Sign Up page==============");

	}

	@Test(priority = 1)
	public void checkVerificationLink() {

		logger.info("==============Test execution started for link verification=================");

		logger.info("=====Reading Email=========");
		signUp.readEmail(13, 30);
		sleep(5);

		logger.info("Clicking on the Okay button");
		signUp.clickOnOkayBtn();
		sleep(3);

		logger.info("==============Test execution ended for link verification====================");

	}

	@Test(priority = 2)
	public void linkShouldExpire() {
		logger.info("==============Test execution started for link Expiration==================");

		logger.info("======Reading email again========");
		signUp.readEmail(13, 15);
		sleep(2);

		logger.info(
				"The link has expired toast message will be shown if the link is clicked more than once or clicked after 5 mins");
		Assert.assertEquals(driver.getPageSource().contains(Constants.LINK_EXPIRED), true);
		sleep(3);

		logger.info("==============Test execution ended for link Expiration==================");
	}

}
