package framework.exceptions;

/**
 * Exceção para erros de negocio em tempo de execução. 
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
	 * Inicializa a classe com a exceção ocorrida
	 * 
	 * @param cause exceção ocorrida
	 */
	public BusinessRuntimeException(Throwable cause) {
		super(cause);
	}

	
	
}
