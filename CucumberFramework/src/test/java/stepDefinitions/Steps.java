package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import dataProviders.ConfigFileReader;
import managers.FileReaderManager;
import managers.PageObjectManager;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ProductListingPage;

public class Steps {
	WebDriver driver;
	HomePage homePage;
	ProductListingPage productListingPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	PageObjectManager pageObjectManager;
	ConfigFileReader configFileReader;
	

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() {
		configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver", FileReaderManager.getInstance().getConfigReader().getDriverPath());
//		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		pageObjectManager = new PageObjectManager(driver);
		homePage = pageObjectManager.getHomePage();
		homePage.navigateTo_HomePage();	
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String product) {
		homePage.perform_Search(product);
	}

	@When("^choose to buy the first item$")
	public void choose_to_buy_the_first_item() {
		productListingPage = pageObjectManager.getProductListingPage();
		productListingPage.select_Product(0);
		productListingPage.clickOn_AddToCart();
	}

	@When("^moves to checkout from mini cart$")
	public void moves_to_checkout_from_mini_cart() {
		cartPage = pageObjectManager.getCartPage();
		cartPage.clickOn_Cart();
		cartPage.clickOn_ContinueToCheckout();
	}

	@When("^enter personal details on checkout page$")
	public void enter_personal_details_on_checkout_page() throws Throwable {
		checkoutPage = pageObjectManager.getCheckoutPage();
		checkoutPage.fill_PersonalDetails();
	}


	@When("^select same delivery address$")
	public void select_same_delivery_address() throws Throwable {
		checkoutPage.check_ShipToDifferentAddress(false);
	}

	@When("^select payment method as \"([^\"]*)\" payment$")
	public void select_payment_method_as_payment(String arg1) throws Throwable {
		checkoutPage.select_PaymentMethod("CheckPayment");
	}

	@When("^place the order$")
	public void place_the_order() throws Throwable {
		checkoutPage.check_TermsAndCondition(true);
		checkoutPage.clickOn_PlaceOrder();
		
		driver.quit();
	}

}
