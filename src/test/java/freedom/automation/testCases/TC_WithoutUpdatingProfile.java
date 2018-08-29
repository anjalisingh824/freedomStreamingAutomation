package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import freedom.automation.pages.Main;
import freedom.automation.pages.Profile;
import freedom.automation.utils.Constants;

public class TC_WithoutUpdatingProfile extends Main {

	private Logger logger = Logger.getLogger(TC_WithoutUpdatingProfile.class);
	public Profile profile;

	@Test(priority = 0)
	public void testLoginFirstIntoTheSystem() {

		logger.info("======Test execution started for Logging into the system======");

		logger.info("Creating instance for the Profile Page Factory");
		profile = new Profile();

		logger.info("Clicking on the login button present on the home page");
		profile.clickOnLoginbtn();

		logger.info("Doing login");
		profile.enteringValuesForLogin(1);

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
	public void testClickChangeBtnWithoutUpdatingEmail() {
		logger.info("======Test execution started after without updating email ,Clicking on the Change button======");

		logger.info("Clicking on the Change button besides Email ");
		profile.clickOnChangeBtnForEmail();
		sleep(2);

		logger.info("Error message is displayed when trying to click Change button without updating Email");
		Assert.assertEquals(driver.getPageSource().contains(Constants.OLD_NEW_EMAIL_IS_SAME), true);
		sleep(3);

		logger.info("======Test execution ended after without updating email ,Clicking on the Change button======");
	}

	@Test(priority = 2)
	public void testClickChangeBtnWithoutUpdatingUserN() {
		logger.info("==Test execution started after without updating UserName ,Clicking on the Change button==");

		logger.info("Clicking on the Change button besides UserName ");
		profile.clickOnChangeBtnForUserName();
		sleep(2);

		logger.info("Error message is displayed when trying to click Change button without updating Email");
		Assert.assertEquals(driver.getPageSource().contains(Constants.OLD_NEW_USERN_IS_SAME), true);
		sleep(3);

		logger.info("==Test execution started after without updating UserName ,Clicking on the Change button===");

	}

}
