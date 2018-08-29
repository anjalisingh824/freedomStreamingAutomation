package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import freedom.automation.pages.Main;
import freedom.automation.pages.Profile;

public class TC_UpdatingUserNameFromProfile extends Main {

	private Logger logger = Logger.getLogger(TC_UpdatingUserNameFromProfile.class);
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
	public void testUpdateUsernameBByClickingNo() {
		logger.info(
				"=====Test execution started for Updating username by clicking the No button from the Popup=======");

		logger.info("Clearing the Username field");
		profile.enterUsername.clear();
		sleep(3);

		logger.info("Entering value in the username field");
		profile.enteringUsername(3);
		sleep(3);

		logger.info("Clicking on the Change Button");
		profile.clickOnChangeBtnForUserName();
		sleep(5);

		logger.info("Clicking on the NO button from the Pop up so that username should not get updated");
		profile.clickNobtn();
		sleep(5);

		logger.info("The username should not be updated");

		logger.info("=====Test execution ended for Updating username by clicking the No button from the Popup=======");

	}

	@Test(priority = 1)
	public void testUpdateUsernameBByClickingYes() {
		logger.info(
				"=====Test execution started for Updating username by clicking the Yes button from the Popup=======");

		logger.info("Clearing the Username field");
		profile.enterUsername.clear();
		sleep(3);

		logger.info("Entering value in the username field");
		profile.enteringUsername(3);
		sleep(2);

		logger.info("Clicking on the Change Button");
		profile.clickOnChangeBtnForUserName();
		sleep(5);

		logger.info("Clicking on the Yes button from the Pop up so that username should  get updated");
		profile.clickYesBtn();
		sleep(5);

		logger.info("The username should be updated");

		logger.info("=====Test execution ended for Updating username by clicking the Yes button from the Popup=======");

	}

}
