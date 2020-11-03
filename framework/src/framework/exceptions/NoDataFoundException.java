package framework.exceptions;

/**
 * Excess�o lan�ada quando uma query n�o retornar dados
 * @author  Luiz Alberto
 * @date    24/01/2005
 */
public class NoDataFoundException extends Exception {

    /**
     * Construtor simples
     */
    public NoDataFoundException() {
        super();
    }

    /**
     * Construtor com a mensagem do erro
     * @param message a mensagem do erro
     */
    public NoDataFoundException(String message) {
        super(message);
    }

}