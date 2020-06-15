import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevWalk;
import org.junit.Test;

public class WriteTagsInfoInHTMLFileTest {
	
	String conteudoQueDeveEstarNoFicheiro = "";
	String conteudoNoHTML;
	RepositorioGit repositorioGit;
	
	@Test
	public void test() {
		
		clonarRepositorio();
 
		adicionarInfoDasTagsNoHTML(); 
		
		List<Ref> listaTags;
		try {
			listaTags = repositorioGit.git.tagList().call();
			
			RevWalk walk = new RevWalk(repositorioGit.repository);
	        Ref annotatedTag;
	        RevObject objeto;
			
			for (Ref tag : listaTags) {
				
				String tagname = tag.getName().replace("refs/tags/", "");
				annotatedTag = repositorioGit.repository.findRef(tagname);
		        objeto = walk.parseAny(annotatedTag.getObjectId());
		        
		        if (objeto instanceof RevCommit) {
		        	
		            RevCommit commit = (RevCommit) objeto;
		            
		            String tagDescription = commit.getFullMessage();
		            String data = commit.getAuthorIdent().getWhen().toString();
		            String filename = repositorioGit.getFileName(commit);
		            String link = "http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/" + tagname + "/" + filename;
		            
		            concatenarInfoAoConteudo(data, filename, tagname, tagDescription, link);
		            
		      	  	walk.close();
		        }	
			}
	        walk.dispose();
	        
	        assertTrue(conteudoNoHTML.trim().contains(conteudoQueDeveEstarNoFicheiro.trim()));
	        
		} catch (GitAPIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void concatenarInfoAoConteudo(String data, String filename, String tagname, String tagDescription, String link) {
		
		conteudoQueDeveEstarNoFicheiro = conteudoQueDeveEstarNoFicheiro + "<tr><td>" + data
		+ "</td><td>" + filename 
		+ "</td><td>" + tagname 
		+ "</td><td>" + tagDescription 
		+ "</td><td><a href=\"" + link + "\">Link</a>"
		+ "</td></tr>\n";
		
	}
	
	private void adicionarInfoDasTagsNoHTML() {
		
		HTML html = new HTML();
		html.startHTMLcontent();
		repositorioGit.writeTagsInfoInHTMLFile();
		html.closeHTMLContent();

		conteudoNoHTML = html.conteudoHTML;
		
	}
	
	private void clonarRepositorio(){
		
		repositorioGit = CloneRepositoryTest.repositorioGit;
		if (repositorioGit == null) {
			repositorioGit = new RepositorioGit();
			repositorioGit.cloneGitRepository("RepositorioTeste", "http://github.com/vbasto-iscte/ESII1920");
		}
		
	}
	
}
