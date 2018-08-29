package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.Main;
import freedom.automation.pages.Profile;
import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class TC_UpdatingEmailFromProfilePage extends Main {

	private Logger logger = Logger.getLogger(TC_WithoutUpdatingProfile.class);
	public Profile profile;

	@Test(priority = 0)
	public void testLoginFirstIntoTheSystem() {

		logger.info("======Test execution started for Logging into the system======");

		logger.info("Creating instance for the Profile Page Factory");
		profile = new Profile();

		logger.info("Clicking on the login button present on the home page");
		profile.clickOnLoginbtn();
		sleep(3);

		logger.info("Doing login");
		profile.enteringValuesForLogin(1);
		sleep(3);

		logger.info("Clicking on the Submit button");
		profile.saveBtn();
		sleep(3);

		logger.info("Asserting the text present for authy");
		String disableAuthyMessageAfterLogin = driver.findElement(By.xpath("/html//div[2]/div[1]//div[2]//p"))
				.getText();

		logger.info("Authy message present is :" + disableAuthyMessageAfterLogin);
		Assert.assertEquals(disableAuthyMessageAfterLogin, Constants.AUTHY_DISABLED_MESSAGE);
		sleep(3);

		logger.info("Skipping the authy");
		profile.skipingAuthy();
		sleep(3);

		logger.info("Clicking on the menu");
		profile.clickOnMenu();
		sleep(3);

		logger.info("Clicking on the Settings button");
		profile.clickOnSettings();
		sleep(5);

		logger.info("======Test execution ended for Logging into the system======");
	}

	@Test(priority = 1)
	public void testUpdateEmail() {

		logger.info("=============Test execution started for updating Email=============");

		logger.info("Clearing the Email field");
		profile.enterEmail.clear();
		sleep(3);

		logger.info("Update Email");
		profile.enteringEmail(3);
		sleep(3);

		logger.info("Click on the Change button");
		profile.clickOnChangeBtnForEmail();
		sleep(3);

		logger.info("Asserting to check for the Email verification message");
		String getTextFromPopUp = driver.findElement(By.xpath("/html//div[2]/div[1]//div[2]/p")).getText();
		logger.info("The text send is :" + getTextFromPopUp);

		Assert.assertEquals(getTextFromPopUp, Constants.EMAIL_SEND_TO_OLD_EMAIL);
		sleep(3);

		logger.info("Clicking on the OK button");
		profile.clickOnOkayBtn();
		sleep(3);

		logger.info("=============Test execution ended for updating Email=============");

	}

	@Test(priority = 2)
	public void testReadingEmailFromOldEmail() {
		logger.info("=========Reading Email from the old email address started===========");

		profile.readEmail(5, 30);
		sleep(10);

		logger.info(
				"Click to check for the message confirming email verification is done when clicked on the link present in the mail");
		String getTextForEmailVerification = driver.findElement(By.xpath("/html//div[2]/div[1]//div[2]//div[1]/p"))
				.getText();
		logger.info("The email confirmation text is : " + getTextForEmailVerification);
		Assert.assertEquals(getTextForEmailVerification, Constants.OLD_EMAIL_VERIFIED);
		sleep(3);

		logger.info("Click on the Okay button");
		profile.clickOnOkayBtn1();
		sleep(7);

		logger.info("=========Reading Email from the old email address ended===========");
	}

	@Test(priority = 3)
	public void testOldEmailShouldPresent() throws Exception {

		logger.info("======Test execution started for checking Old Email Should be present======");

		logger.info("Click on the Menu");
		profile.clickOnMenu();
		sleep(3);

		logger.info("go to settings");
		profile.clickOnSettings();
		sleep(3);

		logger.info("Assert to check whether old email is present or not in the profile section");
		String oldEmailShdPresent = driver.findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("Value");
		logger.info("The Old email is : " + oldEmailShdPresent);
		Assert.assertEquals(oldEmailShdPresent, ExcelUtils.getCellData(5, 0, Constants.PROFILE_SHEET));
		sleep(2);

		logger.info("======Test execution started for checking Old Email Should be present======");

	}

	@Test(priority = 4)
	public void testReadingEmailFromNewEmail() throws Exception {
		logger.info("=========Reading Email from the new email address started===========");

		profile.readEmail(7, 30);
		sleep(7);

		 logger.info("Asserting to check for the new email verification message");
		 String emailUpdated =
		 driver.findElement(By.xpath("/html//div[2]/div[1]//div[2]//div[1]/p")).getText();
		 logger.info("The email text is :" + emailUpdated);
		 Assert.assertEquals(emailUpdated, Constants.EMAIL_UPDATE);
		 sleep(3);

		logger.info("Click on the Okay button");
		profile.clickOnOkayBtn1();
		sleep(5);

		logger.info("=========Reading Email from the new email address ended===========");
	}

	@Test(priority = 5)
	public void testCheckEmailUpdated() throws Exception {

		logger.info("======Test execution started for checking whether the email is updated or not======");

		logger.info("Click on the Menu");
		profile.clickOnMenu();
		sleep(3);

		logger.info("go to settings");
		profile.clickOnSettings();
		sleep(3);

		logger.info("Assert to check whether the email updated message is present");
		String emailUpdatedMessage = driver.findElement(By.xpath("//input[@placeholder='Email']"))
				.getAttribute("Value");
		logger.info("The Updated email is : " + emailUpdatedMessage);
		Assert.assertEquals(emailUpdatedMessage, ExcelUtils.getCellData(7, 0, Constants.PROFILE_SHEET));
		sleep(2);

		logger.info("======Test execution started for checking whether the email is updated or not======");

	}

}
