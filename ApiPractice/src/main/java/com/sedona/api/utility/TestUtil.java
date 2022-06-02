package com.sedona.api.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {
	private static XSSFWorkbook workBook;
	private static XSSFSheet workSheet;
	private static XSSFCell Cell;
	public static Object[][] getDataFromExcel(String path,String fileName,String SheetName) throws Exception {   
		 Object[][] data = null;
  	     FileInputStream ExcelFile = null;
  	     int startRow = 1,startCol = 0,ci,cj;
		 try {
			   System.out.println("path "+path);
			   System.out.println("filename "+fileName);
			   System.out.println("SheetName "+SheetName);
			   File file =    new File(path+"\\"+fileName);
			   ExcelFile = new FileInputStream(file);
 		       workBook = new XSSFWorkbook(ExcelFile);
 		       workSheet = workBook.getSheet(SheetName);
			   System.out.println("ExcelWSheet name "+workBook.getSheet(SheetName)); 
			   int totalRows = workSheet.getLastRowNum();
			   System.out.println("totalRows "+totalRows); 
			   System.out.println("firstLastCellNum "+workSheet.getRow(0).getLastCellNum());
			   System.out.println("last row num "+workSheet.getLastRowNum());
		       System.out.println("firstLastCellNum "+workSheet.getRow(0).getLastCellNum());
		       data = new Object[workSheet.getLastRowNum()][workSheet.getRow(0).getLastCellNum()];
			   System.out.println(data.length);
			   int rowCount = workSheet.getLastRowNum()-workSheet.getFirstRowNum();
		       System.out.println("row count "+rowCount);
		   	   int totalCols = workSheet.getRow(0).getLastCellNum();
			   System.out.println("totalCols "+totalCols);
			   data=new String[totalRows][totalCols];
			   ci=0;
			   for (int i=startRow;i<=totalRows;i++, ci++) {           	   
				  cj=0;
				   for (int j=startCol;j<totalCols;j++, cj++){
					   data[ci][cj]=getCellData(i,j);
					  }
			  	  }
				}catch (FileNotFoundException e){
				  e.printStackTrace();
				}catch (IOException e){
				  e.printStackTrace();
				}
			return(data);
		}
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try{
			DataFormatter fmt = new DataFormatter();
			Cell = workSheet.getRow(RowNum).getCell(ColNum);
        	String CellData = fmt.formatCellValue(Cell);
			//String CellData = Cell.getStringCellValue();
			System.out.println("CellData "+CellData);
			return CellData;
			}catch (Exception e){
			System.out.println("Error message "+e.getMessage());
			throw (e);
			}
		}
}
