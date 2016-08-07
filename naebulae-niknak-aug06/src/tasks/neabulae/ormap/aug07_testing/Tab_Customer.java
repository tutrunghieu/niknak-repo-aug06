package tasks.neabulae.ormap.aug07_testing;

public class Tab_Customer 
{
	public String userName;
	public String userEmail;
	
	public String userPoints = Math.random() + "";
	public String userDevice = "dev" + Math.random() + "";

	public Tab_Customer()
	{
		
	}
	
	public Tab_Customer(String name, String email)
	{
		userName = name;
		userEmail = email;
	}

}
