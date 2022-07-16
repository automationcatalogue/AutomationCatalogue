package TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;
public class TC03_DemoWebShopPurchaseCOD {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();
        driver.get("http://demowebshop.tricentis.com/");
        System.out.println("Webpage DemoWebShop is loaded");
        driver.findElement(By.xpath("//a[text()='Log in']")).click();
        System.out.println("Click action is performed on the Login button");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("aarosagarch@gmail.com");
        System.out.println("aarosagarch@gmail.com is entered as Email");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is entered as password");
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        System.out.println("Click action is performed on Login Button");
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys("Blue Jeans");
        System.out.println("Blue Jeans is Entered as Search data");
        driver.findElement(By.xpath("(//input[@value='Search'])[1]")).click();
        System.out.println("Click action is performed on Search button");
        driver.findElement(By.xpath("//input[@value='Add to cart']")).click();
        System.out.println("Click action is performed on Add to Cart");
        driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
        System.out.println("Click action is performed on the shopping cart ");
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
        List<WebElement> cartElements=driver.findElements(By.xpath
                ("//div[@class='cart-footer']/div[2]/div/table/tbody/tr/td[@class='cart-total-left']"));
        String shippingVal="Shipping: (Ground)",TotalVal="Total:";

        for(WebElement cartEle:cartElements){
            //System.out.println(cartEle.getText());
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
        driver.findElement(By.xpath("//input[@class='button-2 order-completed-continue-button']")).click();
        System.out.println("Click Action is performed on Continue after Order Placement");
        driver.findElement(By.xpath("//a[text()='Log out']")).click();
        System.out.println("Click action is performed on Logout");

        //....Below code is for Credit Card payment....

        /*  driver.findElement(By.xpath("//input[@id='paymentmethod_2']")).click();
        System.out.println("Click action performed on CreditCard Radio Button");
        driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']")).click();
        System.out.println("Click action is performed on Continue for Payment Method");
        driver.findElement(By.xpath("//input[@id='CardholderName']")).sendKeys("test");
        System.out.println("test is entered as CardHolder name");
        driver.findElement(By.xpath("//input[@id='CardNumber']")).sendKeys("1234-5678-9111");
        System.out.println(" 1234-5678-9111 is entered as card number");
        //driver.findElement(By.xpath("")).click();

        Select selectMonth=new Select(driver.findElement(By.xpath("//select[@id='ExpireMonth']")));
        if(selectMonth.isMultiple()){
            System.out.println("Drop-down is Multi selectable");
        }else{
            System.out.println("Drop-down is single selectable");
        }
        selectMonth.selectByIndex(6);
        System.out.println("07 months is selected as month in Expiration date");

        Select selectYear=new Select(driver.findElement(By.xpath("//select[@id='ExpireYear']")));
        selectYear.selectByVisibleText("2025");
        System.out.println("2025 is selected as year in expiration date");
        driver.findElement(By.xpath("//input[@name='CardCode']")).sendKeys("1234");
        System.out.println("1234 is entered as CardCode");
        driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']")).click();
        System.out.println("Click action is performed on continue button for payment information"); */

    }
}
