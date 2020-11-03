package framework.exceptions;

/**
 * Classe de exceção para DAOs
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
     * Inicializa a classe com descrição da exceção
     * @param exception descrição da exceção
     */
    public DAOException(String exception) {
        super(exception);
    }

    /**
     * Inicializa a classe com a exceção ocorrida
     * @param exception exceção ocorrida
     */
    public DAOException(Exception exception) {
        super(exception);
    }

}
