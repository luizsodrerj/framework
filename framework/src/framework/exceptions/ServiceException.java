package framework.exceptions;

/**
 * Classe de exceção para erros em Locator
 * @author  Luiz Alberto
 * @date    01/01/2005
 */
public class ServiceException extends RuntimeException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Inicializa a classe com a mensagem de erro
     * @param message mensagem de erro
     */
    public ServiceException(String message) {
        super(message);
    }
}