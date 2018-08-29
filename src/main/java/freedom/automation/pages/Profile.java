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

public class Profile extends Main {

	private Logger logger = Logger.getLogger(Profile.class);

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement enterUsername;

	@FindBy(xpath = "//input[@placeholder='Email']")
	public WebElement enterEmail;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement enterPassword;

	@FindBy(xpath = "/html/body//div[1]//div[3]/div[2]//a/span")
	public WebElement clickOnChangeButton1;

	@FindBy(xpath = "/html/body//div[1]//div[4]/div[2]//a/span")
	public WebElement clickOnChangeButton2;

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	public WebElement clickOnOkayButton;

	@FindBy(xpath = "//button[contains(text(),'Okay')]")
	public WebElement clickOnOkayButton1;

	@FindBy(xpath = "/html/body//div[2]/div[1]//div[2]/button")
	public WebElement clickDeleteAccountButton;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement clickYesButton;

	@FindBy(xpath = "//button[contains(text(),'No')]")
	public WebElement clickNoButton;

	@FindBy(linkText = "Delete Account")
	public WebElement clickOnDeleteButtonlink;

	@FindBy(xpath = "//input[@type='file']")
	public WebElement fileUpload;

	@FindBy(xpath = "//button[contains(text(),'Update')]")
	public WebElement updateButton;
	
	@FindBy(xpath="//textarea[@placeholder='Bio']")
	public WebElement enterBio;
	
	@FindBy(xpath="//button[contains(text(),'Save Bio')]")
	public WebElement clickOnSaveBio;

	public void enteringValuesForLogin(int row) {
		try {
			enterUsername.sendKeys(ExcelUtils.getCellData(row, 0, Constants.PROFILE_SHEET));
			enterPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.PROFILE_SHEET));

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringEmail(int row) {

		try {

			enterEmail.sendKeys(ExcelUtils.getCellData(row, 0, Constants.PROFILE_SHEET));

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void enteringUsername(int row) {

		try {
			enterUsername.sendKeys(ExcelUtils.getCellData(row, 1, Constants.PROFILE_SHEET));

		} catch (Exception e) {

			e.getMessage();

		}

	}

	public void clickOnChangeBtnForEmail() {

		try {

			clickOnChangeButton1.click();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnChangeBtnForUserName() {

		clickOnChangeButton2.click();

		try {

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnDeleteAccountBtn() {
		try {

			clickOnDeleteButtonlink.click();

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

	public void clickOnOkayBtn1() {
		try {

			clickOnOkayButton1.click();
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
			store.connect(Constants.CONNECTION_EMAIL, ExcelUtils.getCellData(row, 0, Constants.PROFILE_SHEET),
					ExcelUtils.getCellData(row, 1, Constants.PROFILE_SHEET));
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

			String title = StringUtils.substringBetween(msg.getContent().toString().trim(),
					"https://qa1.freedomstreaming.io", "</div>");
			logger.info("value===:" + title);
			String streamerUrl = StringEscapeUtils.unescapeHtml(title);
			String getURL = "https://qa1.freedomstreaming.io" + streamerUrl;
			logger.info(getURL);
			sleep(5);
			driver.get(getURL);

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void clickYesBtn() {
		try {
			clickYesButton.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickNobtn() {
		try {

			clickNoButton.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickDeleteAccountBtn() {

		try {
			clickDeleteAccountButton.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void ImageUpload(int row,int col) {
		try {
			fileUpload.sendKeys(ExcelUtils.getCellData(row, col, Constants.PROFILE_SHEET));
			sleep(3);

			updateButton.click();
			sleep(2);

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void enterInBioField(int row)
	{
		try {
			
			enterBio.sendKeys(ExcelUtils.getCellData(row,1, Constants.PROFILE_SHEET));
			
			
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
