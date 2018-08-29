package freedom.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class TC_AdminChangePassword {

	public WebDriver driver;
	private Logger logger = Logger.getLogger(TC_AdminChangePassword.class);

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

		logger.info("Entering Email Address");
		WebElement enterEmail = driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
		enterEmail.sendKeys(ExcelUtils.getCellData(1, 0, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Entering password Address");
		WebElement enterPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(1, 1, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Clicking on the Login button");
		WebElement clickOnLoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnLoginButton.click();
		sleep(5);

		logger.info("====Test execution ended for Login into the Admin side====");

	}

	@Test(priority = 1)
	public void testEnteringAuthy() {

		logger.info("=====Test execution started for entering Authy manually=====");

		sleep(7);
		logger.info("Clicking on the Submit button");
		WebElement clickOnTheSubmitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnTheSubmitBtn.click();
		sleep(3);

		logger.info("=====Test execution ended for entering Authy manually=====");

	}

	@Test(priority = 2)
	public void testGoToProfilePage() {

		logger.info("===Test execution started for going to the Profile page===");

		logger.info("Clicking to the Menu");
		WebElement clickOnMenu = driver.findElement(By.xpath("/html//div[2]//ul/li[2]//div[3]/i"));
		clickOnMenu.click();
		sleep(3);

		logger.info("Clicking on Settings");
		WebElement clickOnSettings = driver.findElement(By.xpath("/html//div[2]//ul/li[2]/ul/li[2]/a"));
		clickOnSettings.click();
		sleep(3);

		logger.info("===Test execution ended for going to the Profile page===");
	}

	@Test(priority = 3)
	public void testNewAndConPassNotMatch() throws Exception {
		logger.info("====Test execution started for Checking New and Confirm Password do not match====");

		int row = 3;

		logger.info("Passing old Password");
		WebElement enteringOldPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[1]//input"));
		enteringOldPassword.sendKeys(ExcelUtils.getCellData(row, 0, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing New Password");
		WebElement enteringNewPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[2]//input"));
		enteringNewPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing Confirm Password");
		WebElement enteringConfirmPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[3]//input"));
		enteringConfirmPassword.sendKeys(ExcelUtils.getCellData(row, 2, Constants.ADMIN_SETTINGS_SHEET));
		sleep(3);

		logger.info("Checking error for the new and Confirm Password mismatch");
		Assert.assertEquals(driver.getPageSource().contains(Constants.CONFIRM_PASS_NOT_MATCHING), true);
		sleep(5);

		logger.info("====Test execution ended for Checking New and Confirm Password do not match====");
	}

	@Test(priority = 4)
	public void testOldAndNewPassShdNotSame() throws Exception {

		logger.info("====Test execution started for checking New Password should not be same as the old password===");

		logger.info("Refreshing the Page");
		driver.navigate().refresh();
		sleep(5);

		int row = 4;
		logger.info("Passing old Password");
		WebElement enteringOldPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[1]//input"));
		enteringOldPassword.sendKeys(ExcelUtils.getCellData(row, 0, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing New Password");
		WebElement enteringNewPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[2]//input"));
		enteringNewPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing Confirm Password");
		WebElement enteringConfirmPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[3]//input"));
		enteringConfirmPassword.sendKeys(ExcelUtils.getCellData(row, 2, Constants.ADMIN_SETTINGS_SHEET));
		sleep(2);

		logger.info("Clicking on the Save Changes");
		WebElement clickOnSaveChanges = driver.findElement(By.xpath("/html//div[2]//div[4]//button"));
		clickOnSaveChanges.click();
		sleep(2);

		logger.info("Checking for the New and Old password could not be same");
		Assert.assertEquals(driver.getPageSource().contains(Constants.NEWPASS_SHDBE_DIFFRM_OLDPASS), true);
		sleep(5);

		logger.info("====Test execution ended for checking New Password should not be same as the old password===");

	}

	@Test(priority = 5)
	public void testIncorrectOldPassword() throws Exception {

		logger.info("====Test execution started for checking Incorrect old password===");

		logger.info("Refreshing the Page");
		driver.navigate().refresh();
		sleep(5);

		int row = 5;
		logger.info("Passing old Password");
		WebElement enteringOldPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[1]//input"));
		enteringOldPassword.sendKeys(ExcelUtils.getCellData(row, 0, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing New Password");
		WebElement enteringNewPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[2]//input"));
		enteringNewPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing Confirm Password");
		WebElement enteringConfirmPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[3]//input"));
		enteringConfirmPassword.sendKeys(ExcelUtils.getCellData(row, 2, Constants.ADMIN_SETTINGS_SHEET));
		sleep(2);

		logger.info("Clicking on the Save Changes");
		WebElement clickOnSaveChanges = driver.findElement(By.xpath("/html//div[2]//div[4]//button"));
		clickOnSaveChanges.click();
		sleep(2);

		logger.info("Checking for the New and Old password could not be same");
		Assert.assertEquals(driver.getPageSource().contains(Constants.WRONG_OLD_PASSWORD), true);
		sleep(5);

		logger.info("====Test execution started for checking Incorrect old password===");

	}

	@Test(priority = 6)
	public void testChangePassword() throws Exception {
		logger.info("===Test execution started for changing password===");

		logger.info("Refreshing the page");
		driver.navigate().refresh();
		sleep(5);

		int row = 6;
		logger.info("Passing old Password");
		WebElement enteringOldPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[1]//input"));
		enteringOldPassword.sendKeys(ExcelUtils.getCellData(row, 0, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing New Password");
		WebElement enteringNewPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[2]//input"));
		enteringNewPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Passing Confirm Password");
		WebElement enteringConfirmPassword = driver.findElement(By.xpath("/html//div[2]/form/div/div[3]//input"));
		enteringConfirmPassword.sendKeys(ExcelUtils.getCellData(row, 2, Constants.ADMIN_SETTINGS_SHEET));
		sleep(2);

		logger.info("Clicking on the Save Changes");
		WebElement clickOnSaveChanges = driver.findElement(By.xpath("/html//div[2]//div[4]//button"));
		clickOnSaveChanges.click();
		sleep(2);

		logger.info("===Test execution ended for changing password===");

	}

	@Test(priority = 7)
	public void testLogoutAndLoginAgainWithNewPassword() throws Exception {

		logger.info("===Test execution started for checking the New password===");

		logger.info("Clicking to the Menu");
		WebElement clickOnMenu = driver.findElement(By.xpath("/html//div[2]//ul/li[2]//div[3]/i"));
		clickOnMenu.click();
		sleep(3);

		logger.info("Clicking on the Logout button");
		WebElement clickOnLogout = driver.findElement(By.xpath("/html//div[2]//ul/li[2]/ul/li[4]/a"));
		clickOnLogout.click();
		sleep(3);

		logger.info("Entering Email Address");
		WebElement enterEmail = driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
		enterEmail.sendKeys(ExcelUtils.getCellData(8, 0, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Entering password Address");
		WebElement enterPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(8, 1, Constants.ADMIN_SETTINGS_SHEET));

		logger.info("Clicking on the Login button");
		WebElement clickOnLoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnLoginButton.click();
		sleep(5);

		logger.info("===Test execution ended for checking the New password===");

	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

	@AfterClass
	public void tearDown() {

		driver.quit();

	}

}