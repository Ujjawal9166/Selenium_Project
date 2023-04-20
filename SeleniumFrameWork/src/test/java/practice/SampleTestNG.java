package practice;

import org.testng.annotations.Test;


public class SampleTestNG {

	@Test(priority=2)
	public void createContact() {
		System.out.println("contact created");
	}
	@Test(priority=1)
	public void modifyContact() {
		System.out.println("contact modified");
	}
	@Test(priority=3)
	public void deleteProduct() {
		System.out.println("contact deleted");
	}
}
