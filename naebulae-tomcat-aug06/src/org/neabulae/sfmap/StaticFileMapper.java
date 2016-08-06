package org.neabulae.sfmap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;

public class StaticFileMapper 
{
	public String folderPrefix = "/null-folder/" + this.getClass().getName();
	
	public StaticFileMapper(File f)
	{
		folderPrefix = f.getAbsolutePath();
	}


	public String getAbsolutePath(String file) 
	{
		return this.folderPrefix + file;
	}

	public File getAbsoluteFile(String file) 
	{
		return new File( this.folderPrefix + file );
	}
	
	public List<File> readFiles() 
	{
		List<File> res = new ArrayList<File>();
		
		File[] files = (new File(this.folderPrefix)).listFiles();
		
		if(files != null) for(File fj: files) res.add(fj);
		
		return res;
	}
	
	
	public String getMimeType(File f) 
	throws Exception
	{
		InputStream is = new BufferedInputStream(new FileInputStream(f));
		return URLConnection.guessContentTypeFromStream(is);
	}


	public void writeFile(File f, OutputStream out) 
	throws Exception
	{
		FileInputStream in = new FileInputStream(f);
		IOUtils.copy(in, out);
		in.close();
	}

	public void writeFile(File f, HttpServletResponse response) 
	throws Exception
	{
		response.reset();
		response.setContentType(this.getMimeType(f) );
		this.writeFile(f, response.getOutputStream());		
	}


}
