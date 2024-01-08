package AyushmanDhar.SeleniumFramework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyushmanDhar.SeleniumFramework.abstructComponents.AbstructComponents;

public class ProductCatelogue extends AbstructComponents{
	WebDriver driver;
	public ProductCatelogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	By productsBy=By.xpath("//div[@class='card-body']");
	public List<WebElement> getProducts() {
		waitUntilVisibilityBy(productsBy);
		return products;
	}
	
	By additionXpathItemBy=By.xpath(".//b");
	By additionXpathAddToCartButtonBy=By.xpath(".//button[text()=' Add To Cart']");
	By cofirmationAlertBoxBy=By.id("toast-container");
	By loadingScreenBy=By.tagName("ngx-spinner");
	public void addToCartProductByName(List<WebElement> productsList,String productName) {
		productsList.stream().filter(s->s.findElement(additionXpathItemBy).getText().equals(productName)).findFirst().ifPresent(s->s.findElement(additionXpathAddToCartButtonBy).click());
		waitUntilVisibilityBy(cofirmationAlertBoxBy);
		waitUntilInvisibilityBy(loadingScreenBy);
	}
	

}
