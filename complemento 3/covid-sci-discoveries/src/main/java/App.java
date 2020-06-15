import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.exception.AnalysisException;
import pl.edu.icm.cermine.metadata.model.DateType;
import pl.edu.icm.cermine.metadata.model.DocumentAuthor;
import pl.edu.icm.cermine.metadata.model.DocumentDate;

/**
 * Hello world!
 *
 */
public class App {
	private List<File>filess = new ArrayList<File>();
	public List<File> getFiless() {
		return filess;
	}

	public void setFiless(List<File> filess) {
		this.filess = filess;
	}

	public static void main(String[] args) throws IOException {
		try {
			App a = new App();
			a.ListFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  List<File> ListFile(){
		filess.removeAll(filess);
        File file = new File("C:/Users/User/wordpress_with_java/html/wp-content/uploads/2020/06");
        File[] files = file.listFiles();
        for(File f: files){
        	if(f.getName().contains(".pdf")) {
        	filess.add(f);
        	}
        	
        }
        return filess;
    }

	public List<String> extractTitle() throws Exception {
        List<String>Titles = new ArrayList<String>();
        for(File fi: filess){
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream(fi);
		extractor.setPDF(inputStream);
		String result = extractor.getMetadata().getTitle();
		Titles.add(result);
		
        }
		return Titles;
	}

	public List<String> extractJournal() throws Exception {
		
        List<String>Journal = new ArrayList<String>();
        for(File fi: filess){
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream(fi);
		extractor.setPDF(inputStream);
		String result = extractor.getMetadata().getJournal();
		Journal.add(result);
        }
		return Journal;
	}

	public List<String> extractData() throws Exception {
		
        List<String>Date = new ArrayList<String>();
        for(File fi: filess){
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream(fi);
		extractor.setPDF(inputStream);
		String result = extractor.getMetadata().getDate(DateType.PUBLISHED).getYear();
		Date.add(result);
        }
		return Date;
	}

	public List<List<String>> extractAuthors() throws Exception {
		
        List<List<String>> allAuthor = new ArrayList<List<String>>();
        for(File f: filess){
        List<String> Author = new ArrayList<String>();
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream(f);
		extractor.setPDF(inputStream);
		List<DocumentAuthor> result = extractor.getMetadata().getAuthors();
		for (DocumentAuthor c : result) {
			String autor = c.getName();
			Author.add(autor);
		}
		allAuthor.add(Author);
        }
		return allAuthor;
	}
}
