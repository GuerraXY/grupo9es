package program;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Program {

	HashMap<String, ArrayList<String>> tableRowsHashMap;

	
	/**
	 * Clones the repository given by the teacher
	 * The repository is as follows
	 * https://github.com/vbasto-iscte/ESII1920
	 * 
	 * 
	 * 
	 */
	public void cloneGitRepo() {
		File repoLocal = new File("repositorio");
		if (repoLocal.exists())
			try {
				delete(repoLocal);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		try {
			Git.cloneRepository().setURI("https://github.com/vbasto-iscte/ESII1920")
					.setDirectory(repoLocal).call();
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a the files in a given directory
	 * 
	 * @param file directory to delete files from
	 * @throws IOException if the file is null
	 */
	public void delete(File file) throws IOException {

		if (file.isDirectory()) {

			if (file.list().length == 0) {
				file.delete();
			} else {
				String files[] = file.list();

				for (String temp : files) {
					File fileDelete = new File(file, temp);
					delete(fileDelete);
				}

				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			file.delete();
		}
	}

	/**returns a HashMap with the data from the query made to the file in
	 * a repository. This HashMap uses Regions as keys and ArrayLists of Strings
	 * as values that are received from the query.
	 * @return HashMap with query data
	 */
	public HashMap<String, ArrayList<String>> queryRDF() {
		tableRowsHashMap = new HashMap<String, ArrayList<String>>();

		try {
			File inputFile = new File("repositorio/covid19spreading.rdf");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			String query = "/RDF/NamedIndividual/@*";
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			XPathExpression expr = xpath.compile(query);
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nl.getLength(); i++) {
				ArrayList<String> valores = new ArrayList<String>();

				query = "//*[contains(@about,'" + StringUtils.substringAfter(nl.item(i).getNodeValue(), "#")
						+ "')]/Testes/text()";
				expr = xpath.compile(query);
				valores.add((String) expr.evaluate(doc, XPathConstants.STRING));

				query = "//*[contains(@about," + StringUtils.substringAfter(nl.item(i).getNodeValue(), "#")
						+ ")]/Infecoes/text()";
				expr = xpath.compile(query);
				valores.add((String) expr.evaluate(doc, XPathConstants.STRING));

				query = "//*[contains(@about," + StringUtils.substringAfter(nl.item(i).getNodeValue(), "#")
						+ ")]/Internamentos/text()";
				expr = xpath.compile(query);
				
				valores.add((String) expr.evaluate(doc, XPathConstants.STRING));
				tableRowsHashMap.put(StringUtils.substringAfter(nl.item(i).getNodeValue(), "#"), valores);

			}
			validateHashMap(tableRowsHashMap);
			return tableRowsHashMap;

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**Validates a given HashMap so the values of the ArrayLists
	 * that come empty take the value of 0
	 * @param map HashMap to validate
	 */
	public void validateHashMap(HashMap<String, ArrayList<String>> map) {
		for (Map.Entry<String, ArrayList<String>> e : map.entrySet()) {
			
			if (e.getValue().get(0).equals("")) {
				e.getValue().set(0, "0");
				
			}  
			if (e.getValue().get(1).equals("")) {
				e.getValue().set(1, "0");
			}  
			if (e.getValue().get(2).equals("")) {
				e.getValue().set(2, "0");
			}
		}
	}

}
