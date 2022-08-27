package TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;
public class TC08_DWSAllOrdersTotal {
    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver", "C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
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

        List<WebElement> DetailsElements =driver.findElements(By.xpath("//input[@class='button-2 order-details-button']"));
        Double allOrdersCount=0.0;
        for(WebElement Details:DetailsElements){
            Details.click();
            String totalPrice=driver.findElement(By.xpath("//div[@class='order-overview']/div[3]/strong")).getText();
            driver.navigate().back();
            System.out.println("Price of each order is : "+totalPrice);
            Double TotalAsNumber=Double.parseDouble(totalPrice);
            allOrdersCount=allOrdersCount+TotalAsNumber;
        }
        System.out.println("Total cost of all orders is : "+allOrdersCount);

        //driver.findElement(By.xpath("(//input[@class='button-2 order-details-button'])[1]")).click();
        //System.out.println("Click action performed on first order");
        //String OrderTotal=driver.findElement(By.xpath("//div[@class='order-overview']/div[3]/strong")).getText();
        //System.out.println("Order total is : "+OrderTotal);
    }
}
