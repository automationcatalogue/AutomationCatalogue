package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoWebSop {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://demowebshop.tricentis.com/");
        Thread.sleep(2000);
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[text()='Log in']")).click();
        driver.findElement(By.xpath("//div[@class='form-fields']/form/div[2]/input")).sendKeys("aarosagarch@gmail.com");
        driver.findElement(By.name("Password")).sendKeys("Admin@123");

        WebElement elementRememberMe=driver.findElement(By.xpath("//input[@id='RememberMe']"));
        boolean ischkboxRememberMe=elementRememberMe.isSelected();
        System.out.println(ischkboxRememberMe);

        if(!ischkboxRememberMe){
            elementRememberMe.click();
            System.out.println("RememberMe check-box is selected");
        }

       /* WebElement elementLogin=driver.findElement(By.xpath("//input[@value='Log in']"));
        boolean isBtnLoginEnabled=elementLogin.isEnabled();
        if(isBtnLoginEnabled){
            elementLogin.click();
            System.out.println("Login button is enabled and clicked");
        }else{
            throw new Exception();
        }
        System.out.println("Learning WebElement methods");*/


    }
}
