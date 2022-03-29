package framework.exceptions;

/**
 * <p>Descri��o:    Classe de exce��o de neg�cios. </p>
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
     * Construtor com a descri��o da exce��o 
     * @param exception Descri��o da exce��o
     */
    public BusinessException(String exception) {
        super(exception);
    }
    
    /**
     * Construtor com a descri��o da exce��o 
     * @param exception Descri��o da exce��o
     */
    public BusinessException(Exception exception) {
        super(exception);
    }

    
}








