package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseClass;

import java.time.Duration;

public class DemoWebShop_ShoppingCartPage extends BaseClass {
    WebDriver driver;
    public DemoWebShop_ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy (xpath = "//input[@name='termsofservice']")
    WebElement termsOfService;
    @FindBy(xpath = "//button[@name='checkout']")
    WebElement checkoutBtn;

    public void clickCheckout(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(termsOfService));
        termsOfService.click();
        System.out.println("Click action performed on Terms of Service");
        checkoutBtn.click();
        System.out.println("Click action performed on Checkout Button");
    }
}
