package DB;

import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import DB.Databases.PackageDB;
import DB.Databases.RoutesDB;
import DB.Databases.UserDB;
import org.jdom2.*;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;
import DB.Databases.DB;


public class Database {
	private UserDB users;
	private RoutesDB routes;
	private PackageDB packages;
	
	
	public Database(){
		users = new UserDB();
		routes = new RoutesDB();
		packages = new PackageDB();
	}

	/**
	 * Parses a string passed from the client. Convention requires the string conform to strict standards for
	 * the purpose of parsing.
	 *
	 * Takes the first entry on the string as the DB to perform a method on.
	 * Takes the second entry on the string as the method.
	 *
	 * Passes the rest of the string to that method in the DB.
	 *
	 * @param toParse The String passed to this method
	 * @return A string detailing what resulted
	 */
	public String parse(String toParse){
		System.out.println(toParse);
		String toReturn;

		//turn String into Arraylist
		ArrayList<String> data = new ArrayList<String>(Arrays.asList(toParse.split(",")));

		//definitely incorrect size
		if(data.size() < 3)
			return "Invalid parse";

		DB toUse;
		String value = data.remove(0);

		//finds DB to use
		switch(value.trim()){
			case "users":
				toUse = users;
				break;
			case "routes":
				toUse = routes;
				break;
			case "packages":
				toUse = packages;
				break;
			default: return "Invalid database: "+value;
		}

		value = data.remove(0);

		//finds method to use
		switch(value.trim()){
			case "add":
				toReturn = toUse.add(data);
				break;
			case "remove":
				toReturn = toUse.remove(data);
				break;
			case "update":
				toReturn = toUse.update(data);
				break;
			case "find":
				toReturn = toUse.find(data);
				break;
			default: //either an invalid method or a method specific to the database
				toReturn = toUse.attemptMethod(value, data);
				break;
		}

		return toReturn;
	}


	/**
	 * Just testing to see XML
	 *
	 */
	public void test(){
		try{
			//create root element
			Element school = new Element("school");

			//create document object with root element
			Document document = new Document(school);

			Element student = new Element("student");

			student.setAttribute(new Attribute("id", "1"));
			student.addContent(new Element("firstname").setText("ankush"));
			student.addContent(new Element("lastname").setText("thakur"));
			student.addContent(new Element("email").setText("email@email.com"));

			document.getRootElement().addContent(student);

			XMLOutputter xmlOutput = new XMLOutputter();

			xmlOutput.output(document, System.out);

			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter("xmlfile.xml"));

		} catch (Exception io){
			System.out.println(io.getMessage());
		}
	}
}
