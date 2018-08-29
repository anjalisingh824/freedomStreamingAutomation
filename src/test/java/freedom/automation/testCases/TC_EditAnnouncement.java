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

public class TC_EditAnnouncement {

	public WebDriver driver;
	public WebDriver adminDriver;
	private Logger logger = Logger.getLogger(TC_EditAnnouncement.class);
	private String title = Constants.AUTOMATION_UPDATE_PREFIX + System.currentTimeMillis() + "_Title";
	private String description = Constants.AUTOMATION_UPDATE_PREFIX + System.currentTimeMillis() + "_Description";

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
	public void testLoginIntoAdmin() throws Exception {
		logger.info("====Test execution started for Login into the Admin side====");

		logger.info("Entering Email Address");
		WebElement enterEmail = adminDriver.findElement(By.xpath("//input[@placeholder='Email Address']"));
		enterEmail.sendKeys(ExcelUtils.getCellData(1, 0, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		logger.info("Entering password Address");
		WebElement enterPassword = adminDriver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(1, 1, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		logger.info("Clicking on the Login button");
		WebElement clickOnLoginButton = adminDriver.findElement(By.xpath("//button[@type='submit']"));
		clickOnLoginButton.click();
		sleep(5);

		logger.info("====Test execution ended for Login into the Admin side====");

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
	public void testEditAnnouncement() {

		logger.info("====Test execution started for Editing Announcement=====");

		logger.info("Clicking on the Announcement link");
		WebElement clickOnAnnouncement = adminDriver.findElement(By.xpath("/html//div[1]//div[3]/ul/li[5]/a/label"));
		clickOnAnnouncement.click();
		sleep(3);

		logger.info("Clicking to edit the Added field");
		WebElement editAccountUpgrade = adminDriver.findElement(By.xpath("/html//table/tbody/tr[1]/td[3]/i[1]"));
		editAccountUpgrade.click();
		sleep(3);

		logger.info("Clearing values for title and description for editing.");
		WebElement clearingtitle = adminDriver.findElement(By.xpath("//input[@placeholder='Ex. New Year']"));
		clearingtitle.clear();

		WebElement clearDescription = adminDriver
				.findElement(By.xpath("//div[@id='description']//div[@class='fr-element fr-view']"));
		clearDescription.clear();
		sleep(3);

		logger.info("Passing values to Title and Description");
		WebElement enteringtitle = adminDriver.findElement(By.xpath("//input[@placeholder='Ex. New Year']"));
		enteringtitle.sendKeys(title);

		WebElement enteringDescription = adminDriver
				.findElement(By.xpath("//div[@id='description']//div[@class='fr-element fr-view']"));
		enteringDescription.sendKeys(description);
		sleep(3);

		logger.info("Clicking on the Update button");
		WebElement clickOnTheUpdateBtn = adminDriver.findElement(By.xpath("//button[@type='submit']"));
		clickOnTheUpdateBtn.click();
		sleep(3);

		logger.info("===Test execution ended for editing Announcement====");

	}

	@Test(priority = 3)
	public void testLoggingIntoStreamerSide() throws Exception {
		logger.info("===Test execution started for Logging into the Streamer side=====");

		logger.info("Clicking on the Login button");
		WebElement clickOnLoginBtn = driver.findElement(By.linkText("Login"));
		clickOnLoginBtn.click();
		sleep(3);

		logger.info("Passing username and password");
		WebElement enterUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		enterUsername.sendKeys(ExcelUtils.getCellData(9, 0, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		WebElement enterPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(9, 1, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		logger.info("Clicking on the SignIn button");
		WebElement clickOnsignInBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnsignInBtn.click();
		sleep(3);

		logger.info("===Test execution ended for Logging into the Streamer side=====");

	}

	@Test(dependsOnMethods = { "testLoggingIntoStreamerSide" })
	public void testOpenAnnouncementsPage() {
		logger.info("===Test execution started for opening the Announcement page=== ");

		logger.info("Clicking on the Skip button");
		WebElement clickingOnTheSkipBtn = driver.findElement(By.xpath("//button[contains(text(),'Skip')]"));
		clickingOnTheSkipBtn.click();
		sleep(3);

		logger.info("Clicking on the menu");
		WebElement clickOnTheMenu = driver.findElement(By.xpath("//html/body/customer-app-root//div[4]//div[3]/i"));
		clickOnTheMenu.click();
		sleep(3);

		logger.info("Clicking on the Announcements");
		WebElement clickingOnAnnouncements = driver.findElement(By.xpath("/html//div[1]//div[4]//ul[2]/li[2]/a"));
		clickingOnAnnouncements.click();
		sleep(3);

		logger.info("===Test execution ended for opening the Announcement page=== ");
	}

	@Test(dependsOnMethods = { "testOpenAnnouncementsPage" })
	public void testCheckFortheUpdatedAnnouncementFromAdmin() {
		logger.info("===Test execution started for checking the Updated Announcement from the Admin Side===");

		logger.info("Checking for the Title and Description added from the Admin side by using Assert statement");

		logger.info("Checking for the Title Added");
		logger.info(title);
		Assert.assertEquals(driver.getPageSource().contains(title), true);
		sleep(2);

		logger.info("Checking for the Description Added");
		logger.info(description);
		Assert.assertEquals(driver.getPageSource().contains(description), true);
		sleep(5);

		logger.info("===Test execution ended for checking the Updated Announcement from the Admin Side===");

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
		adminDriver.quit();

	}

}
