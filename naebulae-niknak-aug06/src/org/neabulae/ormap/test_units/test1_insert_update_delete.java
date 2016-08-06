package org.neabulae.ormap.test_units;

import java.io.File;

import org.naebulae.util.Joiner;
import org.neabulae.ormap.TableAccessExcel;

public class test1_insert_update_delete 
{
	public static void main(String[] args) 
	throws Exception
	{
//		URL u = new URL("https://www.dropbox.com/s/bac9bntlej8dzka/tab-courses.xlsx?dl=0&raw=1");
		
		File f = new File("C:/Users/henrytu/Dropbox/2016-projects-silkroad/niknak-data/aug01-coffee-bike.xlsx");
		TableAccessExcel db = new TableAccessExcel(f);
		
		db.dropAllTables();
//		
//		db.insert(Tab_Customer.class, new Tab_Customer("Trần Hoàng Anh", "anh@gmail.com"));
//		db.insert(Tab_Customer.class, new Tab_Customer("Hoàng Đức Bình", "binh@gmail.com"));
//		
//		db.insert(Tab_User.class, new Tab_User("Trần Hoàng Anh", "anh@gmail.com"));
//		db.insert(Tab_User.class, new Tab_User("Hoàng Đức Bình", "binh@gmail.com"));
//		db.insert(Tab_User.class, new Tab_User("Nguyễn Anh Cường", "cuong@gmail.com"));
//		
//		db.delete(Tab_User.class, new FuncWhere("userEmail", "binh@gmail.com"));
//		
//		db.update(Tab_User.class,
//				new FuncWhere("userEmail", "cuong@gmail.com"),
//				new FuncUpdater("userName", "Nguyễn Anh Cường 123", 
//						"userEmail1", "cuong1@gmail.com"));
		
		for(String tk: db.getTableNames())
		for(Object rj: db.fetchRowsAsMap(tk))
		{
			System.out.println("=========" + tk);
			Joiner.start("\r\n").printMap(rj);		
		}		
	}

}
