package DB;

public class Main {
	public static void main(String args[]){
		Database db = new Database();

		System.out.println(db.parse("users, add, 01, john,bruce, happy"));
	}
}
