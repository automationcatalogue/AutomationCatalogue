package individual.miscellaneous.selenium_IndividualScripts.jdbc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadTable {
    public static void main(String[] args) throws Exception{
        String dbURL="selenium_IndividualScripts.individual_TestCases.jdbc:mysql://localhost:3306/automationcatalogue";
        String dbUserName="root";
        String dbPassword="V@shi0807";

        String sql_Query="select * from login_credentials where applicationName='orangeHRM'";
        Connection con= DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql_Query);

        String userName="", password="";
        while(rs.next()){

            String aplicationName=rs.getString(1);
            userName=rs.getString(2);
            password=rs.getString(3);
            System.out.println(aplicationName+"\t"+userName+"\t"+password);
        }

        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://testcatalogue-trials7501.orangehrmlive.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("txtUsername")).sendKeys(userName);
        driver.findElement(By.name("txtPassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        Thread.sleep(6000);

    }
}
