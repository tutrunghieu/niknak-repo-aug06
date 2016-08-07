package org.neabulae.ormap;

import java.util.List;

import org.bson.Document;

public interface TableAccessNative {
	
	public void insertNative(Class<?> tname, Document values) throws Exception;
	
	public void deleteNative(Class<?> tname, Document where) throws Exception;

	public void updateNative(Class<?> tname, Document where, Document values) throws Exception;
	
	public List<Document> selectNative(Class<?> tname) throws Exception;

	
	public List<Document> selectNative(Class<?> tname, Document where) throws Exception;
	
	public Document selectOneNative(Class<?> tname, Document where) throws Exception;

	
	
	public void insertNative(String tname, Document values) throws Exception;
	
	public void deleteNative(String tname, Document where) throws Exception;

	public void updateNative(String tname, Document where, Document values) throws Exception;
		
	public List<Document> selectNative(String tname) throws Exception;
	
	public List<Document> selectNative(String tname, Document where) throws Exception;
	
	public Document selectOneNative(String tname, Document where) throws Exception;
	
}
