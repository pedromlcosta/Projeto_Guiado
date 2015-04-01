package maze.logic;


import java.util.Random;
import java.util.Arrays;

public class Main {

	

	public static int random(int n) {
		Random r = new Random();
		return r.nextInt(n);
	}
 
	public static int randomOddNumber(int n) {
		int resp = 2;
		while (resp % 2 == 0)
			resp = random(n);

		return resp;
	}


	

	public static void fillArray(int[] direction) {
		for (int t = 0; t < direction.length;) {
			int i = random(4);
			if (find(direction, i))
				continue;
			else {
				direction[t] = i;
				t++;
			}

		}
	}

	public static boolean find(int a[], int i) {
		for (int t = 0; t < a.length; t++) {
			if (i == a[t])
				return true;
		}
		return false;
	}

	public static void advance(char[][] maze, int x, int y, int dx, int dy, int dx1, int dy1) {
		maze[x + dx][y + dy] = ' ';
		maze[x + dx1][y + dy1] = ' ';

		return;
	}

	public static void fillMaze(char maze[][], char fillWith) {
		for (int i = 0; i < maze.length; i++) {
			Arrays.fill(maze[i], fillWith);
		}
	}

	public static boolean insideBoard(int x, int y, char maze[][]) {

		if (x >= maze.length - 1 || x <= 0 || y >= maze.length - 1 || y <= 0 || maze[x][y] == ' ')
			return false;

		return true;

	}

	public static boolean insideBoardEle(int x, int y, char maze[][]) {

		if (x >= maze.length - 1 || x <= 0 || y >= maze.length - 1 || y <= 0 || maze[x][y] != ' ')
			return false;

		return true;

	}
}