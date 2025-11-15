package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	// File --> Workbook --> Worksheet --> Row - Cell

	// FileInputStream -- Read file
	// FileOutputStream -- Write file
	// XSSFWorkbook -- Workbook
	// XSSFWorksheet -- Worksheet
	// XSSFWorkRow -- WorkRow
	// XSSFWorkCell -- WorkCell
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static XSSFCellStyle style;
	public static String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	// Number of rows in the given file
	// getRowcount

	public static int getRowcount(String sheetName) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		return rowCount;
	}

	// Number of columns in the given file
	// getCellCount

	public static int getCellcount(String sheetName, int rownum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int cellCount = ws.getRow(rownum).getLastCellNum();
		return cellCount;
	}

	// Get the Data of the given file
	public static String getCellData(String sheetName, int rownum, int cellnum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);

		String data = "";
		try {
			DataFormatter dataformatter = new DataFormatter();
			data = dataformatter.formatCellValue(cell);

		} catch (Exception e) {

		}
		return data;
	}

	// Set the Data of the given file

	public static void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		cell.setCellValue(data);

		fo = new FileOutputStream(path);
		wb.write(fo);

		wb.close();
		fi.close();
		fo.close();
	}
}
