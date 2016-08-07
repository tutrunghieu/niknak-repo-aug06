package org.naebulae.util;

import java.lang.reflect.Field;
import java.util.Map;

public class Joiner {

	private String comma;

	public static Joiner start(String cm) 
	{
		Joiner r = new Joiner();
		r.comma = cm;
		return r;
	}
	
	public void printObjectOrMap(Object src)
	throws Exception
	{
		if(src instanceof Map) printMap(src);
		else printObject(src);
	}
	
	public void printObject(Object src)
	throws Exception
	{
		String res = "";
		
		for(Field f: src.getClass().getFields())
		{
			if(res.length()>0) res += comma;			
			res += f.getName() + "=" + f.get(src);
		}

		System.out.println(res);
	}

	@SuppressWarnings("unchecked")
	public void printMap(Object src)
	throws Exception
	{
		Map<String, Object> t = (Map<String, Object>)src;
		
		String res = "";
		
		for(String f: t.keySet())
		{
			if(res.length()>0) res += comma;			
			res += f + "=" + t.get(f);
		}

		System.out.println(res);
	}	
}
