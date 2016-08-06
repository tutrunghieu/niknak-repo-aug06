package org.neabulae.sfmap;

import java.io.File;

public class StaticFileMapperImage extends StaticFileMapper
{
	public StaticFileMapperImage(File f) 
	{
		super(f);
	}

	public StaticFileMapperImage(String f) 
	{
		super(new File(f));
	}

}
