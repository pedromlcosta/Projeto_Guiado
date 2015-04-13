package maze.logic;

import java.io.Serializable;

/**
 * Class that represents the shield element in the maze
 * @author Pedro Costa
 *
 */
public class Shield extends Element implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Shield constructor. Calls superclass Element constructor, to set X, Y and the char Figure.
	 * @param x Shields's X value
	 * @param y Shield's Y value
	 * @param figure Character that represents the shield on the maze array (usually a 'P')
	 */
	public Shield(int x, int y, char figure) {
		super(x, y, figure);

	} 
}
