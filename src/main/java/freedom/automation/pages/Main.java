package freedom.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class Main extends BasePage {

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement enterUsername;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement enterPassword;

	@FindBy(linkText = "Login")
	public WebElement clickOnLoginButton;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;

	@FindBy(xpath = "//button[contains(text(),'Skip')]")
	public WebElement skipAuthy;

	@FindBy(xpath = "//html/body/customer-app-root//div[4]//div[3]/i")
	public WebElement enterMenu;

	@FindBy(xpath = "//html//div[1]//div[4]//ul[3]/li[2]/a")
	public WebElement clickSettings;

	@FindBy(xpath = "//html//div[2]//div[3]/div[1]")
	public WebElement clickSecurityTab;

	@FindBy(xpath = "//html//div[2]//div[1]/div[1]")
	public WebElement clickProfileTab;
	
	@FindBy(xpath = "//html//div[2]//div[4]/div[1]")
	public WebElement clickChannelTab;

	@FindBy(xpath = "//button[contains(text(),'Okay')]")
	public WebElement clickOnOkayButton;

	@FindBy(xpath = "//html/body//app-header/div[1]//div[4]//ul[3]/li[3]/a")
	public WebElement clickLogout;

	public void clickOnLoginbtn() {
		try {

			clickOnLoginButton.click();

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

	public void clickOnSettings() {

		try {

			clickSettings.click();

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

	public void clickOnProfileTab() {
		try {
			clickProfileTab.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringPassword(int row) {

		try {

			enterPassword.sendKeys(ExcelUtils.getCellData(row, 1, Constants.PROFILE_SHEET));

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOkayBtn() {
		try {

			clickOnOkayButton.click();

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
}
