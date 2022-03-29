package framework.exceptions;

/**
 * Exce��o para erros de negocio em tempo de execu��o. 
 * 
 * @author luiz.sodre
 */
public class BusinessRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Inicializa a classe com menssagem de erro
	 * 
	 * @param message menssagem de erro
	 */
	public BusinessRuntimeException(String message) {
		super(message);
	}

	/**
	 * Inicializa a classe com a exce��o ocorrida
	 * 
	 * @param cause exce��o ocorrida
	 */
	public BusinessRuntimeException(Throwable cause) {
		super(cause);
	}

	
	
}
