package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationCreationPage1 {
	public OrganizationCreationPage1(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
    //Declaration
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement organizationCreateImage;
	
	@FindBy(name="accountname")
	private WebElement organizationNameTextField;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	
	//getter Methods
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}
	
	public WebElement getOrganizationCreateImage() {
		return organizationCreateImage;
	}
	
	/**
	 * This Method is used for ClickOn +img
	 */
	public void clickOrganizationCreateImage()
	{
		organizationCreateImage.click();
	}

	/**
	 * This Method is used to Pass Value to Organization TextField
	 */
	public void organizationNamesTextField(String orgName)
	{
		organizationNameTextField.sendKeys(orgName);
	}
	/**
	 * This Method is used to Save OrgName
	 */

	public void saveButton()
	{
		saveButton.click();	
	}
	
}
