package demowebshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.BaseClass;
import utilities.*;

public class TC_02_ReOrder {
    static WebDriver driver;
    @Test
    public  void reOrder() throws Exception {
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);

        String yamlPath=path+"\\src\\main\\resources\\Config.yaml";

        String browserName= YamlUtils.getYamlData(yamlPath,"browser");
        driver= Utils.launchBrowser(browserName);

        new BaseClass(driver);

        String url = YamlUtils.getYamlData(yamlPath,"demoWebShopURL");
        DriverUtils.loadURL(url);

        //DemoWebShop Login
        CommonMethods_demoWebShop.login_DemoWebShop("aarosagarch@gmail.com","Admin@123");

        //Clicks on Orders and perform Reorder
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

        String orderNumber=driver.findElement(By.xpath("//div[@class='section order-completed']/ul/li[1]")).getText();
        System.out.println(orderNumber);

        CommonMethods_demoWebShop.logout_DemoWebShop();

        DriverUtils.closeBrowser();

    }
}
