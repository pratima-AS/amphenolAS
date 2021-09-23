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

public class EqupmentUtility extends BaseClass {

	public EqupmentUtility() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/src/test/resources/TestData/"
			+ "EquipmentTestdata.xlsx";

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

	@DataProvider(name = "EN_002")
	public static Object[][] EN_002() {
		String sheetName = "EN_002";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_003")
	public static Object[][] EN_003() {
		String sheetName = "EN_003";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_004")
	public static Object[][] EN_004() {
		String sheetName = "EN_004";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_005")
	public static Object[][] EN_005() {
		String sheetName = "EN_005";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_007")
	public static Object[][] EN_007() {
		String sheetName = "EN_007";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_008")
	public static Object[][] EN_008() {
		String sheetName = "EN_008";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_009")
	public static Object[][] EN_009() {
		String sheetName = "EN_009";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_010")
	public static Object[][] EN_010() {
		String sheetName = "EN_010";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	//

	@DataProvider(name = "EN_011")
	public static Object[][] EN_011() {
		String sheetName = "EN_011";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_012")
	public static Object[][] EN_012() {
		String sheetName = "EN_012";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_013")
	public static Object[][] EN_013() {
		String sheetName = "EN_013";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EN_028")
	public static Object[][] EN_028() {
		String sheetName = "EN_028";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EEIRTD_006A")
	public static Object[][] EEIRTD_006A() {
		String sheetName = "EEIRTD_006A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	// EEIRTD_006A
	@DataProvider(name = "EEVRTL_006")
	public static Object[][] EEVRTL_006() {
		String sheetName = "EEVRTL_006";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "EEVRTL_006A")
	public static Object[][] EEVRTL_006A() {
		String sheetName = "EEVRTL_006A";
		Object[][] data = getTestData(sheetName);
		return data;
	}
}
