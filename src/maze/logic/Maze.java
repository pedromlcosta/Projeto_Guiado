package maze.logic;

import java.io.Serializable;
import java.util.Arrays;

public class Maze implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char maze[][];

	public Maze(int Size) {
		maze = new char[Size][Size];
	}

	public Maze(char[][] maze) {
		this.maze = maze;
	}

	public int getSize() {
		return maze.length;
	}

	public void mazePrint() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {

				System.out.print(maze[i][j]);
				System.out.print(" ");

			}
			System.out.println("");
		}
	}

	public char[][] getMaze() {
		return maze;
	}

	public void setMaze(char[][] maze) {

		this.maze = maze;
	}

	public void generateMaze(int x, int y) {
		int direction[] = new int[4];
		Arrays.fill(direction, -1);
		Main.fillArray(direction);

		for (int i = 0; i < direction.length; i++) {
			switch (direction[i]) {
			case 0: // w
				if (Main.insideBoard(x - 2, y, maze)) {
					Main.advance(maze, x, y, -1, 0, -2, 0);
					generateMaze(x - 2, y);
				} else {
					break;
				}

			case 1:// a
				if (Main.insideBoard(x, y - 2, maze)) {
					Main.advance(maze, x, y, 0, -1, 0, -2);
					generateMaze(x, y - 2);
				} else {
					break;
				}

			case 2:// s
				if (Main.insideBoard(x + 2, y, maze)) {
					Main.advance(maze, x, y, 1, 0, 2, 0);
					generateMaze(x + 2, y);
				} else {
					break;
				}

			case 3:// d
				if (Main.insideBoard(x, y + 2, maze)) {
					Main.advance(maze, x, y, 0, 1, 0, 2);
					generateMaze(x, y + 2);

				} else {
					break;
				}
			default:
				break;
			}

		}
		return;
	}

	public boolean addElement(int pos1, int pos2, Element ele) {
		if (pos1 >= maze.length - 1 || pos1 <= 0 || pos2 >= maze.length - 1 || pos2 <= 0 || maze[pos2][pos1] != ' ') {
			return false;
		} else {
			maze[pos2][pos1] = ele.getFigure();
			ele.setX(pos1);
			ele.setY(pos2);
			return true;
		} 
	}

	// Adaptada a classes
	public boolean generateExit(Status s) {
		int pos1, pos2;
		boolean created = false;

		while (!created) {

			pos1 = Main.random(s.getMaze().getMaze().length);
			pos2 = Main.random(s.getMaze().getMaze().length);

			// se for nos cantos, gera de novo
			if ((pos1 == 0 && (pos2 == 0 || pos2 == s.getMaze().getMaze().length - 1)) || pos2 == 0 && (pos1 == 0 || pos1 == s.getMaze().getMaze().length - 1) || pos1 == pos2) {
				created = false;
			}
			// se tiver uma parede a bloquear, gera de novo
			else if (pos1 == 0 && s.getMaze().getMaze()[pos2][pos1 + 1] == 'X') {
				created = false;
			} else if (pos1 == s.getMaze().getMaze().length - 1 && s.getMaze().getMaze()[pos2][pos1 - 1] == 'X') {
				created = false;
			} else if (pos2 == 0 && s.getMaze().getMaze()[pos2 + 1][pos1] == 'X') {
				created = false;
			} else if (pos2 == s.getMaze().getMaze().length - 1 && s.getMaze().getMaze()[pos2 - 1][pos1] == 'X') {
				created = false;
			}
			// de resto, cria a saida
			else if (pos1 == 0 || pos1 == s.getMaze().getMaze().length - 1 || pos2 == 0 || pos2 == s.getMaze().getMaze().length - 1) {
				s.getMaze().getMaze()[pos2][pos1] = s.getExit().getFigure();
				s.getExit().setX(pos1);
				s.getExit().setY(pos2);
				created = true;
			}
		}
		return true;

	}

	public boolean generateCharacter(Element ele) {
		int pos1, pos2;

		while (true) {
			pos1 = Main.random(maze.length);
			pos2 = Main.random(maze.length);
			if (addElement(pos1, pos2, ele)) {

				return true;
			}
		}
	}

	public String toString() {
		String resp = "";
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {// constante

				resp += maze[i][j];

				resp += " ";
			}
			resp += "\n";
		}
		return resp;
	}
}