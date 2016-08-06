package apps.niknak.controllers;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.neabulae.rmap.RequestNumber;
import org.neabulae.rmap.RequestTarget;
import org.neabulae.sfmap.StaticFileMapper;
import org.neabulae.sfmap.StaticFileMapperIcon;
import org.neabulae.sfmap.StaticFileMapperImage;

import apps.niknak.services.ExcelDataAccess;
import apps.niknak.services.List2;

@RequestNumber("1")
public class ReformController  extends RequestTarget 
{
	@RequestNumber("1")
	public void jsonAction()
	throws Exception
	{
		StaticFileMapper m = this.helper(StaticFileMapperIcon.class);
		
		String file = super.removeFirst(3);
		out.p("p1:" + file);
		
		File f = m.getAbsoluteFile(file);
		out.p("p2:" + file);
		
		m.writeFile(f, response);		
	}	
	@RequestNumber("1")
	public void iconsAction()
	throws Exception
	{
		StaticFileMapper m = this.helper(StaticFileMapperIcon.class);
		
		String file = super.removeFirst(3);
		out.p("p1:" + file);
		
		File f = m.getAbsoluteFile(file);
		out.p("p2:" + file);
		
		m.writeFile(f, response);		
	}
	
	@RequestNumber("2")
	public void imagesAction()
	throws Exception
	{
		StaticFileMapper m = this.helper(StaticFileMapperImage.class);
		
		String file = super.removeFirst(3);
		out.p("p1:" + file);
		
		File f = m.getAbsoluteFile(file);
		out.p("p2:" + file);
		
		m.writeFile(f, response);		
	}
	
	@RequestNumber("3")
	public void viewAction()
	throws Exception
	{
		String url = super.readParam("url", "https://www.dropbox.com/s/bac9bntlej8dzka/tab-courses.xlsx?dl=0&raw=1").trim();
		super.log("url", url);
		
		String hct = super.readParam("hct", "application/json; charset=utf-8").trim();
		super.log("hct", hct);
		
		response.reset();
		response.setContentType(hct);
				
		try 
		{
			ExcelDataAccess db = super.helper(ExcelDataAccess.class);
		
			List<Map<String, Object>> rows = db.fetchRowsFromUrl(url, "xlsx", 0);
			List2.writeMapObjects(rows);
		
			super.writeJson(rows);
		}
		
		catch(Exception xp) 
		{
			super.log(xp);
			super.writeJson(xp.getClass().getName());
		}
	}
	
	

}
