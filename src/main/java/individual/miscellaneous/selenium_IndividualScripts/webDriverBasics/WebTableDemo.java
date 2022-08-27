package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WebTableDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bseindia.com/markets/currencyDerivatives/most_active.html?flagseries=&flagcall=&flagstrgid=&FlagDrop=");
        driver.manage().window().maximize();

        List<WebElement> rowElements=driver.findElements(By.xpath("//div[@class='largetable']/table/tbody/tr/td/table/tbody[2]/tr"));
        System.out.println("Total number of rows are: "+rowElements.size());

        List<WebElement> columnElements=driver.findElements(By.xpath("//div[@class='largetable']/table/tbody/tr/td/table/tbody[2]/tr[5]/td"));
        System.out.println("Total number of columns are:" + columnElements.size());

        //Getting the column number for Volumes
        int colPosition=0;
        for(int i=1;i<= columnElements.size();i++){
            WebElement element=driver.findElement(By.xpath("//div[@class='largetable']/table/tbody/tr/td/table/tbody[1]/tr/td["+i+"]"));
            String columnName=element.getText();
            if(columnName.contains("Volume")){
                colPosition=i;
                break;
            }
        }
        System.out.println("Column position of Voume is :"+colPosition);

        //Print 8th row data
        System.out.println("8th row data is :");
        for(int i=1;i<= columnElements.size();i++){
            WebElement element=driver.findElement(By.xpath("//div[@class='largetable']/table/tbody/tr/td/table/tbody[2]/tr[8]/td["+i+"]"));
            String data = element.getText();
            System.out.print(data+"\t");
        }
        System.out.println();

        //Print the Volume columns data and sum of all values
        int sum=0;
        for(int i=1;i<= rowElements.size();i++){
            WebElement element=driver.findElement(By.xpath("//div[@class='largetable']/table/tbody/tr/td/table/tbody[2]/tr["+i+"]/td["+colPosition+"]"));
            String data = element.getText();
            data = data.replace(",","");
            System.out.println(data);
            int val=Integer.parseInt(data);
            sum+=val;
        }
        System.out.println("Sum of all volumes is :"+sum);

        //Printing Entire table data
        System.out.println("Complete Web Table data is");
        for(int i=1;i<= rowElements.size();i++){
            for(int j=1;j<=columnElements.size();j++){
                WebElement element=driver.findElement(By.xpath("//div[@class='largetable']/table/tbody/tr/td/table/tbody[2]/tr["+i+"]/td["+j+"]"));
                String data = element.getText();
                System.out.print(data+"\t");
            }
            System.out.println();
        }
    }
}
