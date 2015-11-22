package de.tu.darmstadt.informatik.tanks2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;
import de.tu.darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu.darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu.darmstadt.informatik.tanks2.misc.Map;

public class GameplayState extends EEAGameState {

	private Map map;

	public GameplayState(EEAGame game) {
		super(game);
	    this.map = Map.getInstance();
	}

	@Override
	protected void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
    	// Map parsen
    	try {
			map.parse(map.getSource(), true);
		} catch (SyntaxException e) {
			e.printStackTrace();
			JFrame frame = new JFrame("");
			JOptionPane.showMessageDialog(frame, "Die Map konnte nicht geparst werden", "Syntax Fehler", JOptionPane.ERROR_MESSAGE);
			game.setScreen(LaunchTanks.mainMenu);
			return;
		} catch (SemanticException e) {
			e.printStackTrace();
			JFrame frame = new JFrame("");
			JOptionPane.showMessageDialog(frame, "Die Map konnte nicht geladen werden. Sie ist semantisch inkorrekt.", "Semantischer Fehler", JOptionPane.ERROR_MESSAGE);
			game.setScreen(LaunchTanks.mainMenu);
			return;
		}

	}

}
