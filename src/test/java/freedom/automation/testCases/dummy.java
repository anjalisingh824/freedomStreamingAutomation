package freedom.automation.testCases;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import freedom.automation.pages.BasePage;
import freedom.automation.pages.SignUp;

public class dummy extends BasePage {

	public WebDriver driver;
	public WebDriver adminDriver;
	private Logger logger = Logger.getLogger(dummy.class);
	public SignUp signup;

	@Test(priority = 0)
	public void testLoginIntoAdmin() throws Exception {

		logger.info("test login into Admin started");
		signup = new SignUp();

		logger.info("click on the login button");
		signup.clickOnLoginbtn();

		openInNewTab("Anjali3492", "smart123");

		logger.info("test login into Admin ended");

	}

	public void openInNewTab(String username, String password) {

		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('https://www.google.co.in','_blank');");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
         //  FileUtils.copyFile(scrFile, new File("D:\\screenshot.png"));

	}
}
