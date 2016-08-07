package tasks.neabulae.ormap.aug07_testing;

import java.lang.reflect.Field;

import org.bson.Document;
import org.naebulae.util.Joiner;
import org.neabulae.ormap.mongo.TableAccessMongo;

public class test4_mongo_native 
{
	public static void main(String[] args) 
	throws Exception
	{
		TableAccessMongo db = new TableAccessMongo("test-db1");
		
		db.dropAllTables();
		
		db.insertNative(Tab_User.class, doc(new Tab_User("Trần Hoàng Anh", "anh@gmail.com")) );
		db.insertNative(Tab_User.class, doc(new Tab_User("Hoàng Đức Bình", "binh@gmail.com")) );
		db.insertNative(Tab_User.class, doc(new Tab_User("Nguyễn Anh Cường", "cuong@gmail.com")) );
		
		db.deleteNative(Tab_User.class, doc("userEmail", "binh@gmail.com") );
		
		db.updateNative(Tab_User.class,
				doc("userEmail", "cuong@gmail.com"),
				doc("userName", "Nguyễn Anh Cung 123") );
		
		db.updateNative(Tab_User.class,
				doc("userEmail", "anh@gmail.com"),
				doc("userName", "Nguyễn Hoàng Anh 1234") );
		
//		for(String tk: db.selectTableNames())
		String tk = "tab_user";
		for(Object rj: db.selectNative(Tab_User.class) )
		{
			System.out.println("=========" + tk);
			Joiner.start("\r\n").printMap(rj);		
		}		
	}

	private static Document doc(String... args)
	{
		Document res = new Document();
		
		for(int k=0; k+1<args.length; )
		{
			String nk = args[k++];
			String vk = args[k++];
			res.put(nk, vk);
		}

		return res;
	}

	private static Document doc(Object src)
	throws Exception
	{
		Document res = new Document();
		
		for(Field f: src.getClass().getFields())
		{
			res.append(f.getName(), f.get(src));
		}

		return res;
	}

}
