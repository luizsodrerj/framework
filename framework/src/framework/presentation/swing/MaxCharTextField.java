package framework.presentation.swing;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * Classe utilit�ria que limita um 
 * n�mero m�ximo de caracteres em 
 * um campo de texto
 * 
 * <p>Date: 30/07/2005</p>
 * @author	Luiz Alberto
 */
public class MaxCharTextField extends JTextField  {
    private static final long serialVersionUID = 1L;
    
	private int maxLength;
    
    /**
     * Inicializa a classe com um n�mero m�ximo de caracteres 
     * @param maxLength n�mero m�ximo de caracteres 
     */
    public MaxCharTextField(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Creates the default implementation of the model
     * to be used at construction if one isn't explicitly 
     * given.  An instance of <code>PlainDocument</code> is returned.
     * 
     * @return the default model implementation
     */
    protected Document createDefaultModel() {
        return new MaxCharTextDocument(maxLength);
    }
    
    
}