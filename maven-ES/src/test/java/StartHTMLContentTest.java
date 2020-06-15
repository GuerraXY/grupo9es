import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.Assert;

public class StartHTMLContentTest {

	@Test
	public void test() {
		
		HTML html = new HTML();
		html.startHTMLcontent();
		html.closeHTMLContent();
		
		String conteudoNoHTML = html.conteudoHTML;
		
		String conteudoQueDeveriaEstarNoHTML = "<html>\r\n" + "<head>\r\n" + "<title>\r\n" + "Complemento 4 \r\n" + "</title>\r\n" + "<style>" +
				html.css + 
				"</style>\r\n" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<table class=\"blueTable\">\r\n" + 
				"<thead>\r\n" + 
				"	<tr>\r\n" + 
				"		<th width=\"100px\"><b>File Timestamp</b></th>\r\n" + 
				"		<th><b>File Name</b></th>\r\n" + 
				"		<th><b>File Tag</b></th>\r\n" + 
				"		<th><b>Tag Description</b></th>\r\n" + 
				"		<th><b>Spread Visualization Link</b></th>\r\n" + 
				"	</tr>\r\n" + 
				"</thead>\r\n" + 
				"<tbody>" + 
				"</tbody>\n</table>\n</body>\n</html>";
		
		assertEquals(conteudoQueDeveriaEstarNoHTML.trim(), conteudoNoHTML.trim());
	}

}
