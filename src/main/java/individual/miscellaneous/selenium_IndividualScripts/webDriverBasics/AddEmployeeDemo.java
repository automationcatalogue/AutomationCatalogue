package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AddEmployeeDemo {
    public static void main(String[] args) throws Exception{
        String locationData="India Office";
        String alternateLocation="European Office";

        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://automatecatalog-trials7401.orangehrmlive.com/auth/login");
        driver.manage().window().maximize();
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("Admin@123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        Thread.sleep(6000);

        //PIM
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//input[@id='first-name-box']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='last-name-box']")).sendKeys("David");

        driver.findElement(By.xpath("//button[@data-id='location']")).click();
        List<WebElement> locationElements=driver.findElements(By.xpath("//label[@for='location']/../div[1]//ul[@class='dropdown-menu inner show']/li/a/span"));

        //1st way
        for(WebElement location:locationElements){
            String actualLocation=location.getText();
            if(actualLocation.equalsIgnoreCase(locationData)){
                location.click();
                System.out.println(locationData+" is selected as location");
                break;
            }
        }

        Thread.sleep(4000);
        //2nd way
        driver.findElement(By.xpath("//button[@data-id='location']")).click();
        int size=locationElements.size();
        for(int i=1;i<=size;i++){
            WebElement element=driver.findElement(By.xpath("//label[@for='location']/../div[1]//ul[@class='dropdown-menu inner show']/li["+i+"]/a/span"));
            String actualText=element.getText();
            if(actualText.equalsIgnoreCase(alternateLocation)){
                element.click();
                System.out.println(alternateLocation+" is selected as location");
                break;
            }
        }








    }
}
