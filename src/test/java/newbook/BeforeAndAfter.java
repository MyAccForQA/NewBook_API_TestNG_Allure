package newbook;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.newbook.Excel;

import io.qameta.allure.Step;

public class BeforeAndAfter {

	// CSV file
	private final String pathToFile = "./src/test/resources/com/newbook/";
	private final String cvsSplitBy = ",,";

	// static ???
	private static String[][] result = null;
	public String[] resultTmp = null;

	@BeforeSuite
	public void setUpBeforeSuite() {
		System.out.println("@BeforeSuite");
	}

	@BeforeTest
	public void setUpBeforeTest() {
		System.out.println("@BeforeTest");
	}

	@BeforeMethod
	public void setUpBeforeMethod() {
		System.out.println("@BeforeMethod");
		resultTmp = null;
	}

	@AfterMethod
	public void setDownAfterMethod() {
		System.out.println("@AfterMethod");
		if (resultTmp != null)
			addResult(resultTmp);
	}

	@AfterTest
	public void setUpAfterTest() {
		System.out.println("@AfterTest");

		if (result != null) {
			// Excel excelResult = new Excel(result);
			// excelResult.writeFile();
			new Excel(result).writeFile();
		}
	}

	@AfterSuite
	public void setUpAfterSuite() {
		System.out.println("@AfterSuite");

	}

	@Step("checkAssertEquals")
	public void checkAssertEquals(String act, String exp, String err) {
		// Assert.assertEquals(actual, expected, message);
		assertEquals(act, exp, err);
	}

	private String[][] merge(String[][] in, String[] inStr) {
		String[][] out = new String[in.length + 1][];
		int i = 0;
		for (; i <= in.length - 1; i++)
			out[i] = in[i];

		out[i] = inStr;
		return out;
	}

	// for result
	public void addResult(String... in) {
		// excelResult.addResult(this.getClass().getName(), act, exp, email,
		// pass, mess, key, value);
		// will be/// TC#, this.getClass().getName(), PASS, act, exp, email,
		// pass, mess, key, value

		// item we need add
		int itemMove = 3;
		String[] strTmp = new String[in.length + itemMove];
		// 0 item
		strTmp[0] = (result == null) ? "1" : "" + (result.length + 1);
		// 1 item
		strTmp[1] = in[0];
		// 2 item - PASSED or FAILED
		strTmp[2] = (in[1].equals(in[2])) ? "PASSED" : "FAILED"; // "PASS"; //
		// all next items
		for (int i = 1; i <= in.length - 1; i++)
			strTmp[i + itemMove] = in[i];

		// add new String[] in main String[][]
		if (strTmp[0].equals("1")) {
			result = new String[1][];
			result[0] = strTmp;
		} else
			result = merge(result, strTmp);
	}

	// read file
	public String[][] readCSVFile(String csvFile) {
		String[][] resTemp = null; // out
		String line = ""; // temp

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(pathToFile + csvFile));
			try {
				while ((line = br.readLine()) != null) {
					// use comma as separator
					String[] brLine = line.split(cvsSplitBy);
					if (resTemp == null) { // (resTemp.length == 1)
						resTemp = new String[1][];
						resTemp[0] = brLine;
					} else
						resTemp = merge(resTemp, brLine);
				}
				br.close();
				return resTemp;
			} catch (IOException e) {
				return resTemp;
			}
		} catch (FileNotFoundException e) {
			return null;
		}
	}
}