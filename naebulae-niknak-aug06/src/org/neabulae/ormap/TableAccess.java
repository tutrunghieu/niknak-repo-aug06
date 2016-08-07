package org.neabulae.ormap;

import java.util.List;

import org.bson.Document;
import org.naebulae.util.Joiner;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public abstract class TableAccess implements TableAccessNative, TableAccessTyped
{
	
	public void dropAllTables() 
	throws Exception
	{
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
	


	
	public void insertNative(Class<?> tname, Document values) throws Exception
	{ 
		insertNative(this.tableNameFromClass(tname), values);		
	}
	
	public void deleteNative(Class<?> tname, Document where) throws Exception 
	{ 
		deleteNative(this.tableNameFromClass(tname), where);		
	}

	public void updateNative(Class<?> tname, Document where, Document values) throws Exception
	{
		updateNative(this.tableNameFromClass(tname), where, values);		
	}
		
	public List<Document> selectNative(Class<?> tname) throws Exception 
	{
		return selectNative(this.tableNameFromClass(tname));
	}
	
	public List<Document> selectNative(Class<?> tname, Document where) throws Exception 
	{ 
		return selectNative(this.tableNameFromClass(tname), where);
	}
	
	public Document selectOneNative(Class<?> tname, Document where) throws Exception 
	{
		return selectOneNative(this.tableNameFromClass(tname), where);
	}

	
	public void insertNative(String tname, Document values) throws Exception { }
	
	public void deleteNative(String tname, Document where) throws Exception { }

	public void updateNative(String tname, Document where, Document values) throws Exception {}
		
	public List<Document> selectNative(String tname) throws Exception { return null; }
	
	public List<Document> selectNative(String tname, Document where) throws Exception { return null; }
	
	public Document selectOneNative(String tname, Document where) throws Exception { return null; }

	
	

	public<T1> void insertTyped(Class<T1> tname, T1 values) throws Exception { }
	
	public<T1>  void deleteTyped(Class<T1> tname, T1 where) throws Exception { }

	public<T1>  void updateTyped(Class<T1> tname, TypedWhere<T1> where, TypedUpdater<T1> values) throws Exception {}
		
	public<T1>  List<T1> selectTyped(Class<T1> tname) throws Exception { return null; }
	
	public<T1>  List<T1> selectTyped(Class<T1> tname, TypedWhere<T1> where) throws Exception { return null; }
	
	public<T1>  T1 selectOneTyped(Class<T1> tname, TypedWhere<T1> where) throws Exception { return null; }

	
}
