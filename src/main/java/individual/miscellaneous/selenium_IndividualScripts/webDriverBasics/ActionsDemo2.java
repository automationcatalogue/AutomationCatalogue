package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionsDemo2 {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestpractice.com/students/actions");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebElement element_singleBtn=driver.findElement(By.xpath("//button[@name='click']"));
        WebElement element_doubleBtn=driver.findElement(By.xpath("//button[@name='dblClick']"));

        Actions action = new Actions(driver);
        action.click(element_singleBtn).perform();
        System.out.println("single button is clicked");

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Thread.sleep(2000);

        action.doubleClick(element_doubleBtn).perform();
        System.out.println("single button is clicked");

        alert = driver.switchTo().alert();
        alert.accept();

        WebElement element_source=driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement element_destination=driver.findElement(By.xpath("//div[@id='droppable']"));
       // action.dragAndDrop(element_source, element_destination).build().perform();
        //System.out.println("Drag and Drop action is performed");

       // action.clickAndHold(element_source).release(element_destination).build().perform();
        //System.out.println("Click and Hold, release actions are performed");

        action.dragAndDropBy(element_source,50, 100).build().perform();
        System.out.println("Drag and Drop action is performed with x & y co-ordinates");

        WebElement element_one=driver.findElement(By.name("one"));
        WebElement element_six=driver.findElement(By.name("six"));
        WebElement element_eleven=driver.findElement(By.name("eleven"));
        WebElement element_twelve=driver.findElement(By.name("twelve"));

        action.keyDown(element_one, Keys.CONTROL);
        action.click(element_six).click(element_eleven);
        action.click(element_twelve);
        action.keyUp(Keys.CONTROL);
        action.build().perform();
        System.out.println("Key up and Key down actions are perofmed");

        action.click(driver.findElement(By.name("eight"))).build().perform();
    }
}
