package maze.logic;

public class Darts extends Element {
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
 