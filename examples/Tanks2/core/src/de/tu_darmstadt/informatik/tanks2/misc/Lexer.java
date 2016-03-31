package de.tu_darmstadt.informatik.tanks2.misc;

/**
 * Wertet einen SourceFile zu Tokens aus.
 * 
 * @author jr
 *
 */
public class Lexer {

	private SourceFile sourceFile;
	private boolean debug;

	private char currentChar;
	private StringBuffer currentSpelling;
	private boolean currentlyScanningToken;

	public Lexer(SourceFile source) {
		sourceFile = source;
		currentChar = sourceFile.getSource();
		debug = false;
	}
	
	public Token scan() {
		currentlyScanningToken = false;
		while (currentChar == '!' || currentChar == ' ' || currentChar == '\n' || currentChar == '\r'
				|| currentChar == '\t') {
			scanSeparator();
		}

		currentlyScanningToken = true;
		currentSpelling = new StringBuffer("");
		
		SourcePosition pos = new SourcePosition();
		pos.start = sourceFile.getCurrentLine();
		int kind = scanToken();
		pos.finish = sourceFile.getCurrentLine();
		
		Token token = new Token(kind, currentSpelling.toString(), pos);
		if (debug) {
			System.out.println(token);
		}
		return token;
	}

	public void enableDebugging() {
		debug = true;
	}

	private boolean isLetter(char c) {
		return Character.isAlphabetic(c);
	}

	private boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	/**
	 * Appends the current character to the current token, and gets the next
	 * character from the source program.
	 */
	private void takeIt() {
		if (currentlyScanningToken)
			currentSpelling.append(currentChar);
		currentChar = sourceFile.getSource();
	}

	/**
	 * Skips a single separator.
	 */
	private void scanSeparator() {
		switch (currentChar) {
		case '!':
			takeIt();
			while ((currentChar != SourceFile.EOL) && (currentChar != SourceFile.EOT)) {
				takeIt();
			}
			if (currentChar == SourceFile.EOL) {
				takeIt();
			}
			break;

		case ' ':
		case '\n':
		case '\r':
		case '\t':
			takeIt();
			break;
		}
	}

	private int scanToken() {
		if (isLetter(currentChar)) {
			takeIt();
			while (isLetter(currentChar) || isDigit(currentChar))
				takeIt();
			return Token.IDENTIFIER;
		}

		if (isDigit(currentChar)) {
			takeIt();
			while (isDigit(currentChar) || currentChar == '.')
				takeIt();
			return Token.INTLITERAL;
		}

		switch (currentChar) {

		case '\"':
			takeIt();
			while (currentChar != '\"') {
				takeIt();
			}
			takeIt();
			return Token.IDENTIFIER;

		case '\'':
			takeIt();
			takeIt(); // the quoted character
			if (currentChar == '\'') {
				takeIt();
				return Token.CHARLITERAL;
			} else
				return Token.ERROR;

		case '.':
			takeIt();
			return Token.DOT;

		case ',':
			takeIt();
			return Token.COMMA;

		case '(':
			takeIt();
			return Token.LPAREN;

		case ')':
			takeIt();
			return Token.RPAREN;

		case SourceFile.EOT:
			return Token.EOT;

		default:
			takeIt();
			return Token.ERROR;
		}
	}
}
