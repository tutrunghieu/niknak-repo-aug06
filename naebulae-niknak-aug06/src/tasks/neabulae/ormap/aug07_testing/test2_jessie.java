package tasks.neabulae.ormap.aug07_testing;

import org.naebulae.util.Joiner;
import org.neabulae.ormap.FuncUpdater;
import org.neabulae.ormap.FuncWhere;
import org.neabulae.ormap.TableAccess;
import org.neabulae.ormap.jessie.TableAccessJessie;

public class test2_jessie 
{
	public static void main(String[] args) 
	throws Exception
	{
		TableAccess db = new TableAccessJessie("c:/opt/data-jessie");
		
		db.dropAllTables();
		
		db.insert(Tab_Customer.class, new Tab_Customer("Trần Hoàng Anh", "anh@gmail.com"));
		db.insert(Tab_Customer.class, new Tab_Customer("Hoàng �?ức Bình", "binh@gmail.com"));
		
		db.insert(Tab_User.class, new Tab_User("Trần Hoàng Anh", "anh@gmail.com"));
		db.insert(Tab_User.class, new Tab_User("Hoàng �?ức Bình", "binh@gmail.com"));
		db.insert(Tab_User.class, new Tab_User("Nguyễn Anh Cư�?ng", "cuong@gmail.com"));
		
		db.delete(Tab_User.class, new FuncWhere("userEmail", "binh@gmail.com"));
		
		db.update(Tab_User.class,
				new FuncWhere("userEmail", "cuong@gmail.com"),
				new FuncUpdater("userName", "Nguyễn Anh Cư�?ng 123", 
						"userEmail1", "cuong1@gmail.com"));
		
		for(String tk: db.selectTableNames())
		for(Object rj: db.select(tk))
		{
			System.out.println("=========" + tk);
			Joiner.start("\r\n").printMap(rj);		
		}		
	}

}
