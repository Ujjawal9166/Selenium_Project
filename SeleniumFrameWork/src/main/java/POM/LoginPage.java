package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement usertbx;
	@FindBy(name="user_password")
	private WebElement passtbx;
	@FindBy(id="submitButton")
	private WebElement lgbtn;
	
	public WebElement getUsertbx() {
		return usertbx;
	}
	public WebElement getPasstbx() {
		return passtbx;
	}
	public WebElement getLgbtn() {
		return lgbtn;
	}
	
	public void setLogin(String un, String pw) {
		usertbx.sendKeys(un);
		passtbx.sendKeys(pw);
		lgbtn.click();
	}
}
