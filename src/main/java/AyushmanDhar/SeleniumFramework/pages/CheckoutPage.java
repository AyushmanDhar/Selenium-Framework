package AyushmanDhar.SeleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyushmanDhar.SeleniumFramework.abstructComponents.AbstructComponents;

public class CheckoutPage extends AbstructComponents{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	private WebElement countryBox;
	public void inputCountryBox(String countryName) {
		countryBox.sendKeys(countryName);
		Actions ac=new Actions(driver);
		ac.click(driver.findElement(By.xpath("//span[text()=' "+countryName+"']"))).build().perform();
	}
	
	
	@FindBy(xpath="//a[text()='Place Order ']")
	private WebElement placeOrderButton;
	public ConfirmationPage clickPaceOrderButton() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
		
	}
	public boolean verifyCountryName(String countryName) {
		return (countryBox.getAttribute("value").strip().equals(countryName));
		
	}
}
