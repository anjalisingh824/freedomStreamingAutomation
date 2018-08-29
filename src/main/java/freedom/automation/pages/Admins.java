package freedom.automation.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import freedom.automation.utils.Constants;
import freedom.automation.utils.ExcelUtils;

public class Admins extends AdminMain {

	private Logger logger = Logger.getLogger(Admins.class);

	public Admins() {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	@FindBy(xpath = "/html//div[1]//div[3]/ul/li[3]/a/label")
	public WebElement clickAdminModule;

	@FindBy(xpath = "/html//div/div/h4/span/button")
	public WebElement clickAddButton;

	@FindBy(xpath = "//input[@placeholder='Ex. John']")
	public WebElement enterUsername;

	@FindBy(xpath = "//input[@placeholder='Ex. johndoe@mail.com']")
	public WebElement enterEmail;

	@FindBy(xpath = "//input[@placeholder='***********']")
	public WebElement enteringPassword;

	@FindBy(xpath = "/html//div[2]/table/tbody/tr[1]/td[6]/button/mat-icon")
	public WebElement clickDetailIcon;

	@FindBy(xpath = "/html//div[2]/div[2]/div/div/button[2]")
	public WebElement clickEdit;

	@FindBy(xpath = "/html//div[1]/div[2]/div/div/button[3]")
	public WebElement clickDelete;

	@FindBy(xpath = "//button[contains(text(),'No')]")
	public WebElement clickNoButton;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement clickYesButton;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement searchElements;

	@FindBy(xpath = "/html//div[2]/table/tbody/tr[1]/td[5]//label/span")
	public WebElement disableAdmin;

	@FindBy(xpath = "/html//div[2]//ul/li[2]/div/div[3]/i")
	public WebElement clickOntheMenu;

	public void dropDown(WebDriver driver, int row, int cellno, String sheetName) throws Exception {

		List<WebElement> elementList = driver.findElements(By.tagName("option"));
		System.out.println(elementList.size());
		String str = ExcelUtils.getCellData(row, cellno, sheetName);
		boolean check = false;

		for (int i = 0; i < elementList.size(); i++) {

			if (elementList.get(i).getText().equals(str)) {
				logger.info("Entering for loop");
				WebElement elementClick = elementList.get(i);
				Thread.sleep(5000);
				elementClick.click();
				check = true;
				break;
			}

		}
		if (!check) {
			elementList.get(1).click();
		}

	}

	public void clickAdminM() {
		try {
			clickAdminModule.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnAddBtn() {
		try {
			clickAddButton.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringUsername(int row, String sheetName) {

		try {

			enterUsername.sendKeys(ExcelUtils.getCellData(row, 0, sheetName));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringEmail(int row, String sheetName) {
		try {

			enterEmail.sendKeys(ExcelUtils.getCellData(row, 2, sheetName));

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void enteringAdminPassword(int row, String sheetName) {

		try {

			enteringPassword.sendKeys(ExcelUtils.getCellData(row, 3, Constants.ADD_ADMIN_SHEET));

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void clickOnDetailedIcon() {
		try {
			clickDetailIcon.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnEditBtn() {
		try {

			clickEdit.click();

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

	public void clickOnDelete() {
		try {
			clickDelete.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnNoBtn() {
		try {

			clickNoButton.click();

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

	public void searchAdmin(int row, String sheetName) {
		try {

			searchElements.sendKeys(ExcelUtils.getCellData(row, 0, sheetName));

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void disablingAdmin() {
		try {

			disableAdmin.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickMenu() {
		try {

			clickOntheMenu.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
