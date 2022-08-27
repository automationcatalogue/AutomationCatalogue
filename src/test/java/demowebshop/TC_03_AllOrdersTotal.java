package demowebshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TC_03_AllOrdersTotal {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        System.out.println("Chrome Browser is launched");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();
        driver.get("http://demowebshop.tricentis.com/");
        System.out.println("DemoWebShop website is loaded");

        //DemoWebShop Login
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

        driver.findElement(By.xpath("//a[text()='Log out']")).click();
        System.out.println("Click action is performed on Logout");

        driver.quit();
        System.out.println("Browser is closed");
    }
}
