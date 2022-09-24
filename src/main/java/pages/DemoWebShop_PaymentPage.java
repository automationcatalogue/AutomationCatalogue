package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BaseClass;

import java.util.List;

public class DemoWebShop_PaymentPage extends BaseClass {
    WebDriver driver;
    public DemoWebShop_PaymentPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy(xpath = "(//input[@title='Continue'])[1]")
    WebElement continueBtn_billing;
    @FindBy(xpath = "(//input[@title='Continue'])[2]")
    WebElement continueBtn_shipping;
    @FindBy(xpath = "//input[@onclick='ShippingMethod.save()']")
    WebElement shipping_method;
    @FindBy(xpath ="//input[@id='paymentmethod_0']" )
    WebElement cashOnDelivery_RadioBtn;
    @FindBy(xpath ="//input[@onclick='PaymentMethod.save()']" )
    WebElement continueForPaymentMethod;
    @FindBy(xpath = "//input[@class='button-1 payment-info-next-step-button']")
    WebElement continueForPaymentInformation;
    @FindBy(xpath = "//div[@class='cart-footer']/div[2]/div/table/tbody/tr/td[@class='cart-total-left']")
    List<WebElement> allCartElements;

    @FindBy(xpath = "//div[@class='cart-footer']/div/div/table/tbody/tr[2]/td[2]")
    WebElement element_ShippingPrice;
    @FindBy(xpath = "//div[@class='cart-footer']/div/div/table/tbody/tr[5]/td[2]")
    WebElement element_totalPrice;
    @FindBy(xpath = "//input[@onclick='ConfirmOrder.save()']")
    WebElement confirmOrderBtn;
    @FindBy(xpath = "//div[@class='section order-completed']/ul/li[1]")
    WebElement element_orderNumber;
    public void orders_payments(){
        //driver.findElement(By.xpath("(//input[@title='Continue'])[1]")).click();
        continueBtn_billing.click();
        System.out.println("Click action performed on Continue Button for billing address");
        //driver.findElement(By.xpath("(//input[@title='Continue'])[2]")).click();
        continueBtn_shipping.click();
        System.out.println("Click action performed on Continue Button for shipping address");
        //driver.findElement(By.xpath("//input[@onclick='ShippingMethod.save()']")).click();
        shipping_method.click();
        System.out.println("Click action performed on Continue Button for shipping method");
        //driver.findElement(By.xpath("//input[@id='paymentmethod_0']")).click();
        cashOnDelivery_RadioBtn.click();
        System.out.println("Click action is performed on Cash On Delivery Radio Button");
        //driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']")).click();
        continueForPaymentMethod.click();
        System.out.println("Click action is performed on Continue for payment method");
        //driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
        continueForPaymentInformation.click();
        System.out.println("Click action is performed on Continue for Payment Information");

        //List<WebElement> cartElements=driver.findElements(By.xpath ("//div[@class='cart-footer']/div[2]/div/table/tbody/tr/td[@class='cart-total-left']"));
        List<WebElement> cartElements=allCartElements;
        String shippingVal="Shipping: (Ground)",TotalVal="Total:";
        for(WebElement cartEle:cartElements){
            if(cartEle.getText()==shippingVal){
                //String ShippingPrice=driver.findElement(By.xpath("//div[@class='cart-footer']/div/div/table/tbody/tr[2]/td[2]")).getText();
                String ShippingPrice=element_ShippingPrice.getText();
                System.out.println("Shipping cost is :"+ ShippingPrice);
            }
            if(cartEle.getText()==TotalVal){
                //String TotalPrice=driver.findElement(By.xpath("//div[@class='cart-footer']/div/div/table/tbody/tr[5]/td[2]")).getText();
                String TotalPrice=element_totalPrice.getText();
                System.out.println("Total price is :"+TotalPrice);
            }
        }

        //driver.findElement(By.xpath("//input[@onclick='ConfirmOrder.save()']")).click();
        confirmOrderBtn.click();
        System.out.println("Click action is performed on Confirm Button for Confirm Order");

        //String orderNumber=driver.findElement(By.xpath("//div[@class='section order-completed']/ul/li[1]")).getText();
        String orderNumber=element_orderNumber.getText();
        System.out.println(orderNumber);
    }
}
