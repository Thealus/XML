package DB.Databases;

import java.util.ArrayList;
import java.util.List;

public class UserDB extends DB {
	public UserDB(){
		elements = loadElements();
		name = "user";
		checkFile(name);
		//check if XML file exists
		//if so, load
		//else, create one
	}


	public String login(List<String> value){
		//get username entry
		//check login
		return null;
	}

	private List<String> loadElements(){
		List<String> toReturn = new ArrayList<String>();
		toReturn.add("id");
		toReturn.add("username");
		toReturn.add("password");
		toReturn.add("type");


		return toReturn;
	}

	@Override
	public String attemptMethod(String type, List<String> entry){
		if(type.equals("login"))
			return login(entry);

		return "Invalid UserDB method: "+type;
	}
}
