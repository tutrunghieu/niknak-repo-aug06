package org.neabulae.sfmap;

import java.io.File;

public class StaticFileMapperImage extends StaticFileMapper
{
	public StaticFileMapperImage(File f, String act) 
	{
		super(f, act);
	}

	public StaticFileMapperImage(String f, String act) 
	{
		super(new File(f), act);
	}

}
