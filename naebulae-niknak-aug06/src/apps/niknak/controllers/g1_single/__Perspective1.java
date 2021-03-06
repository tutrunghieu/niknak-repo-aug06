package apps.niknak.controllers.g1_single;

import java.io.File;
import java.lang.reflect.Method;

import org.neabulae.rmap.RequestTarget;

public class __Perspective1 extends RequestTarget
{
	protected void renderMethod(Method m)
	throws Exception
	{
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
//		out = new HtmlWriter_8859_1(this.response.getWriter());
		
		renderMeta();
				
		out.println("<body>");
		renderToolBar();
		
		out.println("<div class=row>");
		
		out.println("<div class=col-md-2>");
		renderLeftMenu(m);
		out.println("</div>");
		
		out.println("<div class=col-md-10>");
		renderContent(m);
		out.println("</div>");

		out.println("</div>");
		out.println("</body>");		
	}
	
	protected void renderToolBar() 
	{
		
		out.println("<nav class='navbar navbar-default navbar-static-top'>");
		out.anchor("courses", super.baseUri() + "/course");
		
	
		out.middot2();
		out.anchor("outlets", super.baseUri() + "/outlet");
		
		out.middot2();
		out.anchor("workers", super.baseUri() + "/worker");		

		out.middot2();
		out.anchor("orders", super.baseUri() + "/order");
		out.println("</nav>");
		
	}
	

	private void renderLeftMenu(Method m) 
	{
		out.println("<div class='panel panel-default'>");
		
			out.println("<div class='list-group'>");
			
			out.println("<a href='#' class='list-group-item'>"
					+ "<i class='fa fa-comment fa-fw'></i> Left item"
					+ "<span class='pull-right text-muted small'><em>4 minutes ago</em>"
					+ "</span></a>");
			
			out.println("<a href='#' class='list-group-item'>"
					+ "<i class='fa fa-film'></i> Left item"
					+ "<span class='pull-right text-muted small'><em>4 minutes ago</em>"
					+ "</span></a>");
			
			out.println("<a href='#' class='list-group-item'>"
					+ "<i class='fa fa-film'></i> Left item"
					+ "<span class='pull-right text-muted small'><em>4 minutes ago</em>"
					+ "</span></a>");
			
			out.println("<a href='#' class='list-group-item'>"
					+ "<i class='fa fa-film'></i> Left item"
					+ "<span class='pull-right text-muted small'><em>4 minutes ago</em>"
					+ "</span></a>");
			
			out.println("<a href='#' class='list-group-item'>"
					+ "<i class='fa fa-film'></i> Left item"
					+ "<span class='pull-right text-muted small'><em>4 minutes ago</em>"
					+ "</span></a>");
			
			out.println("</div>");				
		
		out.println("</div>");				
	}

	private void renderMeta() 
	{
		out.println("<head>");
		out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css'>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js'></script>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
		
				
		out.println("<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>");
		out.println("</head>");		
	}

	private void renderContent(Method m) 
	throws Exception
	{
		m.invoke(this);
	}

	
	protected void printImage(File fk, String lk) 
	{
		out.println("<div class='col-md-3'>");
		
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-body'>");
		out.image(lk, "image");				
		out.println("</div>");
		
		out.println("<div class='panel-footer'>");
		out.println(fk.getName());
		out.println("</div>");
		
		out.println("</div>");
		
		out.println("</div>");
	}
}
