package org.neabulae.rmap;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestHeplers 
{

	public static RequestHeplers start() 
	{
		return new RequestHeplers();
	}
	
	protected Map<Class<?>, Object> helpers = new LinkedHashMap<Class<?>, Object>(); 

	@SuppressWarnings("unchecked")
	public<T1> T1 get(Class<T1> cl) 
	{
		return (T1)helpers.get(cl);
	}
	
	public<T1> void put(Class<T1> cl, T1 v)
	{
		helpers.put(cl, v);
	}

	public int size() 
	{
		return helpers.size();
	}

}
