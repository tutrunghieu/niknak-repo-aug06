package org.neabulae.ormap;

import java.util.List;

import org.naebulae.util.Joiner;

public abstract class TableAccess 
{
	
	public void dropAllTables() 
	throws Exception
	{
	}
	
	public String tableNameFromClass(Class<?> tname) 
	{
		return tname.getSimpleName().toLowerCase();
	}
	
	
	public abstract List<String> selectTableNames() 
	throws Exception;
	
	public abstract List<Object> select(String tname) 
	throws Exception;

	public abstract void insert(String tname, Object row) 
	throws Exception;
	
	public abstract void delete(String tname, FuncWhere lf) 
	throws Exception;

	public abstract void update(String tname, FuncWhere lf, FuncUpdater src)
	throws Exception;
	

	
	public List<Object> select(Class<?> tname) 
	throws Exception
	{
		return select(this.tableNameFromClass(tname));
	}
	
	public void insert(Class<?> tname, Object row) 
	throws Exception
	{
		insert(this.tableNameFromClass(tname), row);
	}
	

	
	
	
	public void delete(Class<?> tname, FuncWhere lf)
	throws Exception
	{
		delete(this.tableNameFromClass(tname), lf);
	}
	

	
	public void update(Class<?> tname, FuncWhere lf, FuncUpdater src)
	throws Exception
	{
		update(this.tableNameFromClass(tname), lf, src);
	}
	
	public void print(Class<?> cl) 
	throws Exception
	{
		print(this.tableNameFromClass(cl));
	}
	
	public void print(String tname)
	throws Exception
	{
		List<Object> rows = this.select(tname);
		for(Object rk: rows) 
		{
			System.out.println("======" + tname + ":" + rk.hashCode());
			Joiner.start("\r\n").printMap(rk);
		}
		
	}	
}
