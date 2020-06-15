import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.Assert;

public class AddLineToHTMLContentTest {

	@Test
	public void test() {

			String data = "Tue May 26 12:52:32 BST 2020";
			String fileName = "testeFile"; 
			String fileTag = "NovoTeste";
			String tagDescription = "A fazer um novo teste";
			String spreadVisualizationLink = "http://visualdataweb.de/webvowl/teste";
			
			HTML html = new HTML();
			html.startHTMLcontent(); 
			html.addLineToHTMLContent(data, fileName, fileTag, tagDescription, spreadVisualizationLink);
			html.closeHTMLContent();
			
			String conteudoQueEstaNoHTML = html.conteudoHTML;
			
			String conteudoQueDeveriaEstarNoHTML = "<tr><td>" + data 
					+ "</td><td>" + fileName 
					+ "</td><td>" + fileTag 
					+ "</td><td>" + tagDescription 
					+ "</td><td><a href=\"" + spreadVisualizationLink + "\">Link</a>"
					+ "</td></tr>\n";
			
			assertTrue(conteudoQueEstaNoHTML.trim().contains(conteudoQueDeveriaEstarNoHTML.trim()));
		
	}

}
