package framework.presentation.swing;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Classe utilitária que limita um 
 * número máximo de caracteres em 
 * um campo de texto
 * 
 * <p>Date: 30/07/2005</p>
 * @author	Luiz Alberto
 */
public class MaxCharTextDocument extends PlainDocument  {
    private static final long serialVersionUID = 1L;
    
	private int maxLength;

    /**
     * Inicializa a classe com um número máximo de caracteres 
     * @param maxLength número máximo de caracteres 
     */
    public MaxCharTextDocument(int maxLength) {
        super();
        this.maxLength = maxLength;
    }

    /**
     * Inserts some content into the document.  
     * Inserting content causes a write lock to be held while the
     * actual changes are taking place, followed by notification
     * to the observers on the thread that grabbed the write lock.
     * <p>
     * This method is thread safe, although most Swing methods
     * are not. Please see 
     * <A HREF="http://java.sun.com/products/jfc/swingdoc-archive/threads.html">Threads
     * and Swing</A> for more information.
     * 
     * @param offs the starting offset >= 0
     * @param str the string to insert; does nothing with null/empty strings
     * @param a the attributes for the inserted content
     * @exception BadLocationException  the given insert position is not a valid 
     *    position within the document
     * @see Document#insertString
     */
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null)  {
            return;
        }
        String oldString = getText(0, getLength());
        
        if (oldString.length() < maxLength)  {
            super.insertString(offs, str, a);
        }
    }
    
}