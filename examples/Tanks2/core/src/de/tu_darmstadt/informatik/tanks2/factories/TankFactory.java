package de.tu_darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.Input;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu_darmstadt.informatik.eea.action.RotateAction;
import de.tu_darmstadt.informatik.eea.component.EEAComponent;
import de.tu_darmstadt.informatik.eea.component.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.component.collision.RectangleCollisionComponent;
import de.tu_darmstadt.informatik.eea.event.ANDEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.KeyDownEvent;
import de.tu_darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu_darmstadt.informatik.eea.event.MovementDoesNotCollideEvent;
import de.tu_darmstadt.informatik.eea.event.TimedEvent;
import de.tu_darmstadt.informatik.tanks2.LaunchTanks;
import de.tu_darmstadt.informatik.tanks2.AI.TankAI;
import de.tu_darmstadt.informatik.tanks2.AI.TowerAI;
import de.tu_darmstadt.informatik.tanks2.actions.ChangeMineAmmoAction;
import de.tu_darmstadt.informatik.tanks2.actions.ChangeAmmunitionAction;
import de.tu_darmstadt.informatik.tanks2.actions.ScatterShootAction;
import de.tu_darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu_darmstadt.informatik.tanks2.actions.SpawnMineAction;
import de.tu_darmstadt.informatik.tanks2.entities.Tank;
import de.tu_darmstadt.informatik.tanks2.events.HasMinesLeftEvent;
import de.tu_darmstadt.informatik.tanks2.events.RandomEvent;
import de.tu_darmstadt.informatik.tanks2.events.HasAmmunitionLeftEvent;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options.Difficulty;

/**
 * Eine Factory zum erzeugen von Tanks, die entweder vom einem Spieler oder von einer AI gesteurt werden.
 * @author jr
 *
 */
public class TankFactory {
	private final String difficulty;
	private final boolean debug;
	private IResourceManager resourcesManager;
	private ShotFactory shotFactory;
	private MineFactory mineFactory;

	/**
	 * Erzeugt eine neue TankFactory.
	 * 
	 * @param difficulty
	 *            Der Schwierigkeitsgrad der AI Tanks.
	 * @param resourcesManager
	 *            Der ResourcesManager fuer die Bilder
	 * @param shotFactory
	 *            Die ShotFactory fuer die Schuesse
	 * @param mineFactory
	 *            Die MineFactory fuer die Mines
	 * @param debug
	 *            Der Debugmodus
	 */
	public TankFactory(String difficulty, IResourceManager resourcesManager, ShotFactory shotFactory,
			MineFactory mineFactory, boolean debug) {
		this.difficulty = difficulty;
		this.debug = debug;
		this.resourcesManager = resourcesManager;
		this.shotFactory = shotFactory;
		this.mineFactory = mineFactory;
	}

