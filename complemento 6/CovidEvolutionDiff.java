package complemento6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

public class CovidEvolutionDiff {
	private static final int NUM_OF_TAGS = 2;
	private static Git git;
	private static final String gitUrl = "https://github.com/vbasto-iscte/ESII1920.git";
	private static final String repoPath = "repositorio_complemento6";
	private static final String fileToSearch = "covid19spreading.rdf";
	private static Repository repo;
	private static List<Ref> tags;
	private static List<File> files;
	private static List<String> ontologies;
	private static List<String> classes;
	private static List<String> regions;
	private static String[] table1regionInformation = new String[5];
	private static String[] table2regionInformation = new String[5];

	private static Boolean addedParameters = false;
	private static int table = 1;
	
	private static final String[] columnDynamicValues = {"$firstcolumn", "$secondcolumn", "$thirdcolumn", "$fourthcolumn"};
	
	private static final String[] regionDynamicValues = {"$region1", "$region2", "$region3", "$region4", "$region5"};
	
	private static final String[] infectionDynamicValues = {"$table1region1infections", "$table1region2infections", "$table1region3infections", "$table1region4infections", "$table1region5infections",
			"$table2region1infections", "$table2region2infections", "$table2region3infections", "$table2region4infections", "$table2region5infections"};
	
	private static final String[] internmentDynamicValues = {"$table1region1internments", "$table1region2internments", "$table1region3internments", "$table1region4internments", "$table1region5internments",
			"$table2region1internments", "$table2region2internments", "$table2region3internments", "$table2region4internments", "$table2region5internments"};

