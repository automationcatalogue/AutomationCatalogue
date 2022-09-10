package demowebshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseClass;
import utilities.*;
import java.time.Duration;
import java.util.List;

public class TC_01_PurchaseCOD {
    static WebDriver driver;
    @BeforeClass
    public void beforePurchaseCOD()throws Exception{
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);

        String yamlPath=path+"\\src\\main\\resources\\Config.yaml";

        String browserName= YamlUtils.getYamlData(yamlPath,"browser");
        driver= Utils.launchBrowser(browserName);

        new BaseClass(driver);

        String url = YamlUtils.getYamlData(yamlPath,"demoWebShopURL");
        DriverUtils.loadURL(url);
    }
    @Test
    public void purchaseCOD() throws Exception {

        //Demo WebShop Login
        CommonMethods_demoWebShop.login_DemoWebShop("aarosagarch@gmail.com","Admin@123");

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));

        //Selecting Blue Jeans and Add to Cart
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys("Blue Jeans");
        System.out.println("Blue Jeans is Entered as Search data");
        driver.findElement(By.xpath("(//input[@value='Search'])[1]")).click();
        System.out.println("Click action is performed on Search button");
        driver.findElement(By.xpath("//input[@value='Add to cart']")).click();
        System.out.println("Click action is performed on Add to Cart");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Shopping cart']")));

        driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
        System.out.println("Click action is performed on the shopping cart ");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='termsofservice']")));

        driver.findElement(By.xpath("//input[@name='termsofservice']")).click();
        System.out.println("Click action performed on Terms of Service");
        driver.findElement(By.xpath("//button[@name='checkout']")).click();
        System.out.println("Click action performed on Checkout Button");
        driver.findElement(By.xpath("(//input[@title='Continue'])[1]")).click();
        System.out.println("Click action performed on Continue Button for billing address");
        driver.findElement(By.xpath("(//input[@title='Continue'])[2]")).click();
        System.out.println("Click action performed on Continue Button for shipping address");
        driver.findElement(By.xpath("//input[@onclick='ShippingMethod.save()']")).click();
        System.out.println("Click action performed on Continue Button for shipping method");
        driver.findElement(By.xpath("//input[@id='paymentmethod_0']")).click();
        System.out.println("Click action is performed on Cash On Delivery Radio Button");
        driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']")).click();
        System.out.println("Click action is performed on Continue for payment method");
        driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
        System.out.println("Click action is performed on Continue for Payment Information");

        List<WebElement> cartElements=driver.findElements(By.xpath ("//div[@class='cart-footer']/div[2]/div/table/tbody/tr/td[@class='cart-total-left']"));
        String shippingVal="Shipping: (Ground)",TotalVal="Total:";
        for(WebElement cartEle:cartElements){
            if(cartEle.getText()==shippingVal){
                String ShippingPrice=driver.findElement(By.xpath("//div[@class='cart-footer']/div/div/table/tbody/tr[2]/td[2]")).getText();
                System.out.println("Shipping cost is :"+ ShippingPrice);
            }
            if(cartEle.getText()==TotalVal){
                String TotalPrice=driver.findElement(By.xpath("//div[@class='cart-footer']/div/div/table/tbody/tr[5]/td[2]")).getText();
                System.out.println("Total price is :"+TotalPrice);
            }
        }

        driver.findElement(By.xpath("//input[@onclick='ConfirmOrder.save()']")).click();
        System.out.println("Click action is performed on Confirm Button for Confirm Order");

        String orderNumber=driver.findElement(By.xpath("//div[@class='section order-completed']/ul/li[1]")).getText();
        System.out.println(orderNumber);

        CommonMethods_demoWebShop.logout_DemoWebShop();

        DriverUtils.closeBrowser();

    }
}
