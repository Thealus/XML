package DB.Databases;

import java.util.List;
import java.util.ArrayList;

public class RoutesDB extends DB {
	public RoutesDB(){
		elements = loadElements();
		name = "routes";
		checkFile(name);
	}

	public String findRoute(List<String> entry){
		return "Finding Route";
	}

	private List<String> loadElements(){
		List<String> toReturn = new ArrayList<String>();
		toReturn.add("id");
		toReturn.add("origin");
		toReturn.add("destination");
		toReturn.add("company");
		toReturn.add("distance");
		toReturn.add("transportType");
		toReturn.add("weightCost");
		toReturn.add("volumeCost");

		return toReturn;
	}

	@Override
	public String attemptMethod(String type, List<String> entry){
		if(type.equals("findRoute"))
			return findRoute(entry);
		return "Invalid RoutesDB method: "+type;
	}
}