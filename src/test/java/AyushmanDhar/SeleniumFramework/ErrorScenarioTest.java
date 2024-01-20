package AyushmanDhar.SeleniumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AyushmanDhar.SeleniumFramework.TestComponents.BaseTest;
import AyushmanDhar.SeleniumFramework.pages.CartPage;
import AyushmanDhar.SeleniumFramework.pages.ProductCatelogue;

public class ErrorScenarioTest extends BaseTest{
	@Test(dataProvider="getInvalidCredentials",groups= {"ErrorHandling"})
	public void errorLogin(HashMap<String,String>input) throws IOException {
		lp.sendLoginDetails(input.get("email"), input.get("password"));
		String errorMessage=lp.loginErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
	@Test(dataProvider="getValidCredentialWithItem",groups= {"ErrorHandling"})
	public void wrongCartItem(HashMap<String, String>input) throws IOException {
		ProductCatelogue pc=lp.sendLoginDetails(input.get("email"), input.get("password"));
		List<WebElement>listOfItems=pc.getProducts();
		String productName=input.get("product");
		String wrongProductName="WrongProductName";
		pc.addToCartProductByName(listOfItems, productName);
		CartPage cp=pc.clickCartButton();
		List<WebElement> cartItems=cp.getCartItems();
		Assert.assertFalse(cp.verifyCartItems(cartItems, wrongProductName));
	}
	
	@DataProvider
	public Object[][] getInvalidCredentials() throws IOException {
		List<HashMap<String,String>> data=getJSONDataToMap("InvalidCredentials.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	@DataProvider
	public Object[][] getValidCredentialWithItem() throws IOException {
		List<HashMap<String,String>> data=getJSONDataToMap("ValidCredentialsWithItems.json");
		return new Object[][] {{data.get(0)}};
	}
	

}
