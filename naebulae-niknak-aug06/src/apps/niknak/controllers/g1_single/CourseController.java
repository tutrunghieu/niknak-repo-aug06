package apps.niknak.controllers.g1_single;

import java.io.File;
import java.util.List;

import org.neabulae.rmap.RequestNumber;
import org.neabulae.sfmap.StaticFileMapperIcon;

@RequestNumber("1")
public class CourseController  extends __Perspective1 
{
	
	@RequestNumber("1")
	public void indexAction()
	throws Exception
	{
		StaticFileMapperIcon db = this.helper(StaticFileMapperIcon.class);
		
		List<File> files = db.readFiles();
		for(File fk: files)
		if(!fk.getName().endsWith(".txt"))
		{
			String lk = db.completeLink(this.baseUri(), fk.getName() );
			printImage(fk, lk);
		}
	}


	@RequestNumber("2")
	public void addAction()
	throws Exception
	{
		
	}

	@RequestNumber("3")
	public void removeAction()
	throws Exception
	{
		
	}
	
	@RequestNumber("4")
	public void editAction()
	throws Exception
	{
		
	}
}
