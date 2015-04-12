package maze.logic;

import java.io.Serializable;

public class Element implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int X;
	int Y;
	char Figure;

	public Element(int x, int y, char figure) {

		X = x;
		Y = y;
		Figure = figure;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public char getFigure() {
		return Figure;
	}

	public void setFigure(char figure) {
		Figure = figure;
	}

	public boolean equals(Object o1) {
		return o1 != null && o1 instanceof Element
				&& this.X==((Element) o1).X
				&& this.Y == ((Element) o1).Y;
	}

}
