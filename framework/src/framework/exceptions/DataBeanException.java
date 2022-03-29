package framework.exceptions;

/**
 * Classe de exceção para a classe DataBean
 * @author  Luiz Alberto
 * @date    15/01/2005
 */
public class DataBeanException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Inicializa a classe com a mensagem de erro
     * @param message mensagem de erro
     */
    public DataBeanException(String message) {
        super(message);
    }

}