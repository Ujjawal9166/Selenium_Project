package Organization;

import org.testng.annotations.Test;

import Generic_utility.BaseClass;
import Generic_utility.Excel_Utility;
import Generic_utility.Java_Utility;
import Generic_utility.Property_Utility;
import Generic_utility.Webdriver_Utility;
import POM.HomePage;
import POM.LoginPage;
import POM.OrganizationCreationPage1;
import POM.ValidationAndVerificationPage;

public class CreateOrganizationTest extends BaseClass {

	@Test
	public void createOrganization() throws Throwable {

		Property_Utility plib=new Property_Utility();   
		driver.manage().window().maximize();

		Webdriver_Utility wlib=new Webdriver_Utility();
		wlib.waitForPageToLoad(driver);
		String URL = plib.getKeyValue("url");
		String USERNAME = plib.getKeyValue("username");
		String PASSWORD = plib.getKeyValue("password");

		driver.get(URL);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setLogin(USERNAME, PASSWORD);

		HomePage home=new HomePage(driver);
		home.clickOrganizationLink();

		OrganizationCreationPage1 org=new OrganizationCreationPage1(driver);
		org.clickOrganizationCreateImage();

		Java_Utility jlib=new Java_Utility();
		int RanNum = jlib.getRandomNum();

		Excel_Utility elib=new Excel_Utility();
		String exceldata = elib.getExcelData("organization",0,0)+RanNum;


		org.organizationNamesTextField(exceldata);

		org.saveButton();
		ValidationAndVerificationPage validate=new ValidationAndVerificationPage(driver);

		validate.organisationValidation(driver, exceldata);
		home.signOutLinkText(driver);
		
		driver.close();
	}

}


