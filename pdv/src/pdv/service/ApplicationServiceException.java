package pdv.service;

/**
 * Classe para tratamento e lançamento de exceções
 * ocorridas em um CRUDService.
 * 
 * @author Luiz Alberto
 *
 */
public class ApplicationServiceException extends RuntimeException {

	private static final long serialVersionUID = -7299825807351038290L;

	/**
	 * Inicializa a classe com a mensagem da exceção ocorrida
	 * 
	 * @param message mensagem da exceção ocorrida
	 */
	public ApplicationServiceException(String message) {
		super(message);
	}

	/**
	 * Inicializa a classe com a exceção ocorrida
	 * 
	 * @param cause exceção ocorrida
	 */
	public ApplicationServiceException(Throwable cause) {
		super(cause);
	}


}
