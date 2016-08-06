package org.neabulae.rmap;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.naebulae.writers.HtmlWriter;

public class RequestTarget 
{
	protected RequestMapper mapper;
	protected RequestHeplers helpers;
	
	private ObjectMapper __helperJson = null;
	
	protected HttpServletRequest request;
	protected  String requestUri;
	protected HttpServletResponse response;
	protected HtmlWriter out;
	

	public void indexAction()
	throws Exception
	{
		
	}
	
	public void processRequest() 
	throws Exception
	{
		Method m = mapper.findMethodForLink(requestUri);
		if(m==null) m = this.getClass().getMethod("indexAction");
		
		System.out.println("Switching to " + this.getClass() + " >> " + m.getName());
		renderMethod(m);
	}
	
	protected void renderMethod(Method m)
	throws Exception
	{
		m.invoke(this);		
	}

	public void enrichRequest(RequestMapper m, RequestHeplers h, HttpServletRequest input, HttpServletResponse output)
	throws Exception
	{
		this.mapper = m;
		this.helpers = h;
		
		this.request = input;
		this.requestUri = input.getRequestURI();
		
		this.response = output;		
		this.out = new HtmlWriter(output.getWriter());
	}

	public String readParam(String name, String dv) 
	{
		String v = request.getParameter(name);
		return v==null ? dv : v;
	}

	public<T1> T1 helper(Class<T1> cl)
	{
		T1 res = helpers.get(cl);
		
		if(res == null)
		try
		{
			helpers.put(cl, res = cl.newInstance());
		}
		catch(Exception xp) {}
		
		return res;
	}

	
	public ObjectMapper helperJson()
	{
		if(__helperJson==null) __helperJson = new ObjectMapper();
		return __helperJson;
	}

	public void writeJson(Object src) 
	throws Exception
	{
		helperJson().writeValue(response.getWriter(), src);		
	}

	public void log(Exception xp) 
	{
		xp.printStackTrace(System.out);
	}

	public void log(String string, String url) 
	{
		System.out.println(string + ": " + url);
	}

	
	public String baseUri() 
	{
		return "/" + requestUri.split("\\/")[1];
	}

	public String removeFirst(int kpar) 
	{
		int pk = 0;
		String uri = requestUri;
		for(int k=0; k<kpar; k++) pk = uri.indexOf('/', pk+1);
		return requestUri.substring(pk);
	}

}
