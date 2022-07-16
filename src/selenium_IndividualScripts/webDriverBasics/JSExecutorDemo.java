package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class JSExecutorDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('twotabsearchtextbox').value='iPhone'");
        WebElement element = driver.findElement(By.id("nav-search-submit-button"));
        js.executeScript("arguments[0].click();",element);

        js.executeScript("window.scrollBy(0,200)");
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        WebElement iphoneElement=driver.findElement(By.xpath("//span[text()='Apple iPhone 12 (64GB) - Green']"));
        js.executeScript("arguments[0].scrollIntoView(true);",iphoneElement);

        js.executeScript("history.go(0)");
        String title=js.executeScript("return document.title;").toString();
        System.out.println("title is :"+title);

        String url=js.executeScript("return document.URL;").toString();
        System.out.println("URL is :"+url);

        String domain=js.executeScript("return document.domain;").toString();
        System.out.println("Domain is :"+domain);

        js.executeScript("window.location = \'https://www.facebook.com'");

    }
}
