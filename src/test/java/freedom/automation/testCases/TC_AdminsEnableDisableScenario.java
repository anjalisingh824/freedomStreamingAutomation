/**
 * @author Anjali
 * 1)Login into Admin side
 * 2)Add Admin
 * 3)Search the added Admin
 * 4)Disable it
 * 5)Logout 
 * 6)Try logging In with the disabled admin
 * 7)Logging In with valid credentials.
 * 8)Enabling the disabled admin
 * 9)Logging out
 * 10)Logging In with the enabled admin
 
 */

package freedom.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.pages.Admins;
import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class TC_AdminsEnableDisableScenario extends Admins {

	public WebDriver driver;
	private Logger logger = Logger.getLogger(TC_AdminsAddSearchEditDelete.class);
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
		admin.enteringUsername(7, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Selecting role Admin or Super Admin");
		admin.dropDown(driver, 7, 1, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Entering Email");
		admin.enteringEmail(7, Constants.ADD_ADMIN_SHEET);

		logger.info("Entering password");
		admin.enteringAdminPassword(7, Constants.ADD_ADMIN_SHEET);

		logger.info("Click Add Admin button");
		admin.clickOnSubmitBtns();
		sleep(3);

		logger.info("Assert after Account has been added");
		Assert.assertEquals(driver.getPageSource().contains(Constants.ADMIN_ACCOUNT_SUCCESSFUL), true);
		sleep(3);

		logger.info("====Test execution ended for adding the Admin====");
	}

	@Test(priority = 4)
	public void searchAddedAdmin() throws Exception {
		logger.info("=====Test execution started for searching the added Admin=====");

		admin.searchAdmin(7, Constants.ADD_ADMIN_SHEET);
		sleep(3);

		logger.info("Assert the searched data to check for the correct information added");

		logger.info("Confirming added username");
		String confirmUsername = driver.findElement(By.xpath("/html//div[2]/table/tbody/tr/td[2]")).getText();
		logger.info("The username is :" + confirmUsername);
		Assert.assertEquals(confirmUsername, ExcelUtils.getCellData(7, 0, Constants.ADD_ADMIN_SHEET));

		logger.info("Confirming added Role");
		String confirmRole = driver.findElement(By.xpath("/html//div/div[2]/table/tbody/tr/td[3]")).getText();
		logger.info("Admin role is :" + confirmRole);
		Assert.assertEquals(confirmRole, ExcelUtils.getCellData(7, 1, Constants.ADD_ADMIN_SHEET));
		sleep(3);

		logger.info("=====Test execution ended for searching the added Admin=====");
	}

	@Test(priority = 5)
	public void disableAdmin() {
		logger.info("===Test execution started for disabling the admin====");

		logger.info("Disabling the added admin");
		admin.disablingAdmin();
		sleep(5);

		logger.info("===Test execution ended for disabling the admin====");
	}

	@Test(priority = 6)
	public void loggingOut() {
		logger.info("===Test execcution started for logging out===");

		logger.info("Clicking on the menu");
		admin.clickMenu();
		sleep(2);

		logger.info("Clicking on Logout");
		admin.LoggingOut();
		sleep(3);

		logger.info("===Test execcution ended for logging out===");

	}

	@Test(priority = 7)
	public void testLoginAgain() throws Exception {
		logger.info("===Test execution started to login again with the disabled account===");

		logger.info("Entering Email");
		admin.enterEmailAddress.sendKeys(ExcelUtils.getCellData(7, 2, Constants.ADD_ADMIN_SHEET));

		logger.info("Entering password");
		admin.enterPassword.sendKeys(ExcelUtils.getCellData(7, 3, Constants.ADD_ADMIN_SHEET));

		logger.info("Clicking submit");
		admin.clickOnSubmitBtns();
		sleep(3);

		logger.info("Assert to check disable error message");
		Assert.assertEquals(driver.getPageSource().contains(Constants.ADMIN_DISABLED), true);
		sleep(5);

		logger.info("===Test execution ended to login again with the disabled account===");

	}

	@Test(priority = 8)
	public void LoginWithValidCredentials() {
		logger.info("===Test execution started to login with valid credentials");

		logger.info("Refershing the page");
		driver.navigate().refresh();
		sleep(5);

		logger.info("Logging with valid credentials now");
		admin.loginIntoAdmin(1, Constants.ADD_ADMIN_SHEET);
		sleep(3);

		logger.info("Entering Authy");
		admin.enteringAuthy();
		sleep(5);

		logger.info("===Test execution ended to login with valid credentials");

	}

	@Test(priority = 9)
	public void searchingDisabledAdminAndEnable() {
		logger.info("===Test execution started to search the disabled admin===");

		logger.info("Go to the Admins module");
		admin.clickAdminM();
		sleep(3);

		logger.info("Searching the disabled admin");
		admin.searchAdmin(7, Constants.ADD_ADMIN_SHEET);
		sleep(3);

		logger.info("Enabling it");
		admin.disablingAdmin();
		sleep(3);

		logger.info("===Test execution ended to search the disabled admin===");
	}

	@Test(priority = 10)
	public void loggingOutAfterEnable() {
		logger.info("====Test execution started for logging out After Enabling the Admin====");

		logger.info("Clicking on the menu");
		admin.clickMenu();
		sleep(3);

		logger.info("Click Logout");
		admin.LoggingOut();
		sleep(3);

		logger.info("====Test execution ended for logging out After Enabling the Admin====");

	}

	@Test(priority = 11)
	public void loginWithEnabledAdmin() throws Exception {
		logger.info("===Test execution started to login after enable Admin==== ");

		logger.info("Enter Email");
		admin.enterEmailAddress.sendKeys(ExcelUtils.getCellData(7, 2, Constants.ADD_ADMIN_SHEET));

		logger.info("Enter password");
		admin.enterPassword.sendKeys(ExcelUtils.getCellData(7, 3, Constants.ADD_ADMIN_SHEET));

		logger.info("Clicking on submit");
		admin.clickOnSubmitBtns();
		sleep(7);

		logger.info("===Test execution ended to login after enable Admin==== ");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
