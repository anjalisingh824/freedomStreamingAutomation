package freedom.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import freedom.automation.utils.Constants;

public class Announcements extends AdminMain {

	private Logger logger = Logger.getLogger(Announcements.class);
	public String title = Constants.AUTOMATION_PREFIX + System.currentTimeMillis() + "_Title";
	public String description = Constants.AUTOMATION_PREFIX + System.currentTimeMillis() + "_Description";
	public String updateTitle = Constants.AUTOMATION_UPDATE_PREFIX + System.currentTimeMillis() + "_Title";
	public String updateDescription = Constants.AUTOMATION_UPDATE_PREFIX + System.currentTimeMillis() + "_Description";

	public Announcements() {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	@FindBy(xpath = "/html//div[1]//div[3]/ul/li[6]/a/label")
	public WebElement clickAnnouncements;

	@FindBy(xpath = "//button[contains(text(),'Add')]")
	public WebElement clickAddButton;

	@FindBy(xpath = "//input[@placeholder='Ex. New Year']")
	public WebElement enterTitle;

	@FindBy(xpath = "//div[@id='description']//div[@class='fr-element fr-view']")
	public WebElement enterDescription;

	@FindBy(xpath = "/html//div/div/div/div/table/tbody/tr[1]/td[3]/i[2]")
	public WebElement viewAddedAnnouncement;

	@FindBy(xpath = "/html//div[1]/div[1]//div[3]/div/a")
	public WebElement clickViewAll;

	@FindBy(xpath = "/html//table/tbody/tr[1]/td[3]/i[1]")
	public WebElement editAnnouncements;

	@FindBy(xpath = "/html//div/div/div[1]/div[1]/div/a/img")
	public WebElement clickOnLogo;

	public void clickOnAnnouncements() {

		try {
			clickAnnouncements.click();

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

	public void enteringTitle(String title) {
		try {

			enterTitle.sendKeys(title);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void enteringDescription(String description) {
		try {
			enterDescription.sendKeys(description);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void viewAddedAnnouncements() {

		try {

			viewAddedAnnouncement.click();
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void clickOnViewAll() {
		try {

			clickViewAll.click();

		} catch (Exception e) {

			e.getMessage();

		}
	}

	public void editTheAnnouncements() {

		try {
			editAnnouncements.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnTheLogo() {
		try {
			clickOnLogo.click();
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
