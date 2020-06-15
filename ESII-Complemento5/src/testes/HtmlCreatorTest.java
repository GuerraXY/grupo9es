package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import program.HtmlCreator;

class HtmlCreatorTest {
	
	File htmlFile;
	FileWriter fw ;
	HtmlCreator creator = new HtmlCreator();

	@Test
	void makeHTMLFileTest() throws IOException {
		
		htmlFile  = new File("teste.html");
		if (htmlFile.exists()) {
			htmlFile.delete();
		}
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> alentejo = new ArrayList<String>();
		ArrayList<String> centro = new ArrayList<String>();
		ArrayList<String> lisboa = new ArrayList<String>();
		ArrayList<String> algarve = new ArrayList<String>();
		ArrayList<String> norte = new ArrayList<String>();
		alentejo.add("50");
		alentejo.add("50");
		alentejo.add("50");
		centro.add("0");
		centro.add("50");
		centro.add("50");
		lisboa.add("0");
		lisboa.add("50");
		lisboa.add("50");
		algarve.add("50");
		algarve.add("50");
		algarve.add("50");
		norte.add("0");
		norte.add("50");
		norte.add("50");
		map.put("Alentejo", alentejo);
		map.put("Centro", centro);
		map.put("Lisboa", lisboa);
		map.put("Algarve", algarve);
		map.put("Norte", norte);
		creator.makeHTMLFile(map);
		String fileContent = "<html>\n" + 
				"<head>\n" + 
				"<title>Projeto ES</title>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"	<div>\n" + 
				"		<select id=\"regiao\" name=\"regioes\" onchange=\"filterByRegiao()\">\n" + 
				"\n" + 
				"			<option value=\"vazio\">Todas</option>\n" + 
				"			<option value=\"alentejo\">Alentejo</option>\n" + 
				"			<option value=\"centro\">Centro</option>\n" + 
				"			<option value=\"lisboa\">Lisboa</option>\n" + 
				"			<option value=\"algarve\">Algarve</option>\n" + 
				"			<option value=\"norte\">Norte</option>\n" + 
				"		</select>\n" + 
				"	</div>\n" + 
				"	<br>\n" + 
				"\n" + 
				"	<div>\n" + 
				"		<label>Numero de Infecoes: </label> <select id=\"comparacao\"\n" + 
				"			name=\"comparacao\">\n" + 
				"			<option value=\"igual\">igual a</option>\n" + 
				"			<option value=\"diferente\">diferente de</option>\n" + 
				"			<option value=\"maior\">maior que</option>\n" + 
				"			<option value=\"menor\">menor que</option>\n" + 
				"			<option value=\"igualsup\">igual ou superior a</option>\n" + 
				"			<option value=\"igualinf\">igual ou inferior a</option>\n" + 
				"		</select> <input id=\"valorInf\" type=\"number\" min=\"0\">\n" + 
				"	</div>\n" + 
				"	<br>\n" + 
				"\n" + 
				"	<div>\n" + 
				"		<label>Numero de Internamentos: </label> <select id=\"comparacao2\"\n" + 
				"			name=\"comparacao\">\n" + 
				"			<option value=\"igual\">igual a</option>\n" + 
				"			<option value=\"diferente\">diferente de</option>\n" + 
				"			<option value=\"maior\">maior que</option>\n" + 
				"			<option value=\"menor\">menor que</option>\n" + 
				"			<option value=\"igualsup\">igual ou superior a</option>\n" + 
				"			<option value=\"igualinf\">igual ou inferior a</option>\n" + 
				"		</select> <input id=\"valorInter\" type=\"number\" min=\"0\">\n" + 
				"	</div>\n" + 
				"	<br>\n" + 
				"\n" + 
				"	<div>\n" + 
				"		<label>Numero de Testes: </label> <select id=\"comparacao3\"\n" + 
				"			name=\"comparacao\">\n" + 
				"			<option value=\"igual\">igual a</option>\n" + 
				"			<option value=\"diferente\">diferente de</option>\n" + 
				"			<option value=\"maior\">maior que</option>\n" + 
				"			<option value=\"menor\">menor que</option>\n" + 
				"			<option value=\"igualsup\">igual ou superior a</option>\n" + 
				"			<option value=\"igualinf\">igual ou inferior a</option>\n" + 
				"		</select> <input id=\"valorTestes\" type=\"number\" min=\"0\">\n" + 
				"	</div>\n" + 
				"	<br>\n" + 
				"	<label> Inverter?</label>\n" + 
				"	<input id=\"operador3\" type=\"checkbox\">\n" + 
				"	<input id=\"applyFilter\" type=\"button\" value=\"Aplicar Filtros\"\n" + 
				"		onclick=\"aplicarFiltros()\">\n" + 
				"	<input id=\"clearFilter\" type=\"button\" value=\"Apagar Filtros\"\n" + 
				"		onclick=\"clearFilters()\">\n" + 
				"\n" + 
				"	<table border=\"1\" bordercolor=\"#000000\" id=\"tabela\">\n" + 
				"		<tr>\n" + 
				"			<td><b>Região</b></td>\n" + 
				"			<td><b>Infecoes</b></td>\n" + 
				"			<td><b>Internamentos</b></td>\n" + 
				"			<td><b>Testes</b></td>\n" + 
				"		</tr><tr><td>Alentejo</td><td>50</td><td>50</td><td>50</td></tr>\n" + 
				"<tr><td>Centro</td><td>0</td><td>50</td><td>50</td></tr>\n" + 
				"<tr><td>Lisboa</td><td>0</td><td>50</td><td>50</td></tr>\n" + 
				"<tr><td>Algarve</td><td>50</td><td>50</td><td>50</td></tr>\n" + 
				"<tr><td>Norte</td><td>0</td><td>50</td><td>50</td></tr>\n" + 
				"</table><div>\n" + 
				"\n" + 
				"		<label>Calcular o total de:</label> <select id=\"criterios\">\n" + 
				"			<option value=\"inf\">Numero de Infecoes</option>\n" + 
				"			<option value=\"inter\">Numero de Internamentos</option>\n" + 
				"			<option value=\"testes\">Numero de Testes</option>\n" + 
				"		</select>\n" + 
				"\n" + 
				"		<div>\n" + 
				"			<label>Alentejo</label><input id=\"alentejo\" type=\"checkbox\"> <label>Centro</label><input\n" + 
				"				id=\"centro\" type=\"checkbox\"> <label>Lisboa</label><input\n" + 
				"				id=\"lisboa\" type=\"checkbox\"> <label>Algarve</label><input\n" + 
				"				id=\"algarve\" type=\"checkbox\"> <label>Norte</label><input\n" + 
				"				id=\"norte\" type=\"checkbox\"> <br> <input type=\"button\"\n" + 
				"				value=\"Calcular\" onclick=\"calcularTotal()\">\n" + 
				"		</div>\n" + 
				"		Total:<input type=\"number\" id=\"total\" disabled>\n" + 
				"	</div>\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"	<script>\n" + 
				"		var trDisplay = new Array();\n" + 
				"		var trDisplayInf = new Array();\n" + 
				"		var trDisplayInter = new Array();\n" + 
				"		var trDisplayTestes = new Array();\n" + 
				"\n" + 
				"		function filterByRegiao() {\n" + 
				"			// Declare variables\n" + 
				"			var input, filter, table, tr, td, i, txtValue;\n" + 
				"			input = document.getElementById(\"regiao\");\n" + 
				"			filter = input.value.toUpperCase();\n" + 
				"			table = document.getElementById(\"tabela\");\n" + 
				"			tr = table.getElementsByTagName(\"tr\");\n" + 
				"			trDisplay = new Array();\n" + 
				"			console.log(\"-------------------------------------------\");\n" + 
				"\n" + 
				"			// Loop through all table rows, and hide those who don't match the search query\n" + 
				"			for (i = 1; i < tr.length; i++) {\n" + 
				"				td = tr[i].getElementsByTagName(\"td\")[0];\n" + 
				"				if (td) {\n" + 
				"\n" + 
				"					txtValue = td.textContent || td.innerText;\n" + 
				"\n" + 
				"					if (filter === \"VAZIO\" || filter == txtValue.toUpperCase()) {\n" + 
				"						console\n" + 
				"								.log(\n" + 
				"										tr[i].getElementsByTagName(\"td\")[0].textContent,\n" + 
				"										\"válido na região\");\n" + 
				"						//filterByInfecao(filter);\n" + 
				"\n" + 
				"					} else {\n" + 
				"						if (!trDisplay.includes(tr[i])) {\n" + 
				"							trDisplay.push(tr[i]);\n" + 
				"						}\n" + 
				"					}\n" + 
				"\n" + 
				"				}\n" + 
				"			}\n" + 
				"			for (var i = 1; i < tr.length; i++) {\n" + 
				"				if (!trDisplay.includes(tr[i])) {\n" + 
				"					tr[i].style.display = \"\";\n" + 
				"				} else {\n" + 
				"					tr[i].style.display = \"none\";\n" + 
				"				}\n" + 
				"			}\n" + 
				"		}\n" + 
				"		function filterByInfecao() {\n" + 
				"			var input, filter, regiaoFilter, table, tr, td, i, txtValue;\n" + 
				"			input = document.getElementById(\"valorInf\");\n" + 
				"			regiaoFilter = document.getElementById(\"regiao\").value\n" + 
				"					.toUpperCase();\n" + 
				"			filter = input.value.toUpperCase();\n" + 
				"			table = document.getElementById(\"tabela\");\n" + 
				"			tr = table.getElementsByTagName(\"tr\");\n" + 
				"\n" + 
				"			for (i = 1; i < tr.length; i++) {\n" + 
				"				td = tr[i].getElementsByTagName(\"td\")[1];\n" + 
				"				td0 = tr[i].getElementsByTagName(\"td\")[0];\n" + 
				"				if (td) {\n" + 
				"\n" + 
				"					txtValue = td.innerText;\n" + 
				"					if (regiaoFilter === \"VAZIO\") {\n" + 
				"						if (document.getElementById(\"comparacao\").value == \"igual\") {\n" + 
				"							if ((txtValue - filter == 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.splice(trDisplayInf\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao\").value == \"diferente\") {\n" + 
				"							if ((txtValue - filter != 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.splice(trDisplayInf\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao\").value == \"maior\") {\n" + 
				"							if ((txtValue - filter > 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.splice(trDisplayInf\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao\").value == \"menor\") {\n" + 
				"							if ((txtValue - filter < 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.splice(trDisplayInf\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao\").value == \"igualsup\") {\n" + 
				"							if ((txtValue - filter >= 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.splice(trDisplayInf\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao\").value == \"igualinf\") {\n" + 
				"							if ((txtValue - filter <= 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.splice(trDisplayInf\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInf.includes(tr[i])) {\n" + 
				"									trDisplayInf.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						}\n" + 
				"\n" + 
				"					}\n" + 
				"				}\n" + 
				"\n" + 
				"			}\n" + 
				"			console.log(trDisplayInf);\n" + 
				"			loadTableItems();\n" + 
				"		}\n" + 
				"		function filterByInter() {\n" + 
				"			var input, filter, table, regiaoFilter, tr, td, i, txtValue;\n" + 
				"			input = document.getElementById(\"valorInter\");\n" + 
				"			regiaoFilter = document.getElementById(\"regiao\").value\n" + 
				"					.toUpperCase();\n" + 
				"			filter = input.value.toUpperCase();\n" + 
				"			table = document.getElementById(\"tabela\");\n" + 
				"			tr = table.getElementsByTagName(\"tr\");\n" + 
				"\n" + 
				"			for (i = 1; i < tr.length; i++) {\n" + 
				"				td = tr[i].getElementsByTagName(\"td\")[2];\n" + 
				"				td0 = tr[i].getElementsByTagName(\"td\")[0];\n" + 
				"				td1 = tr[i].getElementsByTagName(\"td\")[1];\n" + 
				"				if (td) {\n" + 
				"\n" + 
				"					txtValue = td.textContent || td.innerText;\n" + 
				"\n" + 
				"					if (regiaoFilter === \"VAZIO\") {\n" + 
				"						if (document.getElementById(\"comparacao2\").value == \"igual\") {\n" + 
				"							if ((txtValue - filter == 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.splice(trDisplayInter\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao2\").value == \"diferente\") {\n" + 
				"							if ((txtValue - filter != 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.splice(trDisplayInter\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao2\").value == \"maior\") {\n" + 
				"							if ((txtValue - filter > 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.splice(trDisplayInter\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao2\").value == \"menor\") {\n" + 
				"							if ((txtValue - filter < 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.splice(trDisplayInter\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao2\").value == \"igualsup\") {\n" + 
				"							if ((txtValue - filter >= 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.splice(trDisplayInter\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao2\").value == \"igualinf\") {\n" + 
				"							if ((txtValue - filter <= 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na infecao\");\n" + 
				"								if (trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.splice(trDisplayInter\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayInter.includes(tr[i])) {\n" + 
				"									trDisplayInter.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						}\n" + 
				"					}\n" + 
				"				}\n" + 
				"\n" + 
				"			}\n" + 
				"			loadTableItems();\n" + 
				"		}\n" + 
				"		function filterByTestes() {\n" + 
				"			var input, filter, table, regiaoFilter, tr, td, i, txtValue;\n" + 
				"			input = document.getElementById(\"valorTestes\");\n" + 
				"			regiaoFilter = document.getElementById(\"regiao\").value\n" + 
				"					.toUpperCase();\n" + 
				"			filter = input.value.toUpperCase();\n" + 
				"			table = document.getElementById(\"tabela\");\n" + 
				"			tr = table.getElementsByTagName(\"tr\");\n" + 
				"\n" + 
				"			for (i = 1; i < tr.length; i++) {\n" + 
				"				td = tr[i].getElementsByTagName(\"td\")[3];\n" + 
				"				td0 = tr[i].getElementsByTagName(\"td\")[0];\n" + 
				"				td1 = tr[i].getElementsByTagName(\"td\")[1];\n" + 
				"				td2 = tr[i].getElementsByTagName(\"td\")[2];\n" + 
				"\n" + 
				"				if (td) {\n" + 
				"\n" + 
				"					txtValue = td.textContent || td.innerText;\n" + 
				"\n" + 
				"					if (regiaoFilter === \"VAZIO\") {\n" + 
				"						if (document.getElementById(\"comparacao3\").value == \"igual\") {\n" + 
				"							if ((txtValue - filter == 0) || filter == \"\") {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na teste\");\n" + 
				"								if (trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.splice(trDisplayTestes\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao3\").value == \"diferente\") {\n" + 
				"							if ((txtValue - filter != 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na teste\");\n" + 
				"								if (trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.splice(trDisplayTestes\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao3\").value == \"maior\") {\n" + 
				"							if ((txtValue - filter > 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na teste\");\n" + 
				"								if (trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.splice(trDisplayTestes\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao3\").value == \"menor\") {\n" + 
				"							if ((txtValue - filter < 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na teste\");\n" + 
				"								if (trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.splice(trDisplayTestes\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao3\").value == \"igualsup\") {\n" + 
				"							if ((txtValue - filter >= 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na teste\");\n" + 
				"								if (trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.splice(trDisplayTestes\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						} else if (document.getElementById(\"comparacao3\").value == \"igualinf\") {\n" + 
				"							if ((txtValue - filter <= 0 || filter == \"\")) {\n" + 
				"								console\n" + 
				"										.log(\n" + 
				"												tr[i]\n" + 
				"														.getElementsByTagName(\"td\")[0].textContent,\n" + 
				"												\"válido na teste\");\n" + 
				"								if (trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.splice(trDisplayTestes\n" + 
				"											.indexOf(tr[i]), 1);\n" + 
				"								}\n" + 
				"\n" + 
				"							} else {\n" + 
				"								if (!trDisplayTestes.includes(tr[i])) {\n" + 
				"									trDisplayTestes.push(tr[i]);\n" + 
				"								}\n" + 
				"							}\n" + 
				"						}\n" + 
				"					}\n" + 
				"				}\n" + 
				"				//TODO\n" + 
				"				/*\n" + 
				"				fazer ifs para cada caso de operadores\n" + 
				"				\n" + 
				"				 */\n" + 
				"\n" + 
				"			}\n" + 
				"			loadTableItems();\n" + 
				"		}\n" + 
				"\n" + 
				"		function clearFilters() {\n" + 
				"			var regiaoFilter, infecaoFilter, interFilter, testeFilter, table, tr;\n" + 
				"			table = document.getElementById(\"tabela\");\n" + 
				"			tr = table.getElementsByTagName(\"tr\");\n" + 
				"			document.getElementById(\"regiao\").selectedIndex = \"0\";\n" + 
				"			document.getElementById(\"valorInf\").value = \"\";\n" + 
				"			document.getElementById(\"valorInter\").value = \"\";\n" + 
				"			document.getElementById(\"valorTestes\").value = \"\";\n" + 
				"			trDisplay = new Array();\n" + 
				"			for (var i = 1; i < tr.length; i++) {\n" + 
				"				if (!trDisplay.includes(tr[i])) {\n" + 
				"					tr[i].style.display = \"\";\n" + 
				"				} else {\n" + 
				"					tr[i].style.display = \"none\";\n" + 
				"				}\n" + 
				"			}\n" + 
				"		}\n" + 
				"\n" + 
				"		function loadTableItems() {\n" + 
				"			tr = document.getElementById(\"tabela\").getElementsByTagName(\"tr\");\n" + 
				"			for (var i = 1; i < tr.length; i++) {\n" + 
				"\n" + 
				"				if (!trDisplayInf.includes(tr[i])\n" + 
				"						&& !trDisplayInter.includes(tr[i])\n" + 
				"						&& !trDisplayTestes.includes(tr[i])) {\n" + 
				"					if (!document.getElementById(\"operador3\").checked) {\n" + 
				"						tr[i].style.display = \"\";\n" + 
				"					} else {\n" + 
				"						tr[i].style.display = \"none\";\n" + 
				"					}\n" + 
				"				} else {\n" + 
				"					if (!document.getElementById(\"operador3\").checked) {\n" + 
				"						tr[i].style.display = \"none\";\n" + 
				"					} else {\n" + 
				"						tr[i].style.display = \"\";\n" + 
				"					}\n" + 
				"				}\n" + 
				"			}\n" + 
				"		}\n" + 
				"		function aplicarFiltros() {\n" + 
				"			filterByInfecao();\n" + 
				"			filterByInter();\n" + 
				"			filterByTestes();\n" + 
				"		}\n" + 
				"		\n" + 
				"		function calcularTotal(){\n" + 
				"			var total, nome;\n" + 
				"			total = 0;\n" + 
				"			tr = document.getElementById(\"tabela\").getElementsByTagName(\"tr\");\n" + 
				"			\n" + 
				"			if(document.getElementById(\"criterios\").value == \"inf\"){\n" + 
				"				for (var i = 1; i < tr.length; i++) {\n" + 
				"					nome = tr[i].getElementsByTagName(\"td\")[0].textContent;\n" + 
				" 					if(document.getElementById(nome.toLowerCase()).checked == true){\n" + 
				" 						console.log(\"entrei\");\n" + 
				" 						total = total + parseInt(tr[i].getElementsByTagName(\"td\")[1].textContent);\n" + 
				" 					}\n" + 
				" 					\n" + 
				"				}\n" + 
				"			} else if (document.getElementById(\"criterios\").value == \"inter\"){\n" + 
				"				for (var i = 1; i < tr.length; i++) {\n" + 
				"					nome = tr[i].getElementsByTagName(\"td\")[0].textContent;\n" + 
				" 					if(document.getElementById(nome.toLowerCase()).checked == true){\n" + 
				" 						console.log(\"entrei\");\n" + 
				" 						total = total + parseInt(tr[i].getElementsByTagName(\"td\")[2].textContent);\n" + 
				" 					}\n" + 
				"				}\n" + 
				"			}else{\n" + 
				"				for (var i = 1; i < tr.length; i++) {\n" + 
				"					nome = tr[i].getElementsByTagName(\"td\")[0].textContent;\n" + 
				" 					if(document.getElementById(nome.toLowerCase()).checked == true){\n" + 
				" 						console.log(\"entrei\");\n" + 
				" 						total = total + parseInt(tr[i].getElementsByTagName(\"td\")[3].textContent);\n" + 
				" 					}\n" + 
				"				}\n" + 
				"			}\n" + 
				"			document.getElementById(\"total\").value = total;\n" + 
				"		}\n" + 
				"	</script>\n" + 
				"</body>\n" + 
				"</html>\n" + 
				"\n" + 
				"";
		String actualFileContent = "";
		StringBuilder sb = new StringBuilder();
		String currentLine;
		BufferedReader br = new BufferedReader(new FileReader("teste.html"));
		while((currentLine = br.readLine()) != null) {
			sb.append(currentLine).append("\n");
		}
		actualFileContent = sb.toString();
		
		assertEquals(fileContent,actualFileContent);
		
		
	}
	
	
	@Test
	void getHTMLFileWriterTest() throws IOException {
		 htmlFile = new File("teste.html");
		 if (htmlFile.exists()) {
				htmlFile.delete();
			}
		fw= new FileWriter(htmlFile, true);
		creator.setHTMLFileWriter(fw);
		FileWriter fw2 = creator.getHTMLFileWriter();
		assertEquals(fw,fw2);
	}
	
	@Test
	void getHtmlFile() {
		htmlFile = new File("teste.html");
		 if (htmlFile.exists()) {
				htmlFile.delete();
			}
		creator.setHtmlFile(htmlFile);
		File htmlFile2 = creator.getHtmlFile();
		assertEquals(htmlFile,htmlFile2);
	}
	
}
