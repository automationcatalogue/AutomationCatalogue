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

    @FindBy(xpath = "//a[text()='Log in']")
    WebElement element_LogIn;

    @FindBy(xpath = "//div[@class='block block-category-navigation']//a[contains(text(),'Apparel & Shoes')]")
    WebElement element_ApparelShoes;

    @FindBy(xpath = "//input[@id='small-searchterms']")
    WebElement search_bar;
    @FindBy(xpath = "//input[@value='Search'])[1]")
    WebElement SearchBtn_Click;
    @FindBy(xpath = "//input[@value='Add to cart']")
    WebElement addToCart;

    public void clickProductsLink(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        element_ApparelShoes.click();
        System.out.println("Apparel & Shoes link is clicked");
    }

    public void clickLoginLink(){
        element_LogIn.click();
        System.out.println("Click action is performed on the Login button");
    }
}
