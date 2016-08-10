package tasks.viademy.aug05_class_scanning;

import java.lang.reflect.Method;

import org.neabulae.rmap.Class1970;
import org.neabulae.rmap.RequestEntry;
import org.neabulae.rmap.RequestMapper;

public class fetching_class_from_url extends Class1970 
{
	public static void main(String[] args)
	throws Exception
	{
		RequestMapper m = new RequestMapper();
		
		m.addEntriesFromClass(scan_for_classes.class);
		m.listAll(x -> { System.out.println(x); });
		
		String url = "http://localhost:8080/viademy-backend/start/hash";
		Class<?> cl = m.findClassForLink(url);
		System.out.println(url + " >> " + cl.getName());
		
		Method mk = m.findMethodForLink(url);
		System.out.println(url + " >> " + mk.getName());
		
		RequestEntry ek = m.findEntryForLink(url, 0);
		System.out.println(ek);
		
		ek = m.findEntryForLink(url, 1);
		System.out.println(ek);
	}

	
}
