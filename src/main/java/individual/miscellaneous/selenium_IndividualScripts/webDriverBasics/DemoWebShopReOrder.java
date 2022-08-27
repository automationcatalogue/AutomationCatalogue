package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DemoWebShopReOrder {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver","C:\\AutomationCatalogue\\Drivers\\Edge\\edgedriver\\msedgedriver.exe");
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
        driver.findElement(By.xpath("//a[text()='aarosagarch@gmail.com']")).click();
        System.out.println("Click action is performed on Email ");
        driver.findElement(By.xpath("(//a[text()='Orders'])[1]")).click();
        System.out.println("Click action is performed on Orders");
        driver.findElement(By.xpath("(//div[@class='order-list']/div/div/strong/../../div[2])[1]")).click();
        System.out.println("Click action is performed on Details button of first order");
        driver.findElement(By.xpath("//input[@class='button-1 re-order-button']")).click();
        System.out.println("Click action is performed on Reorder Button");
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        System.out.println("Click action is performed on Terms of service Checkbox");
        driver.findElement(By.xpath("//button[@name='checkout']")).click();
        System.out.println("Click action is performed on Checkout button");
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
        driver.findElement(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")).click();
        System.out.println("Click action is performed on Confirm order");



    }
}
