package de.tu_darmstadt.gdi1.tanks.model.map.parser;

public class SourceFile {

	  public static final char EOL = '\n';
	  public static final char EOT = '\u0000';

	  java.io.File sourceFile;
	  java.io.FileInputStream source;
	  int currentLine;

	  public SourceFile(String filename) {
	    try {
	      sourceFile = new java.io.File(filename);
	      source = new java.io.FileInputStream(sourceFile);
	      currentLine = 1;
	    }
	    catch (java.io.IOException s) {
	      sourceFile = null;
	      source = null;
	      currentLine = 0;
	    }
	  }

	  char getSource() {
	    try {
	      int c = source.read();

	      if (c == -1) {
	        c = EOT;
	      } else if (c == EOL) {
	          currentLine++;
	      }
	      return (char) c;
	    }
	    catch (java.io.IOException s) {
	      return EOT;
	    }
	  }

	  public int getCurrentLine() {
	    return currentLine;
	  }
	}
