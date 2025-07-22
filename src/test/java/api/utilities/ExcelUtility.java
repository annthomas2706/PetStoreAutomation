package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private static Workbook workbook;
    private static Sheet sheet;

    // Load the Excel file and sheet
    public static void loadExcel(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    // Get cell data by row and column index
    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long)cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    // Get total row count
    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    // Get total column count from the first row
    public static int getColumnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    // Close workbook
    public static void closeExcel() throws IOException {
        workbook.close();
    }


}
