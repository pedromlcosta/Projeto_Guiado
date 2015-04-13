package maze.logic;

import java.io.Serializable;

/**
 * Class that represents the dragon element in the maze
 * 
 * @author Pedro Costa
 *
 */
public class Dragon extends Element implements Serializable {

	private static final long serialVersionUID = 1L;
	boolean dragonAlive;
	boolean swordDragon;
	boolean isAsleep;

	/**
	 * Dragon constructor. Calls superclass Element constructor, to set X, Y and
	 * the char Figure. Initializes dragon status as alive and dragon/sword
	 * interception as false.
	 * 
	 * @param x
	 *            Dragon's X value
	 * @param y
	 *            Dragon's Y value
	 * @param figure
	 *            Character that represents the dragon on the maze array
	 *            (usually a 'D')
	 */
	public Dragon(int x, int y, char figure) {
		super(x, y, figure);
		dragonAlive = true;
		swordDragon = false;

	}

	/**
	 * 
	 * @return true if the dragon is sleeping and false otherwise
	 */
	public boolean isAsleep() {
		return isAsleep;
	}

	/**
	 * changes the flag isAsleep with the boolean passed by @param	
	 * @param isAsleep
	 */
	public void setAsleep(boolean isAsleep) {
		this.isAsleep = isAsleep;
	}

	/**
	 * 
	 * @return true if the dragon is alive and false if the dragon has already been slain
	 */
	public boolean isDragonAlive() {
		return dragonAlive;
	}

	/**
	 * 
	 * @return true if the dragon is in the same spot as the sword
	 */
	public boolean isSwordDragon() {
		return swordDragon;
	}

	/**
	 * changes the flag swordDragon
	 * @param swordDragon
	 */
	public void setSwordDragon(boolean swordDragon) {
		this.swordDragon = swordDragon;
	}

	/**
	 * changes the flag DragonAlive 
	 * @param dragonAlive
	 */
	public void setDragonAlive(boolean dragonAlive) {
		this.dragonAlive = dragonAlive;
	}
}
