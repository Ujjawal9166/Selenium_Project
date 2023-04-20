package Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
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

public class CreateProductAddInCampaign {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
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

		Random run=new Random();
		int runNum = run.nextInt(1000);
		
		Sheet sheet = book.getSheet("product");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		String excelData = cell.getStringCellValue()+runNum;
		
		driver.findElement(By.name("productname")).sendKeys(excelData);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actData = driver.findElement(By.cssSelector("span.lvtHeaderText")).getText();

		if (actData.contains(excelData)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		WebElement move = driver.findElement(By.xpath("//a[text()='More']"));
		Actions ac = new Actions(driver);
		ac.moveToElement(move).perform();
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.name("campaignname")).sendKeys("marketing");
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		
	}

}
