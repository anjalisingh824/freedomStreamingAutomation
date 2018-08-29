package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.Main;
import freedom.automation.pages.Profile;
import freedom.automation.utils.Constants;

public class TC_DeleteAccountFromProfile extends Main {

	private Logger logger = Logger.getLogger(TC_DeleteAccountFromProfile.class);
	public Profile profile;

	@Test(priority = 0)
	public void testLoginFirstIntoTheSystem() {

		logger.info("======Test execution started for Logging into the system======");

		logger.info("Creating instance for the Profile Page Factory");
		profile = new Profile();

		logger.info("Clicking on the login button present on the home page");
		profile.clickOnLoginbtn();

		logger.info("Doing login");
		profile.enteringValuesForLogin(7);

		logger.info("Clicking on the Submit button");
		profile.saveBtn();

		logger.info("Skipping the authy");
		profile.skipingAuthy();

		logger.info("Clicking on the menu");
		profile.clickOnMenu();

		logger.info("Clicking on the Settings button");
		profile.clickOnSettings();
		sleep(5);

		logger.info("======Test execution ended for Logging into the system======");
	}

	@Test(priority = 1)
	public void testDeleteAccount() {
		logger.info("======Test execution started for Deleting Account======");

		logger.info("Clicking on the Delete Account button");
		profile.clickOnDeleteAccountBtn();
		sleep(3);

		logger.info("Passing password");
		profile.enteringPassword(7);
		sleep(3);

		logger.info("Clicking on Delete Account button");
		profile.clickDeleteAccountBtn();
		sleep(3);

		logger.info("Clicking on the Okay button");
		profile.clickOkayBtn();
		sleep(3);

		logger.info("=====Test execution ended for Deleting Account======");

	}

	@Test(priority = 2)
	public void testReadingMail() {
		logger.info("====Test execution started for Reading email=====");

		logger.info("Started reading mail");
		profile.readEmail(9, 30);
		sleep(10);

		logger.info("Clicking on the Okay button");
		profile.clickOnOkayBtn();
		sleep(5);

		logger.info("====Test execution ended for Reading email=====");
	}

	@Test(priority = 3)
	public void testLoginAfterAccountDelete() {
		logger.info("===Test execution started by login into the System with deleted account======");

		logger.info("Clicking on the Login button");
		profile.clickOnLoginbtn();
		sleep(5);

		logger.info("Passing username and password");
		profile.enteringValuesForLogin(7);
		sleep(3);

		logger.info("Clicking on the submit button");
		profile.saveBtn();
		sleep(2);

		logger.info("The User should not be allowed to login after account deleting the Account");
		Assert.assertEquals(driver.getPageSource().contains(Constants.ACCOUNT_DOES_NOT_EXIST), true);
		sleep(5);

		logger.info("===Test execution ended by login into the System with deleted account======");
	}
}
