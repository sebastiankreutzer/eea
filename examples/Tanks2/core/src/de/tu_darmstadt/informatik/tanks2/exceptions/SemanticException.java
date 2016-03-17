package de.tu_darmstadt.informatik.tanks2.exceptions;

/**
 * Eine Exception die bei einem semantischem Fehler beim Parsen geworfen wird.
 * @author jr
 *
 */
public class SemanticException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SemanticException() {
		super();
	}

	public SemanticException (String s) {
		super(s);
	}

}
