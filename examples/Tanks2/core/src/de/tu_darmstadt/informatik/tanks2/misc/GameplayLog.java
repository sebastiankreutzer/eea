package de.tu_darmstadt.informatik.tanks2.misc;

/**
 * Eine Klasse zum protokollieren des Spielgeschehens.
 * 
 * @author jr
 *
 */
public class GameplayLog {

	private static GameplayLog log = new GameplayLog();
	public Timer timer;
	private int numberOfShots;
	private String mapName, nextMap, background;
	private long timeLimit;
	private boolean multiplayer;

	private GameplayLog() {
		this.numberOfShots = 0;
		this.setNextMap("null");
		this.setBackground("null");
		this.setMapName("null");

		timer = new Timer();
		setTimeLimit(0);

		this.multiplayer = false;
	}

	public String getBackground() {
		return this.background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public static GameplayLog getInstance() {
		return log;
	}

	public void setNumberOfShots(int shots) {
		this.numberOfShots = shots;
	}

	public int getNumberOfShots() {
		return this.numberOfShots;
	}

	public void incrementNumberOfShots(int value) {
		this.numberOfShots += value;
	}

	public String getNextMap() {
		return nextMap;
	}

	public void setNextMap(String nextMap) {
		this.nextMap = nextMap;
	}

	public long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapName() {
		return this.mapName;
	}

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Map ");
		stringBuffer.append(background);
		stringBuffer.append(" ");
		stringBuffer.append("\"" + mapName + "\"");
		stringBuffer.append(" ");
		stringBuffer.append(nextMap);
		stringBuffer.append(" ");
		stringBuffer.append(timeLimit);
		stringBuffer.append(" ");
		stringBuffer.append(timer.get());
		stringBuffer.append(" ");
		stringBuffer.append(numberOfShots);
		return stringBuffer.toString();
	}

	public void setMultiplayer(boolean isMultiplayer) {
		this.multiplayer = isMultiplayer;
	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public class Timer {

		long startTime, elapsedTime = 0;

		public void start() {
			startTime = System.currentTimeMillis();
		}

		public void stop() {
			elapsedTime = elapsedTime + System.currentTimeMillis() - startTime;
		}

		public void set(long time) {
			elapsedTime = time;
		}

		public long get() {
			return System.currentTimeMillis() - startTime;
		}

		public void reset() {
			elapsedTime = 0;
		}

	}

}
