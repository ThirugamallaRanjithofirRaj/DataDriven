package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
XSSFWorkbook wb;
public ExcelFileUtil(String inputpath) {
	// TODO Auto-generated constructor stub
}
//constructor for reading excel path
public void ExcelFileUtil1(String Excelpath) throws Throwable
{
FileInputStream fi = new FileInputStream(Excelpath);
wb = new XSSFWorkbook(fi);
}
//count no of rows in sheet
public int rowCount(String SheetName) 
{
	return wb.getSheet(SheetName).getLastRowNum();
	}
//read cell data
public String getCellData(String SheetName, int row,int column) 
{ 
	String data="";
			if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
			{
				int celldata = (int)wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
				data = String.valueOf(celldata);
				
			}
			else
			{
				data = wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
			}
			return data;
}
//method for write results
public void setCellData(String ShetName,int row,int column,String status,String WriteExcel)throws Throwable
{
	//get sheet from wb
	XSSFSheet ws = wb.getSheet(ShetName);
	//get row from sheet
	XSSFRow rowNum =ws.getRow(column);
	//create cell
	XSSFCell cell = rowNum.createCell(column);
	//write status into cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
	}
		else if(status.equalsIgnoreCase("Blocked"))
	{	
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
	FileOutputStream fo = new FileOutputStream(WriteExcel);
	wb.write(fo);
	}
		
}










