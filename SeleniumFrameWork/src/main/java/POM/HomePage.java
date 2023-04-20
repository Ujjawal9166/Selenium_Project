package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_utility.Webdriver_Utility;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Products")
	private WebElement productLink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;

	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement signoutImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;

	
	//Getters method
	public WebElement getProductLink() {
		return productLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getSignoutImg() {
		return signoutImg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	//business logic
	
	public void clickProductLink() {
		productLink.click();
	}
	public void moreLinktab(WebDriver driver) {
		
		Webdriver_Utility wlib = new Webdriver_Utility();
		wlib.mouseOverOnElement(driver, moreLink);
	}
	public void campaignLinkText() {
	
		campaignLink.click();
	}
	
	public void clickOrganizationLink() {
		orgLink.click();
	}
	
	public void signOutLinkText(WebDriver driver) {
		Actions act=new Actions(driver);
		act.moveToElement(signoutImg).perform();
		signOutLink.click();
		
	}
	
}
