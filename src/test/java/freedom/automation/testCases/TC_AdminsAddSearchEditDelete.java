/**
 * @author Anjali
 * 1)Login into Admin side
 * 2)Add Admin
 * 3)Search Added Admin
 * 4)Edit Admin
 * 5)Search Edited Admin
 * 6)Delete Admin
 * 7)Search Deleted Admin
 * 8)Logout
 * 9)Try Logging with the Deleted Admin
 * 
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

public class TC_AdminsAddSearchEditDelete extends Admins {

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
		admin.enteringUsername(5, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Selecting role Admin or Super Admin");
		admin.dropDown(driver, 5, 1, Constants.ADD_ADMIN_SHEET);
		sleep(2);

		logger.info("Entering Email");
		admin.enteringEmail(5, Constants.ADD_ADMIN_SHEET);

		logger.info("Entering password");
		admin.enteringAdminPassword(5, Constants.ADD_ADMIN_SHEET);

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

		admin.searchAdmin(5, Constants.ADD_ADMIN_SHEET);
		sleep(3);

		logger.info("Assert the searched data to check for the correct information added");

		logger.info("Confirming added username");
		String confirmUsername = driver.findElement(By.xpath("/html//div[2]/table/tbody/tr/td[2]"))
				.getText();
		logger.info("The username is :" + confirmUsername);
		Assert.assertEquals(confirmUsername, ExcelUtils.getCellData(5, 0, Constants.ADD_ADMIN_SHEET));

		logger.info("Confirming added Role");
		String confirmRole = driver.findElement(By.xpath("/html//div/div[2]/table/tbody/tr/td[3]"))
				.getText();
		logger.info("Admin role is :" + confirmRole);
		Assert.assertEquals(confirmRole, ExcelUtils.getCellData(5, 1, Constants.ADD_ADMIN_SHEET));
		sleep(3);

		logger.info("=====Test execution ended for searching the added Admin=====");
	}

	@Test(priority = 5)
	public void clearFieldForEditing() {
		logger.info("====Test execution started to clear fields");

		logger.info("Clicking to check the detailed info");
		admin.clickOnDetailedIcon();
		sleep(2);

		admin.clickOnEditBtn();
		sleep(2);

		logger.info("Clearing username");
		admin.enterUsername.clear();

		logger.info("Clearing email");
		admin.enterEmail.clear();

		logger.info("====Test execution ended to clear fields");
	}

	@Test(priority = 6)
	public void editAddedAdmin() throws Exception {
		logger.info("===Test execution started for editing the added Admin====");

		logger.info("Editing username");
		admin.enteringUsername(6, Constants.ADD_ADMIN_SHEET);

		logger.info("Editing the Role");
		admin.dropDown(driver, 6, 1, Constants.ADD_ADMIN_SHEET);

		logger.info("Editing Email");
		admin.enteringEmail(6, Constants.ADD_ADMIN_SHEET);

		logger.info("Clicking on the updating button");
		admin.clickOnSubmitBtns();
		sleep(3);

		logger.info("Assert to check whether the the details are updated");
		Assert.assertEquals(driver.getPageSource().contains(Constants.ADMIN_UPDATED_SUCCESSFULLY), true);
		sleep(5);

		logger.info("clear search field");
		admin.searchElements.clear();
		sleep(3);

		logger.info("===Test execution ended for editing the added Admin====");
	}

	@Test(priority = 7)
	public void searchEditedAdmin() throws Exception {
		logger.info("====Test execution started to search for the edited field====");

		admin.searchAdmin(6, Constants.ADD_ADMIN_SHEET);
		sleep(3);

		logger.info("Assert the searched data to check for the correct information added");

		logger.info("Confirming added username");
		String confirmUsername = driver.findElement(By.xpath("/html//div[2]/table/tbody/tr/td[2]"))
				.getText();
		logger.info("The username is : " + confirmUsername);
		Assert.assertEquals(confirmUsername, ExcelUtils.getCellData(6, 0, Constants.ADD_ADMIN_SHEET));

		logger.info("Confirming added Role");
		String confirmRole = driver.findElement(By.xpath("/html//div/div[2]/table/tbody/tr/td[3]"))
				.getText();
		logger.info("Admin Role is :" + confirmRole);
		Assert.assertEquals(confirmRole, ExcelUtils.getCellData(6, 1, Constants.ADD_ADMIN_SHEET));
		sleep(3);

		logger.info("====Test execution ended to search for the edited field====");
	}

	@Test(priority = 8)
	public void deleteAdmin() {
		logger.info("===Test execution started to delete Admin===");

		logger.info("Clicking on the detailed icon");
		admin.clickOnDetailedIcon();
		sleep(2);

		logger.info("Clicking on Delete");
		admin.clickOnDelete();
		sleep(3);

		logger.info("Clicking on the Yes button that is appeared on the pop up");
		admin.clickYesBtn();
		sleep(3);

		logger.info("===Test execution ended to delete Admin=== ");

	}

	@Test(priority = 9)
	public void searchDeletedAdmin() {

		logger.info("clear search field");
		admin.searchElements.clear();
		sleep(3);

		logger.info("===Test execution started to search the Deleted Admin===");

		admin.searchAdmin(6, Constants.ADD_ADMIN_SHEET);
		sleep(3);

		logger.info("===Test execution ended to search the Deleted Admin===");

	}
	
	@AfterClass()
	public void tearDown()
	{
		driver.quit();
	}

}
