import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.exception.AnalysisException;

class AppTest2 {

	@Test
	void Filesstest() {
		App a = new App();
		List<File> expected = new ArrayList<File>();
		List<File> actual =a.getFiless();
		assertEquals(expected,actual);
	}
	
	@Test
	void SetFilesstest() {
		App a = new App();
		List<File> expected = new ArrayList<File>();
		a.setFiless(expected);
		List<File> actual =a.getFiless();
		assertEquals(expected,actual);
	}
	@Test
	void GetFilesstest() {
		App a = new App();
		List<File> expected = a.getFiless();
		List<File> actual =a.getFiless();
		assertEquals(expected,actual);
	}
	
	@Test
	void ListFiletest() {
		App a = new App();
		List<File>actual = a.ListFile();
        List<File> expected =a.getFiless();
        assertEquals(expected,actual);
        }
	
	@Test
	void extractTitletest() throws Exception {
		App a = new App();
		List<String> expected = a.extractTitle();
		List<String> actual = new ArrayList<String>();
		assertEquals(expected,actual);
		
	}
	
	@Test
	void extractDatetest() throws Exception {
		App a = new App();
		List<String> expected = a.extractData();
		List<String> actual = new ArrayList<String>();
		assertEquals(expected,actual);
		
	}
	
	@Test
	void extractJournaltest() throws Exception {
		App a = new App();
		List<String> expected = a.extractJournal();
		List<String> actual = new ArrayList<String>();
		assertEquals(expected,actual);
		
	}

}
