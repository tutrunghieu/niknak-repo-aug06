package org.neabulae.rmap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Class1970 
{
	public static Class1970 start() 
	{
		return new Class1970();
	}
	
	public File fileFromClass(Class<?> start) 
	{
		String name = '/' + start.getName().replace('.', '/') + ".class";
		File f = new File( start.getResource(name).getFile() );
		return f;
	}

	public Class<?> classFromFile(File fk, int start)
	throws Exception
	{
		String nk = fk.getAbsolutePath();
		
		nk = nk.substring(start, nk.length()-6).replace('\\', '/').replaceAll("/", ".");
//		System.out.println(nk);
		
		return Class.forName(nk);
	}

	public File getClassFolder(Class<?> cl) 
	{
		File f = fileFromClass(cl).getParentFile();
		int start = f.getAbsolutePath().length() - cl.getPackage().getName().length();
		return new File(f.getAbsolutePath().substring(0, start) );
	}
	
	public int getClassFolderLength(Class<?> cl) 
	{
		File f = fileFromClass(cl).getParentFile();
		return f.getAbsolutePath().length() - cl.getPackage().getName().length();
	}

	public boolean isClassFile(File fk) 
	{
		String nk = fk.getName();
		return nk.endsWith(".class") && nk.indexOf('$') < 0;
	}

	public List<File> readFilesFromClass(Class<?> cl) 
	{
		File f = start().fileFromClass(cl);
		return readFilesFromFolder(f.getParentFile());
	}

	public List<File> readFilesFromFolder(File f0) 
	{
		List<File> res = new ArrayList<File> ();
		
		Stack<File> todo = new Stack<File>();
		todo.add(f0);
		
		while(!todo.isEmpty())
		{
			File fk = todo.pop();
			
			File[] files = fk.listFiles();
			if(files == null) continue;
			
			for(File fj: files)
			if( fj.isFile() ) res.add(fj);
			else if( fj.isDirectory() ) todo.add(fj);
		}
		
		return res;
	}	
}
