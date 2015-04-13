package maze.logic;

import java.io.Serializable;

/**
 * Class that represents the exit element in the maze
 * @author Pedro Costa
 *
 */
public class Exit extends Element implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * Exit constructor. Calls superclass Element constructor, to set X, Y and the char Figure.
	 * @param x Exit's X value
	 * @param y Exit's Y value
	 * @param figure Character that represents the dragon on the maze array (usually a 'S')
	 */
	public Exit(int x, int y, char figure) {
		super(x, y, figure);
	 
	}
}
 