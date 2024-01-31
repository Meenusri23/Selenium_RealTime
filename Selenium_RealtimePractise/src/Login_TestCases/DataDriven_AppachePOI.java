package Login_TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven_AppachePOI {
	
	static List<String> UsernameList = new ArrayList<String>();
	static List<String> PasswordList = new ArrayList<String>();

	public void readExcel() throws IOException
	{
		FileInputStream Excel = new FileInputStream("D:\\DataDriven.xlsx");
		Workbook workbook = new XSSFWorkbook(Excel);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator =  sheet.iterator();
		while(rowIterator.hasNext())
		{
			Row rowvalue = rowIterator.next();
			Iterator<Cell> columnIterator = rowvalue.iterator();
			int i = 2;
			while(columnIterator.hasNext()) {
				if(i%2==0)
				{
					UsernameList.add(columnIterator.next().getStringCellValue());
					
			
				}
				
				else
				{
					PasswordList.add(columnIterator.next().getStringCellValue());
				}
				i++;
			}
		}
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DataDriven_AppachePOI data = new DataDriven_AppachePOI();
		data.readExcel();
		System.out.println("Username List" +UsernameList);
		System.out.println("Password List" +PasswordList);
	}

}
