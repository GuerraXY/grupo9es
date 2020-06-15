import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

/**
 * Classe que vai ter todos os metodos relacionados com o repositorio do GitHub
 * @author Pedro Guerra
 *
 */
public class RepositorioGit {
	
	/**
	 * Clone do repositorio do GitHub onde estao as tags necessarias para a execucao do complemento 4
	 */
	public Repository repository;
	/**
	 * Variavel que aponta para o repositorio Git
	 */
	public Git git;

	/**
	 * Faz um clone de um repositorio do Github para um ficheiro local
	 * @param clonedRepositoryLocation localizacao onde o repositorio GitHub vai ser clonado
	 * @param gitRepositoryLink link do repositorio do GitHub que vai ser clonado
	 */
	public void cloneGitRepository(String clonedRepositoryLocation, String gitRepositoryLink) {
			
			File file = new File(clonedRepositoryLocation);

			try {
				if (file.exists()){
					delete(file);
				}
				Git.cloneRepository()
				  .setURI(gitRepositoryLink)
				  .setDirectory(new File(clonedRepositoryLocation))
				  .call();
				
				
				git = Git.open(file);
				repository = git.getRepository();
				
			} catch (InvalidRemoteException e) {
				e.printStackTrace();
			} catch (TransportException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
	/**
	 * Remove um ficheiro do computador
	 * @param file ficheiro que vai ser removido
	 * @throws IOException 
	 */
	private void delete(File file) throws IOException{
	
		if(file.isDirectory()){
	
			if(file.list().length==0){
			   file.delete();
			}else{
	    	   String files[] = file.list();
	    	   
	    	   for (String temp : files) {
	    	      File fileDelete = new File(file, temp);
	    	      delete(fileDelete);
	    	   }
	
	    	   if(file.list().length==0){
	       	     file.delete();
	    	   }
			}
		}else{
			file.delete();
		}
	}
	
	
	/**
	 * Escreve a informacao das tags existentes no repositorio Github para a tabela HTML
	 */
	public void writeTagsInfoInHTMLFile() {
		
		List<Ref> list;
		try {
			list = git.tagList().call();
			for (Ref tag : list) {
				
				String tagname = tag.getName().replace("refs/tags/", "");
				insertTagIntoHTMLFromName(repository, tagname);
				
			}
		} catch (GitAPIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Insere uma linha na tabela HTML com a informacao de uma tag com um certo nome
	 * @param repository repositorio local onde esta a informacao de todas as tags existentes no repositorio Github
	 * @param tagName nome da tag a ser inserida na tabela HTML
	 * @throws IOException
	 */
	private void insertTagIntoHTMLFromName(Repository repository, String tagName) throws IOException {
		
    	RevWalk walk = new RevWalk(repository);
        Ref annotatedTag = repository.findRef(tagName);
        RevObject objeto = walk.parseAny(annotatedTag.getObjectId());
        
        if (objeto instanceof RevCommit) {
        	
            RevCommit commit = (RevCommit) objeto;
            
            String tagDescription = commit.getFullMessage();
            String data = commit.getAuthorIdent().getWhen().toString();
            String filename = getFileName(commit);
            
      	  	walk.close();
      	  
      	  	String link = "http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/" + tagName + "/" + filename;
        
      	  	HTML.addLineToHTMLContent(data.toString(), filename, tagName, tagDescription, link);
        
        }
        
        walk.dispose();
	}

	/**
	 * Devolve o nome do ficheiro de uma tag
	 * @param commit tag da qual se quer descobrir o nome do ficheiro
	 * @return nome do ficheiro da tag
	 */
	String getFileName(RevCommit commit) {
	
	ObjectId treeId = commit.getTree();
    TreeWalk treeWalk = new TreeWalk(repository);
    String filename = "";
    try {
		
    	treeWalk.reset(treeId);
      	while (treeWalk.next()) {
      		filename = treeWalk.getPathString();
      	}
      	
	} catch (MissingObjectException e) {
		e.printStackTrace();
	} catch (IncorrectObjectTypeException e) {
		e.printStackTrace();
	} catch (CorruptObjectException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  treeWalk.close();
	  
	  return filename;
	
}
	
}
