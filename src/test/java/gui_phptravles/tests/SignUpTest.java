package gui_phptravles.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.validation.Assertions;
import com.shaft.validation.Assertions.ElementAttributeType;

import gui_phptravles.pages.HomePage;
import gui_phptravles.pages.ProfilePage;
import gui_phptravles.pages.SignUpPage;

public class SignUpTest {
	private WebDriver driver;
    private  HomePage homepage;
    private SignUpPage signUpPage;
    private ProfilePage profilePage;
    
	@BeforeClass
	public void beforeclass() {
		driver = DriverFactory.getDriver();
		BrowserActions.navigateToURL(driver,"https://www.phptravels.net/");
		homepage = new HomePage(driver);
		signUpPage = new SignUpPage(driver);
		profilePage = new ProfilePage(driver);
	}

	@Test
	public void SignUp() {
		homepage.NavigatetoSignUpPage();
		signUpPage.UserSignUp("Ahmed", "Abdelkawy", "011188998823", "ejkhkjkuyk@gmail.com", "Test12345","Supplier");
		
		
		
		Assertions.assertEquals("https://www.phptravels.net/login/signup",profilePage.getcurrenturl());
	    	

	}
}
