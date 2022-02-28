package gui_phptravles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class SignUpPage {
	private WebDriver driver;

	// Create constructor
	public SignUpPage(WebDriver driver) {
		this.driver = driver;

	}

	// Element Locators
     private By FirstName_TextField = By.xpath("//input[@placeholder='First Name']");
     private By LastName_TextField = By.xpath("//input[@placeholder='Last Name']");
     private By Phone_TextField = By.xpath("//input[@placeholder='Phone']");
     private By Email_TextField = By.xpath("//input[@placeholder='Email']");
     private By Password_TextField = By.xpath("//input[@Placeholder='Password']"); 
    // private By AccountType_dropdown = By.id("select2-account_type-container");
    // private By AccountType_dropdown = By.xpath("//span[@class= 'select2-selection select2-selection--single']");
    // private By Supplier_check = By.xpath("//li[.='Supplier']");
     private By SignUp_button = By.xpath("//button[@type='submit']");
     
	// methods and actions
	public void UserSignUp(String firstName, String lastName, String phone, String email,String password,String Supplier) {
		new ElementActions(driver)
		.type( FirstName_TextField, firstName)
		.type(LastName_TextField, lastName)
		.type(Phone_TextField, phone)
		.type(Email_TextField, email)
		.type(Password_TextField, password)
		.click(SignUp_button);
		//.click(AccountType_dropdown)
		//.select(AccountType_dropdown,Supplier)
		
		
	}



}
