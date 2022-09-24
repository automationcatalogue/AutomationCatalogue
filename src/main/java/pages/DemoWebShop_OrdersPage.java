package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BaseClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DemoWebShop_OrdersPage extends BaseClass {
    WebDriver driver;
    public DemoWebShop_OrdersPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy(xpath = "//a[text()='aarosagarch@gmail.com']")
    WebElement user_email;
    @FindBy(xpath = "(//a[text()='Orders'])[1]")
    WebElement ordersBtn;

    @FindBy(xpath = "(//div[@class='order-list']/div/div/strong/../../div[2])[1]")
    WebElement firstOrderDetails;
    @FindBy(xpath = "//input[@class='button-1 re-order-button']")
    WebElement reOrderBtn;
    @FindBy(xpath = "//input[@id='termsofservice']")
    WebElement termsOfServiceCheckBox;
    @FindBy(xpath ="//button[@name='checkout']" )
    WebElement checkoutBtn;

    @FindBy(xpath = "//div[@class='page account-page order-list-page']//ul/li[3]")
    List<WebElement> listOfAllOrdersPlaced;

    @FindBy(xpath = "//div[@class='page account-page order-list-page']//ul/li[2]")
    List<WebElement> listOfOrdersForDayWise;



    public void ordersPage(){
        //driver.findElement(By.xpath("//a[text()='aarosagarch@gmail.com']")).click();
        user_email.click();
        System.out.println("Click action is performed on Email ");
        //driver.findElement(By.xpath("(//a[text()='Orders'])[1]")).click();
        ordersBtn.click();
        System.out.println("Click action is performed on Orders");
    }
    public void reOrder(){
        //driver.findElement(By.xpath("(//div[@class='order-list']/div/div/strong/../../div[2])[1]")).click();
        firstOrderDetails.click();
        System.out.println("Click action is performed on Details button of first order");
        //driver.findElement(By.xpath("//input[@class='button-1 re-order-button']")).click();
        reOrderBtn.click();
        System.out.println("Click action is performed on Reorder Button");
        //driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        termsOfServiceCheckBox.click();
        System.out.println("Click action is performed on Terms of service Checkbox");
        //driver.findElement(By.xpath("//button[@name='checkout']")).click();
        checkoutBtn.click();
        System.out.println("Click action is performed on Checkout button");
    }

    public void allOrdersTotal(){
        double totalOrderValue = 0.00;
        //List<WebElement> elements_allOrdersPrice = driver.findElements(By.xpath("//div[@class='page account-page order-list-page']//ul/li[3]"));
        List<WebElement> elements_allOrdersPrice=listOfAllOrdersPlaced;
        System.out.println("Total Number of Orders placed :"+elements_allOrdersPrice.size());


        for(WebElement element_OrderValue:elements_allOrdersPrice){
            String orderValue = element_OrderValue.getText();
            orderValue = orderValue.split(":")[1].trim();
            Double dOrderValue = Double.parseDouble(orderValue);
            totalOrderValue += dOrderValue;
        }
        System.out.println("Sum of All Order values is :"+totalOrderValue);
    }
    public void dayWiseOrdersTotal(){
        HashMap<String, Double> map_DayWiseOrders = new HashMap<String, Double>();
        //List<WebElement> elements_allOrders = driver.findElements(By.xpath("//div[@class='page account-page order-list-page']//ul/li[2]"));
        List<WebElement> elements_allOrders=listOfOrdersForDayWise;
        for(WebElement element_Order:elements_allOrders){
            String orderDate = element_Order.getText();
            orderDate = orderDate.split(":")[1].trim();
            orderDate = orderDate.split(" ")[0];

            String orderValue = element_Order.findElement(By.xpath("./../li[3]")).getText();
            orderValue = orderValue.split(":")[1].trim();
            Double dOrderValue = Double.parseDouble(orderValue);

            if(map_DayWiseOrders.containsKey(orderDate)){
                double dtotalOrderValue =map_DayWiseOrders.get(orderDate);
                dtotalOrderValue +=dOrderValue;
                map_DayWiseOrders.put(orderDate,dtotalOrderValue);
            }else{
                map_DayWiseOrders.put(orderDate,dOrderValue);
            }
        }
        //Printing sumOf Orders Daywise
        Set<Map.Entry<String, Double>> allEntries_Daywise =  map_DayWiseOrders.entrySet();
        for(Map.Entry<String, Double> eachEntry_Daywise:allEntries_Daywise){
            System.out.println("Order Date is :"+eachEntry_Daywise.getKey()+" Sum of all Orders :"+eachEntry_Daywise.getValue());
        }
    }
}
