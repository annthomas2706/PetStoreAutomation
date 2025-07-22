package api.utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider; 

public class DataProviders {
	
	    @DataProvider(name = "AllUserData")
	    public String[][] getAllData() throws IOException {
	        ExcelUtility.loadExcel(System.getProperty("user.dir")+"/testData/UserData.xlsx", "Sheet1");

	        int rows = ExcelUtility.getRowCount();
	        int cols = ExcelUtility.getColumnCount();
	        String[][] data = new String[rows][cols];

	        for (int i = 1; i <= rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                data[i - 1][j] = ExcelUtility.getCellData(i, j);
	            }
	        }

	        return data;
	    }

	    @DataProvider(name = "UserNamesOnly")
	    public String[] getOnlyUsernames() throws IOException {
	        ExcelUtility.loadExcel(System.getProperty("user.dir")+"/testData/UserData.xlsx", "Sheet1");

	        int rows = ExcelUtility.getRowCount();
	       String[] usernames = new String[rows];

	        for (int i = 1; i <= rows; i++) {
	            usernames[i - 1] = ExcelUtility.getCellData(i, 1); // Assuming Username is 2nd column (index 1)
	        }

	        return usernames;
	    }
	}




