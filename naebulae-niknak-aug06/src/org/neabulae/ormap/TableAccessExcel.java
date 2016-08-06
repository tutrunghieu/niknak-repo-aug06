package org.neabulae.ormap;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TableAccessExcel extends TableAccess
{
	protected Object inputObject;
	protected InputStream inputStream;
	protected Workbook workbook;

	public TableAccessExcel(URL u) 
	throws Exception
	{
		inputObject  = u;
		inputStream = u.openStream();
		workbook = getWorkbook(u, "xlsx");
	}

	public TableAccessExcel(File f) 
	throws Exception
	{
		inputObject  = f;
		inputStream = new FileInputStream(f);
		workbook = getWorkbook(f);
	}
	
	
	protected String getCellValue(Cell ck, DataFormatter formatter) 
	{
		if(ck==null) return "";
		return formatter.formatCellValue(ck);	
	}

	protected Workbook getWorkbook(File f)
    throws Exception 
	{
	    String fname = f.getName();
	    
	    if (fname.endsWith("xlsx")) return new XSSFWorkbook(new FileInputStream(f));
	    else if (fname.endsWith("xls")) return new HSSFWorkbook(new FileInputStream(f));
	    else return null;
	}
	
	protected Workbook getWorkbook(URL f, String ext)
    throws Exception 
	{
	    if (ext.endsWith("xlsx")) return new XSSFWorkbook(f.openStream());
	    else if (ext.endsWith("xls")) return new HSSFWorkbook(f.openStream());
	    else return null;
	}
	
	@Override
	public void dropAllTables() throws Exception 
	{
	}


	@Override
	public void insert(String tname, Object row) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String tname, FuncWhere lf) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String tname, FuncWhere lf, FuncUpdater src) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public List<String> selectTableNames() throws Exception 
	{
		List<String> res = new ArrayList<String> ();
		
		for(int k=0; k<workbook.getNumberOfSheets(); k++) 
		{
			res.add(workbook.getSheetName(k));
		}
		
		return res;
	}

	
	public List<Object> select(String tname) 
	{
		return fetchRowsAsMap(workbook.getSheet(tname));
	}
	
	public List<Object> fetchRowsAsMap(Sheet w) 
	{
		List<String> fields = new ArrayList<String>();
		
		Row r0 = w.getRow(0);
		for(int k=0; k<=r0.getLastCellNum(); k++)
		{
			Cell c = r0.getCell(k);
			if(c == null) break;
			fields.add( c.getStringCellValue() );
		}

		 DataFormatter formatter = new DataFormatter(Locale.US);		
		 
		List<Object> rows = new ArrayList<Object>();
		for(int r=1; r<=w.getLastRowNum(); r++)
		{
			Row row = w.getRow(r);
			if(row == null) continue;
			
			Map<String, Object> rk = new LinkedHashMap<String, Object>();
			
			for(int c=0; c<fields.size(); c++)
			{
				Cell ck = row.getCell(c);
				String vk = getCellValue(ck, formatter);
				String fk = fields.get(c);
				rk.put(fk, vk);
			} //for each field

			rows.add(rk);
		} //for each row
		
		return rows;
	}


}
