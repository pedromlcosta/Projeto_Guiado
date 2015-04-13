package maze.logic;

import java.io.Serializable;
import java.util.ArrayList;

import maze.logic.Main;
import maze.logic.Hero;

public class Status implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int m = 0;
	static int n = 0;
	// Dragon dragons[];
	ArrayList<Dragon> dragons;
	Hero hero;
	Exit exit;
	Sword sword;
	Shield shield;
	// Darts darts[];
	ArrayList<Darts> darts;
	Maze maze;
	int dragonChoice, mazeChoice, dragonSize, dartsSize;

	public int getDragonSize() {
		return dragonSize;
	}

	public void setDragonSize(int dragonSize) {
		this.dragonSize = dragonSize;
	}

	public int getDartsSize() {
		return dartsSize;
	}

	public void setDartsSize(int dartsSize) {
		this.dartsSize = dartsSize;
	}

	boolean gameOver;

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getMazeChoice() {
		return mazeChoice;
	}

	public void setMazeChoice(int mazeChoice) {
		this.mazeChoice = mazeChoice;
	}

	public int getDragonChoice() {
		return dragonChoice;
	}

	public void setDragonChoice(int dragonChoice) {
		this.dragonChoice = dragonChoice;
	}

	public Status() {
		// quantos dragões devem existir
		gameOver = false;
		// dragons = new Dragon[1];
		// darts = new Darts[1];
		dragons = new ArrayList<Dragon>();
		darts = new ArrayList<Darts>();
		hero = new Hero(0, 0, 'H');
		sword = new Sword(0, 0, 'E');
		shield = new Shield(0, 0, 'P');
		exit = new Exit(0, 0, 'S');
		maze = new Maze(0);

	}

	public int getHeroX() {
		return hero.getX();
	}

	public int getHeroY() {
		return hero.getY();
	}

	public int getSwordX() {
		return sword.getX();
	}

	public int getSwordY() {
		return sword.getY();
	}

	public int getDragonX(int i) {
		return dragons.get(i).getX();
	}

	public int getDragonY(int i) {
		return dragons.get(i).getY();
	}

	public ArrayList<Dragon> getDragons() {
		return dragons;
	}
	
	// TODO: mudar para "i"
	public void initNumbers() {
		// dragons = new Dragon[Main.random(maze.getMaze().length / 2) + 1];
		//
		// darts = new Darts[Main.random(maze.getMaze().length / 2) + 1];
	}

	public void initDragons(char figure, int nDragons) {
		for (int i = 0; i < nDragons; i++) {
			dragons.add(new Dragon(0, 0, figure));
			maze.generateCharacter(dragons.get(i));
		}
	}

	public void setDragons(ArrayList<Dragon> dragons) {
		this.dragons = dragons;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Exit getExit() {
		return exit;
	}

	public void setExit(Exit exit) {
		this.exit = exit;
	}

	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public ArrayList<Darts> getDarts() {
		return darts;
	}

	public void setDarts(ArrayList<Darts> darts) {
		this.darts = darts;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	public void setHeroPos(int x, int y) {
		if (!Main.insideBoardEle(x, y, maze.getMaze()))
			return;
		// maze.getMaze()[this.hero.getX()][this.hero.getX()] = ' ';
		this.hero.setX(x);
		this.hero.setY(y); // TODO: Change to "set and get"
		maze.getMaze()[this.hero.getY()][this.hero.getX()] = 'H';

	}

	public void setSwordPos(int x, int y) {
		if (!Main.insideBoardEle(x, y, maze.getMaze()))
			return;
		// maze.getMaze()[this.sword.getX()][this.sword.getX()] = ' ';
		this.sword.setX(x);
		this.sword.setY(y);
		maze.getMaze()[this.sword.getY()][this.sword.getX()] = 'E';

	}

	public void setExitPos(int x, int y) {

		// maze.getMaze()[this.exit.getX()][this.exit.getX()] = ' ';
		this.exit.setX(x);
		this.exit.setY(y);
		maze.getMaze()[this.exit.getY()][this.exit.getX()] = 'S';

	}

	public void setShieldPos(int x, int y) {
		if (!Main.insideBoardEle(x, y, maze.getMaze()))
			return;
		// maze.getMaze()[this.shield.getX()][this.shield.getX()] = ' ';
		this.shield.setX(x);
		this.shield.setY(y);
		maze.getMaze()[this.shield.getY()][this.shield.getX()] = 'P';
	}

	// Só usar quando só houver UM dragão
	public void setDragonPos(int x, int y) {
		if (!Main.insideBoardEle(x, y, maze.getMaze()))
			return;
		// maze.getMaze()[dragons[0].getX()][dragons[0].getY()] = ' ';
		dragons.get(0).setX(x);
		dragons.get(0).setY(y);
		maze.getMaze()[dragons.get(0).getY()][dragons.get(0).getX()] = 'D';
	}

	public boolean dragonsAlive() {
		if(dragons.isEmpty())
			return false;
		
		for (int i = 0; i < dragons.size(); i++) {
			if (dragons.get(i).isDragonAlive())
				return true; // se ha algum vivo, da true
		}
		return false; // se nao houve nenhum vivo, e false
	}

	public void heroDarts() {
		if (mazeChoice == 1)
			return;
		for (int i = 0; i < darts.size(); i++) {
			if (!darts.get(i).isPickedUp()) {
				if (darts.get(i).getX() == hero.getX() && darts.get(i).getY() == hero.getY()) {
					darts.get(i).setPickedUp(true);
					hero.incDarts();
				}
			}
		}
	}

	public void initDarts(char figure, int nDarts) {
		for (int i = 0; i < nDarts; i++) {
			darts.add(new Darts(0, 0, figure));
			darts.get(i).setPickedUp(false);
			maze.generateCharacter(darts.get(i));
		}
	}

	public void throwDart(char direction) {
		hero.decDarts();
		switch (direction) {
		case 'w':
		case 'W':
			for (int i = 0; i < dragons.size(); i++) {
				if (hero.getX() == dragons.get(i).getX() && hero.getY() > dragons.get(i).getY()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons.get(i).getX(), dragons.get(i).getY())) {
						dragons.get(i).setDragonAlive(false);
						maze.getMaze()[dragons.get(i).getY()][dragons.get(i).getX()] = ' ';
					}
					break;
				}

			}
			break;
		case 'a':
		case 'A':
			for (int i = 0; i < dragons.size(); i++) {
				if (hero.getY() == dragons.get(i).getY() && hero.getX() > dragons.get(i).getX()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons.get(i).getX(), dragons.get(i).getY())) {
						dragons.get(i).setDragonAlive(false);
						maze.getMaze()[dragons.get(i).getY()][dragons.get(i).getX()] = ' ';
					}
					break;
				}
			}
			break;
		case 's':
		case 'S':
			for (int i = 0; i < dragons.size(); i++) {
				if (hero.getX() == dragons.get(i).getX() && hero.getY() < dragons.get(i).getY()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons.get(i).getX(), dragons.get(i).getY())) {
						dragons.get(i).setDragonAlive(false);
						maze.getMaze()[dragons.get(i).getY()][dragons.get(i).getX()] = ' ';
					}
					break;
				}
			}
			break;
		case 'd':
		case 'D':
			for (int i = 0; i < dragons.size(); i++) {
				if (hero.getY() == dragons.get(i).getY() && hero.getX() < dragons.get(i).getX()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons.get(i).getX(), dragons.get(i).getY())) {
						dragons.get(i).setDragonAlive(false);
						maze.getMaze()[dragons.get(i).getY()][dragons.get(i).getX()] = ' ';
					}
					break;
				}
			}
			break;
		}

	}

	public boolean insideRange(int x, int y, int x1, int y1, int dist) {
		return ((x == x1 && Math.abs(y1 - y) <= dist) || (y1 == y && Math.abs(x1 - x) <= dist));
	}

	public boolean insideRange(int x, int y, int x1, int y1) {
		return ((x == x1) || (y1 == y));

	}

	public boolean obstacles(int x, int y, int x1, int y1) {//

		int dist;
		if (x == x1) {
			dist = Math.abs(y1 - y);
			if (y < y1) {

				for (int i = 0; i < dist; i++) {
					if (maze.getMaze()[y + i][x] == 'X') {
						// System.out.println(true);
						return true;
					}
				}
				return false;

			} else {

				for (int i = 0; i < dist; i++) {
					if (maze.getMaze()[y - i][x] == 'X') {
						// System.out.println(true);
						return true;
					}
				}
				return false;

			}

		} else if (y == y1) {
			dist = Math.abs(x1 - x);
			if (x < x1) {
				for (int i = 0; i < dist; i++) {
					if (maze.getMaze()[y][x + i] == 'X') {
						// System.out.println(true);
						return true;
					}
				}
				return false;

			} else {
				for (int i = 0; i < dist; i++) {
					if (maze.getMaze()[y][x - i] == 'X') {
						return true;
					}
				}
				return false;

			}

		}

		return false;
	}

	public void generate(int choice) {
		Main.fillMaze(maze.getMaze(), 'X');
		maze.generateMaze(Main.randomOddNumber(maze.getMaze().length), Main.randomOddNumber(maze.getMaze().length));
		maze.generateExit(this);
		maze.generateCharacter(hero);
		maze.generateCharacter(sword);
		maze.generateCharacter(shield);
		if (choice == 0) {
			initDarts('-', Main.random(maze.getMaze().length / 2) + 1);
			initDragons('D', Main.random(maze.getMaze().length / 2) + 1);
		} else {
			initDarts('-', dartsSize);
			initDragons('D', dragonSize);
		}
		// call a function which depending on the size of the board will "count"
		// the number of dragons

		// TODO: substituir por funcao?

	}

	public void createMazeWalls() {
		char temp[][] = maze.getMaze();

		// Enche tudo com espaços vazios
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				temp[i][j] = ' ';
			}
		}
		// Enche os lados e cantos com paredes
		for (int i = 0; i < temp.length; i++) {
			temp[0][i] = 'X';
			temp[temp.length - 1][i] = 'X';
			temp[i][0] = 'X';
			temp[i][temp.length - 1] = 'X';
		}
		maze.setMaze(temp);
	}

	public void updateBoard(char inputChar) {

		boolean heroPlayed = false;

		switch (inputChar) {
		case 'A':
		case 'a':
			heroPlayed = moveHero(hero.getX() - 1, hero.getY());
			break;

		case 'W':
		case 'w':
			heroPlayed = moveHero(hero.getX(), hero.getY() - 1);
			break;

		case 'D':
		case 'd':
			heroPlayed = moveHero(hero.getX() + 1, hero.getY());
			break;

		case 'S':
		case 's':
			heroPlayed = moveHero(hero.getX(), hero.getY() + 1);
			break;

		case 'E':
		case 'e':
			heroPlayed = true; // Hero played, but decided to stay on the same
								// place
			break;
		}
		if (heroPlayed) {
			// DRAGON MOVEMENT STUB - dragon only moves if the player played
			for (int i = 0; i < dragons.size(); i++)
				if (getDragonChoice() == 1) {
					// OPTION 1 -> STATIC DRAGON
				} else if (getDragonChoice() == 2) {
					// OPTION 2 -> DRAGON WITH RANDOM MOVEMENT
					moveDragon(dragons.get(i));
				} else {

					// OPTION 3 -> DRAGON WITH RANDOM MOVEMENT AND SLEEP
					int number = Main.random(10);
					if (dragons.get(i).isDragonAlive()) {
						if (dragons.get(i).isAsleep()) { // IF ALREADY ASLEEP,
															// HAS
															// 20% CHANCE TO
															// WAKE UP
															// AND
															// MOVE
							if (number == 0 || number == 1) {
								dragons.get(i).setAsleep(false);
								dragons.get(i).setFigure('D');
								maze.getMaze()[dragons.get(i).getY()][dragons.get(i).getX()] = 'D';
								moveDragon(dragons.get(i));
							}
						} else { // IF NOT ASLEEP, HAS 10% CHANCE TO GO ASLEEP
									// AND
									// 90%
									// CHANCE TO MOVE
							if (number == 0) {
								dragons.get(i).setAsleep(true);
								dragons.get(i).setFigure('Z');
								maze.getMaze()[dragons.get(i).getY()][dragons.get(i).getX()] = 'Z';
							} else
								moveDragon(dragons.get(i));
						}

					}
				}
		}
		updateStatus();

	}

	// TODO: Alterar para ver se a mudanca for valida, mudar so x e y, senao
	// ficar
	// O resto das alteracoes, passar para a funcao updateStatus

	public boolean moveHero(int newX, int newY) {

		// INVALID MOVEMENTS - parede, dragao adormecido ou ir para a saida
		// quando nao pode
		if (maze.getMaze()[newY][newX] == 'X' || maze.getMaze()[newY][newX] == 'Z') {// ver
			return false;
		}

		else if (maze.getMaze()[newY][newX] == 'S' && !(hero.isArmed() && !dragonsAlive())) {
			return false;
		}

		// VALID MOVEMENTS - espada, escudo, espaco vazio, dardo e saida
		else {

			if (maze.getMaze()[newY][newX] == 'E') {
				// Encontrou espada
				hero.setArmed(true);

				if (hero.isHasShield()) {
					hero.setFigure('K');
					maze.getMaze()[newY][newX] = 'K';
				} else {
					hero.setFigure('A');
					maze.getMaze()[newY][newX] = 'A';
				}
			}

			if (maze.getMaze()[newY][newX] == 'P') {
				// Encontrou escudo
				hero.setHasShield(true);

				if (hero.isArmed()) {
					hero.setFigure('K');
					maze.getMaze()[newY][newX] = 'K';
				} else {
					hero.setFigure('p');
					maze.getMaze()[newY][newX] = 'p';

				}
			}

			if (hero.isArmed() && hero.isHasShield()) {
				maze.getMaze()[newY][newX] = 'K';
			} else if (hero.isHasShield() && (!hero.isArmed())) {
				if (maze.getMaze()[newY][newX] == 'D') // if he has the shield,
														// he can move next to
														// the dragon, we need
														// to limit the movment
					return false;
				maze.getMaze()[newY][newX] = 'p';
			} else if (hero.isArmed() && (!hero.isHasShield())) {
				maze.getMaze()[newY][newX] = 'A';
			} else if (!hero.isArmed() && !hero.isHasShield()) {
				maze.getMaze()[newY][newX] = 'H';
			}

			// //-----UPDATES POSITION-----////
			maze.getMaze()[hero.getY()][hero.getX()] = ' '; // Place where he
															// was becomes empty
			hero.setX(newX);
			hero.setY(newY);
			heroDarts();
			return true;
		}
	}

	public boolean moveDragon(Dragon dragon) {

		int new_x = 0, new_y = 0;

		if (!dragon.isDragonAlive())
			return false;

		// Faz random ate conseguir uma direcao valida (que nao e parede nem
		// saida)
		do {
			int random = Main.random(5);

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
		} while (maze.getMaze()[new_y][new_x] != ' ' && (maze.getMaze()[new_y][new_x] != 'E'));
		if (maze.getMaze()[new_y][new_x] == 'E') { // Dragon landed on the sword
			maze.getMaze()[new_y][new_x] = 'F';
			maze.getMaze()[dragon.getX()][dragon.getY()] = ' ';
			dragon.setSwordDragon(true);
		} else { // -> If the dragon doesn't land on the sword
			dragon.setFigure('D');
			maze.getMaze()[new_y][new_x] = 'D';// check
			if (dragon.isSwordDragon()) { // -> Was on the sword's place and
											// left it
				dragon.setFigure('E');
				maze.getMaze()[dragon.getY()][dragon.getX()] = 'E';
			} else {
				maze.getMaze()[dragon.getY()][dragon.getX()] = ' ';
			}
			dragon.setSwordDragon(false);
		}

		dragon.setX(new_x);
		dragon.setY(new_y);

		return true;
	}

	// Updates status - is only called after the coordinates change(after the
	// units move)

	private void updateStatus() {

		int dragonRange = 1;
		if (mazeChoice == 2) {
			if (hero.hasShield)
				dragonRange = 1;
			else if (!hero.hasShield)
				dragonRange = 3;
		}

		for (int i = 0; i < getDragons().size(); i++) {

			if (dragons.get(i).isDragonAlive()) {

				if (insideRange(hero.getX(), hero.getY(), dragons.get(i).getX(), dragons.get(i).getY(), 1) && hero.isArmed()) {

					maze.getMaze()[dragons.get(i).getY()][dragons.get(i).getX()] = ' ';
					dragons.get(i).setDragonAlive(false);

				} else if (insideRange(hero.getX(), hero.getY(), dragons.get(i).getX(), dragons.get(i).getY(), dragonRange) && !dragons.get(i).isAsleep) {
					if (!obstacles(hero.getX(), hero.getY(), dragons.get(i).getX(), dragons.get(i).getY())) {
						// System.out.println("Morto");
						maze.getMaze()[hero.getY()][hero.getX()] = ' ';
						hero.setHeroAlive(false);
					}
				}
			}

			// CHECKS IF GAME IS OVER
			if (hero.isArmed() && !dragonsAlive()) {
				if (hero.getX() == exit.getX() && hero.getY() == exit.getY()) {
					gameOver = true;
				}
			}

			if (!hero.isHeroAlive()) {
				gameOver = true;
			}
		}
	}

	public void defaultMaze() {

		char maze[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		setMazeChoice(1);
		setDragonChoice(1);
		getMaze().setMaze(maze);
		setHeroPos(1, 1);
		setExitPos(9, 5);
		setSwordPos(4, 4);
		// setDragons(new Dragon[1]);
		// dragons[0] = new Dragon(0, 0, 'D');
		dragons.add(new Dragon(0, 0, 'D'));
		setDragonPos(1, 3);
	}

	public void randomMaze(int size, int choice) {
		maze.setMaze(new char[size][size]);
		generate(choice);
	}
}
