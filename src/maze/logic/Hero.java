package maze.logic;

import java.io.Serializable;

public class Hero extends Element implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean isArmed;
	boolean hasShield;
	boolean isHeroAlive;
	int nDarts;

	/**
	 * truth if the hero is alive or false if he is dead
	 * 
	 * @return truth/false
	 */
	public boolean isHeroAlive() {
		return isHeroAlive;
	}

	/**
	 * changes the flag isHeroalive
	 * 
	 * @param isHeroAlive
	 */
	public void setHeroAlive(boolean isHeroAlive) {
		this.isHeroAlive = isHeroAlive;
	}

	/**
	 * Creates a Hero Object, calls the super constructor ( Element)
	 * 
	 * @param x
	 * @param y
	 * @param figure
	 */
	public Hero(int x, int y, char figure) {
		super(x, y, figure);
		isArmed = false;
		hasShield = false;
		isHeroAlive = true;
		nDarts = 0;

	}

	/**
	 * true if the hero has the sword or false if the hero has yet to catch the
	 * sword
	 * 
	 * @return true/false
	 */
	public boolean isArmed() {
		return isArmed;
	}

	/**
	 * changes the flag isArmed
	 * @param isArmed
	 */
	public void setArmed(boolean isArmed) {
		this.isArmed = isArmed;
	}

	/**
	 * return true if the hero has the shield and false otherwise
	 * @return true/false
	 */
	
	public boolean isHasShield() {
		return hasShield;
	}

	/**
	 * changes the flag hasShield
	 * @param hasShield
	 */
	public void setHasShield(boolean hasShield) {
		this.hasShield = hasShield;
	}

	/**
	 * the numbers of darts that hero has 
	 * @return
	 */
	public int getnDarts() {

		return nDarts;
	}

	/**
	 * increases by 1 the number of darts the hero has
	 */
	public void incDarts() {
		nDarts++;
	}

	/**
	 * decreases the number of darts the hero owns
	 */
	public void decDarts() {
		if (nDarts == 0)
			return;
		nDarts--;
	}

	/**
	 * Changes the number of Darts the hero owns
	 * @param nDarts
	 */
	public void setnDarts(int nDarts) {
		this.nDarts = nDarts;
	}
}