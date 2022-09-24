package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseClass;

import java.time.Duration;

public class DemoWebShop_CartPage extends BaseClass {
    WebDriver driver;
    public DemoWebShop_CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy (xpath = "//input[@name='termsofservice']")
    WebElement termsOfService;
    @FindBy(xpath = "//button[@name='checkout']")
    WebElement checkoutBtn;
    public void cartPage_checkout(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='termsofservice']")));
        wait.until(ExpectedConditions.elementToBeClickable(termsOfService));
        //driver.findElement(By.xpath("//input[@name='termsofservice']")).click();
        termsOfService.click();
        System.out.println("Click action performed on Terms of Service");
        //driver.findElement(By.xpath("//button[@name='checkout']")).click();
        checkoutBtn.click();
        System.out.println("Click action performed on Checkout Button");
    }
}
