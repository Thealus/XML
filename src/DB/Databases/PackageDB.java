package DB.Databases;

import java.util.ArrayList;
import java.util.List;

public class PackageDB extends DB {

	public PackageDB(){
		elements = loadElements();
		name = "package";
		checkFile(name);
	}

	private List<String> loadElements(){
		List<String> toReturn = new ArrayList<String>();
		toReturn.add("id");
		toReturn.add("date");
		toReturn.add("to");
		toReturn.add("from");
		toReturn.add("weight");
		toReturn.add("volume");
		toReturn.add("priority");

		return toReturn;
	}

	@Override
	public String attemptMethod(String type, List<String> entry){
		return "Invalid PackageDB method: "+type;
	}
}
