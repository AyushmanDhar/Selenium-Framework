package AyushmanDhar.SeleniumFramework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends CartPage {
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[2]")
	List<WebElement> orderedItems;
	public Boolean verifyOrderedItem(String productName) {
		return (orderedItems.stream().anyMatch(s->s.getText().equals(productName)));
	}

}
