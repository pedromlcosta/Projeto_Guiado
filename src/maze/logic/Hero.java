package maze.logic;

public class Hero extends Element {
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
		nDarts--;
	}



	public void setnDarts(int nDarts) {
		this.nDarts = nDarts;
	}
}