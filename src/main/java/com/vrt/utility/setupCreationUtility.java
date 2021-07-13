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

public class setupCreationUtility extends BaseClass {

	public setupCreationUtility() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/src/test/resources/TestData/"
			+ "SetupTestData.xlsx";

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
	// SETUP Creation module related Test Data reference
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@DataProvider(name = "SetupCreation")
	public static Object[][] SetupCreation() {
		String sheetName = "SetupCreation_RT";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SetupCreation_1")
	public static Object[][] SetupCreation_1() {
		String sheetName = "SetupCreation_1";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET003")
	public static Object[][] SET003() {
		String sheetName = "SET003";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET004")
	public static Object[][] SET004() {
		String sheetName = "SET004";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET005")
	public static Object[][] SET005() {
		String sheetName = "SET005";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET008")
	public static Object[][] SET008() {
		String sheetName = "SET008";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET009")
	public static Object[][] SET009() {
		String sheetName = "SET009";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET010")
	public static Object[][] SET010() {
		String sheetName = "SET010";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET015")
	public static Object[][] SET015() {
		String sheetName = "SET015";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET016")
	public static Object[][] SET016() {
		String sheetName = "SET016";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET017")
	public static Object[][] SET017() {
		String sheetName = "SET017";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET018")
	public static Object[][] SET018() {
		String sheetName = "SET018";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET019")
	public static Object[][] SET019() {
		String sheetName = "SET019";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET020")
	public static Object[][] SET020() {
		String sheetName = "SET020";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SET022")
	public static Object[][] SET022() {
		String sheetName = "SET022";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET033")
	public static Object[][] SET033() {
		String sheetName = "SET033";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ADMN037A")
	public static Object[][] ADMN037A() {
		String sheetName = "ADMN037A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	// Data Providers

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Sensor Configuration module related Test Data reference
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@DataProvider(name = "TcSC004")
	public static Object[][] SC004() {
		String sheetName = "TcSC004";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC005")
	public static Object[][] SC005() {
		String sheetName = "TcSC005";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC006")
	public static Object[][] SC006() {
		String sheetName = "TcSC006";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC007")
	public static Object[][] SC007() {
		String sheetName = "TcSC007";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC012")
	public static Object[][] SC0012() {
		String sheetName = "TcSC012";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC013")
	public static Object[][] SC0013() {
		String sheetName = "TcSC013";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC014")
	public static Object[][] SC0014() {
		String sheetName = "TcSC014";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC015")
	public static Object[][] SC0015() {
		String sheetName = "TcSC015";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC020")
	public static Object[][] SC0020() {
		String sheetName = "TcSC020";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC021")
	public static Object[][] SC021() {
		String sheetName = "TcSC021";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC022")
	public static Object[][] SC022() {
		String sheetName = "TcSC022";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC023")
	public static Object[][] SC023() {
		String sheetName = "TcSC023";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC041")
	public static Object[][] SC041() {
		String sheetName = "TcSC041";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC042")
	public static Object[][] SC042() {
		String sheetName = "TcSC042";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC043")
	public static Object[][] SC043() {
		String sheetName = "TcSC043";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC046")
	public static Object[][] SC046() {
		String sheetName = "TcSC046";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC046a")
	public static Object[][] SC046a() {
		String sheetName = "TcSC046a";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC048")
	public static Object[][] SC048() {
		String sheetName = "TcSC048";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC049")
	public static Object[][] SC049() {
		String sheetName = "TcSC049";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC050")
	public static Object[][] SC050() {
		String sheetName = "TcSC050";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC052")
	public static Object[][] SC052() {
		String sheetName = "TcSC052";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC053")
	public static Object[][] SC053() {
		String sheetName = "TcSC053";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC056")
	public static Object[][] SC056() {
		String sheetName = "TcSC056";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC058")
	public static Object[][] SC058() {
		String sheetName = "TcSC058";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC059")
	public static Object[][] SC059() {
		String sheetName = "TcSC059";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC062")
	public static Object[][] SC062() {
		String sheetName = "TcSC062";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC063")
	public static Object[][] SC063() {
		String sheetName = "TcSC063";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC064")
	public static Object[][] SC064() {
		String sheetName = "TcSC064";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SC082")
	public static Object[][] SC082() {
		String sheetName = "SC082";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SC083")
	public static Object[][] SC083() {
		String sheetName = "SC083";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SC084")
	public static Object[][] SC084() {
		String sheetName = "SC084";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SC084_1")
	public static Object[][] SC084_1() {
		String sheetName = "SC084_1";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SC110")
	public static Object[][] SC110() {
		String sheetName = "SC110";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "GS013")
	public static Object[][] GS013() {
		String sheetName = "GS013";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "GS014")
	public static Object[][] GS014() {
		String sheetName = "GS014";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "SC086")
	public static Object[][] SC086() {
		String sheetName = "SC086";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CAL007")
	public static Object[][] CAL007() {
		String sheetName = "CAL007";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CAL008")
	public static Object[][] CAL008() {
		String sheetName = "CAL008";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CAL012")
	public static Object[][] CAL012() {
		String sheetName = "CAL012";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CAL013")
	public static Object[][] CAL013A() {
		String sheetName = "CAL013";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CAL017")
	public static Object[][] CAL017() {
		String sheetName = "CAL017";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "CAL018A")
	public static Object[][] CAL018A() {
		String sheetName = "CAL018A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP011")
	public static Object[][] QP011() {
		String sheetName = "QP011";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP012")
	public static Object[][] QP012() {
		String sheetName = "QP012";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP014A")
	public static Object[][] QP014A() {
		String sheetName = "QP014A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP016A")
	public static Object[][] QP016A() {
		String sheetName = "QP016A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP018A")
	public static Object[][] QP018A() {
		String sheetName = "QP018A";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP028")
	public static Object[][] QP028() {
		String sheetName = "QP028";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP030")
	public static Object[][] QP030() {
		String sheetName = "QP030";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP032")
	public static Object[][] QP032() {
		String sheetName = "QP032";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QP034")
	public static Object[][] QP034() {
		String sheetName = "QP034";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "QUAL001")
	public static Object[][] QUAL001() {
		String sheetName = "QUAL001";
		Object[][] data = getTestData(sheetName);
		return data;
	}

}
