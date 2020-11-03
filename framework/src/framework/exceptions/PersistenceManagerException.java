/*
 * Created on 26/04/2005
 */
package framework.exceptions;

/**
 * @author Luiz Alberto
 */
public class PersistenceManagerException extends Exception {

	public PersistenceManagerException() {
		super();
	}

	public PersistenceManagerException(String exception) {
		super(exception);
	}

	public PersistenceManagerException(Throwable exception) {
		super(exception);
	}

}
