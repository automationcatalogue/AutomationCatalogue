package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BaseClass;

public class DemoWebShop_ProductCataloguePage extends BaseClass {
    WebDriver driver;
    public DemoWebShop_ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//h2[@class='product-title']/a[text()='Blue Jeans']")
    WebElement element_BlueJeans;

    public void selectProduct(){
        element_BlueJeans.click();
        System.out.println("Blue Jeans Product link is clicked");
    }
}
