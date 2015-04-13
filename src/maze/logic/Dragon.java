package maze.logic;

import java.io.Serializable;

/**
 * Class that represents the dragon element in the maze
 * @author Pedro Costa
 *
 */
public class Dragon extends Element implements Serializable{
	
	private static final long serialVersionUID = 1L;
	boolean dragonAlive;
	boolean swordDragon;
	boolean isAsleep;
	int sleepsFor;

	/**
	 * Dragon constructor. Calls superclass Element constructor, to set X, Y and the char Figure.
	 * Initializes dragon status as alive and dragon/sword interception as false.
	 * @param x Dragon's X value
	 * @param y Dragon's Y value
	 * @param figure Character that represents the dragon on the maze array (usually a 'D')
	 */
	public Dragon(int x, int y, char figure) {
		super(x, y, figure);
		dragonAlive = true;
		swordDragon = false;

	}
 
	public boolean isAsleep() {
		return isAsleep;
	}

	public void setAsleep(boolean isAsleep) {
		this.isAsleep = isAsleep;
	}

	public int getSleepsFor() {
		return sleepsFor;
	}

	public void setSleepsFor(int sleepsFor) {
		this.sleepsFor = sleepsFor;
	}

	public boolean isDragonAlive() {
		return dragonAlive;
	}

	public boolean isSwordDragon() {
		return swordDragon;
	}

	public void setSwordDragon(boolean swordDragon) {
		this.swordDragon = swordDragon;
	}

	public void setDragonAlive(boolean dragonAlive) {
		this.dragonAlive = dragonAlive;
	}
}
