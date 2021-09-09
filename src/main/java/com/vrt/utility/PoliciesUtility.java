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

public class PoliciesUtility extends BaseClass {

	public PoliciesUtility() throws IOException {
		super();
	}

	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/src/test/resources/TestData/"
			+ "PolicyTestdata.xlsx";

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

	@DataProvider(name = "Policies003")
	public static Object[][] Policies003() {
		String sheetName = "Policies003";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_01")
	public static Object[][] Policies003_01() {
		String sheetName = "Policies003_01";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_02")
	public static Object[][] Policies003_02() {
		String sheetName = "Policies003_02";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_03")
	public static Object[][] Policies003_03() {
		String sheetName = "Policies003_03";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_04")
	public static Object[][] Policies003_04() {
		String sheetName = "Policies003_04";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_05")
	public static Object[][] Policies003_05() {
		String sheetName = "Policies003_05";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_06")
	public static Object[][] Policies003_06() {
		String sheetName = "Policies003_06";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_07")
	public static Object[][] Policies003_07() {
		String sheetName = "Policies003_07";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_08")
	public static Object[][] Policies003_08() {
		String sheetName = "Policies003_08";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_09")
	public static Object[][] Policies003_09() {
		String sheetName = "Policies003_09";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies003_10")
	public static Object[][] Policies003_10() {
		String sheetName = "Policies003_10";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005")
	public static Object[][] Policies005() {
		String sheetName = "Policies005";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_2")
	public static Object[][] Policies005_2() {
		String sheetName = "Policies005_2";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_3")
	public static Object[][] Policies005_3() {
		String sheetName = "Policies005_3";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_4")
	public static Object[][] Policies005_4() {
		String sheetName = "Policies005_4";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_5")
	public static Object[][] Policies005_5() {
		String sheetName = "Policies005_5";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_6")
	public static Object[][] Policies005_6() {
		String sheetName = "Policies005_6";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_7")
	public static Object[][] Policies005_7() {
		String sheetName = "Policies005_7";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_8")
	public static Object[][] Policies005_8() {
		String sheetName = "Policies005_8";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_9")
	public static Object[][] Policies005_9() {
		String sheetName = "Policies005_9";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_10")
	public static Object[][] Policies005_10() {
		String sheetName = "Policies005_10";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_11")
	public static Object[][] Policies005_11() {
		String sheetName = "Policies005_11";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies005_12")
	public static Object[][] Policies005_12() {
		String sheetName = "Policies005_12";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies008")
	public static Object[][] Policies008() {
		String sheetName = "Policies008";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies008_1")
	public static Object[][] Policies008_1() {
		String sheetName = "Policies008_1";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies008_2")
	public static Object[][] Policies008_2() {
		String sheetName = "Policies008_2";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies008_3")
	public static Object[][] Policies008_3() {
		String sheetName = "Policies008_3";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies008_4")
	public static Object[][] Policies008_4() {
		String sheetName = "Policies008_4";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies009")
	public static Object[][] Policies009() {
		String sheetName = "Policies009";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies011")
	public static Object[][] Policies011() {
		String sheetName = "Policies011";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies012")
	public static Object[][] Policies012() {
		String sheetName = "Policies012";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies013")
	public static Object[][] Policies013() {
		String sheetName = "Policies013";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies014")
	public static Object[][] Policies014() {
		String sheetName = "Policies014";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015")
	public static Object[][] Policies015() {
		String sheetName = "Policies015";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015B")
	public static Object[][] Policies015B() {
		String sheetName = "Policies015B";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015A1")
	public static Object[][] Policies015A1() {
		String sheetName = "Policies015A1";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015C")
	public static Object[][] Policies015C() {
		String sheetName = "Policies015C";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015D")
	public static Object[][] Policies015D() {
		String sheetName = "Policies015D";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015E")
	public static Object[][] Policies015E() {
		String sheetName = "Policies015E";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015F")
	public static Object[][] Policies015F() {
		String sheetName = "Policies015F";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies015G")
	public static Object[][] Policies015G() {
		String sheetName = "Policies015G";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies016A")
	public static Object[][] Policies016A() {
		String sheetName = "Policies016A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies022A")
	public static Object[][] Policies022A() {
		String sheetName = "Policies022A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies022_1")
	public static Object[][] Policies022_1() {
		String sheetName = "Policies022_1";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies022_2")
	public static Object[][] Policies022_2() {
		String sheetName = "Policies022_2";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "Policies023_1")
	public static Object[][] Policies023_1() {
		String sheetName = "Policies023_1";
		Object[][] data = getTestData(sheetName);
		return data;
	}

}
