package de.tu_darmstadt.informatik.tanks2.exceptions;

/**
 * Eine Exception die bei einem syntaktischen Fehler beim Parsen geworfen wird.
 * 
 * @author jr
 *
 */
public class SyntaxException extends Exception {

	private static final long serialVersionUID = 1L;

	public SyntaxException() {
		super();
	}

	public SyntaxException(String s) {
		super(s);
	}

}
