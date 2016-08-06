package apps.niknak.controllers.g1_single;

import java.io.File;
import java.util.List;

import org.neabulae.rmap.RequestNumber;
import org.neabulae.sfmap.StaticFileMapperIcon;
import org.neabulae.sfmap.StaticFileMapperImage;

@RequestNumber("1")
public class OutletController  extends __Perspective1 
{
	@RequestNumber("1")
	public void indexAction()
	throws Exception
	{
		out.printStyles(".image { width: 95%; border: solid 1px #aeaeae; border-radius: 3px; }"); 
		StaticFileMapperImage db = this.helper(StaticFileMapperImage.class);
		
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
