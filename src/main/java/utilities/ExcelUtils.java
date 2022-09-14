package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExcelUtils {
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelSheet;
    private static XSSFRow excelRow;
    private static XSSFCell excelCell;

    public static void setExcelFile(String sPath) throws Exception{
        try{
            FileInputStream fis = new FileInputStream(sPath);
            excelWBook = new XSSFWorkbook(fis);
        }catch(FileNotFoundException fe){
            System.err.println("File is not found...!!!"+fe);
            throw (fe);
        }
    }

    public static String getCellData(int iRowNumber, int iCoumnNumber, String sSheetName){
        excelSheet=excelWBook.getSheet(sSheetName);
        try{
            String sCellData = excelSheet.getRow(iRowNumber).getCell(iCoumnNumber).getStringCellValue();
            return sCellData;
        }catch (Exception e){
            System.out.println("Error in While reading the excel data...!!!");
            throw (e);
        }
    }

    public static void setCellData(String sData, int iRowNumber, int iColumnNumber, String sSheetName, String sPath) throws Exception{
        excelSheet=excelWBook.getSheet(sSheetName);
        try{
            excelRow = excelSheet.getRow(iRowNumber);
            excelCell = excelRow.getCell(iColumnNumber);

            if(excelCell == null){
                excelCell=excelRow.createCell(iColumnNumber);
            }
            excelCell.setCellValue(sData);

            FileOutputStream fos = new FileOutputStream(sPath);
            excelWBook.write(fos);
            fos.close();
        }catch(Exception e){
            System.out.println("Error in while writing the excel Data...!!!");
            throw (e);
        }
    }

    public static int getRowNumber(String sTestId, String sSheetName){
        int iRowSize=excelSheet.getLastRowNum();
        int iRowNumber =0;
        for(;iRowNumber<iRowSize;iRowNumber++){
            String sActualData = excelSheet.getRow(iRowNumber).getCell(Constant.sTestId).getStringCellValue();
            if(sActualData.equalsIgnoreCase(sTestId)){
                System.out.println("TestId"+sTestId+" Row number is found :"+iRowNumber);
                break;
            }
        }
        return iRowNumber;
    }
}
