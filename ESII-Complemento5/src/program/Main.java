package program;

import java.util.ArrayList;
import java.util.HashMap;



public class Main {


	public static void main(String[] args) {
		System.out.println("Content-type: text/html\n\n");
		Program p = new Program();
		p.cloneGitRepo();
		HtmlCreator creator = new HtmlCreator();
		HashMap<String, ArrayList<String>> queryResults = p.queryRDF();
		creator.makeHTMLFile(queryResults);
		creator.printHTML();

	}

	
}
