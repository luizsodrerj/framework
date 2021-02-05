package br.com.confidencecambio.javabasico;

public class NomeInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	
	public NomeInvalidoException() {
		super();
	}

	public NomeInvalidoException(String message) {
		super(message);
	}


}
