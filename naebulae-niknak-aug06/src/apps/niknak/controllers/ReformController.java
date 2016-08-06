package apps.niknak.controllers;

import java.util.List;
import java.util.Map;

import org.neabulae.rmap.RequestNumber;
import org.neabulae.rmap.RequestTarget;

import apps.niknak.services.ExcelDataAccess;
import apps.niknak.services.List2;

@RequestNumber("1")
public class ReformController  extends RequestTarget 
{
	
	@RequestNumber("2")
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
