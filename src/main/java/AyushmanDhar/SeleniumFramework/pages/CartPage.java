package AyushmanDhar.SeleniumFramework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyushmanDhar.SeleniumFramework.abstructComponents.AbstructComponents;

public class CartPage extends AbstructComponents {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cart']//h3")
	private List<WebElement> cartItems;
	By CartItemsBy=By.xpath("//div[@class='cart']//h3");
	public List<WebElement> getCartItems(){
		waitUntilVisibilityBy(CartItemsBy);
		return cartItems;
	}
	
	public boolean verifyCartItems(List<WebElement> cartItems,String productName) {
		return(cartItems.stream().anyMatch(s->s.getText().equals(productName)));
	}
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	private WebElement checkoutButton;
	public CheckoutPage clickCheckoutButton() {
		checkoutButton.click();
		CheckoutPage chp=new CheckoutPage(driver);
		return chp;
	}
	
}
