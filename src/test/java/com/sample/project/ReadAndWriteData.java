package com.sample.project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadAndWriteData {
	
	@Test (description = "Read data with apache poi. Dependency is at pom.")
	public void readAndWriteDataApachePoi() throws IOException {

		FileInputStream file = new FileInputStream("./src/test/resources/test data/Emp.xlsx");

		// Create Workbook
		XSSFWorkbook wb = new XSSFWorkbook(file);

		// Go to first sheet from the workbook
		XSSFSheet ws = wb.getSheetAt(0);

		// Loop through each row one by one
		Iterator<Row> rowIterator = ws.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, loop through cells
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				// Check cell type and format
				switch (cell.getCellType()) {
				case NUMERIC:
					DataFormatter formatter = new DataFormatter();
					String formattedCellValue = formatter.formatCellValue(cell);
					System.out.print(formattedCellValue);
					break;
				case STRING:
					System.out.print(cell.getStringCellValue());
					break;
				}
				System.out.print(" ");
			}
			System.out.println("");
		}
		file.close();
	}

}
