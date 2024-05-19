package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data provider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		
		String path = ".\\testData\\ECommerce_LoginData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalRows][totalCols];
		
		int row,col;
		for(row = 1;row <= totalRows; row++) {
			for(col = 0;col < totalCols; col++) {
				logindata[row-1][col] = xlutil.getCellData("Sheet1", row, col);
			}
		}
		return logindata;
	}
}
