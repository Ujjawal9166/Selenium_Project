package practice;

	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class AmazonSearch {

	    public static void main(String[] args) {
	        // Set up ChromeDriver
	    	String key="webdriver.chrome.driver";
			String value="./src/main/resources/chromedriver.exe";
			System.setProperty(key, value);
	        WebDriver driver = new ChromeDriver();
	        
	        // Navigate to Amazon
	        driver.get("https://www.amazon.com/");
	        
	        // Search for iPhones
	        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	        searchBox.sendKeys("iPhone");
	        searchBox.submit();
	        
	        // Get search results
	        List<String> results = new ArrayList<String>();
	        List<WebElement> items = driver.findElements(By.cssSelector(".s-result-item"));
	        for (WebElement item : items) {
	            String title = item.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
	            String price = item.findElement(By.xpath("//div[contains(@class,'a-section a-spacing-none')]//span[@class='a-price-whole']")).getText();
	            results.add(title + "," + price);
	        }
	        
	        // Save results to Excel
	        saveToExcel(results);
	        
	        // Quit driver
	        driver.quit();
	    }
	    
	    private static void saveToExcel(List<String> results) {
	        try {
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            Sheet sheet = workbook.createSheet("iPhone Results");
	            int rowNumber = 0;
	            for (String result : results) {
	                Row row = sheet.createRow(rowNumber++);
	                String[] data = result.split(",");
	                int cellNumber = 0;
	                for (String datum : data) {
	                    Cell cell = row.createCell(cellNumber++);
	                    cell.setCellValue(datum);
	                }
	            }
	            FileOutputStream outputStream = new FileOutputStream("./src/test/resources/testscript.xlsx");
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	}


