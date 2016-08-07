package org.neabulae.ormap.mongo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.neabulae.ormap.FuncUpdater;
import org.neabulae.ormap.FuncWhere;
import org.neabulae.ormap.TableAccess;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TableAccessMongo extends TableAccess 
{
	private String dataHost = "localhost";
	private int dataPort = 27017;
	private String dataName = "null-database";

	public TableAccessMongo(String host, int port, String name) 
	{
		dataHost = host;
		dataPort = port;
		dataName = name;
	}


	public TableAccessMongo(String name) 
	{
		dataName = name;
	}
	
	public void dropAllTables() 
	throws Exception
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		
		MongoDatabase db = mongo.getDatabase(dataName);
		db.drop();
		
		mongo.close();		
	}


	
	@Override
	public void insert(String tname, Object row) throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		MongoDatabase db = mongo.getDatabase(dataName);
		
		MongoCollection<Document> table = db.getCollection(tname);
		
		table.insertOne( convertObjectToDoc(row) );
		
		mongo.close();							
	}


	@SuppressWarnings("unchecked")
	public Document convertObjectToDoc(Object src) 
	throws Exception
	{
		Document res = new Document();
		
		if(src instanceof Map)
		{
			Map<String, Object> t = (Map<String, Object>)src;
			for(String tk: t.keySet()) res.append(tk, t.get(tk));
		}
		
		else 
		{
			for(Field fk: src.getClass().getDeclaredFields())
			if( Modifier.isPublic(fk.getModifiers()) )
//			try
			{
				Object vk = fk.get(src);
				res.put(fk.getName(), vk);
			}
//			catch(Exception xp) {}
		}
		
		
		return res;
	}

	@Override
	public void delete(String tname, FuncWhere lf) 
	throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		MongoDatabase db = mongo.getDatabase(dataName);
		
		MongoCollection<Document> table = db.getCollection(tname);
		
		FuncWhereMongo _lf = (FuncWhereMongo)lf;
		table.deleteOne(_lf.getMongoFilter());
		
		mongo.close();
	}

	@Override
	public void update(String tname, FuncWhere lf, FuncUpdater uf) 
	throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		MongoDatabase db = mongo.getDatabase(dataName);
		
		MongoCollection<Document> table = db.getCollection(tname);
		
		FuncUpdaterMongo _uf = (FuncUpdaterMongo)uf;
		FuncWhereMongo _lf = (FuncWhereMongo)lf;
		
		table.updateOne(
				_lf.getMongoFilter(),
				new Document("$set", _uf.getMongoUpdater())
		);
		
		mongo.close();		
	}

	public List<String> selectTableNames() 
	throws Exception
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		MongoDatabase db = mongo.getDatabase(dataName);

		List<String> res = new ArrayList<String>();
		for(String tk: db.listCollectionNames())
		{
			res.add(tk);
		}
			
		mongo.close();					
		
		Collections.sort(res);
		return res;
	}
	
	@Override
	public List<Object> select(String tname) throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		MongoDatabase db = mongo.getDatabase(dataName);
		
		List<Object> res = new ArrayList<Object>();
		for(Object rk: db.getCollection(tname).find())
			res.add(rk);
		
		mongo.close();					

		return res;		
	}	
	
	public List<Object> select(String tname, FuncWhere lf) throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		MongoDatabase db = mongo.getDatabase(dataName);
		
		List<Object> res = new ArrayList<Object>();
		
		FuncWhereMongo _lf = (FuncWhereMongo)lf;		
		for(Object rk: db
				.getCollection(tname)
				.find(_lf.getMongoFilter()))
		{
			res.add(rk);
		}
		
		mongo.close();					

		return res;		
	}		
	
	public int countRows(String tname) 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		MongoDatabase db = mongo.getDatabase(dataName);
		int res = (int)db.getCollection(tname).count();
		mongo.close();					

		return res;		
	}
		

	public void insertNative(String tname, Document values)
	throws Exception 
	{ 
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		
		MongoDatabase db = mongo.getDatabase(dataName);
		db.getCollection(tname).insertOne(values);
		
		mongo.close();					
	}
	
	public void deleteNative(String tname, Document where)
	throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		
		System.out.println("Deleting " + where);
		MongoDatabase db = mongo.getDatabase(dataName);
		db.getCollection(tname).deleteOne(where);
		
		mongo.close();							
	}

	public void updateNative(String tname, Document where, Document values) 
	throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		
		MongoDatabase db = mongo.getDatabase(dataName);
		
		System.out.println("Updating " + where);
		System.out.println(" set " + values);
		db.getCollection(tname).updateOne(where, new Document("$set", values) );
		
		mongo.close();							
	}
		
	public List<Document> selectNative(String tname) throws Exception 
	{ 
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		
		
		List<Document> res = new ArrayList<Document>();
		
		MongoDatabase db = mongo.getDatabase(dataName);
		for( Document rk: db.getCollection(tname).find())
		{
			res.add(rk);
		}
		
		mongo.close();							
		
		return res; 
	}
	
	public List<Document> selectNative(String tname, Document where) throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		
		
		List<Document> res = new ArrayList<Document>();
		
		MongoDatabase db = mongo.getDatabase(dataName);
		for( Document rk: db.getCollection(tname).find(where))
		{
			res.add(rk);
		}
		
		mongo.close();							
		
		return res; 
	}
	
	public Document selectOneNative(String tname, Document where)
	throws Exception 
	{
		MongoClient mongo = new MongoClient(dataHost, dataPort);
		
		
		Document found = null;
		
		MongoDatabase db = mongo.getDatabase(dataName);
		for( Document rk: db.getCollection(tname).find(where))
		{
			found = rk;
			break;
		}
		
		mongo.close();							
		
		return found; 
	}

	
}
