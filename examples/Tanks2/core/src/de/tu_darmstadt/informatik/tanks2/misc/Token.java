package de.tu_darmstadt.informatik.tanks2.misc;

public class Token {

	private String spelling;
	private int kind;
	private SourcePosition position;

	public final static int INTLITERAL = 0, CHARLITERAL = 1, IDENTIFIER = 2, Explosion = 4, Map = 5, Mine = 6, NPC = 7,
			Pickup = 8, Player = 9, Scattershot = 10, Shot = 11, Tank = 12, Tower = 13, Wall = 14, LPAREN = 15,
			RPAREN = 16, COMMA = 17, DOT = 18, EOT = 19, ERROR = 20;

	private static String[] tokenTable = new String[] { "<int>", "<char>", "<identifier>", "Border", "Explosion", "Map",
			"Mine", "NPC", "Pickup", "Player", "Scattershot", "Shot", "Tank", "Tower", "Wall", "(", ")", ",", ".", "",
			"<error>" };

	private final static int firstReservedWord = Token.Explosion, lastReservedWord = Token.Wall;

	public Token(int kind, String spelling, SourcePosition position) {

		if (kind == Token.IDENTIFIER) {
			int currentKind = firstReservedWord;
			boolean searching = true;

			while (searching) {
				int comparison = tokenTable[currentKind].compareTo(spelling);
				if (comparison == 0) {
					this.kind = currentKind;
					searching = false;
				} else if (comparison > 0 || currentKind == lastReservedWord) {
					this.kind = Token.IDENTIFIER;
					searching = false;
				} else {
					currentKind++;
				}
			}
		} else
			this.kind = kind;

		this.spelling = spelling;
		this.position = position;

	}

	public SourcePosition getSourcePosition() {
		return position;
	}

	public Token(int type, String spelling) {
		this.kind = type;
		this.spelling = spelling;
	}

	public int getType() {
		return kind;
	}

	public String getSpelling() {
		return spelling;
	}

	public static String spell(int kind) {
		return tokenTable[kind];
	}

	public String toString() {
		return "Kind: " + kind + ", spelling: " + spelling + ", position: " + position;
	}

}
