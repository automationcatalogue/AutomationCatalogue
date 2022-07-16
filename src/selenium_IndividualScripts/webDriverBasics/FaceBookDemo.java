package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FaceBookDemo {
    public static void main(String[] args) throws Exception{

        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.facebook.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        //Create New Account
        By createNewAccountLocator=By.linkText("Create New Account");
        WebElement createNewAccountElement=driver.findElement(createNewAccountLocator);
        createNewAccountElement.click();
        System.out.println("Click action is performed Create New Account");

        //driver.findElement(By.linkText("Create New Account")).click();
        Thread.sleep(2000);
        //firstName
        WebElement firstNameelement=driver.findElement(By.name("firstname"));
        firstNameelement.sendKeys("Sagar");
        System.out.println("Sagar is entered as a firstname in firstname text-box");

        Thread.sleep(2000);
        //lastname
        driver.findElement(By.name("lastname")).sendKeys("Chakilala");
        System.out.println("Chakilala is entered as a lastname in lastname text-box");

        //mobilenumber
        Thread.sleep(2000);
        driver.findElement(By.name("reg_email__")).sendKeys("8466807040");
        System.out.println("8466807040 is entered in a mobile number text-box");

        //password
        driver.findElement(By.id("password_step_input")).sendKeys("Test@1234");
        System.out.println("1234 is entered in a password text-box");

        Thread.sleep(2000);

        Select selectDOB = new Select(driver.findElement(By.xpath("//select[@id='day']")));
        boolean multiselect=selectDOB.isMultiple();
        if(multiselect){
            System.out.println("drop-down is multiselected");
        }else{
            System.out.println("drop-down is single selected");
        }

        selectDOB.selectByIndex(7);

        Select selectMonth = new Select(driver.findElement(By.xpath("//select[@id='month']")));
        selectMonth.selectByValue("5");

        Select selectYear = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        selectYear.selectByVisibleText("1997");

        //Gender
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        System.out.println("Click action is performed on Male radio-button");

        //Submit
        driver.findElement(By.name("websubmit")).click();
        System.out.println("Click action is performed on Submit button");



    }
}
