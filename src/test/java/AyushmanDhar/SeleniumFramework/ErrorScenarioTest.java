package AyushmanDhar.SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AyushmanDhar.SeleniumFramework.TestComponents.BaseTest;
import AyushmanDhar.SeleniumFramework.pages.CartPage;
import AyushmanDhar.SeleniumFramework.pages.ProductCatelogue;

public class ErrorScenarioTest extends BaseTest{
	@Test(groups= {"ErrorHandling"})
	public void errorLogin() throws IOException {
		lp.sendLoginDetails("soundbolt3@gmail.com", "AyushD123");
		String errorMessage=lp.loginErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
	@Test
	public void wrongCartItem() throws IOException {
		ProductCatelogue pc=lp.sendLoginDetails("dharayushman@gmail.com", "AyushD123");
		List<WebElement>listOfItems=pc.getProducts();
		String productName="ZARA COAT 3";
		String wrongProductName="ZARA COAT 33";
		pc.addToCartProductByName(listOfItems, productName);
		CartPage cp=pc.clickCartButton();
		List<WebElement> cartItems=cp.getCartItems();
		Assert.assertFalse(cp.verifyCartItems(cartItems, wrongProductName));
	}

}
