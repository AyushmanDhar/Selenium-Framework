package AyushmanDhar.SeleniumFramework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyushmanDhar.SeleniumFramework.abstructComponents.AbstructComponents;

public class ConfirmationPage extends AbstructComponents{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(tagName="h1")
	private WebElement confirmationMessage;
	public boolean verifyConfirmationMessage() {
		return (confirmationMessage.getText().strip().equals("THANKYOU FOR THE ORDER."));
	}
	
	@FindBy(xpath="//td[@class='line-item product-info-column m-3']/div[@class='title']")
	private List<WebElement> orderedItemNames;
	public boolean confirmOrderItem(String productName) {
		return(orderedItemNames.stream().anyMatch(s->s.getText().equals(productName)));
	}
}
