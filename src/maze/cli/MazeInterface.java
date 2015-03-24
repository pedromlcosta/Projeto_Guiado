package maze.cli;

import maze.logic.Main;
import maze.logic.Status;
import java.util.Scanner;

public class MazeInterface {
	private static final int DEFAULT_MAZE = 1;
	private static final int RANDOM_MAZE = 2;
	static Scanner read = new Scanner(System.in);

	public static void main(String args[]) {

		game();

	}

	public static void game() {
		Status s = new Status();
		char answer;
		int size;
		do {
			System.out.println("What would you like to do? ");
			System.out.println("1 - Default Maze");
			System.out.println("2 - Random Generated Maze");
			Scanner read = new Scanner(System.in);
			answer = read.next().charAt(0);

			if (answer == '1') {
				s.setDragonChoice(1);
				s.setMazeChoice(DEFAULT_MAZE);
				defaultMaze(s);
				// geras as coisas
			}
			if (answer == '2') {

				read.reset();
				do {
					System.out.println("Pick the size of the board: (The size of the board must be Odd)");
					read = new Scanner(System.in);
					size = read.nextInt();

				} while (size % 2 == 0);
				// STUB FOR THE USER TO DECIDE THE DRAGON CHOICE
				int dragonChoice;
				do {
					System.out.println("Which mode would you like for the dragon? ");
					System.out.println("1 - Static Dragon");
					System.out.println("2 - Random Moving Dragon");
					System.out.println("3 - Random Moving Dragon w/ sleep time");
					read = new Scanner(System.in);
					dragonChoice = read.nextInt();
					read.reset();
				} while (dragonChoice < 1 && dragonChoice > 3);

				s.setDragonChoice(dragonChoice);
				s.setMazeChoice(RANDOM_MAZE);
				randomMaze(s, size);
				 

			}

			if (answer == '1' || answer == '2')
				break;

		} while (answer != '1' && answer != '2');
		Main.print_maze(s.getMaze().getMaze());
		while (!((s.getHero().getX() == s.getExit().getX() && s.getHero().getY() == s.getExit().getY())) && s.getHero().isHeroAlive() == true) {

			System.out.println("Press W/A/S/D/E/T "); /* char option */
			answer = read.next().charAt(0);

			if (answer == 'T' || answer == 't') {
				if (s.getHero().getnDarts() != 0) {

					System.out.println("Press W/A/S/D ");
					answer = read.next().charAt(0);
					s.throwDart(answer);
					read.nextLine();
				}
			}
			Main.updateBoard(s, answer);
			if (s.getHero().isArmed()) {
				if (s.getHero().getX() == s.getExit().getX() && s.getHero().getY() == s.getExit().getY())
					break;
			}

			Main.print_maze(s.getMaze().getMaze());
		}
		Main.print_maze(s.getMaze().getMaze());
		if (!s.getHero().isHeroAlive())
			System.out.println("The hero has died in battle.");

		System.out.println("\nEND");
		read.close();
	}

	public static void defaultMaze(Status status) {

		char maze[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		// Maze lab = new Maze(maze);
		status.getMaze().setMaze(maze);
		status.setHeroPos(1, 1);
		status.setExitPos(5, 9);
		status.setSwordPos(4, 1);
		status.initDragon('D');
		status.setDragonPos(3, 1);
		// criar função que chamada tipo dragons.default() ???
	}

	public static void randomMaze(Status status, int size) {
		status.getMaze().setMaze(new char[size][size]);
		// char[][] maze = new char[size][size]; Maze lab = new Maze(maze);
		// status.setMaze(lab);
		Main.generate(status);
	}
}