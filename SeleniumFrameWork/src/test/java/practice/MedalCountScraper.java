package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MedalCountScraper {

	public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
		String key="webdriver.chrome.driver";
		String value="./src/main/resources/chromedriver.exe";
		System.setProperty(key, value);

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the Tokyo 2020 Olympic Games medal count page
        driver.get("https://olympics.com/en/olympic-games/tokyo-2020/medals");
        driver.findElement(By.xpath("//button[text()='Yes, I am happy']")).click();

        // Find the medal count table row for Austria
        WebElement austriaRow = driver.findElement(By.xpath("//tbody/tr[contains(.,'Austria')]"));

        // Extract the medal counts for Austria
        WebElement goldCell = austriaRow.findElement(By.xpath("./td[2]"));
        WebElement silverCell = austriaRow.findElement(By.xpath("./td[3]"));
        WebElement bronzeCell = austriaRow.findElement(By.xpath("./td[4]"));

        int goldCount = Integer.parseInt(goldCell.getText());
        int silverCount = Integer.parseInt(silverCell.getText());
        int bronzeCount = Integer.parseInt(bronzeCell.getText());
        int totalCount = goldCount + silverCount + bronzeCount;

        System.out.println("Austria Medal Count:");
        System.out.println("Gold: " + goldCount);
        System.out.println("Silver: " + silverCount);
        System.out.println("Bronze: " + bronzeCount);
        System.out.println("Total: " + totalCount);

        // Close the browser window
        driver.quit();
    }
}
