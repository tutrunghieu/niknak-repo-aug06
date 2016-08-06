package apps.niknak.services;

import java.util.List;
import java.util.Map;

public class List2 {

	public static void writeMapObjects(List<Map<String, Object>> rows) 
	{
		for(Map<String, Object> rk: rows) System.out.println(rk);		
	}
}
