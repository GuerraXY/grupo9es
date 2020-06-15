import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class CloneRepositoryTest {

	static RepositorioGit repositorioGit;
	
	@Test
	public void test() {

		repositorioGit = new RepositorioGit(); 
		repositorioGit.cloneGitRepository("RepositorioTeste", "http://github.com/vbasto-iscte/ESII1920");
		
		File ficheiro = new File("RepositorioTeste"); 
		
		assertTrue(ficheiro.exists() && ficheiro.isDirectory() && ficheiro.list().length > 0);
	}

}
