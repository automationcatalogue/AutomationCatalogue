package stepDefinitions;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utilities.BaseClass;
import utilities.Utils;
import utilities.YamlUtils;


public class CucumberHooks {
    public static String screenshotPath;
    public static String yamlPath;
    public static String browserName;
    static WebDriver driver;

    @Before
    public void setup() throws Exception{
        System.out.println("******************** Execution Started *****************");
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        screenshotPath = Utils.createScreenshotsFolder(path);

        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");

        driver= Utils.launchBrowser(browserName);
        new BaseClass(driver);
    }
}
