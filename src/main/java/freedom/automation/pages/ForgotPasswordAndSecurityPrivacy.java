package freedom.automation.pages;

import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class ForgotPasswordAndSecurityPrivacy extends BasePage {
	public String text;

	public Logger logger = Logger.getLogger(ForgotPasswordAndSecurityPrivacy.class);

	@FindBy(linkText = "Login")
	public WebElement clickOnLoginButton;

	@FindBy(linkText = "Forgot Password?")
	public WebElement clickOnForPass;

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement enterUsername;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement enterPassword;

	@FindBy(xpath = "/html/body//app-header//div[1]//a/img")
	public WebElement clickFreedomLogo;

	@FindBy(xpath = "//html/body/customer-app-root//div[4]//div[3]/i")
	public WebElement enterMenu;

	@FindBy(xpath = "//html/body//app-header/div[1]//div[4]//ul[3]/li[2]/a")
	public WebElement clickSettings;

	@FindBy(xpath = "//input[@placeholder='Registered Email']")
	public WebElement enterRegisteredEmail;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement clickSendButton;

	@FindBy(xpath = "//html//div[2]//div[3]/div[1]")
	public WebElement clickSecurityTab;

	@FindBy(xpath = "//input[@placeholder='Enter One-Time Password']")
	public WebElement enterOTP;

	@FindBy(xpath = "//input[@placeholder='Old Password']")
	public WebElement enterOldpassword;

	@FindBy(xpath = "//input[@placeholder='New Password']")
	public WebElement enterNewPassword;

	@FindBy(xpath = "//input[@placeholder='Confirm Password']")
	public WebElement enterConfirmPassword;

	@FindBy(xpath = "//html/body//app-header/div[1]//div[4]//ul[3]/li[3]/a")
	public WebElement clickLogout;

	@FindBy(xpath = "//button[contains(text(),'Skip')]")
	public WebElement skipAuthy;

	public void enteringValuesForLogin(int row) {
		try {
			enterUsername.sendKeys(ExcelUtils.getCellData(row, 0, Constants.SIGN_UP_SHEET));
			enterPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.SIGN_UP_SHEET));

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enterLoginValForChangePass(int row) {
		try {

			enterUsername.sendKeys(ExcelUtils.getCellData(row, 0, Constants.CHANGE_PASSWORD_SHEET));
			enterPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.CHANGE_PASSWORD_SHEET));

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

	public void clickOnFreedomLogo() {
		try {
			clickFreedomLogo.click();

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

	public void clickOnMenu() {

		try {

			enterMenu.click();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void clickOnForgotPassword() {
		try {
			clickOnForPass.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringRegisteredEmail(int row,int col) {
		try {
			enterRegisteredEmail.sendKeys(ExcelUtils.getCellData(row, col, Constants.SIGN_UP_SHEET));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnSendBtn() {
		try {

			clickSendButton.click();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	/**
	 * @param row
	 * @param t
	 */
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

	public void enteringOTP() {
		try {

			enterOTP.sendKeys(text.trim());
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void changePassword(int row) {
		try {

			enterNewPassword.sendKeys(ExcelUtils.getCellData(row, 0, Constants.SIGN_UP_SHEET));
			enterConfirmPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.SIGN_UP_SHEET));

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnLogout() {
		try {

			clickLogout.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnSecurityAndPrivacyTab() {
		try {

			clickSecurityTab.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnSettings() {

		try {

			clickSettings.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void changePasswordAfterLogin(int row) {
		try {

			enterOldpassword.sendKeys(ExcelUtils.getCellData(row, 0, Constants.CHANGE_PASSWORD_SHEET));
			enterNewPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.CHANGE_PASSWORD_SHEET));
			enterConfirmPassword.sendKeys(ExcelUtils.getCellData(row, 2, Constants.CHANGE_PASSWORD_SHEET));

		} catch (Exception e) {

		}
	}
}
