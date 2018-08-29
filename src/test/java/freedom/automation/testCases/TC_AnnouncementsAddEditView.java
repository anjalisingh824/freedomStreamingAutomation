package freedom.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.pages.Announcements;
import freedom.automation.utils.Constants;

public class TC_AnnouncementsAddEditView extends Announcements {

	public Announcements announcementStreamer;
	public Announcements announcementAdmin;
	private Logger logger = Logger.getLogger(TC_AnnouncementsAddEditView.class);

	@BeforeClass
	public void setUp() throws Exception {
		logger.debug("====================init start======================");
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.get(Constants.STREAMER_URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		adminDriver = new ChromeDriver();
		adminDriver.manage().deleteAllCookies();
		adminDriver.manage().window().maximize();
		adminDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		adminDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		adminDriver.get(Constants.ADMIN_URL);
		logger.debug("====================init start======================");

	}

	@Test(priority = 0)
	public void loginIntoAdmin() throws Exception {
		logger.info("====Test execution started for Login into the Admin side====");

		logger.info("Creating instance for Streamer and admin side");
		announcementStreamer = PageFactory.initElements(driver, Announcements.class);
		announcementAdmin = PageFactory.initElements(adminDriver, Announcements.class);

		announcementAdmin.loginIntoAdmin(1, Constants.ADD_ADMIN_SHEET);
		sleep(3);

		logger.info("====Test execution ended for Login into the Admin side====");

	}

	@Test(priority = 1)
	public void enteringAuthy() {
		logger.info("===Test execution started to enter Authy===");

		announcementAdmin.enteringAuthy();
		sleep(3);

		logger.info("===Test execution ended to enter Authy===");
	}

	@Test(priority = 2)
	public void addAnnouncements() {
		logger.info("===Test execution started to add Announcement====");

		logger.info("Click on the announcements module");
		announcementAdmin.clickOnAnnouncements();
		sleep(3);

		logger.info("Click on the add button");
		announcementAdmin.clickOnAddBtn();
		sleep(3);

		logger.info("Entering title");
		announcementAdmin.enteringTitle(title);

		logger.info("Entering Description");
		announcementAdmin.enteringDescription(description);
		sleep(4);

		logger.info("Clicking on the Submit button");
		announcementAdmin.clickOnSubmitBtns();
		sleep(1);

		// logger.info("Asserting to check the message is visible after adding the
		// announcement");
		// Assert.assertEquals(driver.getPageSource().contains(Constants.ANNOUNCEMENT_ADDED),
		// "Announcement has been added successfully !");
		// sleep(3);

		logger.info("===Test execution ended to add Announcement====");

	}

	@Test(priority = 3)
	public void checkAnnouncementsWithoutLogin() {
		logger.info("===Test execution started to check Announcements without Login===");

		logger.info("Refreshing the page");
		driver.navigate().refresh();
		sleep(5);

		logger.info("Assert to check whether the added Announcement is present on the streamer side");
		logger.info(title);
		Assert.assertEquals(driver.getPageSource().contains(title), true);
		sleep(3);

		logger.info(description);
		Assert.assertEquals(driver.getPageSource().contains(description), true);
		sleep(3);

		logger.info("Click on the 'view all' to check all the announcement list");
		announcementStreamer.clickOnViewAll();
		sleep(3);

		logger.info("Check announcements on the login page after clicking on the 'view all' present on the home page");
		logger.info(title);
		Assert.assertEquals(driver.getPageSource().contains(title), true);
		sleep(3);

		logger.info(description);
		Assert.assertEquals(driver.getPageSource().contains(description), true);
		sleep(3);

		logger.info("===Test execution ended to check Announcements without Login===");
	}

	@Test(priority = 4)
	public void loginIntoStreamer() {
		logger.info("====Test execution started to login into streamer====");

		logger.info("Click on the Login button");
		announcementStreamer.clickOnLoginBtn();
		sleep(3);

		logger.info("Entering username");
		announcementStreamer.enteringStreamerUsername(1, Constants.CHANGE_PASSWORD_SHEET);

		logger.info("Entering password");
		announcementStreamer.enteringStreamerPassword(1, Constants.CHANGE_PASSWORD_SHEET);

		logger.info("Clicking on Submit");
		announcementStreamer.clickOnSubmitBtns();
		sleep(2);

		logger.info("Clicking on the skip button");
		announcementStreamer.skippingAuthy();
		sleep(5);

		logger.info("====Test execution ended to login into streamer====");

	}

	@Test(priority = 5)
	public void checkAnnouncementsAfterLogin() {
		logger.info("===Test execution started to check announcement after login===");

		logger.info("Assert to check whether the added Announcement is present on the streamer side");
		logger.info(title);
		Assert.assertEquals(driver.getPageSource().contains(title), true);
		sleep(3);

		logger.info(description);
		Assert.assertEquals(driver.getPageSource().contains(description), true);
		sleep(3);

		logger.info("Click on the 'view all' to check all the announcement list");
		announcementStreamer.clickOnViewAll();
		sleep(5);

		logger.info("Check announcements on the login page after clicking on the 'view all' present on the home page");
		logger.info(title);
		Assert.assertEquals(driver.getPageSource().contains(title), true);
		sleep(3);

		logger.info(description);
		Assert.assertEquals(driver.getPageSource().contains(description), true);
		sleep(5);

		logger.info("===Test execution ended to check announcement after login===");

	}

	@Test(priority = 6)
	public void editingAnnouncements() {
		logger.info("====Test execution started for editing Announcements====");

		logger.info("===Click on the edit icon===");
		announcementAdmin.editTheAnnouncements();
		sleep(2);

		logger.info("Clearning the fields");
		announcementAdmin.enterTitle.clear();
		announcementAdmin.enterDescription.clear();
		sleep(3);

		logger.info("Entering new Values");
		announcementAdmin.enteringTitle(updateTitle);
		announcementAdmin.enteringDescription(updateDescription);

		logger.info("Clicking on the update button");
		announcementAdmin.clickOnSubmitBtns();
		sleep(3);

		logger.info("====Test execution ended for editing Announcements====");

	}

	@Test(priority = 7)
	public void checkAnnWithLoggingAtStreamerSide() {
		logger.info("===Test execution started to check announcement after Logging In===");

		logger.info("Refresh the page");
		driver.navigate().refresh();
		sleep(3);

		logger.info("Assert to check whether the edited Announcement is present on the streamer side");
		logger.info(title);
		Assert.assertEquals(driver.getPageSource().contains(updateTitle), true);
		sleep(3);

		logger.info(description);
		Assert.assertEquals(driver.getPageSource().contains(updateDescription), true);
		sleep(3);

		logger.info("Click on the freedom logo to go to the HomePage");

		announcementStreamer.clickOnTheLogo();
		sleep(5);

		logger.info("Assert to check whether the edited Announcement is present on the home page of streamer side");
		logger.info(title);
		Assert.assertEquals(driver.getPageSource().contains(updateTitle), true);
		sleep(3);

		logger.info(description);
		Assert.assertEquals(driver.getPageSource().contains(updateDescription), true);
		sleep(3);

		logger.info("===Test execution ended to check announcement after Logging In===");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		adminDriver.quit();
	}

}
