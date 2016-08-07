package org.neabulae.ormap;

import java.util.List;

public interface TableAccessTyped 
{
	
	public<T1> void insertTyped(Class<T1> tname, T1 values) throws Exception;
	
	public<T1>  void deleteTyped(Class<T1> tname, T1 where) throws Exception;

	public<T1>  void updateTyped(Class<T1> tname, 
			TypedWhere<T1> where, TypedUpdater<T1> values) throws Exception;
		
	public<T1>  List<T1> selectTyped(Class<T1> tname) throws Exception;
	
	public<T1>  List<T1> selectTyped(Class<T1> tname, TypedWhere<T1> where) throws Exception;
	
	public<T1>  T1 selectOneTyped(Class<T1> tname, TypedWhere<T1> where) throws Exception;
	
}
