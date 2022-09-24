package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseClass;
import utilities.Utils;

import java.time.Duration;

public class DemoWebShop_HomePage extends BaseClass {
    WebDriver driver;
    public DemoWebShop_HomePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy(xpath = "//input[@id='small-searchterms']")
    WebElement search_bar;
    @FindBy(xpath = "//input[@value='Search'])[1]")
    WebElement SearchBtn_Click;
    @FindBy(xpath = "//input[@value='Add to cart']")
    WebElement addToCart;
    @FindBy(xpath = "//span[text()='Shopping cart']")
    WebElement shoppingCart;


    public void addToCart_DemoWebShop(String screenshotPath,String testcaseName) throws Exception {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        //driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys("Blue Jeans");
        search_bar.sendKeys("Jeans");
        System.out.println("Blue Jeans is Entered as Search data");
        //driver.findElement(By.xpath("(//input[@value='Search'])[1]")).click();
        SearchBtn_Click.click();
        System.out.println("Click action is performed on Search button");
        //driver.findElement(By.xpath("//input[@value='Add to cart']")).click();
        addToCart.click();
        System.out.println("Click action is performed on Add to Cart");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_AddToCart");
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
        WebElement element_CartBtn=shoppingCart;
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element_CartBtn);
        System.out.println("Click action is performed on the shopping cart ");
    }
}
