package maze.logic;

import java.io.Serializable;

public class Hero extends Element implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean isArmed;
	boolean hasShield;
	boolean isHeroAlive;
	int nDarts;

	public boolean isHeroAlive() {
		return isHeroAlive;
	}

	public void setHeroAlive(boolean isHeroAlive) {
		this.isHeroAlive = isHeroAlive;
	}

	public Hero(int x, int y, char figure) {
		super(x, y, figure);
		isArmed = false;
		hasShield = false;
		isHeroAlive = true;
		nDarts = 0;

	}

	public boolean isArmed() {
		return isArmed;
	}

	public void setArmed(boolean isArmed) {
		this.isArmed = isArmed;
	}

	public boolean isHasShield() {
		return hasShield;
	}

	public void setHasShield(boolean hasShield) {
		this.hasShield = hasShield;
	}

	public int getnDarts() {

		return nDarts;
	}

	public void incDarts() {
		nDarts++;
	}

	public void decDarts() {
		if (nDarts == 0)
			return;
		nDarts--;
	}

	public void setnDarts(int nDarts) {
		this.nDarts = nDarts;
	}
}