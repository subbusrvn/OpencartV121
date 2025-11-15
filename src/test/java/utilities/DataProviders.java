package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider 1
	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {

		String path = ".\\testData\\OpenCart_LoginData.xlsx"; // Reading the login data from folder

		ExcelUtility xlutil = new ExcelUtility(path);
		int totalrows = xlutil.getRowcount("Sheet1");
		int totalcolumns = xlutil.getCellcount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcolumns]; // Creating two dimentional Array to store the read
																	// data and passed to the test class

		for (int i = 1; i <= totalrows; i++) {

			for (int j = 0; j < totalcolumns; j++) { // i is the row and j is the column data.

				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);

			}
		}

		return logindata;

	}

	// DataProvider 2

	// DataProvider 3

	// DataProvider 4

	// DataProvider 5

}
