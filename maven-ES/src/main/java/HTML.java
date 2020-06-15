import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Classe que vai ter todos os metodos relacionados com o HTML a ser gerado
 * @author Pedro Guerra
 *
 */
public class HTML {
	
	/**
	 * Conteudo CSS usado no HTML
	 */
	static final String css = "table.blueTable {\r\n" + 
			"  border: 1px solid #1C6EA4;\r\n" + 
			"  font: icon\n" + 
			"  background-color: #EEEEEE;\r\n" + 
			"  width: 100%;\r\n" + 
			"  text-align: left;\r\n" + 
			"  border-collapse: collapse;\r\n" + 
			"}\r\n" + 
			"table.blueTable td, table.blueTable th {\r\n" + 
			"  border: 1px solid #AAAAAA;\r\n" + 
			"  padding: 3px 2px;\r\n" + 
			"}\r\n" + 
			"table.blueTable tbody td {\r\n" + 
			"  font-size: 13px;\r\n" + 
			"    padding-left: 5px;\r\n" + 
			"    padding-top: 20px;\r\n" + 
			"    padding-bottom: 20px;\r\n" + 
			"}\r\n" + 
			"table.blueTable tr:nth-child(even) {\r\n" + 
			"  background: #D0E4F5;\r\n" + 
			"}\r\n" + 
			"table.blueTable thead {\r\n" + 
			"  background: #1C6EA4;\r\n" + 
			"  background: -moz-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);\r\n" + 
			"  background: -webkit-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);\r\n" + 
			"  background: linear-gradient(to bottom, #5592bb 0%, #327cad 66%, #1C6EA4 100%);\r\n" + 
			"  border-bottom: 2px solid #444444;\r\n" + 
			"  \r\n" + 
			"}\r\n" + 
			"table.blueTable thead th {\r\n" + 
			"  font-size: 15px;\r\n" + 
			"    font-weight: bold;\r\n" + 
			"    color: #FFFFFF;\r\n" + 
			"    border-left: 2px solid #D0E4F5;\r\n" + 
			"    padding-top: 15px;\r\n" + 
			"    padding-bottom: 15px;\r\n" + 
			"    padding-left: 5px;\r\n" + 
			"}\r\n" + 
			"table.blueTable thead th:first-child {\r\n" + 
			"  border-left: none;\r\n" + 
			"}\r\n" + 
			"\r\n";
	
	/**
	 * Conteudo HTML necessario para mostrar a tabela com a informacao sobre COVID-19
	 */
	public static String conteudoHTML;

	/**
	 * Escreve conteudo para mostrar as colunas das tabelas na String do HTML 
	 */
	public static void startHTMLcontent() {
		
		conteudoHTML = "";
		System.out.println("Content-type: text/html\n\n");
		conteudoHTML = conteudoHTML + "<html>\r\n" + "<head>\r\n" + "<title>\r\n" + "Complemento 4 \r\n" + "</title>\r\n" + "<style>" +
		css + 
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
		"<tbody>";
		
	}
	
	/**
	 * Insere uma linha na tabela HTML com a informacao de uma tag
	 * @param fileTimestamp Data em que a tag foi gerada
	 * @param fileName Nome do ficheiro da tag
	 * @param fileTag Nome da tag
	 * @param tagDescription Descricao da tag
	 * @param spreadVisualizationLink Link que vai mostrar a informacao da tag visualmente 
	 */
	public static void addLineToHTMLContent(String fileTimestamp, String fileName, String fileTag, String tagDescription, String spreadVisualizationLink) {
		
		if (conteudoHTML.equals("")) {
			
			startHTMLcontent();
			
		}
		
		conteudoHTML = conteudoHTML + "<tr><td>" + fileTimestamp 
				+ "</td><td>" + fileName 
				+ "</td><td>" + fileTag 
				+ "</td><td>" + tagDescription 
				+ "</td><td><a href=\"" + spreadVisualizationLink + "\">Link</a>"
				+ "</td></tr>\n";
		
	}
	
	/**
	 * Termina as tags do conteudo HTML
	 */
	public static void closeHTMLContent() {
		
		if (!conteudoHTML.equals(null)) {
			conteudoHTML = conteudoHTML + "</tbody>\n</table>\n</body>\n</html>";
		}
			
	}
	
	/**
	 * Mostra o ficheiro HTML no browser, escrevendo o conteudo deste, sem caracteres invalidos, na consola
	 */
	public static void showHTMLContent(){
		
		System.out.println(substituirCaracteresInvalidos(conteudoHTML));
		
	}
	
	/**
	 * Substitui os caracteres que nao sao bem interpretados pelo HTML
	 * @param frase frase com os caracteres que devem ser substituidos
	 * @return frase com caracteres validos
	 */
	public static String substituirCaracteresInvalidos(String frase) {
		
		frase = frase.replaceAll("ç", "c");
		frase = frase.replaceAll("ã", "a");
		frase = frase.replaceAll("á", "a");
		frase = frase.replaceAll("ó", "o");
		frase = frase.replaceAll("à", "a");
		frase = frase.replaceAll("é", "e");
		frase = frase.replaceAll("ê", "e");
		frase = frase.replaceAll("õ", "o");
		
		return frase;
	}
	
	
	
}
