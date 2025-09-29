package automationpractice.CuraHealthWeb.utils;

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
