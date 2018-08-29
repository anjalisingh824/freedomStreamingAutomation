package freedom.automation.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import freedom.automation.utils.Constants;

public class BasePage {

	private Logger logger = Logger.getLogger(BasePage.class);
	protected static WebDriver driver;

	public BasePage() {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

	@BeforeClass
	public void setUp() throws Exception {
		logger.debug("====================init start======================");
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.get(Constants.STREAMER_URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.debug("====================init end======================");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
