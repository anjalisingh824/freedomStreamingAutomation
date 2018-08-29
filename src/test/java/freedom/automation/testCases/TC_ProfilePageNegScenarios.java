package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.Main;
import freedom.automation.pages.Profile;
import freedom.automation.utils.Constants;

public class TC_ProfilePageNegScenarios extends Main {

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
	public void testProfilePage() throws Exception {

		logger.info("======Test execution started for checking all the negative scenarios=======");

		logger.info("Clicking on the Email 'Change' link");
		WebElement changeBtn1 = driver.findElement(By.xpath("/html//mat-tab-body[1]//div[1]/form[1]//div[3]/div[2]"));
		changeBtn1.click();
		sleep(2);

		logger.info("Asserting to check for the error message as new email should not be same as old email");
		Assert.assertEquals(driver.getPageSource().contains(Constants.CHANGE_EMAIL_SHOULD_NOT_BE_SAME_AS_OLD), true);
		sleep(3);

		logger.info("Clicking on the Username 'Change' link");
		WebElement changeBtn2 = driver.findElement(By.xpath("/html//mat-tab-body[1]//div[1]/form[1]//div[4]/div[2]"));
		changeBtn2.click();
		sleep(2);

		logger.info("Asserting to check for the error message as new username should not be same as old username");
		Assert.assertEquals(driver.getPageSource().contains(Constants.CHANGE_USRNM_SHD_NT_BE_SAME), true);
		sleep(5);

		logger.info("Uploading image of 5MB to check  limit for the file upload");
		profile.ImageUpload(13, 0);

		logger.info("Asserting to check the error message for file upload limit");
		Assert.assertEquals(driver.getPageSource().contains(Constants.IMAGE_SIZE_ERROR), true);
		sleep(2);

		logger.info("Uploading file of xlsx format");
		profile.ImageUpload(13, 1);

		logger.info("Asserting to check while uploading the file in xlsx format ");
		Assert.assertEquals(driver.getPageSource().contains(Constants.IMAGE_FORMAT_ERROR), true);
		sleep(2);

		logger.info("Uploading file in the Pdf format");
		profile.ImageUpload(13, 2);

		logger.info("Asserting to check while uploading the file in xlsx format ");
		Assert.assertEquals(driver.getPageSource().contains(Constants.IMAGE_FORMAT_ERROR), true);
		sleep(2);

		logger.info("Uploading file of the gif format");
		profile.ImageUpload(13, 3);

		logger.info("Asserting to check while uploading the file in gif format ");
		Assert.assertEquals(driver.getPageSource().contains(Constants.IMAGE_FORMAT_ERROR), true);
		sleep(2);

		logger.info("Checking up the limit for bio field");
		profile.enterInBioField(14);
		sleep(3);

		logger.info("Asserting to check for the error message for the bio field");
		Assert.assertEquals(driver.getPageSource().contains(Constants.BIO_ERROR), true);
		sleep(3);

		logger.info("======Test execution ended for checking all the negative scenarios=======");
	}

}
