package apps.niknak;

import java.io.File;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.neabulae.rmap.RequestEntry;
import org.neabulae.rmap.RequestHeplers;
import org.neabulae.rmap.RequestMapper;
import org.neabulae.rmap.RequestTarget;
import org.neabulae.sfmap.StaticFileMapperIcon;
import org.neabulae.sfmap.StaticFileMapperImage;

import apps.niknak.controllers.HomeController;

public class AppMain 
{
	private static RequestMapper mapper = RequestMapper.start(HomeController.class);
	private static RequestHeplers helpers = RequestHeplers.start();
	
	public static void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		appendHelpers(helpers);
				
		try 
		{
			Class<? extends RequestTarget> r =  mapper.findTargetController(request.getRequestURI(), HomeController.class);
			System.out.println("Switching to: " + r.getName());
		
			RequestTarget tar = r.newInstance();
			tar.enrichRequest(mapper, helpers, request, response);			
			tar.processRequest();
		}
		
		catch(Exception xp)
		{
			System.out.println("============ Controller mapping error");
			xp.printStackTrace(System.out);
		}		
	}

	public static void appendHelpers(RequestHeplers h) 
	{
		if(helpers.size() > 0) return;

		helpers.put(StaticFileMapperIcon.class, 
				new StaticFileMapperIcon("C:/opt/data-icons"));
		
		helpers.put(StaticFileMapperImage.class, 
				new StaticFileMapperImage("C:/opt/data-images"));
	}

	public static void main(String[] args)
	throws Exception
	{
		RequestMapper m = RequestMapper.start(HomeController.class);
		
		String url = "/niknak/reform/view";
		Class<?> cl = m.findClassForLink(url);
		System.out.println(url + " >> " + cl.getName() );
		
		Method mk = m.findMethodForLink(url);
		System.out.println(url + " >> " + mk.getName());
		
		RequestEntry ek = m.findEntryForLink(url, 0);
		System.out.println(ek);
		
		ek = m.findEntryForLink(url, 1);
		System.out.println(ek);
	}
	
}
