package utilities;

import org.openqa.selenium.WebDriver;

public class DriverUtils extends BaseClass{


    public DriverUtils(WebDriver driver) {
        super(driver);
    }

    /**
     * This method is used to load the URL
     * @parameters: String URL
     * @author sagar
     */
    public static void loadURL(String URL){
        getDriver().get(URL);
        System.out.println(URL+" is loaded");
    }

    public static void closeBrowser(){
        getDriver().quit();
        System.out.println("Driver instance is terminated and all browsers are closed");
    }
}
