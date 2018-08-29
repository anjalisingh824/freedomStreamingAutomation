package freedom.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import freedom.automation.utils.Constants;

public class AdminDummy {

	private Logger logger = Logger.getLogger(AdminDummy.class);
	protected static WebDriver driver;

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

	@Test()
	public void test() {

		driver.findElement(By.xpath("//input[@placeholder='Email Address']"))
				.sendKeys("anjali.smartsensesolutions@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("smart123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleep(3);

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

	}

	public void sleep(int seconds) {
		try {
			int miliseconds = seconds * 1000;
			Thread.sleep(Integer.valueOf(miliseconds));
		} catch (Exception e) {
			logger.error("Problem Rise During Custom Sleep for the page.....", e);
		}
	}

}
