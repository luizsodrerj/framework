package framework.exceptions;

/**
 * <p>Descrição:    Classe de exceção de negócios. </p>
 * <p>Data:         22/10/2004. </p>
 * @author          Luiz Alberto
 * @version         1.0
 */
public class BusinessException extends Exception  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Construtor simples
     */
    public BusinessException() {
        super();
    }

    /**
     * Construtor com a descrição da exceção 
     * @param exception Descrição da exceção
     */
    public BusinessException(String exception) {
        super(exception);
    }
    
    /**
     * Construtor com a descrição da exceção 
     * @param exception Descrição da exceção
     */
    public BusinessException(Exception exception) {
        super(exception);
    }

    
}








