package freedom.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class TC_AdminProfile {

	public WebDriver driver;
	private Logger logger = Logger.getLogger(TC_AdminProfile.class);

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
		enterEmail.sendKeys(ExcelUtils.getCellData(1, 0, Constants.ADD_ADMIN_SHEET));

		logger.info("Entering password Address");
		WebElement enterPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(1, 1, Constants.ADD_ADMIN_SHEET));

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
	public void testGoToTheProfilePage() {
		logger.info("=====Test execution started for going to the profile page======");

		WebElement clickOnMenu = driver.findElement(By.xpath("/html//div[2]//ul/li[2]//div[3]/i"));
		clickOnMenu.click();
		sleep(3);

		WebElement clickOnProfile = driver.findElement(By.xpath("/html//div[2]//ul/li[2]/ul/li[1]/a"));
		clickOnProfile.click();
		sleep(3);

		logger.info("=====Test execution ended for going to the profile page======");

	}

	@Test(priority = 3)
	public void testUpdatingUsernameAlreadyTaken() throws Exception {
		logger.info("=====Test execution started by updating username already taken=====");

		logger.info("Clearing the fields first");
		WebElement clearingUsername = driver.findElement(By.xpath("//input[@placeholder='Ex. John']"));
		clearingUsername.clear();
		sleep(3);

		logger.info("Passing username");
		WebElement enteringUsername = driver.findElement(By.xpath("//input[@placeholder='Ex. John']"));
		enteringUsername.sendKeys(ExcelUtils.getCellData(1, 0, Constants.ADMIN_PROFILE));
		sleep(3);

		logger.info("Clicking on the Save Changes");
		WebElement clickOnSaveChanges = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnSaveChanges.click();
		sleep(3);

		logger.info("Checking for the error as the username is already taken");
		Assert.assertEquals(driver.getPageSource().contains(Constants.USERNAME_ALREADY_TAKEN), true);
		sleep(5);

		logger.info("=====Test execution ended by updating username already taken=====");
	}

	@Test(priority = 4)
	public void testByUploadingProfilePicture() throws Exception {

		logger.info("=====Test execution started for Uploading profile picture with different sizes=====");

		int row = 2;
		logger.info("Refreshing the page");
		driver.navigate().refresh();
		sleep(5);

		logger.info("Uploading profile pic in the format of pdf");
		WebElement fileUpload = driver.findElement(By.id("get_file"));
		fileUpload.sendKeys(ExcelUtils.getCellData(row, 1, Constants.ADMIN_PROFILE));
		sleep(3);

		logger.info("Clicking on the Save Changes");
		WebElement clickOnSaveChanges = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnSaveChanges.click();
		sleep(3);

		logger.info("=====Test execution ended for Uploading profile picture with different sizes=====");

	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

	public void tearDown() {

		driver.quit();

	}

}
