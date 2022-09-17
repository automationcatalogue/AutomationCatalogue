package testcases;

import org.testng.annotations.BeforeSuite;
import utilities.YamlUtils;

public class TestConfig {
    public static String browserName;
    public static String yamlPath;
    public static String sTestId;

    @BeforeSuite
    public void testConfigLoad() throws Exception{
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);

        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");

        sTestId = YamlUtils.getYamlData(yamlPath,"TestId");

    }
}
