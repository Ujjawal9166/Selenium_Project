package Generic_utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	
	@BeforeSuite
	public void BS() {
		System.out.println("DataBase Connection");
	}
	
	@BeforeTest
	public void BT() {
		System.out.println("Parallel execution");
	}
	
	@BeforeClass
	public void BC() throws IOException {
		Property_Utility plib=new Property_Utility();
		String BROWSER = plib.getKeyValue("browser");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		System.out.println("Launching the application");
	}
	
	@BeforeMethod
	public void BM() {
		System.out.println("Login application");
	}
	
	@AfterMethod
	public void AM() {
		System.out.println("LOGOUT APPLICATION");
	}
	
	@AfterClass
	public void AC() {
		System.out.println("Browser closed");
	}
	@AfterTest
	public void AT() {
		System.out.println("Parallel execution done ");
	}
	
	@AfterSuite
	public void AS() {
		System.out.println("Database connection close");
	}
}
