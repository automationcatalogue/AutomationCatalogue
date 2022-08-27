package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {
    public static void main(String[] args) throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        String title=driver.getTitle();
        System.out.println("title of the webpage is :"+title);

        Thread.sleep(3000);

        /*WebDriver.Options option=driver.manage();
        WebDriver.Window test=option.window();
        test.maximize();*/

        driver.manage().window().maximize();


        driver.get("https://www.amazon.in/");
        title=driver.getTitle();
        System.out.println("title of the webpage is :"+title);

        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();

        String url=driver.getCurrentUrl();
        System.out.println("Latest URL is :"+url);

        String source=driver.getPageSource();
        System.out.println(source);

        driver.quit();


    }
}
