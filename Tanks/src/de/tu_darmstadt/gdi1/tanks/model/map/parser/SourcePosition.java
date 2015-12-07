package de.tu_darmstadt.gdi1.tanks.model.map.parser;

public class SourcePosition {
	  public int start, finish;

	  public SourcePosition () {
	    start = 0;
	    finish = 0;
	  }

	  public SourcePosition (int s, int f) {
	    start = s;
	    finish = f;
	  }

	  public String toString() {
	    return "(" + start + ", " + finish + ")";
	  }
	

}