	/**
	 * Erzeugt einen neuen Tank.
	 * 
	 * @param x
	 * @param y
	 * @param name
	 *            Die ID des Tanks, falls gleich {@link LaunchTanks#player1} oder
	 *            {@link LaunchTanks#player2} ist der Tank spielergesteuert, ansonsten
	 *            von einer AI
	 * @param maxLife
	 * @param life
	 * @param shootsMax
	 * @param shoots
	 * @param minesMax
	 * @param mines
	 * @param strength
	 * @param speed
	 * @param rotation
	 * @param scale
	 * @return Einen Tank der entweder von einem Spieler oder einer KI gesteuert
	 *         wird.
	 */
	public Tank createTank(float x, float y, String name, int maxLife, int life, int shootsMax, int shoots,
			int minesMax, int mines, int strength, float speed, float rotation, float scale) {

		// Erzeugt einen neuen Tank und setzt die Parameter
		Tank tank = new Tank(name, x, y, rotation, scale);
		tank.addComponent(new RectangleCollisionComponent());

		tank.setSpeed(speed);
		tank.setMaxLife(maxLife);
		tank.setLife(life);
		tank.setMaxAmmunition(shootsMax);
		tank.setAmmunition(shoots);
		tank.setMaxMines(minesMax);
		tank.setMinesAmmo(mines);
		tank.setStrength(strength);

		// Wenn der Tank von Spieler1 gesteuert werden soll
		if (name.equals(LaunchTanks.player1)) {
			// Setzte das Bild fuer Spieler1
			tank.addComponent(new ImageRenderComponent("tankPlayer.png"));

			// initialisiere die Steuerung
			initPlayerControls(speed, tank, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT,
					Input.Keys.K, Input.Keys.M, Input.Keys.L);

		} else if (name.equals(LaunchTanks.player2)) {
			// Mehrspielermodus
			GameplayLog.getInstance().setMultiplayer(true);

			// Setzte das Bild fuer Spieler2
			tank.addComponent(new ImageRenderComponent("tankPlayer2.png"));

			// initialisiere die Steuerung
			initPlayerControls(speed, tank, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.G,
					Input.Keys.F, Input.Keys.H);
		} else {
			// Ansonsten ist der Tank ein Gegner
			tank.addComponent(new ImageRenderComponent("tankOppenent.png"));

			// Waehle die AI je nach Schwierigkeitsgrad
			EEAComponent componentAI;
			if (this.difficulty.equals(Difficulty.EASY.toString()))
				componentAI = new TowerAI(LaunchTanks.player1, shotFactory, debug);
			else
				componentAI = new TankAI(LaunchTanks.player1, shotFactory, debug);
			tank.addComponent(componentAI);

			// Die Munition des Tanks soll automatisch alle 4 bis 6 Sekunden
			// nachgeladen werden.
			EEAEvent reloadEvent = new RandomEvent(4f, 6f, true);
			reloadEvent.addAction(new ChangeAmmunitionAction(1));
			tank.addComponent(reloadEvent);
		}

		return tank;
	}

	private void initPlayerControls(float speed, Tank tank, int up, int down, int left, int right, int shoot, int mine,
			int scattershot) {
		// Die Actions zum Steuern des Tanks
		RotateAction rightRotateAction = new RotateAction(-speed);
		RotateAction leftRotateAction = new RotateAction(speed);
		MoveRelativeAction forwardMoveAction = new MoveRelativeAction(speed, 0);
		MoveRelativeAction backwardMoveAction = new MoveRelativeAction(-speed, 0);

		// Forwaertsbewegung
		EEAEvent mainEvents = new ANDEvent(new KeyDownEvent(up), new MovementDoesNotCollideEvent(forwardMoveAction));
		mainEvents.addAction(forwardMoveAction);
		tank.addComponent(mainEvents);

		// Rueckwertsbewegung
		mainEvents = new ANDEvent(new KeyDownEvent(down), new MovementDoesNotCollideEvent(backwardMoveAction));
		mainEvents.addAction(backwardMoveAction);
		tank.addComponent(mainEvents);

		// Drehen links
		mainEvents = new ANDEvent(new KeyDownEvent(left), new MovementDoesNotCollideEvent(leftRotateAction));
		mainEvents.addAction(leftRotateAction);
		tank.addComponent(mainEvents);

		// Drehen rechts
		mainEvents = new ANDEvent(new KeyDownEvent(right), new MovementDoesNotCollideEvent(rightRotateAction));
		mainEvents.addAction(rightRotateAction);
		tank.addComponent(mainEvents);

		// Schiessen
		mainEvents = new ANDEvent((new KeyPressedEvent(shoot)), new HasAmmunitionLeftEvent(tank));
		mainEvents.addAction(new ShootAction(shotFactory, debug));
		tank.addComponent(mainEvents);

		// Mine legen
		mainEvents = new ANDEvent(new KeyPressedEvent(mine), new HasMinesLeftEvent(tank));
		mainEvents.addAction(new SpawnMineAction(mineFactory, debug));
		mainEvents.addAction(new ChangeMineAmmoAction(-1));
		tank.addComponent(mainEvents);

		// ScatterShoot, verbraucht eine extra Munition
		mainEvents = new ANDEvent(new KeyPressedEvent(scattershot), new HasAmmunitionLeftEvent(tank));
		mainEvents.addAction(new ScatterShootAction(1.5f, shotFactory, debug));
		mainEvents.addAction(new ChangeAmmunitionAction(-1));
		tank.addComponent(mainEvents);

		// Lade regelmaessig nach
		mainEvents = new TimedEvent(8, true);
		mainEvents.addAction(new ChangeMineAmmoAction(1));
		mainEvents.addAction(new ChangeAmmunitionAction(2));
		tank.addComponent(mainEvents);
	}

}
