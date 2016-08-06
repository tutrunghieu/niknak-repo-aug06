package org.neabulae.ormap.test_units;

import java.util.List;

import org.neabulae.ormap.FuncUpdater;
import org.neabulae.ormap.FuncWhere;
import org.neabulae.ormap.TableAccess;

public class TableAccessMongo extends TableAccess 
{
	private String dataHost;
	private int dataPort;
	private String dataName;

	public TableAccessMongo(String host, int port, String name) 
	{
		dataHost = host;
		dataPort = port;
		dataName = name;
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
	public List<String> selectTableNames() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Object> select(String tname) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}	
}
