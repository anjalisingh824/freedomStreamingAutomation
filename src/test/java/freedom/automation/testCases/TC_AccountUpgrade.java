package freedom.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class TC_AccountUpgrade {

	private Logger logger = Logger.getLogger(TC_AccountUpgrade.class);
	public WebDriver driver;
	public WebDriver adminDriver;

	@BeforeClass
	public void setUp() throws Exception {
		logger.debug("====================init start======================");
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
		// driver = new ChromeDriver();
		// driver.get(Constants.STREAMER_URL);
		// driver.manage().deleteAllCookies();
		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		adminDriver = new ChromeDriver();
		adminDriver.manage().deleteAllCookies();
		adminDriver.manage().window().maximize();
		adminDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		adminDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		adminDriver.get(Constants.ADMIN_URL);
		logger.debug("====================init start======================");

	}

	@Test(priority = 0)
	public void testLoginIntoAdmin() throws Exception {
		logger.info("=====Test execution started for Logging into Admin=====");

		logger.info("Entering Email Address");
		WebElement enterEmail = adminDriver.findElement(By.xpath("//input[@placeholder='Email Address']"));
		enterEmail.sendKeys(ExcelUtils.getCellData(1, 0, Constants.ACCOUNT_UPGRADE_SHEET));

		logger.info("Entering password Address");
		WebElement enterPassword = adminDriver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(1, 1, Constants.ACCOUNT_UPGRADE_SHEET));

		logger.info("Clicking on the Login button");
		WebElement clickOnLoginButton = adminDriver.findElement(By.xpath("//button[@type='submit']"));
		clickOnLoginButton.click();
		sleep(5);

		logger.info("=======Test execution ended for Logging into Admin=======");

	}

	@Test(priority = 1)
	public void testEnteringAuthy() {

		logger.info("=====Test execution started for entering Authy manually=====");

		sleep(7);
		logger.info("Clicking on the Submit button");
		WebElement clickOnTheSubmitBtn = adminDriver.findElement(By.xpath("//button[@type='submit']"));
		clickOnTheSubmitBtn.click();
		sleep(3);

		logger.info("=====Test execution ended for entering Authy manually=====");

	}

	@Test(priority = 2)
	public void testGototheEditAccUpgradePage() {
		logger.info("========Test execution started for going to the Account Upgrade page=======");

		logger.info("Clicking on the Account Upgrade");
		WebElement clickOnAccUpgrade = adminDriver.findElement(By.xpath("/html//div[1]//div[3]/ul/li[7]/a/label"));
		clickOnAccUpgrade.click();
		sleep(5);

		logger.info("Editing the Featured Account");
		WebElement editFeaturedAcc = adminDriver.findElement(By.xpath("/html//table/tbody/tr[1]/td[4]/i[1]"));
		editFeaturedAcc.click();
		sleep(3);

		logger.info("========Test execution ended for going to the Account Upgrade page=======");
	}

	@Test(priority = 3)
	public void testClearAccUpgradeField() {

		logger.info("=====Test execution started for clearing the Account upgrade fields=====");

		logger.info("Clearing the fields");
		WebElement clearPriceField = adminDriver.findElement(By.xpath("//input[@placeholder='Enter Price']"));
		clearPriceField.clear();

		WebElement clearDurationField = adminDriver.findElement(By.xpath("//input[@placeholder='Enter Duration']"));
		clearDurationField.clear();

		WebElement clearFreeModeratorsField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Number Of Moderators Allowed']"));
		clearFreeModeratorsField.clear();

		WebElement clearFreeCustomEmoticonsField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Number Of Emoticons Allowed']"));
		clearFreeCustomEmoticonsField.clear();

		WebElement clearExtraModeratorsField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Extra Moderators Fee']"));
		clearExtraModeratorsField.clear();

		WebElement clearSetPasswordField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Set Password Fee']"));
		clearSetPasswordField.clear();

		WebElement clearEntryFeeField = adminDriver.findElement(By.xpath("//input[@placeholder='Enter Entry Fee']"));
		clearEntryFeeField.clear();

		WebElement clearCollectionField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Collection Percentage']"));
		clearCollectionField.clear();

		WebElement clearEmoticonDisField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Emoticon Discount']"));
		clearEmoticonDisField.clear();

		WebElement clearCustomEmoticons = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Extra Emoticon Fee']"));
		clearCustomEmoticons.clear();

		WebElement clearUsernameField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Change UserName Fee']"));
		clearUsernameField.clear();
		sleep(5);

		logger.info("=====Test execution ended for clearing the Account upgrade fields=====");
	}

	@Test(priority = 4)
	public void testEditAccountUpgrade() throws Exception {
		logger.info("====test execution started for editing Account details====");
		int row = 3;

		logger.info("Passing value into fields through excel sheet");
		WebElement enteringPriceValue = adminDriver.findElement(By.xpath("//input[@placeholder='Enter Price']"));
		enteringPriceValue.sendKeys(ExcelUtils.getCellData(row, 0, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringDurationValue = adminDriver.findElement(By.xpath("//input[@placeholder='Enter Duration']"));
		enteringDurationValue.sendKeys(ExcelUtils.getCellData(row, 1, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringFreeModerators = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Number Of Moderators Allowed']"));
		enteringFreeModerators.sendKeys(ExcelUtils.getCellData(row, 2, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringFreeCustomEmoticons = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Number Of Emoticons Allowed']"));
		enteringFreeCustomEmoticons.sendKeys(ExcelUtils.getCellData(row, 3, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringExtraModerators = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Extra Moderators Fee']"));
		enteringExtraModerators.sendKeys(ExcelUtils.getCellData(row, 4, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringSetPassword = adminDriver.findElement(By.xpath("//input[@placeholder='Set Password Fee']"));
		enteringSetPassword.sendKeys(ExcelUtils.getCellData(row, 5, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringEntryFeeField = adminDriver.findElement(By.xpath("//input[@placeholder='Enter Entry Fee']"));
		enteringEntryFeeField.sendKeys(ExcelUtils.getCellData(row, 6, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringCollectionPercentage = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Collection Percentage']"));
		enteringCollectionPercentage.sendKeys(ExcelUtils.getCellData(row, 7, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringEmoticonDisField = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Emoticon Discount']"));
		enteringEmoticonDisField.sendKeys(ExcelUtils.getCellData(row, 8, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringCustomEmoticons = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Extra Emoticon Fee']"));
		enteringCustomEmoticons.sendKeys(ExcelUtils.getCellData(row, 9, Constants.ACCOUNT_UPGRADE_SHEET));

		WebElement enteringUsername = adminDriver
				.findElement(By.xpath("//input[@placeholder='Enter Change UserName Fee']"));
		enteringUsername.sendKeys(ExcelUtils.getCellData(row, 10, Constants.ACCOUNT_UPGRADE_SHEET));
		sleep(3);

		logger.info("Click on the save changes button");
		WebElement clickOnSaveChangesBtn = adminDriver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		clickOnSaveChangesBtn.click();
		sleep(5);

		logger.info("====test execution ended for editing Account details====");

	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

//	@AfterClass
//	public void tearDown() {
//		// driver.quit();
//		adminDriver.quit();
//
//	}

}
