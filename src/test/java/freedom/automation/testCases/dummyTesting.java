package freedom.automation.testCases;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import freedom.automation.utils.Constants;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.codec.binary.Base32;

public class dummyTesting {

	private Logger logger = Logger.getLogger(dummyTesting.class);
	protected static WebDriver driver;

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

	@Test
	public void testing() {

		sleep(5);
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		loginBtn.click();
		sleep(5);

		WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		username.sendKeys("TestingA");

		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		password.sendKeys("smart123");

		WebElement signInBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		signInBtn.click();
		sleep(5);

		String otpKeyStr = "NN4UYJDWGNFXGXKU"; // <- this 2FA secret key.
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

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