	private static final String[] testsDynamicValues = {"$table1region1tests", "$table1region2tests", "$table1region3tests", "$table1region4tests", "$table1region5tests",
			"$table2region1tests", "$table2region2tests", "$table2region3tests", "$table2region4tests", "$table2region5tests"};
	
	
	/** Constructor, initializes the necessary arrays
	 * 
	 */
	public CovidEvolutionDiff() {
		tags = new ArrayList<>();
		files = new ArrayList<File>(NUM_OF_TAGS);
		ontologies = new ArrayList<>();
		classes = new ArrayList<>();		
		regions = new ArrayList<>();
	}

	
	/** Initializes application
	 * 
	 */
	public static void init() {
		if (new File(repoPath).list().length == 0) {
			cloneRepository();
		} else {
			try {
				git = Git.open(new File(repoPath));
				repo = git.getRepository();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			checkTags();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	
	/** Clones git repository
	 * 
	 */
	public static void cloneRepository() {
		try {
			git = Git.cloneRepository().setURI(gitUrl).setDirectory(new File(repoPath)).call();
			repo = git.getRepository();
		} catch (IllegalStateException | GitAPIException e) {
			e.printStackTrace();
		}
	}

	
	/** Checks the last 2 tags
	 * 
	 * @throws GitAPIException
	 */
	public static void checkTags() throws GitAPIException {
		tags = git.tagList().call();
		Ref r;
		for (int i = NUM_OF_TAGS; i != 0; i--) {
			r = tags.get(tags.size() - i);
			git.checkout().setName(r.getName()).call();
			repo = git.getRepository();
			try {
				accessFile(r);
			} catch (RevisionSyntaxException | IOException e) {
				e.printStackTrace(); 
			}
		}
	}


	/** Accesses the file with a given tag
	 * 
	 * @param tag
	 * @throws RevisionSyntaxException
	 * @throws AmbiguousObjectException
	 * @throws IncorrectObjectTypeException
	 * @throws IOException
	 */
	public static void accessFile(Ref tag)
			throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		ObjectId commitId = repo.resolve(Constants.HEAD);

		try (RevWalk revWalk = new RevWalk(repo)) {
			RevCommit commit = revWalk.parseCommit(commitId);
			RevTree tree = commit.getTree();

			try (TreeWalk treeWalk = new TreeWalk(repo)) {
				treeWalk.addTree(tree);
				treeWalk.setRecursive(true);
				treeWalk.setFilter(PathFilter.create(fileToSearch));
				if (!treeWalk.next()) {
					throw new IllegalStateException("Did not find file");
				}

				ObjectId objectId = treeWalk.getObjectId(0);
				ObjectLoader loader = repo.open(objectId);

				createFile(loader, tag);
			}
			revWalk.dispose();
		}
	}

	
	/** Creates a file from a ObjectLoader 
	 * 
	 * @param loader
	 * @param tag
	 */
	public static void createFile(ObjectLoader loader, Ref tag) {
		try {
			String path = repoPath + File.separator + tag.getName().split("/")[2] + ".txt";
			File f = new File(path);
			f.getParentFile().mkdir();
			f.createNewFile();
			loader.copyTo(new FileOutputStream(f));

			files.add(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/** Creates the final HTML page, by extracting the information from the files, creating the HTML code and then writing it to a file
	 * 
	 */
	public static void createWebPage() {
		extractInformationFromFiles();
		String htmlString = buildHtmlString();
		File newHtmlFile = new File("WebPage.html");
		writeToFile(newHtmlFile, htmlString);
		System.out.println(htmlString);
	}

	
	/** Builds the final HTML code and replaces the dynamic values by calling various methods
	 * 
	 * @return the final HTML code
	 */
	public static String buildHtmlString() {
		File htmlTemplateFile = new File("WebTemplate.html");
		String htmlString = readFileContents(htmlTemplateFile);
		String title = "Covid Evolution Diff";
		htmlString = htmlString.replace("$title", title);
		int i = 0;
		for (File f : files) {
			if (i == 0)
				htmlString = htmlString.replace("$table1name", f.toString());
			if (i == 1)
				htmlString = htmlString.replace("$table2name", f.toString());
			i++;
		}
		
		htmlString = setTableColumns(htmlString);
		htmlString = setRegions(htmlString);
		htmlString = setInfectionNumbers(htmlString);
		htmlString = setInternmentNumbers(htmlString);
		htmlString = setTestsNumbers(htmlString);
		return htmlString;
	}

	
	/** Reads the contents of a file
	 * 
	 * @param file
	 * @return the contents
	 */
	public static String readFileContents(File file) {
		StringBuilder fileContents = new StringBuilder((int) file.length());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + System.lineSeparator());
				
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContents.toString();
	}

	
	/** Writes a string to a file
	 * 
	 * @param file
	 * @param string
	 */
	public static void writeToFile(File file, String string) {
		try {
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(string);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/** Splits the strings in the files, in order to extract the values, if there are any on the relevant fields, like "Internamentos", "Infecoes" and "Testes"
	 * 
	 */
	public static void extractInformationFromFiles () {
		for (File f: files) {
			int region = 0;
			try (Scanner s = new Scanner(f)) {
		        while (s.hasNextLine()) {
		            String line = s.nextLine();
		            if (line.contains("<owl:DatatypeProperty") && !addedParameters) {
		            	ontologies.add(line.split(";")[1].split("\"")[0]);
		            }
		            if (line.contains("<owl:Class") && !addedParameters) {
		            	classes.add(line.split(";")[1].split("\"")[0]);
		            }
		            if (line.contains("<owl:NamedIndividual")) {
		            	if (!addedParameters) {
		            		regions.add(line.split(";")[1].split("\"")[0]);
		            	}
		            	if (table == 1) {
		            		table1regionInformation[region] = line.split(";")[1].split("\"")[0];
		            	}
		            	table2regionInformation[region] = line.split(";")[1].split("\"")[0];

		            	while(s.hasNextLine()) {
		            		String line2 = s.nextLine();
		            		if (line2.contains("</owl:")) {
		            			break;
		            		} else {
		            			if (line2.contains("Internamentos")) {
		            				if (table == 1) {
		            					table1regionInformation[region] = table1regionInformation[region].concat(", " + "Internamentos: " + line2.split(">")[1].split("<")[0]);
		            				}
	            					table2regionInformation[region] = table2regionInformation[region].concat(", " + "Internamentos: " + line2.split(">")[1].split("<")[0]);

		            			} else if (line2.contains("Infecoes")) {
		            				if (table == 1) {
		            					table1regionInformation[region] = table1regionInformation[region].concat(", " + "Infecoes: " + line2.split(">")[1].split("<")[0]);
		            				}
	            					table2regionInformation[region] = table2regionInformation[region].concat(", " + "Infecoes: " + line2.split(">")[1].split("<")[0]);

		            			} else if (line2.contains("Testes")) {
		            				if (table == 1) {
		            					table1regionInformation[region] = table1regionInformation[region].concat(", " + "Testes: " + line2.split(">")[1].split("<")[0]);
		            				}
	            					table2regionInformation[region] = table2regionInformation[region].concat(", " + "Testes: " + line2.split(">")[1].split("<")[0]);
		            			}
		            		}
		            	}
		            	region++;
		            }
		        }
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
			addedParameters = true;
			table = 2;
		}
	}
	
	
	/** Replaces the dynamic values on the HTML template, for the column values
	 * 
	 * @param htmlString
	 * @return modified htmlString
	 */
	public static String setTableColumns(String htmlString) {
		for (String classe: classes) {
			htmlString = htmlString.replace(columnDynamicValues[0], classe);
		}
		int i = 1;
		for (String ontologia : ontologies) {
			htmlString = htmlString.replace(columnDynamicValues[i], ontologia);
			i++;
		}
		return htmlString;
	}

	
	/** Replaces the dynamic values on the HTML template, for the region values (first value of each line)
	 * 
	 * @param htmlString
	 * @return modified htmlString
	 */
	public static String setRegions(String htmlString) {
		int i = 0;
		for (String region : regions) {
			htmlString = htmlString.replace(regionDynamicValues[i], region);
			i++;
		}
		return htmlString;
	}

	
	/** Replaces the dynamic values on the HTML template, for the Infection numbers, if they were present, on the file that was read
	 * 
	 * @param htmlString
	 * @return modified htmlString
	 */
	public static String setInfectionNumbers(String htmlString) {
		for (int i=0; i<table1regionInformation.length;i++) {
			try {
				htmlString = htmlString.replace(infectionDynamicValues[i], table1regionInformation[i].split("Infecoes: ")[1].split(",")[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				htmlString = htmlString.replace(infectionDynamicValues[i], "X");
			}
		}		
		
		for (int i=0; i<table2regionInformation.length;i++) {
			try {
				htmlString = htmlString.replace(infectionDynamicValues[i+5], table2regionInformation[i].split("Infecoes: ")[1].split(",")[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				htmlString = htmlString.replace(infectionDynamicValues[i+5], "X");
			}
		}
		return htmlString;
	}
	
	
	/** Replaces the dynamic values on the HTML template, for the Internment numbers, if they were present, on the file that was read
	 * 
	 * @param htmlString
	 * @return modified htmlString
	 */
	public static String setInternmentNumbers(String htmlString) {
		
		for (int i=0; i<table1regionInformation.length;i++) {
			try {
				htmlString = htmlString.replace(internmentDynamicValues[i], table1regionInformation[i].split("Internamentos: ")[1].split(",")[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				htmlString = htmlString.replace(internmentDynamicValues[i], "X");
			}
		}		
		
		for (int i=0; i<table2regionInformation.length;i++) {
			try {
				htmlString = htmlString.replace(internmentDynamicValues[i+5], table2regionInformation[i].split("Internamentos: ")[1].split(",")[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				htmlString = htmlString.replace(internmentDynamicValues[i+5], "X");
			}
		}
		return htmlString;
	}
	
	
	/** Replaces the dynamic values on the HTML template, for the Test numbers, if they were present, on the file that was read
	 * 
	 * @param htmlString
	 * @return modified htmlString
	 */
	public static String setTestsNumbers(String htmlString) {
		
		for (int i=0; i<table1regionInformation.length;i++) {
			try {
				htmlString = htmlString.replace(testsDynamicValues[i], table1regionInformation[i].split("Testes: ")[1].split(",")[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				htmlString = htmlString.replace(testsDynamicValues[i], "X");
			}
		}		
		
		for (int i=0; i<table2regionInformation.length;i++) {
			try {
				htmlString = htmlString.replace(testsDynamicValues[i+5], table2regionInformation[i].split("Testes: ")[1].split(",")[0]);
			} catch (ArrayIndexOutOfBoundsException e) {
				htmlString = htmlString.replace(testsDynamicValues[i+5], "X");
			}
		}
		
		return htmlString;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Content-type: text/html\n\n");
		new CovidEvolutionDiff();
		init();
		createWebPage();
	}
}