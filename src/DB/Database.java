package DB;

public class Database {
	private UserDB users;
	private RoutesDB routes;
	private PackageDB packages;
	
	
	public Database(){
		users = new UserDB();
		routes = new RoutesDB();
		packages = new PackageDB();
	}
	
	public void parse(String toParse){
		//split string
	}
}
