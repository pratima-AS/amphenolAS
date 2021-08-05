package com.vrt.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.vrt.base.BaseClass;

public class PreferencesUtility extends BaseClass {

	public PreferencesUtility() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/src/test/resources/TestData/"
			+ "PreferencesTestdata.xlsx";

	static Workbook book;
	static Sheet sheet;

	// Read TestData from the Excel sheet
	public static Object[][] getTestData(String sheetName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(TestData_sheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(fis);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				// System.out.println(data[i][j]);
			}
		}
		return data;
	}

	// Data Providers

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ADMIN module related Test Data reference
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@DataProvider(name = "PREF002")
	public static Object[][] PREF002() {
		String sheetName = "PREF002";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "PREF002A")
	public static Object[][] PREF002A() {
		String sheetName = "PREF002A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "PREF009A")
	public static Object[][] PREF009A() {
		String sheetName = "PREF009A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "PREF009B")
	public static Object[][] PREF009B() {
		String sheetName = "PREF009B";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "PREF034")
	public static Object[][] PREF034() {
		String sheetName = "PREF034";
		Object[][] data = getTestData(sheetName);
		return data;
	}

}
