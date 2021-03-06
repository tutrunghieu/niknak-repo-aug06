package org.neabulae.ormap.mongo;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.neabulae.ormap.FuncUpdater;

public class FuncUpdaterMongo extends FuncUpdater
{

	public Bson getMongoUpdater() 
	{
		Document res = new Document();
		for(String nk: vals.keySet()) 
		{
			String vk = vals.get(nk);
			res.append(nk, vk);
		}
		
		return res;
	}

}
