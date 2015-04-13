package maze.logic;

import java.io.Serializable;

public class Darts extends Element implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean pickedUp;

	/**
	 * return true if the hero owns the dart 
	 * @return
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}

	/**
	 * changes the flag pickedUp
	 * @param pickedUp
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	/**
	 * Creates a Dart object and calls the super constructor (Element)
	 * @param x
	 * @param y
	 * @param figure
	 */
	public Darts(int x, int y, char figure) {
		super(x, y, figure);
		pickedUp = false;
	}
}
 