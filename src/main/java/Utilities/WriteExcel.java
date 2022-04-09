package Utilities;

import Pages.testCases.prNumber;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class WriteExcel {

    public void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite) throws IOException {

        File file = new File(filePath + "\\" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount + 1);
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void write(String... strings) throws IOException {

        String prTitle = prNumber.prTitle;
        String prCreatedDate = prNumber.prCreatedDate;

        String[] valueToWrite = {prTitle, prCreatedDate};
        WriteExcel objExcelFile = new WriteExcel();
        objExcelFile.writeExcel(System.getProperty("user.dir"), "testData.xlsx", "PRList", valueToWrite);
    }

    public static void delete() throws IOException, FilloException {

        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(System.getProperty("user.dir") +"\\"+ "testData.xlsx");
        String query = "Delete from PRList";
        connection.executeUpdate(query);
        connection.close();
    }
}