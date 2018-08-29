package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.BasePage;
import freedom.automation.pages.SignUp;
import freedom.automation.utils.Constants;

public class TC_SignUpWithVerifiedEmail extends BasePage {

	private Logger logger = Logger.getLogger(TC_SignUpWithVerifiedUsername.class);
	public SignUp signUp;

	@Test(priority = 0)
	public void testSignUp() throws Exception {

		logger.info("============Test execution started for Sign Up page==============");

		logger.info("Creating instance for the SignUp Page Factory");
		signUp = new SignUp();

		logger.info("Clicking on the SIGN UP button");
		signUp.clickOnSignUpBtn();
		sleep(5);

		logger.info("Entering Values for signup");
		signUp.enteringValuesForSignup(17);

		logger.info("Click on the SignUp button");
		signUp.saveBtn();
		sleep(2);

		logger.info("Will be showing toast message for email as it is already registered and verified");
		Assert.assertEquals(driver.getPageSource().contains(Constants.EMAIL_ALREADY_TAKEN), true);
		sleep(5);
		
		logger.info("============Test execution ended for Sign Up page==============");

	}

}
