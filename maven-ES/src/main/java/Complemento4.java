

/**
 * Classe que vai efetuar o Complemento 4, gerar uma tabela HTML com informação sobre o COVID-19
 * @author Pedro Guerra
 *
 */
public class Complemento4 {
	
	/**
	 * Link do repositorio GitHub onde estao as tags com a informacao necessaria
	 */
	private final static String gitRepositoryLink = "http://github.com/vbasto-iscte/ESII1920";
	/**
	 * Localizacao do diretorio onde vai estar o clone do repositorio do GitHub
	 */
	private final static String clonedRepositoryLocation = "Repositorio_Complemento4";

	/**
	 * Classe que vai ser usada para efetuar as funcoes relacionadas com o GitHub
	 */
	private static RepositorioGit repositorioGit;

	/**
	 * Funcao que vai executar todos os metodos para originar e mostrar a tabela HTML com a informacao
	 */
	public void start() {
		
		HTML.startHTMLcontent();
		
		repositorioGit = new RepositorioGit();
		repositorioGit.cloneGitRepository(clonedRepositoryLocation, gitRepositoryLink);
		repositorioGit.writeTagsInfoInHTMLFile();
		
		HTML.closeHTMLContent();
		HTML.showHTMLContent();
		
	}


}
