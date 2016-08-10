package tasks.viademy.aug05_class_scanning;

import java.io.File;

import org.neabulae.rmap.Class1970;

public class scan_for_classes extends Class1970 
{
	public static void main(String[] args)
	throws Exception
	{
		Class<?> cl = scan_for_classes.class;
//		System.out.println("Container folder: " + start().getClassFolder(cl) );
//		System.out.println("Container file: " + start().getClassFolder(cl) );
		int start = start().getClassFolderLength(cl);
		
		for(File fk: start().readFilesFromClass(cl))
		if( start().isClassFile(fk) )
		{
			Class<?> ck = start().classFromFile(fk, start);
			System.out.println(fk + " >> " + ck.getSimpleName());
		}
		
		return;
	}

	
}
