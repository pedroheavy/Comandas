package ifrn.tads.comandas.util;

public class ComandaException extends Exception {
	private static final long serialVersionUID = 1L;

	public ComandaException() {
		super("Exceção do sistema de comandas");
	}

	public ComandaException(String message) {
		super(message);
	}	
}
