package de.tu_darmstadt.gdi1.tanks.model.interfaces;

import de.tu_darmstadt.gdi1.tanks.model.exceptions.SyntaxException;

public interface IParser {
	
	public IMap parseMap() throws SyntaxException;

}
