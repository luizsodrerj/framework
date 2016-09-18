package pdv.service;

/**
 * Classe para tratamento e lan�amento de exce��es
 * ocorridas em um CRUDService.
 * 
 * @author Luiz Alberto
 *
 */
public class ApplicationServiceException extends RuntimeException {

	private static final long serialVersionUID = -7299825807351038290L;

	/**
	 * Inicializa a classe com a mensagem da exce��o ocorrida
	 * 
	 * @param message mensagem da exce��o ocorrida
	 */
	public ApplicationServiceException(String message) {
		super(message);
	}

	/**
	 * Inicializa a classe com a exce��o ocorrida
	 * 
	 * @param cause exce��o ocorrida
	 */
	public ApplicationServiceException(Throwable cause) {
		super(cause);
	}


}
