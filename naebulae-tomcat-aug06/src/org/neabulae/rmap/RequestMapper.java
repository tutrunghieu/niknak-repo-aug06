package org.neabulae.rmap;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

public class RequestMapper  
{
	public static boolean DEBUG = false;
	

	public static RequestMapper start(Class<?> c0) 
	{
		RequestMapper m = new RequestMapper();
		
		try 
		{
			m.addEntriesFromClass(c0);
			if(DEBUG) m.listAll(x -> { System.out.println(x); });
		}
		catch(Exception xp) { xp.printStackTrace(System.out); }
		
		return m;
	}

	
	
	public Map<String, RequestEntry> pairs = new TreeMap<String, RequestEntry>();

	public void addEntriesFromClass(Class<?> c0)
	throws Exception
	{
		Class1970 eng = Class1970.start();
		
		int start = eng.getClassFolderLength(c0);
		
		for(File fk: eng.readFilesFromClass(c0))
		if( eng.isClassFile(fk) )
		{
			Class<?> ck = eng.classFromFile(fk, start);
			String nk = requestNameFromClass(ck);
			if(nk==null) continue;
			
			RequestEntry rk = new RequestEntry();
			rk.targetClass = ck;
			rk.sourceGroup = nk;
			rk.japiNumber = getJapiNumber(ck);
			
			pairs.put(rk.sourceGroup, rk);
			
			addMethodsFromClass(rk);
		}
		
		return;
	}
	
	protected void addMethodsFromClass(RequestEntry ck) 
	{
		for(Method mj: ck.targetClass.getMethods())
		if( Modifier.isPublic(mj.getModifiers()) )
		{
			String lj = requestNameFromMethod(mj);
			if(lj == null) continue;
			
			RequestEntry rk = new RequestEntry();
			rk.targetClass = ck.targetClass;
			rk.targetMethod = mj;
			
			rk.sourceGroup = ck.sourceGroup;
			rk.sourceAction = lj;
			
			rk.japiNumber = ck.japiNumber + "." + getJapiNumber(mj);
			
			pairs.put(ck.sourceGroup + "/" + rk.sourceAction, rk);
		}
		
		return;
	}

	
	protected String getJapiNumber(Class<?> ck) 
	{
		RequestNumber nk = ck.getAnnotation(RequestNumber.class);
		return nk==null ? "" : nk.value();
	}
	
	
	protected String getJapiNumber(Method ck) 
	{
		RequestNumber nk = ck.getAnnotation(RequestNumber.class);
		return nk==null ? "" : nk.value();
	}	

	public void listAll(ClassAction lf) 
	throws Exception
	{
		for(String lk: pairs.keySet())
		{
			RequestEntry rk = pairs.get(lk);
			lf.invokeClassAction(rk);
		}
	}	

	protected String requestNameFromClass(Class<?> ck) 
	{
		String nk = ck.getSimpleName();
		if(!nk.endsWith("Controller")) return null;
		
		nk = nk.substring(0, nk.length()-10).toLowerCase();
		return nk;
	}
	
	protected String requestNameFromMethod(Method mj) 
	{
		String nk = mj.getName();
		if(!nk.endsWith("Action")) return null;
		
		nk = nk.substring(0, nk.length()-6).toLowerCase();
		return nk;
	}
	
	public String[] findSegmentsAfter(String url)
	{
		if(url.contains("://"))
		{
			url = url.substring(url.indexOf('/', 8));
		}
		
		String[] cells = url.split("\\/");
		
		if(DEBUG)
		for(int k=0; k<cells.length; k++)
		{
			System.out.println(k + "=["+cells[k]+"]");
		}

		return cells;
	}


	public Class<?> findClassForLink(String url) 
	{
		String[] cells = findSegmentsAfter(url);
		String dc = (cells.length>2 ? cells[2] : "");
		String df = (cells.length>3 ? cells[3] : "");
		
		return findClassForLink(dc, df);
	}

	public Class<?> findClassForLink(String dc, String df) 
	{
		RequestEntry rk = pairs.get(dc);
		return rk==null ? null : rk.targetClass;
	}

	public Method findMethodForLink(String url) 
	{
		String[] cells = findSegmentsAfter(url);
		String dc = (cells.length>2 ? cells[2] : "");
		String df = (cells.length>3 ? cells[3] : "");
		
		return findMethodForLink(dc, df);
	}

	private Method findMethodForLink(String dc, String df) 
	{
		String uk = dc + "/" + df;
//		System.out.println("Looking for" + uk);
		
		RequestEntry rk = pairs.get(uk);
		return rk==null ? null : rk.targetMethod;
	}

	public RequestEntry findEntryForLink(String url, int level)
	{
		String[] cells = findSegmentsAfter(url);
		String dc = (cells.length>2 ? cells[2] : "");
		String df = (cells.length>3 ? cells[3] : "");
		
		if(level==0) return pairs.get(dc);
		else if(level==1) return pairs.get(dc + "/" + df);
		else return null;
	}

	@SuppressWarnings("unchecked")
	public Class<? extends RequestTarget> findTargetController(String url, Class<? extends RequestTarget> dv)
	{
		Class<?> res = this.findClassForLink(url);
		if(res==null) return dv; 
		
		return (Class<? extends RequestTarget>)res;
	}


}
