package org.neabulae.sfmap;

import java.io.File;

public class StaticFileMapperIcon extends StaticFileMapper
{
	public StaticFileMapperIcon(File f, String act) 
	{
		super(f, act);
	}

	public StaticFileMapperIcon(String f, String act) 
	{
		super(new File(f), act);
	}


}
