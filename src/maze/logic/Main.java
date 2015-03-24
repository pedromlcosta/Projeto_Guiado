package maze.logic;

import maze.cli.MazeInterface;
import java.util.Random;
import java.util.Arrays;

public class Main {

	public static void generate(Status s) {
		fillMaze(s.getMaze().getMaze(), 'X');
		s.getMaze().generateMaze(randomOddNumber(s), randomOddNumber(s));
		s.getMaze().generateExit(s);
		s.getMaze().generateCharacter(s.getHero());
		s.getMaze().generateCharacter(s.getSword());
		s.getMaze().generateCharacter(s.getShield());
		s.Init_Numbers();
		// call a function which depending on the size of the board will "count"
		// the number of dragons
		s.initDragon('D'); 
		for (int i = 0; i < s.getDragons().length; i++)
			s.getMaze().generateCharacter(s.getDragons()[i]);
		s.initDarts('-');
		for (int i = 0; i < s.getDarts().length; i++)
			s.getMaze().generateCharacter(s.getDarts()[i]);

	}

	public static int random(int n) {
		Random r = new Random();
		return r.nextInt(n);
	}
 
	public static int randomOddNumber(Status s) {
		int resp = 2;
		while (resp % 2 == 0)
			resp = random(s.getMaze().getMaze().length);

		return resp;
	}

	public static void updateBoard(Status s, char inputChar) {

		switch (inputChar) {
		case 'A':
		case 'a':
			move_hero(s, s.getHero().getX(), s.getHero().getY() - 1);
			break;

		case 'W':
		case 'w':
			move_hero(s, s.getHero().getX() - 1, s.getHero().getY());
			break;

		case 'D':
		case 'd':
			move_hero(s, s.getHero().getX(), s.getHero().getY() + 1);
			break;

		case 'S':
		case 's':
			move_hero(s, s.getHero().getX() + 1, s.getHero().getY());
			break;
		}

		// DRAGON MOVEMENT STUB
		for (int i = 0; i < s.getDragons().length; i++)
			if (s.getDragonChoice() == 1) {
				// OPTION 1 -> STATIC DRAGON
			} else if (s.getDragonChoice() == 2) {
				// OPTION 2 -> DRAGON WITH RANDOM MOVEMENT
				move_dragon(s, s.getDragons()[i]);
			} else {

				// OPTION 3 -> DRAGON WITH RANDOM MOVEMENT AND SLEEP
				int number = random(10);
				if (s.getDragons()[i].isDragonAlive()) {
					if (s.getDragons()[i].isAsleep()) { // IF ALREADY ASLEEP,
														// HAS
														// 20% CHANCE TO WAKE UP
														// AND
														// MOVE
						if (number == 0 || number == 1) {
							s.getDragons()[i].setAsleep(false);
							s.getMaze().getMaze()[s.getDragons()[i].getX()][s.getDragons()[i].getY()] = 'D';
							move_dragon(s, s.getDragons()[i]);
						}
					} else { // IF NOT ASLEEP, HAS 10% CHANCE TO GO ASLEEP AND
								// 90%
								// CHANCE TO MOVE
						if (number == 0) {
							s.getDragons()[i].setAsleep(true);
							s.getMaze().getMaze()[s.getDragons()[i].getX()][s.getDragons()[i].getY()] = 'Z';
						} else
							move_dragon(s, s.getDragons()[i]);
					}

				}
			}
		update_status(s);

	}

