package freedom.automation.pages;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class SignUp extends Main {

	private Logger logger = Logger.getLogger(SignUp.class);
	public String text;

	@FindBy(linkText = "Sign Up")
	public WebElement signUpButton;

	@FindBy(linkText = "Login")
	public WebElement clickOnLoginButton;

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement enterUsername;

	@FindBy(xpath = "//input[@placeholder='Email']")
	public WebElement enterEmail;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement enterPassword;

	@FindBy(xpath = "//input[@placeholder='Confirm Password']")
	public WebElement enterConfirmPassword;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;

	@FindBy(xpath = "//html//div[2]//div[2]/div[1]//div/mat-tab-body[1]//div[5]//div[1]//div[1]/div[1]")
	public WebElement clickRadioButton;

	@FindBy(xpath = "//button[contains(text(),'Okay')]")
	public WebElement clickOnOkayButton;

	@FindBy(xpath = "//button[contains(text(),'Skip')]")
	public WebElement skipAuthy;

	public void clickOnSignUpBtn() {
		try {
			signUpButton.click();

		} catch (Exception e) {
			e.getMessage();

		}
	}

	public void enteringValuesForSignup(int row) {
		try {
			enterUsername.sendKeys(ExcelUtils.getCellData(row, 0, Constants.SIGN_UP_SHEET));
			enterEmail.sendKeys(ExcelUtils.getCellData(row, 1, Constants.SIGN_UP_SHEET));
			enterPassword.sendKeys(ExcelUtils.getCellData(row, 2, Constants.SIGN_UP_SHEET));
			enterConfirmPassword.sendKeys(ExcelUtils.getCellData(row, 3, Constants.SIGN_UP_SHEET));
			sleep(2);
			clickRadioButton.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringValuesForLogin(int row) {
		try {
			enterUsername.sendKeys(ExcelUtils.getCellData(row, 0, Constants.SIGN_UP_SHEET));
			enterPassword.sendKeys(ExcelUtils.getCellData(row, 2, Constants.SIGN_UP_SHEET));

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void saveBtn() {
		try {

			saveButton.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnRadioBtn() {

		try {

			clickRadioButton.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnOkayBtn() {
		try {

			clickOnOkayButton.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnLoginbtn() {
		try {

			clickOnLoginButton.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void readEmail(int row, int t) {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");

		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect(Constants.CONNECTION_EMAIL, ExcelUtils.getCellData(row, 0, Constants.SIGN_UP_SHEET),
					ExcelUtils.getCellData(row, 1, Constants.SIGN_UP_SHEET));
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

			String title = StringUtils.substringBetween(msg.getContent().toString().trim(), "https://qa1.freedomstreaming.io:3000", "</div>");
			logger.info("value===:" + title);
			String streamerUrl = StringEscapeUtils.unescapeHtml(title);
			String getURL = "https://qa1.freedomstreaming.io:3000" + streamerUrl;
			logger.info(getURL);
			sleep(5);
			driver.get(getURL);

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void skipingAuthy() {
		try {

			skipAuthy.click();

		} catch (Exception e) {

		}
	}
}
