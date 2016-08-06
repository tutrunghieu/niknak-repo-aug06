package org.neabulae.ormap;

import java.util.LinkedHashMap;
import java.util.Map;

public class FuncUpdater {

	private Map<String, String> vals = new LinkedHashMap<String, String>();
	
	public FuncUpdater(String... args)
	{
		for(int k=0; k+1<args.length; )
		{
			String nk = args[k++];
			String vk = args[k++];
			vals.put(nk, vk);
		}
	}

	public LinkedHashMap<String, Object> updateObject(LinkedHashMap<String, Object> mk) 
	{
		for(String vk: vals.keySet()) 
			mk.put(vk, vals.get(vk));
		
		return mk;
	}

}
