package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class DemoWebShopLogin {
    public WebDriver driver;
    public String expectedEmail="aarosagarch@gmail.com";

    @Given("User is loaded DemoWebShop website")
    public void loadDemoWebShop(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
        System.out.println("DemoWebShop website is loaded");
    }

    @And("^User clicks on Login link on topmenu$")
    public void clickLoginButton(){
        driver.findElement(By.xpath("//a[text()='Log in']")).click();
        System.out.println("Login button is clicked");
    }

    @When("^User entered the userName$")
    public void enterUserName(){
        driver.findElement(By.id("Email")).sendKeys("aarosagarch@gmail.com");
        System.out.println("Email is entered");
    }

    @And("^User entered the password$")
    public void enterPassword(){
        driver.findElement(By.id("Password")).sendKeys("Admin@123");
        System.out.println("Password is entered");
    }

    @Then("^User clicks on Login button$")
    public void clickLoginBtn(){
        driver.findElement(By.xpath("//input[@type='submit'][@value='Log in']")).click();
        System.out.println("Login button is clicked");
    }

    @And("^Validate whether Successful Login or not$")
    public void validateLogin(){
        String actualEmail=driver.findElement(By.xpath("//div[@class='header-links']/ul/li[1]/a")).getText();
        Assert.assertEquals(actualEmail,expectedEmail,"Email Ids are not same!!!, Login is unsuccessful");
        System.out.println("Email Id is displayed and Login is successful");
    }
}
