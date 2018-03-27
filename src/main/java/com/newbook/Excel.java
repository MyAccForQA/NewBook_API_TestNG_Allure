package com.newbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	// Excel file
	private final String FILE_NAME = "./MyResult.xlsx";
	private final String SHEET_NAME = "Result";
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private String[][] linesInSheet = null;

	public Excel(String[][] in) {
		// create linesInSheet
		linesInSheet = in; // new String[1][];
	}

	public void writeFile() {
		// create file
		System.out.println("Creating excel");

		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet(SHEET_NAME);
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(3, 10000);
		sheet.setColumnWidth(4, 10000);

		// move all result in sheet
		Font font = null;
		CellStyle style = null;
		int rowNum = 0;
		Row row = null;
		Cell cell = null;
		for (String[] datatype : linesInSheet) {
			System.out.println("rowNum-" + rowNum);
			row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (String field : datatype) {
				// create new cell
				cell = row.createCell(colNum++);
				if (field.equals("FAIL")) {
					// font
					font = workbook.createFont();
					font.setBold(true);
					font.setFontHeightInPoints((short) 14);
					font.setColor(IndexedColors.BLACK.getIndex());
					// style
					style = workbook.createCellStyle();
					style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					style.setFont(font);
					// value
					cell.setCellStyle(style);
					cell.setCellValue(field);
				} else if (field.equals("PASS")) {
					// font
					font = workbook.createFont();
					font.setBold(true);
					font.setFontHeightInPoints((short) 14);
					font.setColor(IndexedColors.BLACK.getIndex());
					// style
					style = workbook.createCellStyle();
					style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					style.setFont(font);
					// value
					cell.setCellStyle(style);
					cell.setCellValue(field);
				} else
					cell.setCellValue(field);

				// make autoSizeColumn for all cell
				for (int i = 0; i < linesInSheet.length; i++)
					sheet.autoSizeColumn(i);
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(new File(FILE_NAME));
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException");
		}

		// Done
		System.out.println("Done");
	}
}