package DB.Databases;
import java.io.*;
import java.util.List;

import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;


public class DB {
	protected List<String> elements;
	protected String name;
	protected File xmlFile;
	protected int entries;

	public int getElementsSize(){
		return elements.size();
	}

	/**
	 * Checks to see if an XML file associated with the DB exists currently, if not, it will create one.
	 *
	 * @param name Name of file to check for
	 */
	protected void checkFile(String name){
		try {
			xmlFile = new File("src\\XML\\"+name + "DB.xml");

			if(!xmlFile.exists()) {
				createFile();

				System.out.println(xmlFile.getName() + " created.");
			} else
				System.out.println(xmlFile.getName()+" exists.");

			entries = countEntries();

			System.out.println(xmlFile+" contains "+entries+" entries.");
		} catch (Exception io){
			System.out.println("Exception: "+io.getMessage());
		}
	}

	/**
	 * Creates an XML file
	 */
	private void createFile(){
		//create root
		Element root = new Element(name+"DB");

		//creates document element and assign root to it
		Document document = new Document(root);

		styleDocument(document);
	}


	/**
	 * Styles the XML document according to conventions.
	 * Any child node of a node will be indented by a tab.
	 *
	 * @param doc XML file to stylise.
	 */
	protected void styleDocument(Document doc){
		try{
			// get object to see output of prepared document
			XMLOutputter xmlOutput = new XMLOutputter();

			// passed fileWriter to write content in specified file
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(xmlFile));
		} catch (IOException io){
			System.out.println(io.getMessage());
		}
	}

	/**
	 * Counts number of entries in XML file.
	 *
	 * @return number of entries.
	 */

	private int countEntries(){
		try {
			int toReturn = 0;

			SAXBuilder builder = new SAXBuilder();

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren(name);

			return list.size();
		} catch(JDOMException | IOException e){
			System.out.println(e.getMessage());
		}

		return 0;
	}

	/**
	 * Generic method to add to an XML file
	 *
	 * @param entry Parse of data to try and add
	 * @return The result of this method
	 */
	public String add(List<String> entry){
		try {
			//check if entry size matches elements size
			if (elements.size() != entry.size())
				return "Add failed, DB size of this DB :" + elements.size() + " but passed elements size: " + entry.size();

			//create fileinput stream
			FileInputStream fis = new FileInputStream(xmlFile);
			//create SAXBUILDER to parse fileinput
			SAXBuilder sb = new SAXBuilder();

			// parse the xml content provided by the file input stream and create a Document object
			Document document = sb.build(fis);
			// get the root element of the document
			Element root = document.getRootElement();

			Element toAdd = new Element(name);

			for (int i = 0; i < elements.size(); i++)
				toAdd.addContent(new Element(elements.get(i)).setText(entry.get(i)));

			root.addContent(toAdd);

			document.setContent(root);

			styleDocument(document);

			return "Adding to " + xmlFile.getName();
		} catch(JDOMException | IOException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Generic method to remove from an XML file
	 *
	 * @param entry Parse of data to try and remove
	 * @return The result of this method
	 */
	public String remove(List<String> entry){
		return "Editing to "+xmlFile.getName();
	}

	/**
	 * Generic method to update an entry in an XML file
	 *
	 * @param entry Parse of data to try and update
	 * @return The result of this method
	 */
	public String update(List<String> entry){
		if(entry.size() %2 != 0){
			return "Update failed, invalid passed size of "+entry.size();
		}

		//find search entry (id)

		//go thru the entry array in twos, First part of pair = entry to update. Second part = updated info
		for(int j = 0; j < entry.size(); j+=2){
			System.out.println("Updating: "+entry.get(j)+","+entry.get(j+1));
		}

		//updated stuff needs to be added to the logfile!

		return "Updating to "+xmlFile.getName();
	}

	/**
	 * Generic Method to find an entry in an XML File
	 *
	 * @param entry Parse of data to try and find
	 * @return The result of this method
	 */
	public String find(List<String> entry){
		SAXBuilder builder = new SAXBuilder();

		try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("name");

			for (int i = 0; i < list.size(); i++) {

				Element node = (Element) list.get(i);

				System.out.println("First Name : " + node.getChildText("firstname"));
				System.out.println("Last Name : " + node.getChildText("lastname"));
				System.out.println("Nick Name : " + node.getChildText("nickname"));
				System.out.println("Salary : " + node.getChildText("salary"));

			}
		} catch(JDOMException | IOException e){
			System.out.println(e.getMessage());
		}
		return "Finding to "+xmlFile.getName();
	}

	public String attemptMethod(String type, List<String> entry){
		return null;
	}
}
