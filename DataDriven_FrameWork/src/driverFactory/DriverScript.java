package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;
import config.AppUtil;

public class DriverScript extends AppUtil {
String inputpath = "./FileInput/LoginData.xlsx";
String outputpath = "./FileOutput/DataDrivenResuts.xlsx";
@Test 
public void StartTest()throws Throwable
{
	//create object for Excel file util class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in Login sheet
	int rc = xl.rowCount("Login");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		//read cell data
		String user = xl.getCellData("Login", i, 0);
		String pass = xl.getCellData("Login", i, 1);
		boolean res = FunctionLibrary.Check_Login(user, pass);
		if(res)
		{
			//if res is true write as Login success into results cell
			xl.setCellData("Login", i, 2, "Login success", outputpath);
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "pass", outputpath);
			}
		else
		{
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./ScreenShot/Iteration/"+i+"Loginpage.png"));
			//capture error message
	String error_message =driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissable']")).getText();
	xl.setCellData("Login", i, 2, error_message, outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
}
}
}
}




