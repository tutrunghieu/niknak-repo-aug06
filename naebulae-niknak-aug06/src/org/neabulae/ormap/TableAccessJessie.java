package org.neabulae.ormap;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.naebulae.util.Joiner;

public class TableAccessJessie implements TableAccess
{
	private File dataFolder;
	private ObjectMapper mapper = new ObjectMapper();

	public TableAccessJessie()
	{
		
	}
	public TableAccessJessie(String f)
	{
		dataFolder = new File(f);
	}

	public void dropAllTables() 
	throws Exception
	{
		FileUtils.deleteDirectory(dataFolder);
	}
	

	
	public void insert(String tname, Object row) 
	throws Exception
	{
		File f = new File(dataFolder.getAbsolutePath() + "/" + tname + "/" + System.currentTimeMillis() + ".json");		
		f.getParentFile().mkdirs();
		mapper.writeValue(f, row);
	}

	public void insert(Class<?> tname, Object row) 
	throws Exception
	{
		insert(this.tableNameFromClass(tname), row);
	}
	
	public String tableNameFromClass(Class<?> tname) 
	{
		return tname.getSimpleName().toLowerCase();
	}
	
	public void print(Class<?> cl) 
	throws Exception
	{
		print(this.tableNameFromClass(cl));
	}
	
	public void print(String tname)
	throws Exception
	{
		List<Object> rows = this.fetchRowsAsMap(tname);
		for(Object rk: rows) 
		{
			System.out.println("======" + tname + ":" + rk.hashCode());
			Joiner.start("\r\n").printMap(rk);
		}
		
	}
	
	public List<Object> fetchRowsAsMap(Class<?> tname) 
	throws Exception
	{
		return fetchRowsAsMap(this.tableNameFromClass(tname));
	}
	
	public List<Object> fetchRowsAsMap(String tname) 
	throws Exception
	{
		File f = new File(dataFolder.getAbsolutePath() + "/" + tname);		
		
		List<Object> res = new ArrayList<Object>(); 
		
		File[] files = f.listFiles();
		
		if(files != null)
		for(File fk: files)
			res.add(mapper.readValue(fk, LinkedHashMap.class));
		
		return res;
	}
	
	public void delete(Class<?> tname, FuncWhere lf)
	throws Exception
	{
		delete(this.tableNameFromClass(tname), lf);
	}
	
	@SuppressWarnings("unchecked")
	public void delete(String tname, FuncWhere lf) 
	throws Exception
	{
		File f = new File(dataFolder.getAbsolutePath() + "/" + tname);		
		
		File[] files = f.listFiles();
		
		if(files != null)
		for(File fk: files)
		{
			LinkedHashMap<String, Object> mk = mapper.readValue(fk, LinkedHashMap.class);
			if( lf.invokeBooleanAction(mk) ) fk.delete();
		}
		
		return;
	}
	
	public void update(Class<?> tname, FuncWhere lf, FuncUpdater src)
	throws Exception
	{
		update(this.tableNameFromClass(tname), lf, src);
	}
	
	@SuppressWarnings("unchecked")
	public void update(String tname, FuncWhere lf, FuncUpdater src)
	throws Exception
	{
		File f = new File(dataFolder.getAbsolutePath() + "/" + tname);		
		
		File[] files = f.listFiles();
		
		if(files != null)
		for(File fk: files)
		{
			LinkedHashMap<String, Object> mk = mapper.readValue(fk, LinkedHashMap.class);
			if( lf.invokeBooleanAction(mk) )
			{
				mk = src.updateObject(mk);
				mapper.writeValue(fk, mk);
			}
		}
		
		return;		
	}
	
	public List<String> getTableNames() 
	{
		File[] files = dataFolder.listFiles();
		
		List<String> res = new ArrayList<String>();
		if(files != null) for(File fj: files) res.add(fj.getName());
		return res;
	}


}
