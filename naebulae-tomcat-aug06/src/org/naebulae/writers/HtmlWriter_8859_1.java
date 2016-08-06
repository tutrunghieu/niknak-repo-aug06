package org.naebulae.writers;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HtmlWriter_8859_1 extends HtmlWriter
{
	public String iso_8859_1tc(Object s)
	{
		if(s == null) return "";
		try { return new String(s.toString().getBytes(), "iso-8859-1"); }
		catch(Exception xp) { return s.toString(); }
	}	
	
	
	public HtmlWriter_8859_1(Writer t) 
	{ 
		super(t); 
	}
	
	public HtmlWriter_8859_1(File t) throws Exception
	{ 
		super(new PrintWriter(t)); 
	}

	public void td2(String cl, String inner) 
	{
		dataWriter.println("<td class="+cl+">"+this.iso_8859_1tc(inner)+"</td>");		
	}
	
	
	public void td(Object s) 
	{
		dataWriter.println("<td>"+this.iso_8859_1tc(s)+"</td>");		
	}
	
	public void th(Object s) 
	{
		dataWriter.println("<th>"+this.iso_8859_1tc(s)+"</th>");		
	}
	
	public void anchor(String text, String link) 
	{
		dataWriter.println("<a href='"+link+"'>"+this.iso_8859_1tc(text)+"</a>");		
	}

	public void anchorTar(String text, String link) 
	{
		dataWriter.println("<a href='"+link+"' target=_blank>"+this.iso_8859_1tc(text)+"</a>");		
	}
	
	public void span(String text) 
	{
		dataWriter.println("<span>"+this.iso_8859_1tc(text)+"</span>");
	}
	

	public void h1(String text) 
	{
		dataWriter.println("<h1>"+this.iso_8859_1tc(text)+"</h1>");
	}
	
	public void h2(String text) 
	{
		dataWriter.println("<h2>"+this.iso_8859_1tc(text)+"</h2>");
	}

	public void h3(String text) 
	{
		dataWriter.println("<h3>"+this.iso_8859_1tc(text)+"</h3>");
	}
	
	public void p(String text) 
	{
		dataWriter.println("<p>"+this.iso_8859_1tc(text)+"</p>");		
	}
		
	
	public void print(String text)
	{
		dataWriter.print(this.iso_8859_1tc(text));
	}
	

	public void println(String text)
	{
		dataWriter.println(this.iso_8859_1tc(text));
	}	
	
	
	
	public void headerRowFromStrings(String... fields)
	{
		dataWriter.print("<tr>");
		for(Object ak: fields) dataWriter.print("<th>"+this.iso_8859_1tc(ak)+"</th>"); 
		dataWriter.println("</tr>");
	}	
		
	public void headerRowFromFields(Field... fields) 
	{
		dataWriter.print("<tr>");
		for(Field ak: fields) dataWriter.print("<th>"+this.iso_8859_1tc(ak.getName())+"</th>"); 
		dataWriter.println("</tr>");		
	}
	
	public void headerRowFromSet(Set<String> fields) 
	{
		dataWriter.print("<tr>");
		for(String ak: fields) dataWriter.print("<th>"+this.iso_8859_1tc(ak)+"</th>"); 
		dataWriter.println("</tr>");		
	}
	
	
	public void headerRowFromList(List<String> fields)
	{
		dataWriter.print("<tr>");
		for(String ak: fields) dataWriter.print("<th>"+this.iso_8859_1tc(ak)+"</th>"); 
		dataWriter.println("</tr>");
	}	
		
	
	public void dataRowFromArrayObj(Object... fields)
	{
		dataWriter.print("<tr>");
		for(Object ak: fields) dataWriter.print("<td>"+this.iso_8859_1tc(ak)+"</td>"); 
		dataWriter.println("</tr>");
	}
	
	public void dataRowFromArrayStr(String... fields)
	{
		dataWriter.print("<tr>");
		for(Object ak: fields) dataWriter.print("<td>"+this.iso_8859_1tc(ak)+"</td>"); 
		dataWriter.println("</tr>");
	}		
	
	public<T> void dataRowFromList(List<T> names) 
	{
		dataWriter.print("<tr>");
		for(T nk: names) dataWriter.print("<td>"+this.iso_8859_1tc(nk)+"</td>"); 
		dataWriter.println("</tr>");						
	}
	
	public<T> void dataRowFromSet(Set<T> names) 
	{
		dataWriter.print("<tr>");
		for(T nk: names) dataWriter.print("<td>"+this.iso_8859_1tc(nk)+"</td>"); 
		dataWriter.println("</tr>");						
	}
	

	public void dataRowFromObject(Object obj) throws Exception 
	{
		if(dataFields == null) 
		{
			dataFields = getFieldArrayFromObject(obj);
			dataWriter.print("<tr>");
			for(Field fk: dataFields) dataWriter.print("<td>"+this.iso_8859_1tc(fk.getName())+"</td>"); 
			dataWriter.println("</tr>");		
		}
		
		dataWriter.print("<tr>");
		for(Field fk: dataFields) dataWriter.print("<td>"+this.iso_8859_1tc(fk.get(obj))+"</td>"); 
		dataWriter.println("</tr>");		
		
	}
	
	@SuppressWarnings("unchecked")
	public void dataRowFromMap(Object s, List<String> fields)  
	{
		Map<String, Object> obj = (Map<String, Object>)s;
		
		dataWriter.print("<tr>");
		for(String fk: fields) dataWriter.print("<td>"+this.iso_8859_1tc(obj.get(fk))+"</td>"); 
		dataWriter.println("</tr>");				
	}
	
	@SuppressWarnings("unchecked")
	public void dataRowFromMapIso(Object s, List<String> fields)
	throws Exception
	{
		Map<String, Object> obj = (Map<String, Object>)s;
		
		dataWriter.print("<tr>");
		for(String fk: fields) 
		{
			Object vk = obj.get(fk);
			dataWriter.print("<td>"+this.iso_8859_1tc(vk==null ? "" : vk.toString())+"</td>"); 
		}
		dataWriter.println("</tr>");				
	}	
	
	@SuppressWarnings("unchecked")
	public void dataRowFromMap(Object s, String[] fields)  
	{
		Map<String, Object> obj = (Map<String, Object>)s;
		
		dataWriter.print("<tr>");
		for(String fk: fields) dataWriter.print("<td>"+this.iso_8859_1tc(obj.get(fk))+"</td>"); 
		dataWriter.println("</tr>");				
	}
	
	@SuppressWarnings("unchecked")
	public void dataRowFromMap(Object s, Set<String> fields)  
	{
		Map<String, Object> obj = (Map<String, Object>)s;
		
		if(fields == null) fields = obj.keySet();
		
		dataWriter.print("<tr>");
		for(String fk: fields) dataWriter.print("<td>"+this.iso_8859_1tc(obj.get(fk))+"</td>"); 
		dataWriter.println("</tr>");				
	}
	
	public void dataCellFromLink(String text, String href) 
	{
		dataWriter.println("<td>");
		dataWriter.println("<a href='"+href+"'>"+this.iso_8859_1tc(text)+"</a>");
		dataWriter.println("</td>");
	}	


	public String anchorText(String text, String link) 
	{
		return "<a href='"+link+"'>"+this.iso_8859_1tc(text)+"</a>";
	}
	
	public String anchorTextTar(String text, String link) 
	{
		return "<a target=_blank href='"+link+"'>"+this.iso_8859_1tc(text)+"</a>";
	}
	
	

	public String spanText(String text, String cl) 
	{
		return "<span class='"+cl+"'>"+this.iso_8859_1tc(text)+"</span>";
	}

	
}
