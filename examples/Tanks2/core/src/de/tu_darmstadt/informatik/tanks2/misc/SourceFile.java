package de.tu_darmstadt.informatik.tanks2.misc;

import java.io.IOException;

import com.badlogic.gdx.utils.GdxRuntimeException;

import de.tu_darmstadt.informatik.eea.IResourcesManager;

/**
 * Ein SourceFile oeffnet einen InputStream der dann Zeichen fuer Zeichen
 * eingelesen werden kann.
 * 
 * @author jr
 *
 */
public class SourceFile {

	public static final char EOL = '\n';
	public static final char EOT = '\u0000';

	private java.io.InputStream source;
	private int currentLine;

	/**
	 * Erzeuge einen neuen SourceFile.
	 * 
	 * @param path
	 *            Der Dateipfad
	 * @param resourcesManager
	 *            Der ResourcesManager
	 */
	public SourceFile(String path, IResourcesManager resourcesManager) {
		try {
			source = resourcesManager.openROMFile(path).read();
			currentLine = 1;
		} catch (IOException e) {
			System.out.println("Error loading file " + path + " : " + e.toString());
			source = null;
			currentLine = 0;
		}
	}

	/**
	 * Gibt das naechste Zeichen dieses SourceFile zurueck.
	 * 
	 * @return Das naechste Zeichen, {@link SourceFile#EOL} bei Zeilenende oder
	 *         {@link SourceFile#EOT} bei Dateiende.
	 */
	char getSource() {
		try {
			int c = source.read();

			if (c == -1) {
				c = EOT;
				source.close();
			} else if (c == EOL) {
				currentLine++;
			}
			return (char) c;
		} catch (java.io.IOException s) {
			return EOT;
		}
	}

	/**
	 * Gibt die Anzahl der abgearbeiteten Zeilen an.
	 * 
	 * @return Die Nummer der aktuelle Zeile.
	 */
	public int getCurrentLine() {
		return currentLine;
	}
}
