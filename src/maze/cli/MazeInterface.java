package maze.cli;

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

		readInitialInput(s);

		print_maze(s.getMaze().getMaze());

		// ///-----MAIN GAME CYCLE-----/////
		while (true) {

			// ///------READS USER INPUT------/////
			System.out.println("Press W/A/S/D/E/T "); /* char option */
			answer = read.next().charAt(0);

			if (answer == 'T' || answer == 't') {
				if (s.getHero().getnDarts() != 0) {
					System.out.println("Press W/A/S/D/C (C cancela o lançamento) ");
					answer = read.next().charAt(0);
					if (answer != 'C') {
						s.throwDart(answer);
					}
					read.nextLine();
				}
			}

			// ///------UPDATES BOARD ACCORDINGLY------/////
			s.updateBoard(answer);

			// ///------PRINTS CURRENT MAZE------/////
			print_maze(s.getMaze().getMaze());

			if (!s.getHero().isHeroAlive()) {
				System.out.println("Morreu.");
			} else {
				System.out.println("Esta vivo");
			}

			// ///-----CHECKS IF GAME IS FINISHED------/////
			// TODO Matar tudo ao finalizar o jogo
			if (s.isGameOver())
				break;
		}

		print_maze(s.getMaze().getMaze());

		if (!s.getHero().isHeroAlive())
			System.out.println("The hero has died in battle.");

		System.out.println("\nEND");
		read.close();
	}

	private static void readInitialInput(Status s) {
		char answer;
		int size;
		Scanner read = new Scanner(System.in);
		do {
			System.out.println("What would you like to do? ");
			System.out.println("1 - Default Maze");
			System.out.println("2 - Random Generated Maze");
			read.reset();
			answer = read.next().charAt(0);

			if (answer == '1') {
				s.setDragonChoice(1);
				s.setMazeChoice(DEFAULT_MAZE);
				s.defaultMaze();
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
				s.randomMaze(size,0);

			}

			if (answer == '1' || answer == '2'){
				break;
			}
		} while (answer != '1' && answer != '2');
	}

	public static void print_maze(char[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}

		System.out.println("");
	}


}