package org.neabulae.sfmap;

import java.io.File;

public class StaticFileMapperIcon extends StaticFileMapper
{
	public StaticFileMapperIcon(File f) 
	{
		super(f);
	}

	public StaticFileMapperIcon(String f) 
	{
		super(new File(f));
	}

}
