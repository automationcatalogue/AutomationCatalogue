package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelDemo {
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir");
        System.out.println("Path of the current project is :"+path);

        FileInputStream fis = new FileInputStream(path+"\\TestData\\AutomationCatalogue.xlsx");
        XSSFWorkbook wbk = new XSSFWorkbook(fis);
        XSSFSheet sh = wbk.getSheet("Add Employee");

        XSSFRow row = sh.getRow(2);
        int rowcount=sh.getLastRowNum();
        System.out.println("Total number of rows present are :"+rowcount);

        XSSFCell cell = row.getCell(1);
        String userName = cell.getStringCellValue();
        System.out.println("UserName is :"+userName);

        cell=row.getCell(2);
        String password = cell.getStringCellValue();
        System.out.println("password is :"+password);

        cell=row.getCell(3);
        String firstname = cell.getStringCellValue();
        System.out.println("firstname is :"+firstname);

        cell=row.getCell(4);
        String middlename = cell.getStringCellValue();
        System.out.println("middlename is :"+middlename);

        cell=row.getCell(5);
        String lastname = cell.getStringCellValue();
        System.out.println("lastname is :"+lastname);

        cell=row.getCell(6);
        String location = cell.getStringCellValue();
        System.out.println("location is :"+location);

        fis.close();




    }

}
