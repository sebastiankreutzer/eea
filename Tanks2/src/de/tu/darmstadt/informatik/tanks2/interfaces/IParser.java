package de.tu.darmstadt.informatik.tanks2.interfaces;

import de.tu.darmstadt.informatik.tanks2.exceptions.SyntaxException;

public interface IParser {
	
	public IMap parseMap() throws SyntaxException;

}
