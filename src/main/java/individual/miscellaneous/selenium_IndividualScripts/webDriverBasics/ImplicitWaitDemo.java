package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class ImplicitWaitDemo {

    static String expectedOriginText="HYDERABAD DECAN - HYB";
    static String expecteddestinationText="DELHI S ROHILLA - DEE";

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        driver.get("https://www.irctc.co.in/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//button[@type='submit'][@class='btn btn-primary']")).click();
        selectTrain(driver,"origin",expectedOriginText);
        selectTrain(driver,"destination",expecteddestinationText);
        driver.findElement(By.xpath("//button[@label='Find Trains']")).click();
    }

    static void selectTrain(WebDriver ob1, String data, String textType){
        ob1.findElement(By.xpath("//p-autocomplete[@id='"+data+"']/span/input")).click();
        List<WebElement> elements=ob1.findElements(By.xpath("//p-autocomplete[@id='"+data+"']/span/div/ul/li"));
        for(WebElement ele:elements){
            String actualText=ele.getText();
            if(actualText.equalsIgnoreCase(textType)){
                ele.click();
                break;
            }
        }
    }
}
