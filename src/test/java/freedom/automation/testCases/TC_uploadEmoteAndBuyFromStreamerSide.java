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

public class TC_uploadEmoteAndBuyFromStreamerSide {

	public WebDriver driver;
	public WebDriver adminDriver;
	String path = "/home/smart/Downloads/EMO/(OK).png";
	private Logger logger = Logger.getLogger(TC_uploadEmoteAndBuyFromStreamerSide.class);

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
		enterEmail.sendKeys(ExcelUtils.getCellData(1, 0, Constants.EMOTE_SHEET));

		logger.info("Entering password Address");
		WebElement enterPassword = adminDriver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(1, 1, Constants.EMOTE_SHEET));

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
	public void testGoToUploadEmote() {
		logger.info("=============Test execution started for going to the upload emote page===============");

		logger.info("Clicking on the Emotes module");
		WebElement clickOnEmotes = adminDriver.findElement(By.xpath("/html//div[1]//div[3]/ul/li[10]/a/span/i"));
		clickOnEmotes.click();
		sleep(3);

		logger.info("Clicking on the upload Emote");
		WebElement clickOnUploadEmotes = adminDriver
				.findElement(By.xpath("/html//div[1]//div[3]/ul/li[10]/ul/li[1]/a/label"));
		clickOnUploadEmotes.click();
		sleep(3);

		logger.info("Clicking on the upload button to upload emote");
		WebElement clickOnTheUploadBtn = adminDriver.findElement(By.xpath("/html//h4/span[3]/button"));
		clickOnTheUploadBtn.click();
		sleep(3);

		logger.info("=============Test execution ended for going to the upload emote page===============");

	}

	@Test(priority = 3)
	public void testUploadEmote() throws Exception {

		logger.info("=========Test execution started for uploading emote==========");

		logger.info("Uploading emote");

		adminDriver.findElement(By.xpath("")).sendKeys(path);

		logger.info("=========Test execution ended for uploading emote=========");

	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

}
