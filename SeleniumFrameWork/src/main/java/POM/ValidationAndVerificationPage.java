package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValidationAndVerificationPage {
	public ValidationAndVerificationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
  @FindBy(css="span.dvHeaderText")
	private WebElement actualOrganizationData;
	
  //getter method
	public WebElement getActualOrganizationData() {
		return actualOrganizationData;
	}
	
	//bussiness logics
	public void organisationValidation(WebDriver driver,String data)
	{
	String actData = actualOrganizationData.getText();
	
	if(actData.contains(data))
	   {
	  	 System.out.println("pass");
	   }
	   else
	   {
	  	 System.out.println("fail");
	   }
	}
}
