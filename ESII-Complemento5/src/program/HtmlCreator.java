package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HtmlCreator {

	private final String queryResultsHTML = "QueryResults.html";

	

	private final String queryResultsBeginning = "<html>\n" + "<head>\n" + "<title>Projeto ES</title>\n"
			+ "</head>\n" + "<body>\n" + "	<div>\n"
			+ "		<select id=\"regiao\" name=\"regioes\" onchange=\"filterByRegiao()\">\n" + "\n"
			+ "			<option value=\"vazio\">Todas</option>\n"
			+ "			<option value=\"alentejo\">Alentejo</option>\n"
			+ "			<option value=\"centro\">Centro</option>\n"
			+ "			<option value=\"lisboa\">Lisboa</option>\n"
			+ "			<option value=\"algarve\">Algarve</option>\n"
			+ "			<option value=\"norte\">Norte</option>\n" + "		</select>\n" + "	</div>\n"
			+ "	<br>\n" + "\n" + "	<div>\n"
			+ "		<label>Numero de Infecoes: </label> <select id=\"comparacao\"\n"
			+ "			name=\"comparacao\">\n" + "			<option value=\"igual\">igual a</option>\n"
			+ "			<option value=\"diferente\">diferente de</option>\n"
			+ "			<option value=\"maior\">maior que</option>\n"
			+ "			<option value=\"menor\">menor que</option>\n"
			+ "			<option value=\"igualsup\">igual ou superior a</option>\n"
			+ "			<option value=\"igualinf\">igual ou inferior a</option>\n"
			+ "		</select> <input id=\"valorInf\" type=\"number\" min=\"0\">\n" + "	</div>\n" + "	<br>\n"
			+ "\n" + "	<div>\n" + "		<label>Numero de Internamentos: </label> <select id=\"comparacao2\"\n"
			+ "			name=\"comparacao\">\n" + "			<option value=\"igual\">igual a</option>\n"
			+ "			<option value=\"diferente\">diferente de</option>\n"
			+ "			<option value=\"maior\">maior que</option>\n"
			+ "			<option value=\"menor\">menor que</option>\n"
			+ "			<option value=\"igualsup\">igual ou superior a</option>\n"
			+ "			<option value=\"igualinf\">igual ou inferior a</option>\n"
			+ "		</select> <input id=\"valorInter\" type=\"number\" min=\"0\">\n" + "	</div>\n" + "	<br>\n"
			+ "\n" + "	<div>\n" + "		<label>Numero de Testes: </label> <select id=\"comparacao3\"\n"
			+ "			name=\"comparacao\">\n" + "			<option value=\"igual\">igual a</option>\n"
			+ "			<option value=\"diferente\">diferente de</option>\n"
			+ "			<option value=\"maior\">maior que</option>\n"
			+ "			<option value=\"menor\">menor que</option>\n"
			+ "			<option value=\"igualsup\">igual ou superior a</option>\n"
			+ "			<option value=\"igualinf\">igual ou inferior a</option>\n"
			+ "		</select> <input id=\"valorTestes\" type=\"number\" min=\"0\">\n" + "	</div>\n" + "	<br>\n"
			+ "	<label> Inverter?</label>\n" + "	<input id=\"operador3\" type=\"checkbox\">\n"
			+ "	<input id=\"applyFilter\" type=\"button\" value=\"Aplicar Filtros\"\n"
			+ "		onclick=\"aplicarFiltros()\">\n"
			+ "	<input id=\"clearFilter\" type=\"button\" value=\"Apagar Filtros\"\n"
			+ "		onclick=\"clearFilters()\">\n" + "\n"
			+ "	<table border=\"1\" bordercolor=\"#000000\" id=\"tabela\">\n" + "		<tr>\n"
			+ "			<td><b>Região</b></td>\n" + "			<td><b>Infecoes</b></td>\n"
			+ "			<td><b>Internamentos</b></td>\n" + "			<td><b>Testes</b></td>\n" + "		</tr>";
	private final String queryResultsEnd = "<div>\n" + "\n"
			+ "		<label>Calcular o total de:</label> <select id=\"criterios\">\n"
			+ "			<option value=\"inf\">Numero de Infecoes</option>\n"
			+ "			<option value=\"inter\">Numero de Internamentos</option>\n"
			+ "			<option value=\"testes\">Numero de Testes</option>\n" + "		</select>\n" + "\n"
			+ "		<div>\n"
			+ "			<label>Alentejo</label><input id=\"alentejo\" type=\"checkbox\"> <label>Centro</label><input\n"
			+ "				id=\"centro\" type=\"checkbox\"> <label>Lisboa</label><input\n"
			+ "				id=\"lisboa\" type=\"checkbox\"> <label>Algarve</label><input\n"
			+ "				id=\"algarve\" type=\"checkbox\"> <label>Norte</label><input\n"
			+ "				id=\"norte\" type=\"checkbox\"> <br> <input type=\"button\"\n"
			+ "				value=\"Calcular\" onclick=\"calcularTotal()\">\n" + "		</div>\n"
			+ "		Total:<input type=\"number\" id=\"total\" disabled>\n" + "	</div>\n" + "\n" + "\n"
			+ "\n" + "\n" + "	<script>\n" + "		var trDisplay = new Array();\n"
			+ "		var trDisplayInf = new Array();\n" + "		var trDisplayInter = new Array();\n"
			+ "		var trDisplayTestes = new Array();\n" + "\n" + "		function filterByRegiao() {\n"
			+ "			// Declare variables\n" + "			var input, filter, table, tr, td, i, txtValue;\n"
			+ "			input = document.getElementById(\"regiao\");\n"
			+ "			filter = input.value.toUpperCase();\n"
			+ "			table = document.getElementById(\"tabela\");\n"
			+ "			tr = table.getElementsByTagName(\"tr\");\n" + "			trDisplay = new Array();\n"
			+ "			console.log(\"-------------------------------------------\");\n" + "\n"
			+ "			// Loop through all table rows, and hide those who don't match the search query\n"
			+ "			for (i = 1; i < tr.length; i++) {\n"
			+ "				td = tr[i].getElementsByTagName(\"td\")[0];\n" + "				if (td) {\n" + "\n"
			+ "					txtValue = td.textContent || td.innerText;\n" + "\n"
			+ "					if (filter === \"VAZIO\" || filter == txtValue.toUpperCase()) {\n"
			+ "						console\n" + "								.log(\n"
			+ "										tr[i].getElementsByTagName(\"td\")[0].textContent,\n"
			+ "										\"válido na região\");\n"
			+ "						//filterByInfecao(filter);\n" + "\n" + "					} else {\n"
			+ "						if (!trDisplay.includes(tr[i])) {\n"
			+ "							trDisplay.push(tr[i]);\n" + "						}\n"
			+ "					}\n" + "\n" + "				}\n" + "			}\n"
			+ "			for (var i = 1; i < tr.length; i++) {\n"
			+ "				if (!trDisplay.includes(tr[i])) {\n" + "					tr[i].style.display = \"\";\n"
			+ "				} else {\n" + "					tr[i].style.display = \"none\";\n"
			+ "				}\n" + "			}\n" + "		}\n" + "		function filterByInfecao() {\n"
			+ "			var input, filter, regiaoFilter, table, tr, td, i, txtValue;\n"
			+ "			input = document.getElementById(\"valorInf\");\n"
			+ "			regiaoFilter = document.getElementById(\"regiao\").value\n"
			+ "					.toUpperCase();\n" + "			filter = input.value.toUpperCase();\n"
			+ "			table = document.getElementById(\"tabela\");\n"
			+ "			tr = table.getElementsByTagName(\"tr\");\n" + "\n"
			+ "			for (i = 1; i < tr.length; i++) {\n"
			+ "				td = tr[i].getElementsByTagName(\"td\")[1];\n"
			+ "				td0 = tr[i].getElementsByTagName(\"td\")[0];\n" + "				if (td) {\n" + "\n"
			+ "					txtValue = td.innerText;\n"
			+ "					if (regiaoFilter === \"VAZIO\") {\n"
			+ "						if (document.getElementById(\"comparacao\").value == \"igual\") {\n"
			+ "							if ((txtValue - filter == 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.splice(trDisplayInf\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.push(tr[i]);\n" + "								}\n"
			+ "							}\n"
			+ "						} else if (document.getElementById(\"comparacao\").value == \"diferente\") {\n"
			+ "							if ((txtValue - filter != 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.splice(trDisplayInf\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.push(tr[i]);\n" + "								}\n"
			+ "							}\n"
			+ "						} else if (document.getElementById(\"comparacao\").value == \"maior\") {\n"
			+ "							if ((txtValue - filter > 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.splice(trDisplayInf\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.push(tr[i]);\n" + "								}\n"
			+ "							}\n"
			+ "						} else if (document.getElementById(\"comparacao\").value == \"menor\") {\n"
			+ "							if ((txtValue - filter < 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.splice(trDisplayInf\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.push(tr[i]);\n" + "								}\n"
			+ "							}\n"
			+ "						} else if (document.getElementById(\"comparacao\").value == \"igualsup\") {\n"
			+ "							if ((txtValue - filter >= 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.splice(trDisplayInf\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.push(tr[i]);\n" + "								}\n"
			+ "							}\n"
			+ "						} else if (document.getElementById(\"comparacao\").value == \"igualinf\") {\n"
			+ "							if ((txtValue - filter <= 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.splice(trDisplayInf\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInf.includes(tr[i])) {\n"
			+ "									trDisplayInf.push(tr[i]);\n" + "								}\n"
			+ "							}\n" + "						}\n" + "\n" + "					}\n"
			+ "				}\n" + "\n" + "			}\n" + "			console.log(trDisplayInf);\n"
			+ "			loadTableItems();\n" + "		}\n" + "		function filterByInter() {\n"
			+ "			var input, filter, table, regiaoFilter, tr, td, i, txtValue;\n"
			+ "			input = document.getElementById(\"valorInter\");\n"
			+ "			regiaoFilter = document.getElementById(\"regiao\").value\n"
			+ "					.toUpperCase();\n" + "			filter = input.value.toUpperCase();\n"
			+ "			table = document.getElementById(\"tabela\");\n"
			+ "			tr = table.getElementsByTagName(\"tr\");\n" + "\n"
			+ "			for (i = 1; i < tr.length; i++) {\n"
			+ "				td = tr[i].getElementsByTagName(\"td\")[2];\n"
			+ "				td0 = tr[i].getElementsByTagName(\"td\")[0];\n"
			+ "				td1 = tr[i].getElementsByTagName(\"td\")[1];\n" + "				if (td) {\n" + "\n"
			+ "					txtValue = td.textContent || td.innerText;\n" + "\n"
			+ "					if (regiaoFilter === \"VAZIO\") {\n"
			+ "						if (document.getElementById(\"comparacao2\").value == \"igual\") {\n"
			+ "							if ((txtValue - filter == 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.splice(trDisplayInter\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao2\").value == \"diferente\") {\n"
			+ "							if ((txtValue - filter != 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.splice(trDisplayInter\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao2\").value == \"maior\") {\n"
			+ "							if ((txtValue - filter > 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.splice(trDisplayInter\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao2\").value == \"menor\") {\n"
			+ "							if ((txtValue - filter < 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.splice(trDisplayInter\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao2\").value == \"igualsup\") {\n"
			+ "							if ((txtValue - filter >= 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.splice(trDisplayInter\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao2\").value == \"igualinf\") {\n"
			+ "							if ((txtValue - filter <= 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na infecao\");\n"
			+ "								if (trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.splice(trDisplayInter\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayInter.includes(tr[i])) {\n"
			+ "									trDisplayInter.push(tr[i]);\n"
			+ "								}\n" + "							}\n" + "						}\n"
			+ "					}\n" + "				}\n" + "\n" + "			}\n"
			+ "			loadTableItems();\n" + "		}\n" + "		function filterByTestes() {\n"
			+ "			var input, filter, table, regiaoFilter, tr, td, i, txtValue;\n"
			+ "			input = document.getElementById(\"valorTestes\");\n"
			+ "			regiaoFilter = document.getElementById(\"regiao\").value\n"
			+ "					.toUpperCase();\n" + "			filter = input.value.toUpperCase();\n"
			+ "			table = document.getElementById(\"tabela\");\n"
			+ "			tr = table.getElementsByTagName(\"tr\");\n" + "\n"
			+ "			for (i = 1; i < tr.length; i++) {\n"
			+ "				td = tr[i].getElementsByTagName(\"td\")[3];\n"
			+ "				td0 = tr[i].getElementsByTagName(\"td\")[0];\n"
			+ "				td1 = tr[i].getElementsByTagName(\"td\")[1];\n"
			+ "				td2 = tr[i].getElementsByTagName(\"td\")[2];\n" + "\n" + "				if (td) {\n"
			+ "\n" + "					txtValue = td.textContent || td.innerText;\n" + "\n"
			+ "					if (regiaoFilter === \"VAZIO\") {\n"
			+ "						if (document.getElementById(\"comparacao3\").value == \"igual\") {\n"
			+ "							if ((txtValue - filter == 0) || filter == \"\") {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na teste\");\n"
			+ "								if (trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.splice(trDisplayTestes\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao3\").value == \"diferente\") {\n"
			+ "							if ((txtValue - filter != 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na teste\");\n"
			+ "								if (trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.splice(trDisplayTestes\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao3\").value == \"maior\") {\n"
			+ "							if ((txtValue - filter > 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na teste\");\n"
			+ "								if (trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.splice(trDisplayTestes\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao3\").value == \"menor\") {\n"
			+ "							if ((txtValue - filter < 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na teste\");\n"
			+ "								if (trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.splice(trDisplayTestes\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao3\").value == \"igualsup\") {\n"
			+ "							if ((txtValue - filter >= 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na teste\");\n"
			+ "								if (trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.splice(trDisplayTestes\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.push(tr[i]);\n"
			+ "								}\n" + "							}\n"
			+ "						} else if (document.getElementById(\"comparacao3\").value == \"igualinf\") {\n"
			+ "							if ((txtValue - filter <= 0 || filter == \"\")) {\n"
			+ "								console\n" + "										.log(\n"
			+ "												tr[i]\n"
			+ "														.getElementsByTagName(\"td\")[0].textContent,\n"
			+ "												\"válido na teste\");\n"
			+ "								if (trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.splice(trDisplayTestes\n"
			+ "											.indexOf(tr[i]), 1);\n"
			+ "								}\n" + "\n" + "							} else {\n"
			+ "								if (!trDisplayTestes.includes(tr[i])) {\n"
			+ "									trDisplayTestes.push(tr[i]);\n"
			+ "								}\n" + "							}\n" + "						}\n"
			+ "					}\n" + "				}\n" + "				//TODO\n" + "				/*\n"
			+ "				fazer ifs para cada caso de operadores\n" + "				\n" + "				 */\n"
			+ "\n" + "			}\n" + "			loadTableItems();\n" + "		}\n" + "\n"
			+ "		function clearFilters() {\n"
			+ "			var regiaoFilter, infecaoFilter, interFilter, testeFilter, table, tr;\n"
			+ "			table = document.getElementById(\"tabela\");\n"
			+ "			tr = table.getElementsByTagName(\"tr\");\n"
			+ "			document.getElementById(\"regiao\").selectedIndex = \"0\";\n"
			+ "			document.getElementById(\"valorInf\").value = \"\";\n"
			+ "			document.getElementById(\"valorInter\").value = \"\";\n"
			+ "			document.getElementById(\"valorTestes\").value = \"\";\n"
			+ "			trDisplay = new Array();\n" + "			for (var i = 1; i < tr.length; i++) {\n"
			+ "				if (!trDisplay.includes(tr[i])) {\n" + "					tr[i].style.display = \"\";\n"
			+ "				} else {\n" + "					tr[i].style.display = \"none\";\n"
			+ "				}\n" + "			}\n" + "		}\n" + "\n" + "		function loadTableItems() {\n"
			+ "			tr = document.getElementById(\"tabela\").getElementsByTagName(\"tr\");\n"
			+ "			for (var i = 1; i < tr.length; i++) {\n" + "\n"
			+ "				if (!trDisplayInf.includes(tr[i])\n"
			+ "						&& !trDisplayInter.includes(tr[i])\n"
			+ "						&& !trDisplayTestes.includes(tr[i])) {\n"
			+ "					if (!document.getElementById(\"operador3\").checked) {\n"
			+ "						tr[i].style.display = \"\";\n" + "					} else {\n"
			+ "						tr[i].style.display = \"none\";\n" + "					}\n"
			+ "				} else {\n"
			+ "					if (!document.getElementById(\"operador3\").checked) {\n"
			+ "						tr[i].style.display = \"none\";\n" + "					} else {\n"
			+ "						tr[i].style.display = \"\";\n" + "					}\n"
			+ "				}\n" + "			}\n" + "		}\n" + "		function aplicarFiltros() {\n"
			+ "			filterByInfecao();\n" + "			filterByInter();\n" + "			filterByTestes();\n"
			+ "		}\n" + "		\n" + "		function calcularTotal(){\n" + "			var total, nome;\n"
			+ "			total = 0;\n"
			+ "			tr = document.getElementById(\"tabela\").getElementsByTagName(\"tr\");\n" + "			\n"
			+ "			if(document.getElementById(\"criterios\").value == \"inf\"){\n"
			+ "				for (var i = 1; i < tr.length; i++) {\n"
			+ "					nome = tr[i].getElementsByTagName(\"td\")[0].textContent;\n"
			+ " 					if(document.getElementById(nome.toLowerCase()).checked == true){\n"
			+ " 						console.log(\"entrei\");\n"
			+ " 						total = total + parseInt(tr[i].getElementsByTagName(\"td\")[1].textContent);\n"
			+ " 					}\n" + " 					\n" + "				}\n"
			+ "			} else if (document.getElementById(\"criterios\").value == \"inter\"){\n"
			+ "				for (var i = 1; i < tr.length; i++) {\n"
			+ "					nome = tr[i].getElementsByTagName(\"td\")[0].textContent;\n"
			+ " 					if(document.getElementById(nome.toLowerCase()).checked == true){\n"
			+ " 						console.log(\"entrei\");\n"
			+ " 						total = total + parseInt(tr[i].getElementsByTagName(\"td\")[2].textContent);\n"
			+ " 					}\n" + "				}\n" + "			}else{\n"
			+ "				for (var i = 1; i < tr.length; i++) {\n"
			+ "					nome = tr[i].getElementsByTagName(\"td\")[0].textContent;\n"
			+ " 					if(document.getElementById(nome.toLowerCase()).checked == true){\n"
			+ " 						console.log(\"entrei\");\n"
			+ " 						total = total + parseInt(tr[i].getElementsByTagName(\"td\")[3].textContent);\n"
			+ " 					}\n" + "				}\n" + "			}\n"
			+ "			document.getElementById(\"total\").value = total;\n" + "		}\n" + "	</script>\n"
			+ "</body>\n" + "</html>\n" + "\n" + "";

	FileWriter HTMLFileWriter;
	File htmlFile;

	/**Creates an HTML file with the data from a given HashMap. It uses an already 
	 * constructed String with the HTML structure and appends a table with
	 * the data from the HashMap.
	 * 
	 * @param queryResults HashMap with query data
	 */
	public void makeHTMLFile(HashMap<String, ArrayList<String>> queryResults) {

		htmlFile = new File(queryResultsHTML);

		if (htmlFile.exists()) {
			htmlFile.delete();
		}

		try {
			HTMLFileWriter = new FileWriter(htmlFile, true);

			HTMLFileWriter.write(queryResultsBeginning);
			addLineToHTMLFile(queryResults);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Appends the data from the HashMap given to the HTML file created
	 * @param queryResults HashMap with data
	 * 
	 */
	public void addLineToHTMLFile(HashMap<String, ArrayList<String>> queryResults) {

		try {
			for (String key : queryResults.keySet()) {
				HTMLFileWriter.write("<tr><td>" + key + "</td><td>" + queryResults.get(key).get(0) + "</td><td>"
						+ queryResults.get(key).get(1) + "</td><td>" + queryResults.get(key).get(2) + "</td></tr>\n");
			}
			finishHTMLFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**Appends the rest of the HTML structure to the file
	 * 
	 */
	private void finishHTMLFile() {
		try {
			HTMLFileWriter.write("</table>" + queryResultsEnd);
			HTMLFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**Prints to the console the content in the HTML file
	 * in order to be properly read in WordPress
	 * 
	 */
	public void printHTML() {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(htmlFile));
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**Getter for the HTML FileWriter
	 * @return FileWriter for the class
	 */
	public FileWriter getHTMLFileWriter() {
		return HTMLFileWriter;
	}

	/**Sets the class FileWriter to the given FileWriter
	 * @param hTMLFileWriter FileWriter to be set as class FileWriter
	 */
	public void setHTMLFileWriter(FileWriter hTMLFileWriter) {
		HTMLFileWriter = hTMLFileWriter;
	}

	/** Returns the HTML file used in the Class
	 * @return the HTML file
	 */
	public File getHtmlFile() {
		return htmlFile;
	}

	/** Sets the HTML File of the classe
	 * @param htmlFile HTML file to be set as the class HTML File
	 */
	public void setHtmlFile(File htmlFile) {
		this.htmlFile = htmlFile;
	}
}
