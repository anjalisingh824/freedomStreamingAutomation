package freedom.automation.pages;

import org.apache.log4j.Logger;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class AdminMain {

	String otpKeyStr;
	String twoFactorCode;
	protected static WebDriver driver;
	protected static WebDriver adminDriver;
	private Logger logger = Logger.getLogger(AdminMain.class);

	@FindBy(xpath = "//input[@placeholder='Email Address']")
	public WebElement enterEmailAddress;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement enterPassword;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement clickBtn;

	@FindBy(xpath = "//input[@placeholder='2FA Code']")
	public WebElement enter2FA;

	@FindBy(xpath = "/html//div[2]//ul/li[2]/ul/li[4]/a")
	public WebElement clickOnLogout;

	@FindBy(linkText = "Login")
	public WebElement clickOnLoginButton;

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement enterStreamerUsername;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement enterStreamerPassword;

	@FindBy(xpath = "//button[contains(text(),'Skip')]")
	public WebElement skipAuthy;

	public void loginIntoAdmin(int row, String sheetName) {
		try {

			enterEmailAddress.sendKeys(ExcelUtils.getCellData(row, 0, sheetName));
			enterPassword.sendKeys(ExcelUtils.getCellData(row, 1, sheetName));
			clickBtn.click();
			sleep(3);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringAuthy() {

		try {

			otpKeyStr = "NQYVIS2VNU7GKTKU"; // <- this 2FA secret key.
			Totp totp = new Totp(otpKeyStr);
			twoFactorCode = totp.now();
			logger.info(twoFactorCode);
			sleep(2);

			logger.info("Entering Authy");
			enter2FA.sendKeys(twoFactorCode);
			sleep(2);

			logger.info("Clicking on the submit button");
			clickBtn.click();
			sleep(2);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnSubmitBtns() {
		try {

			clickBtn.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void LoggingOut() {
		try {

			clickOnLogout.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

	public void enteringStreamerUsername(int row, String sheetName) {
		try {
			enterStreamerUsername.sendKeys(ExcelUtils.getCellData(row, 0, sheetName));

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void enteringStreamerPassword(int row, String sheetName) {

		try {

			enterStreamerPassword.sendKeys(ExcelUtils.getCellData(row, 1, sheetName));

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnLoginBtn() {
		try {
			clickOnLoginButton.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void skippingAuthy() {
		try {

			skipAuthy.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
