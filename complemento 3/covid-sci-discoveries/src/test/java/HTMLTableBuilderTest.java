import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HTMLTableBuilderTest {

	@Test
	void getHTMLStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getHTML_START();
		String actual = t.getHTML_START();
		assertEquals(expected, actual);
	}
	@Test
	void setHTMLStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "ola";
		t.setHTML_START(expected);
		String actual = t.getHTML_START();
		assertEquals(expected, actual);
	}
	
	@Test
	void getHTMLEndtest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getHTML_END();
		String actual = t.getHTML_END();
		assertEquals(expected, actual);
	}
	
	
	@Test
	void setHTMLEndtest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "adeus";
		t.setHTML_END(expected);
		String actual = t.getHTML_END();
		assertEquals(expected, actual);
	}
	
	@Test
	void getTableStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getTABLE_START();
		String actual = t.getTABLE_START();
		assertEquals(expected, actual);
	}
	
	@Test
	void setTableStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "ola";
		t.setTABLE_START(expected);
		String actual = t.getTABLE_START();
		assertEquals(expected, actual);
	}
	
	@Test
	void getTableEndtest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getTABLE_END();
		String actual = t.getTABLE_END();
		assertEquals(expected, actual);
	}
	
	@Test
	void setTableEndtest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "adeus";
		t.setTABLE_END(expected);
		String actual = t.getTABLE_END();
		assertEquals(expected, actual);
	}
	
	@Test
	void getTableStartBordertest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getTABLE_START_BORDER();
		String actual = t.getTABLE_START_BORDER();
		assertEquals(expected, actual);
	}
	
	@Test
	void setTableStartBordertest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "ola";
		t.setTABLE_START_BORDER(expected);
		String actual = t.getTABLE_START_BORDER();
		assertEquals(expected, actual);
	}
	
	@Test
	void getHeaderStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getHEADER_START();
		String actual = t.getHEADER_START();
		assertEquals(expected, actual);
	}
	
	@Test
	void setHeaderStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "ola";
		t.setHEADER_START(expected);
		String actual = t.getHEADER_START();
		assertEquals(expected, actual);
	}
	
	@Test
	void getHeaderEndttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getHEADER_END();
		String actual = t.getHEADER_END();
		assertEquals(expected, actual);
	}
	@Test
	void setHeaderEndtest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "adeus";
		t.setHEADER_END(expected);
		String actual = t.getHEADER_END();
		assertEquals(expected, actual);
	}
	
	@Test
	void getRowStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getROW_START();
		String actual = t.getROW_START();
		assertEquals(expected, actual);
	}
	@Test
	void setRowStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "ola";
		t.setROW_START(expected);
		String actual = t.getROW_START();
		assertEquals(expected, actual);
	}
	
	@Test
	void getRowEndtest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getROW_END();
		String actual = t.getROW_END();
		assertEquals(expected, actual);
	}
	
	@Test
	void setRowEndtest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "ola";
		t.setROW_END(expected);
		String actual = t.getROW_END();
		assertEquals(expected, actual);
	}
	
	@Test
	void getColumnStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = t.getCOLUMN_START();
		String actual = t.getCOLUMN_START();
		assertEquals(expected, actual);
	}
	
	@Test
	void setColumnStarttest() {
		HTMLTableBuilder t = new HTMLTableBuilder("header", true, 1, 1);
		String expected = "ola";
		t.setCOLUMN_START(expected);
		String actual = t.getCOLUMN_START();
		assertEquals(expected, actual);
	}

}
