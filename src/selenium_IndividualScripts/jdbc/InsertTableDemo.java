package selenium_IndividualScripts.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTableDemo {
    public static void main(String[] args) throws Exception{
        String dbURL="selenium_IndividualScripts.individual_TestCases.jdbc:mysql://localhost:3306/automationcatalogue";
        String dbUserName="root";
        String dbPassword="V@shi0807";

        //String sql_Query="insert into login_credentials values('OrangeHRM', 'Admin', 'Admin@123')";
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the application Name");
        String appName=sc.next();

        System.out.println("Enter the Username");
        String un=sc.next();

        System.out.println("Enter the Password");
        String pwd=sc.next();

        String sql_Query="insert into login_credentials values('"+appName+"','"+un+"','"+pwd+"')";
        System.out.println(sql_Query);

        Connection con=DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        Statement st=con.createStatement();
        int rows=st.executeUpdate(sql_Query);
        System.out.println("Number of rows effected is :"+rows);
    }
}
