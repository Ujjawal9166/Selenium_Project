package Generic_utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

public class Listener {

	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		
		EventFiringWebDriver edriver=new EventFiringWebDriver(null);
		File src=edriver.getScreenshotAs(OutputType.FILE);
		
//		try {
//			FileUtils.copyFile(src, new File("./Screenshot/"+testName+));
//		}
	}
}
