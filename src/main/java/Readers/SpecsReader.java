package Readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import model.locators;

public class SpecsReader {
	List<locators> listcred;

	public SpecsReader() throws IOException {
		 String path=  System.getProperty("user.dir");
		FileReader reader = new FileReader(
				new File(path+"\\src\\test\\resources\\specs.spec"));

		BufferedReader bReader = new BufferedReader(reader);
		String line;
		listcred = new ArrayList<locators>();
		while ((line = bReader.readLine()) != null) {
			StringTokenizer tokens = new StringTokenizer(line, "%");
			if (tokens.countTokens() == 3) {
				String elementName = tokens.nextElement().toString().trim();
				String type = tokens.nextElement().toString().trim();
				String locators = tokens.nextElement().toString().trim();
				locators obj = new locators(elementName, type, locators);
				listcred.add(obj);
			}
		}
	

	}


	public locators getObjByElement(String element) {
		locators loc = null;
		Iterator<locators> itr = listcred.iterator();
		int flag = 0;

		while (itr.hasNext()) {
			loc = itr.next();
			String a = element;
			String b = loc.getElement();

			if (a.equalsIgnoreCase(b)) {
				return loc;
			}

		}
		return loc;

	}
}
