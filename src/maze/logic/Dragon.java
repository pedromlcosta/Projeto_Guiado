package maze.logic;

import java.io.Serializable;

public class Dragon extends Element implements Serializable{
	boolean dragonAlive;
	boolean swordDragon;
	boolean isAsleep;
	int sleepsFor;

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
