/*
 * Created on 26/04/2005
 */
package agendaweb.persistence;

/**
 * @author Luiz Alberto
 */
public class PersistenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public PersistenceException() {
		super();
	}

	public PersistenceException(String exception) {
		super(exception);
	}

	public PersistenceException(Throwable exception) {
		super(exception);
	}

}
