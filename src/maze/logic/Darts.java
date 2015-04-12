package maze.logic;

import java.io.Serializable;

public class Darts extends Element implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean pickedUp;

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public Darts(int x, int y, char figure) {
		super(x, y, figure);
		pickedUp = false;
	}
}
 