package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import utilities.YamlUtils;

public class BaseClass {

    private static WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }

}
