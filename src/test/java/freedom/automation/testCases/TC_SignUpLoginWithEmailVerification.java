package freedom.automation.testCases;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.Main;
import freedom.automation.pages.SignUp;
import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class TC_SignUpLoginWithEmailVerification extends Main {

	private Logger logger = Logger.getLogger(TC_SignUpLoginWithEmailVerification.class);
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
		signUp.enteringValuesForSignup(3);

		logger.info("Click on the SignUp button");
		signUp.saveBtn();
		sleep(3);

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
		sleep(5);

		logger.info("============Test execution ended for Sign Up page==============");

	}

	@Test(priority = 1)
	public void verifyingLink() {
		logger.info("=================Reading Email Started================");

		signUp.readEmail(1, 30);
		sleep(7);

		logger.info("=================Reading Email ended===============");
	}

	@Test(priority = 2)
	public void LoginAfterVerification() {

		logger.info("============Test execution started for Login After Verification==============");

		logger.info("Asserting to check whether the signup message is present after successfull signup");
		String signUpSuccessText = driver.findElement(By.xpath("/html//div[2]/div[1]//div[2]//div[1]/p")).getText();

		logger.info("The signup text is: " + signUpSuccessText);
		Assert.assertEquals(signUpSuccessText, Constants.SIGNUP_SUCCESSFULL_MESSAGE);
		sleep(3);

		logger.info("Clicking on the Okay button");
		signUp.clickOnOkayBtn();
		sleep(5);

		logger.info("Clicking on the Login button");
		signUp.clickOnLoginbtn();
		sleep(3);

		logger.info("Passing Username and password");
		signUp.enteringValuesForLogin(3);
		sleep(2);

		logger.info("Clicking on the SignIn button");
		signUp.saveBtn();
		sleep(5);

		logger.info("Asserting the text present for authy");
		String disableAuthyMessageAfterLogin = driver.findElement(By.xpath("/html//div[2]/div[1]//div[2]//p"))
				.getText();

		logger.info("Authy message present is :" + disableAuthyMessageAfterLogin);
		Assert.assertEquals(disableAuthyMessageAfterLogin, Constants.AUTHY_DISABLED_MESSAGE);
		sleep(3);

		logger.info("Skip authy");
		signUp.skipingAuthy();
		sleep(5);

		logger.info("============Test execution ended for Login After Verification==============");
	}

	@Test(priority = 3)
	public void VerifyDetailsAfterLogin() throws Exception {
		logger.info("=======Test execution started for verifying details after Signup under profile section=======");

		logger.info("Click on the Menu");
		signUp.clickOnMenu();
		sleep(3);

		logger.info("go to settings");
		signUp.clickOnSettings();
		sleep(3);

		logger.info("verify the details which are used while registering should match");

		logger.info("Verifying email");
		String emailMatch = driver.findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("value");
		logger.info("Email present is :" + emailMatch);

		logger.info("Asserting to check whether the present email is same as entered one");
		Assert.assertEquals(ExcelUtils.getCellData(3, 1, Constants.SIGN_UP_SHEET), emailMatch);
		sleep(2);

		String userNameMatch = driver.findElement(By.xpath("//input[@placeholder='Username']")).getAttribute("value");
		logger.info("Username present is: " + userNameMatch);

		logger.info("Asserting to check whether the present username is same as entered one");
		Assert.assertEquals(ExcelUtils.getCellData(3, 0, Constants.SIGN_UP_SHEET), userNameMatch);
		sleep(3);

		logger.info("=======Test execution ended for verifying details after Signup under profile section=======");
	}

}
