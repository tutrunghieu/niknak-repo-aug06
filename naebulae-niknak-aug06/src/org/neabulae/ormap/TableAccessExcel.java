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
import org.naebulae.util.Joiner;

import apps.niknak.services.ExcelDataAccess;

public class TableAccessExcel implements TableAccess
{
	private Object inputObject;
	private InputStream inputStream;
	private Workbook workbook;

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

	public static Workbook getWorkbook(File f)
    throws Exception 
	{
	    String fname = f.getName();
	    
	    if (fname.endsWith("xlsx")) return new XSSFWorkbook(new FileInputStream(f));
	    else if (fname.endsWith("xls")) return new HSSFWorkbook(new FileInputStream(f));
	    else return null;
	}
	
	public static Workbook getWorkbook(URL f, String ext)
    throws Exception 
	{
	    if (ext.endsWith("xlsx")) return new XSSFWorkbook(f.openStream());
	    else if (ext.endsWith("xls")) return new HSSFWorkbook(f.openStream());
	    else return null;
	}
	@Override
	public String tableNameFromClass(Class<?> cl) throws Exception 
	{
		return cl.getSimpleName().toLowerCase();
	}
	
	@Override
	public void dropAllTables() throws Exception 
	{
	}

	@Override
	public List<String> getTableNames() throws Exception 
	{
		List<String> res = new ArrayList<String> ();
		
		for(int k=0; k<workbook.getNumberOfSheets(); k++) 
		{
			res.add(workbook.getSheetName(k));
		}
		
		return res;
	}

	@Override
	public void insert(Class<?> tname, Object row) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(String tname, Object row) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String tname, FuncWhere where) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Class<?> tname, FuncWhere where) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String tname, FuncWhere where, FuncUpdater set) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Class<?> tname, FuncWhere where, FuncUpdater set) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public List<Object> fetchRowsAsMap(String tname) 
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
	
	private String getCellValue(Cell ck, DataFormatter formatter) 
	{
		if(ck==null) return "";
		return formatter.formatCellValue(ck);	
	}

	@Override
	public void print(String tname) throws Exception 
	{
		List<Object> rows = this.fetchRowsAsMap(tname);
		for(Object rk: rows) 
		{
			System.out.println("======" + tname + ":" + rk.hashCode());
			Joiner.start("\r\n").printMap(rk);
		}
		
	}
	

	@Override
	public void print(Class<?> tk) throws Exception 
	{
		// TODO Auto-generated method stub
		
	}


}
