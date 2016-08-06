package apps.niknak.services;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataAccess 
{

	public Workbook getWorkbook(URL f, String ext)
    throws Exception 
	{
	    if (ext.endsWith("xlsx")) return new XSSFWorkbook(f.openStream());
	    else if (ext.endsWith("xls")) return new HSSFWorkbook(f.openStream());
	    else return null;
	}
			
	public Workbook getWorkbook(File f)
    throws Exception 
	{
	    String fname = f.getName();
	    
	    if (fname.endsWith("xlsx")) return new XSSFWorkbook(new FileInputStream(f));
	    else if (fname.endsWith("xls")) return new HSSFWorkbook(new FileInputStream(f));
	    else return null;
	}

	public List<Map<String, Object>> fetchRowsAsMap(Sheet w) 
	{
		List<String> fields = new ArrayList<String>();
		
		Row r0 = w.getRow(0);
		for(int k=0; k<=r0.getLastCellNum(); k++)
		{
			Cell c = r0.getCell(k);
			if(c == null) break;
			fields.add( c.getStringCellValue() );
		}
			
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for(int r=1; r<=w.getLastRowNum(); r++)
		{
			Row row = w.getRow(r);
			if(row == null) continue;
			
			Map<String, Object> rk = new LinkedHashMap<String, Object>();
			
			for(int c=0; c<fields.size(); c++)
			{
				Cell ck = row.getCell(c);
				String vk = (ck==null ? "" : ck.getStringCellValue());
				String fk = fields.get(c);
				rk.put(fk, vk);
			} //for each field

			rows.add(rk);
		} //for each row
		
		return rows;
	}

	public List<Map<String, Object>> fetchRowsFromUrl(String url, String ext, int k)
	throws Exception
	{
		Workbook w = getWorkbook(new URL(url), ext);
		return fetchRowsAsMap(w.getSheetAt(k));
	}
	
}
