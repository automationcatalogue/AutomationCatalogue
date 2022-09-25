package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseClass;
import utilities.Utils;

import java.time.Duration;

public class DemoWebShop_ProductDisplayPage extends BaseClass {

    WebDriver driver;
    public DemoWebShop_ProductDisplayPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='add-to-cart-panel']/input[starts-with(@id,'add-to-cart-button')]")
    WebElement element_AddToCart;

    @FindBy(xpath = "//span[text()='Shopping cart']")
    WebElement shoppingCart;

    public void clickAddToCart(String screenshotPath, String testcaseName) throws Exception{
        element_AddToCart.click();
        System.out.println("Add To Cart button is clicked");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_AddToCart");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
        WebElement element_CartBtn=shoppingCart;
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element_CartBtn);
        System.out.println("Click action is performed on the shopping cart ");
    }
}
