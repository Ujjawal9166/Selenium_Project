package Campaign;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateCampaignWithProductTest {

	@Test
	public void CreateCampaignWithProduct() throws EncryptedDocumentException, IOException {


		String key = "webdriver.chrome.driver";
		String value = "./src/main/resources/chromedriver.exe";
		System.setProperty(key, value);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		FileInputStream fis = new FileInputStream("./src/test/resources/propertydata.properties");
		Properties p = new Properties();
		p.load(fis);

		String url = p.getProperty("url");
		String un = p.getProperty("username");
		String pw = p.getProperty("password");

		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pw);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();

		FileInputStream fis1 = new FileInputStream("./src/test/resources/testscript.xlsx");
		Workbook book = WorkbookFactory.create(fis1);

		Random run = new Random();
		int runNum = run.nextInt(1000);

		Sheet sheet = book.getSheet("product");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		String excelData = cell.getStringCellValue() + runNum;

		driver.findElement(By.name("productname")).sendKeys(excelData);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		WebElement move = driver.findElement(By.xpath("//a[text()='More']"));
		Actions ac = new Actions(driver);
		ac.moveToElement(move).perform();
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();

		FileInputStream fis2 = new FileInputStream("./src/test/resources/testscript.xlsx");
		Workbook book1 = WorkbookFactory.create(fis2);

		Random run1 = new Random();
		int runNum1 = run1.nextInt(1000);

		Sheet sheet1 = book1.getSheet("campaign");
		Row row1 = sheet1.getRow(0);
		Cell cell1 = row1.getCell(0);
		String campData = cell1.getStringCellValue() + runNum1;

		driver.findElement(By.name("campaignname")).sendKeys(campData);
		driver.findElement(By.xpath("//img[@title='Select']")).click();

		Set<String> allId = driver.getWindowHandles();
		Iterator<String> id = allId.iterator();

		while (id.hasNext()) {
			String wid = id.next();
			driver.switchTo().window(wid);
			String title = driver.getTitle();

			if (title.contains("Products&action")) {
				break;
			}
		}
		driver.findElement(By.name("search_text")).sendKeys(excelData);
		driver.findElement(By.name("search")).click();
		// dynamic Xpath
		driver.findElement(By.xpath("//a[text()='" + excelData + "']")).click();

		Set<String> allId1 = driver.getWindowHandles();
		Iterator<String> id1 = allId1.iterator();

		while (id1.hasNext()) {
			String wid1 = id1.next();
			driver.switchTo().window(wid1);
			String title = driver.getTitle();

			if (title.contains("Campaigns&action")) {
				break;
			}
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String act = driver.findElement(By.xpath("//span[@id='dtlview_Assigned To']")).getText();
		if (act.contains(campData)) {
			System.out.println("capaign pass");
		} else {
			System.out.println("campaign fail");
		}

		String act1 = driver.findElement(By.xpath("//span[@id='dtlview_Product']")).getText();
		if (act1.contains(excelData)) {
			System.out.println("Product pass");
		} else {
			System.out.println("product fail");
		}

		WebElement lgOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions ac1 = new Actions(driver);
		ac1.moveToElement(lgOut).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
	}

}
