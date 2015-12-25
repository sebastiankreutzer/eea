package de.tu.darmstadt.informatik.tanks2.misc;


public class Scanner {
	
	//Da FileIO noch nicht implementiert dient der Vordefinierte Tokenstream dem Test
	

	
	
	  private SourceFile sourceFile;
	  private boolean debug;

	  private char currentChar;
	  private StringBuffer currentSpelling;
	  private boolean currentlyScanningToken;

	  private boolean isLetter(char c) {
	    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	  }

	  private boolean isDigit(char c) {
	    return (c >= '0' && c <= '9');
	  }

	


	///////////////////////////////////////////////////////////////////////////////

	  public Scanner(SourceFile source) {
	    sourceFile = source;
	    currentChar = sourceFile.getSource();
	    debug = false;
	  }

	  public void enableDebugging() {
	    debug = true;
	  }

	  // takeIt appends the current character to the current token, and gets
	  // the next character from the source program.

	  private void takeIt() {
	    if (currentlyScanningToken)
	      currentSpelling.append(currentChar);
	    currentChar = sourceFile.getSource();
	  }

	  // scanSeparator skips a single separator.

	  private void scanSeparator() {
	    switch (currentChar) {
	    case '!':
	      {
	        takeIt();
	        while ((currentChar != SourceFile.EOL) && (currentChar != SourceFile.EOT))
	          takeIt();
	        if (currentChar == SourceFile.EOL)
	          takeIt();
	      }
	      break;

	    case ' ': case '\n': case '\r': case '\t':
	      takeIt();
	      break;
	    }
	  }

	  private int scanToken() {

	    switch (currentChar) {

	    case 'a':  case 'b':  case 'c':  case 'd':  case 'e':
	    case 'f':  case 'g':  case 'h':  case 'i':  case 'j':
	    case 'k':  case 'l':  case 'm':  case 'n':  case 'o':
	    case 'p':  case 'q':  case 'r':  case 's':  case 't':
	    case 'u':  case 'v':  case 'w':  case 'x':  case 'y':
	    case 'z':
	    case 'A':  case 'B':  case 'C':  case 'D':  case 'E':
	    case 'F':  case 'G':  case 'H':  case 'I':  case 'J':
	    case 'K':  case 'L':  case 'M':  case 'N':  case 'O':
	    case 'P':  case 'Q':  case 'R':  case 'S':  case 'T':
	    case 'U':  case 'V':  case 'W':  case 'X':  case 'Y':
	    case 'Z':
	      takeIt();
	      while (isLetter(currentChar) || isDigit(currentChar))
	        takeIt();
	      return Token.IDENTIFIER;

	    case '0':  case '1':  case '2':  case '3':  case '4':
	    case '5':  case '6':  case '7':  case '8':  case '9':
	      takeIt();
	      while (isDigit(currentChar) || currentChar == '.')
	        takeIt();
	      return Token.INTLITERAL;
	      
	      
	    case '\"':
	    	takeIt();
	    	while(currentChar != '\"'){
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

	  public Token scan () {
		 // i++;
		 // return stream[i-1];
		  
		
	    Token tok;
	    SourcePosition pos;
	    int kind;

	    currentlyScanningToken = false;
	    while (currentChar == '!'
	           || currentChar == ' '
	           || currentChar == '\n'
	           || currentChar == '\r'
	           || currentChar == '\t')
	      scanSeparator();

	    currentlyScanningToken = true;
	    currentSpelling = new StringBuffer("");
	    pos = new SourcePosition();
	    pos.start = sourceFile.getCurrentLine();

	    kind = scanToken();

	    pos.finish = sourceFile.getCurrentLine();
	    tok = new Token(kind, currentSpelling.toString(), pos);
	    if (debug)
	      System.out.println(tok);
	    return tok;
	    
	  }

}
