package freedom.automation.testCases;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.BasePage;
import freedom.automation.pages.SignUp;
import freedom.automation.utils.Constants;

public class TC_SignUpLoginWithoutEmailVerification extends BasePage {

	private Logger logger = Logger.getLogger(TC_SignUpLoginWithoutEmailVerification.class);
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
		signUp.enteringValuesForSignup(3);
		sleep(2);

		logger.info("Click on the SignUp button");
		signUp.saveBtn();
		sleep(5);

		logger.info("Verify for the email verification text present on the popup.");
		String emailVerificationMessage = driver
				.findElement(By.xpath("/html//div[1]//mat-tab-body[1]//app-authy-authentication/div/div[1]/div"))
				.getText();
		logger.info("The email verification message is :" + emailVerificationMessage);

		logger.info("Getting useful text from the popup");
		String getText = StringUtils.substringBetween(emailVerificationMessage.trim(), "Please", "Only");
		logger.info("The get text is : " + getText.trim());

		logger.info("Concating with the useful text");
		String concatText = "Please" + getText;
		logger.info(concatText.trim());

		logger.info("Asserting the get text from the stored text");
		Assert.assertEquals(Constants.EMAIL_VERIFICATION_MESSAGE.trim(), concatText.trim());
		sleep(3);

		logger.info("Click on the Okay button");
		signUp.clickOnOkayBtn();
		sleep(3);

		logger.info("============Test execution ended for Sign Up page==============");

	}

	@Test(priority = 1)
	public void LoginAfterVerification() {
		logger.info("============Test execution started for Login Without Verification==============");

		logger.info("Clicking on the Login button");
		signUp.clickOnLoginbtn();
		sleep(3);

		logger.info("Passing Username and password");
		signUp.enteringValuesForLogin(3);
		sleep(5);

		logger.info("Clicking on the SignIn button");
		signUp.saveBtn();
		sleep(3);

		logger.info("Check whether it is allowed to login without verification");
		Assert.assertEquals(driver.getPageSource().contains(Constants.USERNAME_PASSWORD_NOT_PRESENT), true);
		sleep(5);

		logger.info("============Test execution ended for Login Without Verification==============");
	}
}
