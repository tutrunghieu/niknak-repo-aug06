package org.neabulae.ormap;

import java.util.List;

public interface TableAccess 
{
	String tableNameFromClass(Class<?> cl)throws Exception;
	
	void dropAllTables()throws Exception;
	List<String> getTableNames()throws Exception;
	
	void insert(Class<?> tname, Object row)throws Exception;
	void insert(String tname, Object row) throws Exception;
	
	void delete(String tname, FuncWhere where)throws Exception;
	void delete(Class<?> tname, FuncWhere where)throws Exception;
	
	void update(String tname, FuncWhere where, FuncUpdater set)throws Exception;
	void update(Class<?> tname, FuncWhere where, FuncUpdater set)throws Exception;

	List<Object> fetchRowsAsMap(String tname) throws Exception;

	void print(String tk)throws Exception;
	void print(Class<?> tk)throws Exception;
	
}
