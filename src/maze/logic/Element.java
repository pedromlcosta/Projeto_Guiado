package maze.logic;

import java.io.Serializable;

public class Element implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int X;
	int Y;
	char Figure;

	/**
	 * Creates an Object of the Type Elements
	 * @param x - coordinate of the element
	 * @param y - coordinate of the element
	 * @param figure - char that will be the figure of the element in the Maze
	 */
	public Element(int x, int y, char figure) {

		X = x;
		Y = y;
		Figure = figure;
	}

	/**
	 * 
	 * @return X of the position
	 */
	public int getX() {
		return X;
	}

	/**
	 * changes the X with x
	 * @param x
	 */
	public void setX(int x) {
		X = x;
	}

	
	/**
	 *  
	 * @return  Y of the position
	 */
	public int getY() {
		return Y;
	}

	/**
	 * 
	 * @param y is the new Y
	 */
	public void setY(int y) {
		Y = y;
	}

	/**
	 * 
	 * @return the figure of the Element
	 */
	public char getFigure() {
		return Figure;
	}

	/**
	 * Changes the figure of the element
	 * 
	 * @param figure
	 */
	public void setFigure(char figure) {
		Figure = figure;
	}

	/**
	 * @param Object o1 the element to be compared
	 * @return true if they are equal or false if they are different 
	 */
	public boolean equals(Object o1) {
		return o1 != null && o1 instanceof Element && this.X == ((Element) o1).X && this.Y == ((Element) o1).Y;
	}

}
