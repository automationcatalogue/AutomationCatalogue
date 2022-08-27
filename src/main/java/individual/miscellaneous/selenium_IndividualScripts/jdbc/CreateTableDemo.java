package individual.miscellaneous.selenium_IndividualScripts.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTableDemo {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");

        Connection con=DriverManager.getConnection("selenium_IndividualScripts.individual_TestCases.jdbc:mysql://localhost:3306/automationcatalogue","root", "V@shi0807");
        System.out.println("Connection is successful");

        Statement st = con.createStatement();
        String sql_Query="create table login_credentials(applicationName varchar(20), userName varchar(20), password varchar(20))";
        int rows=st.executeUpdate(sql_Query);
        System.out.println("Number of rows effected :"+rows);

    }

}
