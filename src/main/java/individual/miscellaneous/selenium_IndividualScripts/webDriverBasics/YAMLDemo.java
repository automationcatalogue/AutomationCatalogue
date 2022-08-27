package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Map;

public class YAMLDemo {
    public static void main(String[] args) throws Exception{
        Yaml yaml = new Yaml();
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\TestConfig.yaml");
        //HashMap<String, String> map=ob.load(fis);
        Map<String, String> map = (Map<String, String>) yaml.load(inputStream);
        String browser=map.get("browser");
        System.out.println("Browser is :"+browser);

        String chromeDriverPath=map.get("chromeDriverExefilepath");
        System.out.println("Chrome Driver path is :"+chromeDriverPath);

        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        String orangeHRMURL = map.get("orangehrmURL");

        driver.get(orangeHRMURL);
        System.out.println(orangeHRMURL+ " is loaded");

        FileWriter fw = new FileWriter(System.getProperty("user.dir")+"\\TestData\\TestConfig.yaml");
        map.put("employeeid","999");
        yaml.dump(map,fw);
        fw.close();
    }
}
