package resources;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	//Identify testCases column by scanning the entire 1st row
	//Once column is indentified then can entire testcase column to identify purchase testcase row
	//After you grab purchase testcase row = pull all the data of that row
	
	public ArrayList<String> getData(String testcaseName, String sheetName) throws IOException
	{
		//fileInputStream argument
				ArrayList<String> a = new ArrayList<String>();
				
				FileInputStream fis = new FileInputStream("D:\\Nikita Modi\\Eclipse Workspace\\ExcelDriven\\testdata\\demodata.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				
				int sheets = workbook.getNumberOfSheets();
				for(int i=0;i<sheets;i++)
				{
					if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
					{
						XSSFSheet sheet= workbook.getSheetAt(i);
						
						//Identify testCases column by scanning the entire 1st row
						Iterator<Row> rows = sheet.iterator(); //sheet is collection of rows
						Row firstrow = rows.next();
						Iterator<Cell> cell = firstrow.cellIterator(); //row is collection of cells
						
						int k=0;
						int coloumn = 0;
						while(cell.hasNext())
						{	
							Cell value = cell.next();
							if(value.getStringCellValue().equalsIgnoreCase("testdata"))
							{
								//desired column
								coloumn=k;
								
							}
							k++;
						}
						System.out.println(coloumn);
						
						//Once column is indentified then can entire testcase column to identify purchase testcase row
						while(rows.hasNext())
						{
							Row r = rows.next();
							if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase("RestAddbook"))
							{
								//After you grab purchase testcase row = pull all the data of that row
								Iterator<Cell> cv = r.cellIterator();
								while(cv.hasNext())
								{
									Cell c = cv.next();
									if(c.getCellType()==CellType.STRING)
									{
										a.add(c.getStringCellValue());
									}
									else
									{
										a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
										
									}
								}
							}
							
						}
						
					}
					
				}
				return a;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
	}

}
