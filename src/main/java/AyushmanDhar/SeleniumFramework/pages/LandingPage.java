package AyushmanDhar.SeleniumFramework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyushmanDhar.SeleniumFramework.abstructComponents.AbstructComponents;

public class LandingPage extends AbstructComponents {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement loginButton;
	
	public ProductCatelogue sendLoginDetails(String email,String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		return new ProductCatelogue(driver);
	}
	
	@FindBy(xpath="//div[@id='toast-container']/div")
	WebElement loginToastContainer;
	public String loginErrorMessage() {
		waitUntilVisible(loginToastContainer);
		return loginToastContainer.getText();
	}
	
	
	
}
