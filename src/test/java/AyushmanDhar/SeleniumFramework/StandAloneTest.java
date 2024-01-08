package AyushmanDhar.SeleniumFramework;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	public static void main(String[]args){
		String productName="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("soundbolt32@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("AyushD123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card-body']")));
		List<WebElement> listOfItems=driver.findElements(By.xpath("//div[@class='card-body']"));
		listOfItems.stream().filter(s->s.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst().ifPresent(s->s.findElement(By.xpath(".//button[text()=' Add To Cart']")).click());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> cartItems=driver.findElements(By.xpath("//div[@class='cart']//h3"));
		Assert.assertTrue(cartItems.stream().anyMatch(s->s.getText().equals(productName)));
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		WebElement countryBox=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		countryBox.sendKeys("India");
		Actions ac=new Actions(driver);
		ac.click(driver.findElement(By.xpath("//span[text()=' India']"))).build().perform();
		Assert.assertEquals(countryBox.getAttribute("value").strip(), "India");
		driver.findElement(By.xpath("//div[text()='CVV Code ']/following-sibling::input")).sendKeys("123");
		driver.findElement(By.xpath("//div[text()='Name on Card ']/following-sibling::input")).sendKeys("Ayush Dhar");
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		List<WebElement> orderedItemNames=driver.findElements(By.xpath("//td[@class='line-item product-info-column m-3']/div[@class='title']"));
		Assert.assertTrue(orderedItemNames.stream().anyMatch(s->s.getText().equals(productName)));
		Assert.assertEquals(driver.findElement(By.tagName("h1")).getText().strip(), "THANKYOU FOR THE ORDER.");
		driver.quit();
	}
}
