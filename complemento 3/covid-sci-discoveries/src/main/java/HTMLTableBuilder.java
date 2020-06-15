import java.io.File;
import java.util.List;

public class HTMLTableBuilder {
	private int columns;
	private final StringBuilder table = new StringBuilder();
	public static String HTML_START = "<html>";
	public static String HTML_END = "</html>";
	public static String TABLE_START_BORDER = "<table border=\"1\">";
	public static String TABLE_START = "<table>";
	public static String TABLE_END = "</table>";
	public static String HEADER_START = "<th>";
	public static String HEADER_END = "</th>";
	public static String ROW_START = "<tr>";
	public static String ROW_END = "</tr>";
	public static String COLUMN_START = "<td>";
	public static String COLUMN_END = "</td>";

	/**
	 * @param header
	 * @param border
	 * @param rows
	 * @param columns
	 */
	public HTMLTableBuilder(String header, boolean border, int rows, int columns) {
		this.columns = columns;
		if (header != null) {
			table.append("<b>");
			table.append(header);
			table.append("</b>");
		}
		table.append(HTML_START);
		table.append(border ? TABLE_START_BORDER : TABLE_START);
		table.append(TABLE_END);
		table.append(HTML_END);
	}

	/**
	 * @param values
	 */
	public void addTableHeader(String... values) {
		if (values.length != columns) {
			System.out.println("Error column lenth");
		} else {
			int lastIndex = table.lastIndexOf(TABLE_END);
			if (lastIndex > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append(ROW_START);
				for (String value : values) {
					sb.append(HEADER_START);
					sb.append(value);
					sb.append(HEADER_END);
				}
				sb.append(ROW_END);
				table.insert(lastIndex, sb.toString());
			}
		}
	}

	/**
	 * @param values
	 */
	public void addRowValues(String... values) {
		if (values.length != columns) {
			System.out.println("Error column lenth");
		} else {
			int lastIndex = table.lastIndexOf(ROW_END);
			if (lastIndex > 0) {
				int index = lastIndex + ROW_END.length();
				StringBuilder sb = new StringBuilder();
				sb.append(ROW_START);
				for (String value : values) {
					sb.append(COLUMN_START);
					sb.append(value);
					sb.append(COLUMN_END);
				}
				sb.append(ROW_END);
				table.insert(index, sb.toString());
			}
		}
	}

	/**
	 * @return
	 */
	public String build() {
		return table.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Content-type: text/html\n\n");
		App a = new App();
		a.ListFile();
		try {
			List<String> title = a.extractTitle();
			List<String> journal = a.extractJournal();
			List<String> date = a.extractData();
			List<List<String>> authors = a.extractAuthors();
			List<File> files = a.ListFile();
			HTMLTableBuilder htmlBuilder = new HTMLTableBuilder(null, true, 4, 4);
			htmlBuilder.addTableHeader("Article Title", "Journal Name", "Publication Year", "Authors");
			for (int i = 0; i < title.size(); i++) {
				htmlBuilder.addRowValues(
						"<a href=http://192.168.99.100/wp-content/uploads/2020/06/"+files.get(i).getName()+">" + title.get(i).toString() + "</a>",
						journal.get(i).toString(), date.get(i).toString(), authors.get(i).toString());
			}
			String table = htmlBuilder.build();
			System.out.println(table.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getHTML_START() {
		return HTML_START;
	}

	public static void setHTML_START(String hTML_START) {
		HTML_START = hTML_START;
	}

	public static String getHTML_END() {
		return HTML_END;
	}

	public static void setHTML_END(String hTML_END) {
		HTML_END = hTML_END;
	}

	public static String getTABLE_START_BORDER() {
		return TABLE_START_BORDER;
	}

	public static void setTABLE_START_BORDER(String tABLE_START_BORDER) {
		TABLE_START_BORDER = tABLE_START_BORDER;
	}

	public static String getTABLE_START() {
		return TABLE_START;
	}

	public static void setTABLE_START(String tABLE_START) {
		TABLE_START = tABLE_START;
	}

	public static String getTABLE_END() {
		return TABLE_END;
	}

	public static void setTABLE_END(String tABLE_END) {
		TABLE_END = tABLE_END;
	}

	public static String getHEADER_START() {
		return HEADER_START;
	}

	public static void setHEADER_START(String hEADER_START) {
		HEADER_START = hEADER_START;
	}

	public static String getHEADER_END() {
		return HEADER_END;
	}

	public static void setHEADER_END(String hEADER_END) {
		HEADER_END = hEADER_END;
	}

	public static String getROW_START() {
		return ROW_START;
	}

	public static void setROW_START(String rOW_START) {
		ROW_START = rOW_START;
	}

	public static String getROW_END() {
		return ROW_END;
	}

	public static void setROW_END(String rOW_END) {
		ROW_END = rOW_END;
	}

	public static String getCOLUMN_START() {
		return COLUMN_START;
	}

	public static void setCOLUMN_START(String cOLUMN_START) {
		COLUMN_START = cOLUMN_START;
	}

	public static String getCOLUMN_END() {
		return COLUMN_END;
	}

	public static void setCOLUMN_END(String cOLUMN_END) {
		COLUMN_END = cOLUMN_END;
	}

}
