package freedom.automation.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class GoLive extends Main {

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement enterUsername;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement enterPassword;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement clickOnSubmit;

	@FindBy(xpath = "/html//div/div/div[1]/div[4]/span[1]/a")
	public WebElement clickGoLive;

	@FindBy(xpath = "/html//div[2]//div[2]//form/div[3]/button")
	public WebElement clickGoLivePopUp;

	@FindBy(xpath = "//input[@placeholder='Enter Title']")
	public WebElement enterTitle;

	@FindBy(xpath = "//textarea[@placeholder='Enter Description']")
	public WebElement enterDescription;

	@FindBy(xpath = "/html//div[2]//mat-checkbox[6]/label/div")
	public WebElement checkbox1;

	@FindBy(xpath = "/html//div[2]//mat-checkbox[7]/label/div")
	public WebElement checkbox2;

	@FindBy(xpath = "/html//div[2]/button[2]")
	public WebElement uploadButton;

	public void loginIntoStreamer(int row) {

		try {
			enterUsername.sendKeys(ExcelUtils.getCellData(row, 0, Constants.GO_LIVE_SHEET));
			enterPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.GO_LIVE_SHEET));
			sleep(3);
			clickOnSubmit.click();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void clickOnGoLive() {

		try {
			clickGoLive.click();
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void enteringTitle() {
		try {
			enterTitle.sendKeys(Keys.TAB);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void enteringDescription() {

		try {
			enterDescription.sendKeys(Keys.TAB);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void addGoLive(int row) {
		try {
			enterTitle.sendKeys(ExcelUtils.getCellData(row, 0, Constants.GO_LIVE_SHEET));
			enterDescription.sendKeys(ExcelUtils.getCellData(row, 1, Constants.GO_LIVE_SHEET));
			sleep(3);
			clickOnSubmitBtn();

		} catch (Exception e) {
			e.getMessage();

		}
	}

	public void clickOnGoLivePopUp() {
		try {
			clickGoLivePopUp.click();
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void clickOnSubmitBtn() {
		try {
			clickOnSubmit.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnCheckboxes() {
		try {
			checkbox1.click();
			sleep(3);
			checkbox2.click();
			sleep(3);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void uploadBtn() {
		try {

			uploadButton.click();

		} catch (Exception e) {
			e.getMessage();
		}

	}

}
