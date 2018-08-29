package freedom.automation.testCases;

import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.pages.Main;
import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class TC_AdminForgotPassword extends Main {

	private Logger logger = Logger.getLogger(TC_AdminForgotPassword.class);
	public WebDriver driver;
	public String text;

	@BeforeClass
	public void setUp() throws Exception {
		logger.debug("====================init start======================");
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.get(Constants.ADMIN_URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.debug("====================init end======================");

	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

	@Test(priority = 0)
	public void testForgetPassword() throws Exception {
		logger.info("=====Test execution started for logging into the Admin system=====");

		logger.info("Clicking on the Forgot password");

		WebElement clickOnForgotPass = driver.findElement(By.linkText("Forgot Password?"));
		clickOnForgotPass.click();
		sleep(5);

		WebElement enterEmail = driver.findElement(By.xpath("//input[@placeholder='Registered Email']"));
		enterEmail.sendKeys(ExcelUtils.getCellData(1, 0, Constants.ADMIN_FORGOT_PASSWORD_SHEET));
		sleep(3);

		WebElement clickOnSaveButton = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnSaveButton.click();
		sleep(5);

		logger.info("=====Test execution started for logging into the Admin system=====");

	}

	@Test(priority = 1)
	public void readingEmail() {

		logger.info("Reading Email started");

		String otpKeyStr = "NQYVIS2VNU7GKTKU"; // <- this 2FA secret key.
		Totp totp = new Totp(otpKeyStr);
		String twoFactorCode = totp.now();
		System.out.println(twoFactorCode);
		sleep(2);

		WebElement enter2FA = driver.findElement(By.xpath("//input[@placeholder='2FA Code']"));
		enter2FA.sendKeys(twoFactorCode);
		sleep(2);

		WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		submitBtn.click();
		sleep(5);
		logger.info("Reading Email ended");

	}

	@Test(priority = 2)
	public void testenteringOTP() {
		logger.info("======Test execution started for reading OTP=====");

		WebElement fetchOTP = driver.findElement(By.xpath("//input[@placeholder='OTP']"));
		fetchOTP.sendKeys(text.trim());
		sleep(3);

		logger.info("Clicking on the Send Button");
		WebElement ClickOnTheSendBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		ClickOnTheSendBtn.click();
		sleep(5);

		logger.info("======Test execution ended for reading OTP=====");
	}

	@Test(priority = 3)
	public void testchangePassword() throws Exception {

		logger.info("=======Test execution started for changing password=======");

		logger.info("Entering new Password");
		WebElement enterNewPassword = driver.findElement(By.xpath("//input[@placeholder='New Password']"));
		enterNewPassword.sendKeys(ExcelUtils.getCellData(3, 0, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		logger.info("Entering Confirm Password");
		WebElement enterConfirmPassword = driver.findElement(By.xpath("//input[@placeholder='Confirm Password']"));
		enterConfirmPassword.sendKeys(ExcelUtils.getCellData(3, 1, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		logger.info("Click on the save button");
		WebElement clickOnSaveButton = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnSaveButton.click();
		sleep(5);

		logger.info("=======Test execution ended for changing password=======");

	}

	@Test(priority = 4)
	public void testLoginWithUpdatedPassword() throws Exception {
		logger.info("=======Test execution started for logging in New password========");

		logger.info("Entering Email Address");
		WebElement enterEmail = driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
		enterEmail.sendKeys(ExcelUtils.getCellData(5, 0, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		logger.info("Entering password Address");
		WebElement enterPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		enterPassword.sendKeys(ExcelUtils.getCellData(5, 1, Constants.ADMIN_FORGOT_PASSWORD_SHEET));

		logger.info("Clicking on the Login button");
		WebElement clickOnLoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		clickOnLoginButton.click();
		sleep(5);

		logger.info("=======Test execution ended for logging in New password========");
	}

	public void ReadMail(int row, int t) {

		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");

		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect(Constants.CONNECTION_EMAIL,
					ExcelUtils.getCellData(row, 0, Constants.ADMIN_FORGOT_PASSWORD_SHEET),
					ExcelUtils.getCellData(row, 1, Constants.ADMIN_FORGOT_PASSWORD_SHEET));
			Folder inbox = store.getFolder("INBOX");
			sleep(t);
			inbox.open(Folder.READ_ONLY);
			Message msg = inbox.getMessage(inbox.getMessageCount());

			Address[] in = msg.getFrom();
			for (Address address : in) {
				System.out.println("FROM:" + address.toString());
			}
			logger.info("SENT DATE:" + msg.getSentDate());
			logger.info("SUBJECT:" + msg.getSubject());
			logger.info("CONTENT:" + msg.getContent());

			HTMLEditorKit kit = new HTMLEditorKit();
			HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
			Reader reader = new StringReader(msg.getContent().toString());
			kit.read(reader, doc, 0);
			for (HTMLDocument.Iterator iterator = doc.getIterator(HTML.Tag.SPAN); iterator.isValid(); iterator.next()) {
				AttributeSet set = iterator.getAttributes();
				if (set != null) {
					if (set.toString().contains("#7eafac")) {
						int startOffset = iterator.getStartOffset();
						int endOffSet = iterator.getEndOffset();
						text = doc.getText(startOffset, (endOffSet - startOffset));
						logger.info(text);
					}
				}

			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
