package maze.logic;

public class Element {
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
}
