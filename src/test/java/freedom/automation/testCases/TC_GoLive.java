/*Adding GoLive by checking mandate fields and submitting GoLive without any age restriction.
 * 
 * 
 * 
 * 
 * 
 * 
 */

package freedom.automation.testCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import freedom.automation.pages.BasePage;
import freedom.automation.pages.GoLive;

public class TC_GoLive extends BasePage {

	private Logger logger = Logger.getLogger(TC_GoLive.class);
	public GoLive goLive;

	@Test(priority = 0)
	public void testLoginFirst() {

		logger.info("====Test execution started for logging into the system=====");

		logger.info("Creating instance");
		goLive = new GoLive();

		logger.info("Clicking on Login button");
		goLive.clickOnLoginbtn();
		sleep(3);

		logger.info("Logging In");
		goLive.loginIntoStreamer(1);
		sleep(3);

		logger.info("Clicking on the Skip button");
		goLive.skipingAuthy();
		sleep(3);

		logger.info("Clicking on Go Live");
		goLive.clickOnGoLive();
		sleep(5);

		logger.info("====Test execution ended for logging into the system=====");

	}

	@Test(priority = 1)
	public void testMandatoryField() {
		logger.info("===Test execution started for Checking the mandatory field===");      

		goLive.enteringTitle();
		goLive.enteringDescription();
		sleep(3);

		logger.info("===Test execution ended for Checking the mandatory field===");

	}

	@Test(priority = 2)
	public void testAddGoLive() {
		logger.info("===Test executions started for adding go live=====");

		logger.info("Adding go live");
		goLive.addGoLive(3);
		sleep(3);

		logger.info("Clicking on the checkboxes for the related go live video");

		logger.info("===Test executions ended for adding go live=====");

	}

	@Test(priority = 3)
	public void testAfterGoLive() {
		logger.info("===Test execution started for testing go live further====");

		logger.info("Clicking on the GO Live button on the popup");
		goLive.clickOnGoLivePopUp();
		sleep(5);

		logger.info("===Test execution ended for testing go live further====");

	}
}
