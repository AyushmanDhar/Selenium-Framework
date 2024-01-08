package AyushmanDhar.SeleniumFramework.abstructComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AyushmanDhar.SeleniumFramework.pages.CartPage;
import AyushmanDhar.SeleniumFramework.pages.OrdersPage;

public class AbstructComponents {
	WebDriver driver;
	public AbstructComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public void waitUntilVisibilityBy(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitUntilVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntilInvisibilityBy(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;
	public CartPage clickCartButton() {
		cartButton.click();
		return new CartPage(driver);
		
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement ordersButton;
	public OrdersPage clickOrdersButton() {
		ordersButton.click();
		return new OrdersPage(driver);
		
	}
}
