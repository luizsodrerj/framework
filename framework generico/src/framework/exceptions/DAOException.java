package framework.exceptions;

/**
 * Classe de exce��o para DAOs
 * @author  Luiz Alberto
 * @date    20/01/2005
 */
public class DAOException extends Exception {
    
    /**
     * Inicializa a classe
     */
    public DAOException() {
        super();
    }

    /**
     * Inicializa a classe com descri��o da exce��o
     * @param exception descri��o da exce��o
     */
    public DAOException(String exception) {
        super(exception);
    }

    /**
     * Inicializa a classe com a exce��o ocorrida
     * @param exception exce��o ocorrida
     */
    public DAOException(Exception exception) {
        super(exception);
    }

}
