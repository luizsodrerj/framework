package framework.xml;

/**
 * Classe de exceção para erros de análise de xml 
 * @author Luiz Alberto
 * @date   01/10/2004
 */
public class XMLException extends Exception {

    /**
     * Inicializa a classe
     */
    public XMLException() {
        super();
    }

    /**
     * Inicializa a classe com mensagem de erro
     * @param error mensagem de erro
     */
    public XMLException(String error) {
        super(error);
    }

}
