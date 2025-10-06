package automationpractice.CuraHealthWeb.utils;

/**
 * Utility class for reading data from Excel files using Apache POI.
 * <p>
 * Provides a method to extract all data from a specified sheet in an Excel file (.xlsx)
 * and return it as a list of rows, where each row is a list of string values.
 * <p>
 * Usage example:
 * <pre>
 *     List<List<String>> data = ExcelUtils.getSheetData("path/to/file.xlsx", "Sheet1");
 * </pre>
 *
 * Dependencies:
 * - Apache POI (org.apache.poi.ss.usermodel, org.apache.poi.xssf.usermodel)
 *
 * Author: [Your Name]
 * Date: October 6, 2025
 */

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static List<List<String>> getSheetData(String filePath, String sheetName) throws IOException {
        List<List<String>> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    rowData.add(cell.getStringCellValue());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    rowData.add(String.valueOf(cell.getNumericCellValue()));
                } else {
                    rowData.add("");
                }
            }
            data.add(rowData);
        }
        workbook.close();
        fis.close();
        return data;
    }
}