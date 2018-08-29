package freedom.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.pages.Admins;
import freedom.automation.utils.Constants;

public class TC_AdminsNegativeScenario extends Admins {

	public WebDriver driver;
	private Logger logger = Logger.getLogger(TC_AdminsNegativeScenario.class);
	public Admins admin;

	@BeforeClass
	public void setUp() throws Exception {
		logger.debug("====================init start======================");
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.get(Constants.ADMIN_URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(priority = 0)
	public void testLoginIntoAdmin() throws Exception {
		logger.info("====Test execution started for Login into the Admin side====");

		logger.info("Creating instances for the admin class");
		admin = PageFactory.initElements(driver, Admins.class);
		sleep(5);

		logger.info("Logging into the Admin system");
		admin.loginIntoAdmin(1, Constants.ADD_ADMIN_SHEET);

		logger.info("====Test execution ended for Login into the Admin side====");

	}

	@Test(priority = 1)
	public void testEnteringAuthy() {

		logger.info("=====Test execution started for entering Authy manually=====");

		admin.enteringAuthy();
		sleep(5);

		logger.info("=====Test execution ended for entering Authy manually=====");

	}

	@Test(priority = 2)
	public void testGoToAddAdminsPage() {
		logger.info("======Test execution started for going to the Add Admins Page======");

		logger.info("Clicking on the Admins Module");
		admin.clickAdminM();
		sleep(3);

		logger.info("Clicking on the add button");
		admin.clickOnAddBtn();
		sleep(5);

		logger.info("======Test execution ended for going to the Add Admins Page======");

	}

	@Test(priority = 3)
	public void testAddAdmin() throws Exception {
		logger.info("====Test execution started for adding the Admin====");

		logger.info("Entering username");
		admin.enteringUsername(3, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Selecting role Admin or Super Admin");
		admin.dropDown(driver, 3, 1, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Entering Email");
		admin.enteringEmail(3, Constants.ADD_ADMIN_SHEET);

		logger.info("Entering password");
		admin.enteringAdminPassword(3, Constants.ADD_ADMIN_SHEET);

		logger.info("Click Add Admin button");
		admin.clickOnSubmitBtns();
		sleep(3);

		logger.info("Asserting to check whether the same username can be taken again");
		Assert.assertEquals(driver.getPageSource().contains(Constants.USERNAME_ALREADY_TAKEN), true);
		sleep(5);

		logger.info("====Test execution ended for adding the Admin====");
	}

	@Test(priority = 4)
	public void clearFields() {
		logger.info("===Test execution started to clear the fields===");

		logger.info("Refreshing the page");
		driver.navigate().refresh();
		sleep(5);

		logger.info("Clicking on the add button again");
		admin.clickOnAddBtn();
		sleep(3);

		logger.info("===Test execution ended to clear the fields===");

	}

	@Test(priority = 5)
	public void testAddAdminAgain() throws Exception {
		logger.info("====Test execution started for adding the Admin again====");

		logger.info("Entering username");
		admin.enteringUsername(4, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Selecting role Admin or Super Admin");
		admin.dropDown(driver, 4, 1, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Entering Email");
		admin.enteringEmail(4, Constants.ADD_ADMIN_SHEET);

		logger.info("Entering password");
		admin.enteringAdminPassword(4, Constants.ADD_ADMIN_SHEET);

		logger.info("Click Add Admin button");
		admin.clickOnSubmitBtns();
		sleep(3);

		logger.info("Asserting to check whether the same username can be taken again");
		Assert.assertEquals(driver.getPageSource().contains(Constants.EMAIL_ALREADY_TAKEN), true);

		logger.info("====Test execution ended for adding the Admin again====");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}

}