	public static void print_maze(char[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {

				System.out.print(maze[i][j]);

				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	// No need to adaptar,alterou-se a chamada, mandar apenas o heroi como
	// argumento
	public static boolean move_hero(Status s, int newX, int newY) {

		
		if (s.getMaze().getMaze()[newX][newY] == 'X' || s.getMaze().getMaze()[newX][newY] == 'Z') {
			return false;
		}

		else {
		
			if (s.getMaze().getMaze()[newX][newY] == 'E') {
				// encontrou espada
				s.getHero().setArmed(true);
				
				if (s.getHero().isHasShield())
					s.getMaze().getMaze()[newX][newY] = 'K';
				else
					s.getMaze().getMaze()[newX][newY] = 'A';
			}

			if (s.getMaze().getMaze()[newX][newY] == 'P') {
				// encontrou escudo
				s.getHero().setHasShield(true);

				if (s.getHero().isArmed())
					s.getMaze().getMaze()[newX][newY] = 'K';
				else {
					s.getMaze().getMaze()[newX][newY] = 'p';

				}
			}

			if (s.getHero().isArmed() && s.getHero().isHasShield())
				s.getMaze().getMaze()[newX][newY] = 'K';
			else if (s.getHero().isHasShield() && (!s.getHero().isArmed())) {
				if (s.getMaze().getMaze()[newX][newY] == 'D')
					return false;
				s.getMaze().getMaze()[newX][newY] = 'p';
			}
			if (s.getHero().isArmed() && (!s.getHero().isHasShield())) { // Esta
																			// armado
				s.getMaze().getMaze()[newX][newY] = 'A';
			} else {
				if (s.getMaze().getMaze()[newX][newY] == 'S') {
					return false;
				}

				if (!(s.getHero().isArmed() || s.getHero().isHasShield())) {
					s.getMaze().getMaze()[newX][newY] = 'H';
				}
			}

			s.getMaze().getMaze()[s.getHero().getX()][s.getHero().getY()] = ' ';
			s.getHero().setX(newX);
			s.getHero().setY(newY);
			s.heroDarts();
			return true;
		}

	}

	// adaptar para classes
	public static boolean move_dragon(Status s, Dragon dragon) {

		int new_x = 0, new_y = 0;

		if (!dragon.isDragonAlive())
			return false;

		// Faz random ate conseguir uma direcao valida (que nao e parede nem
		// saida)
		do {
			int random = random(5);

			switch (random) {
			case 0: { // w - cima
				new_x = dragon.getX() - 1;
				new_y = dragon.getY();
				break;
			}
			case 1: { // a - esquerda
				new_x = dragon.getX();
				new_y = dragon.getY() - 1;
				break;
			}
			case 2: {// s - baixo
				new_x = dragon.getX() + 1;
				new_y = dragon.getY();
				break;
			}
			case 3: {// d - direita
				new_x = dragon.getX();
				new_y = dragon.getY() + 1;
				break;
			}
			case 4: {// manter posicao
				return false;
			}
			default:
				break;
			}
		} while (s.getMaze().getMaze()[new_x][new_y] != ' ' || (new_x == s.getSword().getX() && new_y == s.getSword().getY()));// (new_x

		if (s.getMaze().getMaze()[new_x][new_y] == 'E') { // -> If the dragon
															// lands on the
															// sword
			s.getMaze().getMaze()[new_x][new_y] = 'F';
			s.getMaze().getMaze()[dragon.getX()][dragon.getY()] = ' ';
			dragon.setSwordDragon(true);
		} else { // -> If the dragon doesn't land on the sword
			s.getMaze().getMaze()[new_x][new_y] = 'D';
			if (dragon.isSwordDragon()) { // -> Was on the sword's place and
											// left it
				s.getMaze().getMaze()[dragon.getX()][dragon.getY()] = 'E';
			} else {
				s.getMaze().getMaze()[dragon.getX()][dragon.getY()] = ' ';
			}
			dragon.setSwordDragon(false);
		}

		dragon.setX(new_x);
		dragon.setY(new_y);

		return true;
	}

	// Updates status - is only called after the coordinates change(after the
	// hero and dragon move

	private static void update_status(Status s) {

		for (int i = 0; i < s.getDragons().length; i++) {

			if (s.getDragons()[i].isDragonAlive()) {

				// only worth checking if the hero is 3<= distance from the
				// dragon if doesn´t have the shield
				if (!s.getHero().isHasShield()) {

					if (!s.getDragons()[i].isAsleep()) {
						if (s.distance(s.getHero().getX(), s.getHero().getY(), s.getDragons()[i].getX(), s.getDragons()[i].getY(), 3)) {
							if (!s.obstacles(s.getHero().getX(), s.getHero().getY(), s.getDragons()[i].getX(), s.getDragons()[i].getY())) {
								s.getHero().setHeroAlive(false);
								s.getMaze().getMaze()[s.getHero().getX()][s.getHero().getY()] = ' ';
							}
						}
					}
				}
				// else if (s.getHero().isHasShield()) {
				if (s.distance(s.getHero().getX(), s.getHero().getY(), s.getDragons()[i].getX(), s.getDragons()[i].getY(), 1)) {

					// System.out.println("Dragao adjacente");
					if (s.getHero().isArmed()) {
						s.getDragons()[i].setDragonAlive(false);
						s.getMaze().getMaze()[s.getDragons()[i].getX()][s.getDragons()[i].getY()] = ' ';
						if (s.getHero().isHasShield())
							s.getMaze().getMaze()[s.getHero().getX()][s.getHero().getY()] = 'K';
						else
							s.getMaze().getMaze()[s.getHero().getX()][s.getHero().getY()] = 'A';

					} else {
						if (!s.getDragons()[i].isAsleep()) { // IF DRAGON IS
																// AWAKE, HERO
																// DIES
							s.getHero().setHeroAlive(false);
							s.getMaze().getMaze()[s.getDragons()[i].getX()][s.getDragons()[i].getY()] = 'D';
							s.getMaze().getMaze()[s.getHero().getX()][s.getHero().getY()] = ' ';
						}
						if (s.getDragons()[i].isAsleep()) {
							// if dragon is asleep, nothing happens to the
							// player
						}

					}
				}
				// }
			}
		}

	}

	// public static double distance(int x, int y, int x1, int y1) {
	// return Math.sqrt(Math.pow((x1 - x), 2) + Math.pow((y1 - y), 2));
	// }

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