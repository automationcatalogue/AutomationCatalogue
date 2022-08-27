package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectDemo {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");

        driver.manage().window().maximize();
        driver.switchTo().frame("iframeResult");
        System.out.println("Switched into iframe");

        Select selectVehicle=new Select(driver.findElement(By.xpath("//select[@name='cars']")));
        boolean multiselect=selectVehicle.isMultiple();
        if(multiselect){
            System.out.println("drop-down is multi selected");
        }else{
            System.out.println("drop-down is single selected");
        }

        selectVehicle.selectByIndex(0);
        selectVehicle.selectByValue("opel");
        selectVehicle.selectByVisibleText("Audi");
        Thread.sleep(2000);

        //selectVehicle.deselectByVisibleText("Volvo");
        //selectVehicle.deselectByIndex(2);
        //selectVehicle.deselectByValue("audi");
        //selectVehicle.deselectAll();
        System.out.println("Selected options are: ");
        List<WebElement> vehicleElements=selectVehicle.getAllSelectedOptions();
        for(WebElement ele:vehicleElements){
            String txt=ele.getText();
            System.out.println(txt);
        }

        System.out.println("All available options");
        List<WebElement> AllvehicleElements=selectVehicle.getOptions();
        for(WebElement ele:AllvehicleElements){
            System.out.println(ele.getText());
        }

        System.out.println(selectVehicle.getFirstSelectedOption().getText());
    }
}
