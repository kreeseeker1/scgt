package br.unicamp.ic.sgct.client.dominio.exception;

public class InfraException extends Exception {
	private static final long serialVersionUID = -2250603969681097616L;

	public InfraException() {
		super();
	}

	public InfraException(String message, Throwable cause) {
		super(message, cause);
	}

	public InfraException(String message) {
		super(message);
	}

	public InfraException(Throwable cause) {
		super(cause);
	}
}