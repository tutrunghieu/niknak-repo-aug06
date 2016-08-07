package org.neabulae.ormap;

import java.util.LinkedHashMap;
import java.util.Map;

public class FuncWhere 
{
	protected Map<String, String> vals = new LinkedHashMap<String, String>();
	
	public FuncWhere(String... args)
	{
		for(int k=0; k+1<args.length; )
		{
			String nk = args[k++];
			String vk = args[k++];
			vals.put(nk, vk);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean invokeBooleanAction(Object t) 
	{
		LinkedHashMap<String, Object> t1 = (LinkedHashMap<String, Object>)t;
		
		boolean res = true;
		for(String vk: vals.keySet())
		{
			String ak = vals.get(vk);
			Object bk = t1.get(vk);
			res &= ak.equals(bk);
			
			if(res == false) break;
		}
					
		return res;
	}

}
