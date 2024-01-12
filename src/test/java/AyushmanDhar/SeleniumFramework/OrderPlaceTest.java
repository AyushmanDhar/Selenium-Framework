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
import AyushmanDhar.SeleniumFramework.pages.CheckoutPage;
import AyushmanDhar.SeleniumFramework.pages.ConfirmationPage;
import AyushmanDhar.SeleniumFramework.pages.OrdersPage;
import AyushmanDhar.SeleniumFramework.pages.ProductCatelogue;

public class OrderPlaceTest extends BaseTest{
	@Test(dataProvider="getValidCredentialsWithItems",groups="PurchaseOrder")
	public void submitOrder(HashMap<String,String>input) throws IOException {
		ProductCatelogue pc=lp.sendLoginDetails(input.get("email"), input.get("password"));
		List<WebElement>listOfItems=pc.getProducts();
		pc.addToCartProductByName(listOfItems, input.get("product"));
		CartPage cp=pc.clickCartButton();
		List<WebElement> cartItems=cp.getCartItems();
		Assert.assertTrue(cp.verifyCartItems(cartItems, input.get("product")));
		CheckoutPage chp=cp.clickCheckoutButton();
		String countryName="Belgium";
		chp.inputCountryBox(countryName);
		Assert.assertTrue(chp.verifyCountryName(countryName));
		ConfirmationPage cfp=chp.clickPaceOrderButton();
		Assert.assertTrue(cfp.verifyConfirmationMessage());
		Assert.assertTrue(cfp.confirmOrderItem(input.get("product")));
	}
	
	@Test(dataProvider="getValidCredentialWithItem", dependsOnMethods="submitOrder")
	public void ordersTest(HashMap<String,String>input) throws IOException {
		lp.sendLoginDetails(input.get("email"), input.get("password"));
		OrdersPage op=lp.clickOrdersButton();
		String productName=input.get("product");
		Assert.assertTrue(op.verifyOrderedItem(productName));
	}
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"soundbolt32@gmail.com","AyushD123","ZARA COAT 3"},{"dharayushman@gmail.com","AyushD123","IPHONE 13 PRO"}};
	}*/
	@DataProvider
	public Object[][] getValidCredentialsWithItems() throws IOException {
		List<HashMap<String,String>> data=getJSONDataToMap("ValidCredentialsWithItems.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	@DataProvider
	public Object[][] getValidCredentialWithItem() throws IOException {
		List<HashMap<String,String>> data=getJSONDataToMap("ValidCredentialsWithItems.json");
		return new Object[][] {{data.get(0)}};
	}
	
	

}
