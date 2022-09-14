package demowebshop;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.BaseClass;
import utilities.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TC_08_AllOrdersTotal {
    static WebDriver driver;
    @Test
    public void allOrdersTotal() throws Exception {
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

        driver.findElement(By.xpath("//a[text()='aarosagarch@gmail.com']")).click();
        System.out.println("Click action is performed on Email ");
        driver.findElement(By.xpath("(//a[text()='Orders'])[1]")).click();
        System.out.println("Click action is performed on Orders");

        double totalOrderValue = 0.00;
        List<WebElement> elements_allOrdersPrice = driver.findElements(By.xpath("//div[@class='page account-page order-list-page']//ul/li[3]"));
        System.out.println("Total Number of Orders placed :"+elements_allOrdersPrice.size());

        for(WebElement element_OrderValue:elements_allOrdersPrice){
            String orderValue = element_OrderValue.getText();
            orderValue = orderValue.split(":")[1].trim();
            Double dOrderValue = Double.parseDouble(orderValue);
            totalOrderValue += dOrderValue;
        }
        System.out.println("Sum of All Order values is :"+totalOrderValue);

        HashMap<String, Double> map_DaywiseOrders = new HashMap<String, Double>();
        List<WebElement> elements_allOrders = driver.findElements(By.xpath("//div[@class='page account-page order-list-page']//ul/li[2]"));
        for(WebElement element_Order:elements_allOrders){
            String orderDate = element_Order.getText();
            orderDate = orderDate.split(":")[1].trim();
            orderDate = orderDate.split(" ")[0];

            String orderValue = element_Order.findElement(By.xpath("./../li[3]")).getText();
            orderValue = orderValue.split(":")[1].trim();
            Double dOrderValue = Double.parseDouble(orderValue);

            if(map_DaywiseOrders.containsKey(orderDate)){
                double dtotalOrderValue =map_DaywiseOrders.get(orderDate);
                dtotalOrderValue +=dOrderValue;
                map_DaywiseOrders.put(orderDate,dtotalOrderValue);
            }else{
                map_DaywiseOrders.put(orderDate,dOrderValue);
            }
        }
        //Printing sumOf Orders Daywise
        Set<Map.Entry<String, Double>> allEntries_Daywise =   map_DaywiseOrders.entrySet();
        for(Map.Entry<String, Double> eachEntry_Daywise:allEntries_Daywise){
            System.out.println("Order Date is :"+eachEntry_Daywise.getKey()+" Sum of all Orders :"+eachEntry_Daywise.getValue());
        }

        CommonMethods_demoWebShop.logout_DemoWebShop();

        DriverUtils.closeBrowser();

    }
}
