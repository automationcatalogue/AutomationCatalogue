package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class XPathsDemo {
    public static void main(String[] args) {
        String text="Home summer needs";
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.amazon.in/");
        String s1=driver.findElement(By.xpath("//h2[text()='"+text+"']")).getText();
        System.out.println(s1);

    }
}
