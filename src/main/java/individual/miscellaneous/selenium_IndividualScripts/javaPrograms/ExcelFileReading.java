package individual.miscellaneous.selenium_IndividualScripts.javaPrograms;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelFileReading {
    public static void main(String[] args) throws Exception {
        String excelFilepath=".\\src\\main\\resources\\TestData.xlsx";
        FileInputStream fis=new FileInputStream(excelFilepath);
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheet("AddEmployee");

        int rows=sheet.getLastRowNum();
        System.out.println("Row size  in the given sheet are "+rows);
        int cells=sheet.getRow(rows).getLastCellNum();
        System.out.println("Cell size in given sheet are" +cells);
        for(int r=0;r<=rows;r++){
            XSSFRow excelRow=sheet.getRow(r);
            //String Value=sheet.getRow(r).getCell();
            for(int c=0;c<=cells;c++){
                XSSFCell excelCell=excelRow.getCell(c);
                //System.out.println(excelCell.getCellType());
                if(excelCell==null) {
                    continue;
                }
                switch(excelCell.getCellType()){
                    case STRING: System.out.print(excelCell.getStringCellValue());break;
                    case NUMERIC: System.out.print(excelCell.getNumericCellValue());break;
                }
                System.out.print(" : ");
            }

            System.out.println();
        }
    }
}
