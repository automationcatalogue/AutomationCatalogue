package utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;

public class YamlUtils {

    private static Yaml yaml;

    /**
     * This method is used to load the yaml data
     * @param yamlPath
     * @return HashMap<String, String>
     * @throws Exception
     * @author sagar
     */
    public static HashMap<String, String> loadYamlData(String yamlPath) throws Exception{
        yaml = new Yaml();
        FileInputStream fis = new FileInputStream(yamlPath);
        HashMap<String, String> map_yaml =yaml.load(fis);
        System.out.println("Yaml Data is loaded");
        return map_yaml;
    }

    /**
     * This method is used to get the yaml data
     * @param yamlPath
     * @param key
     * @return String
     * @throws Exception
     * @author sagar
     */
    public static String getYamlData(String yamlPath, String key) throws Exception{
        HashMap map_yaml =loadYamlData(yamlPath);
        String value = (String) map_yaml.get(key);
        System.out.println(key+" returned value from yaml file is :"+value);
        return value;

    }

    /**
     * This method is used to set the yaml data
     * @param yamlPath
     * @param key
     * @param newValue
     * @throws Exception
     * @author sagar
     */
    public static void setYamlData(String yamlPath,String key, String newValue) throws Exception{
        HashMap map_yaml =loadYamlData(yamlPath);
        FileWriter fw_yamlWriter = new FileWriter(yamlPath);
        map_yaml.put(key,newValue);
        yaml.dump(map_yaml,fw_yamlWriter);
        System.out.println(newValue+" is updated in the yaml file against to "+key);
        fw_yamlWriter.close();
    }
}
