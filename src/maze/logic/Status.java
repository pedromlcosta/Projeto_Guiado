package maze.logic;

import maze.logic.Main;
import maze.logic.Hero;

public class Status {
	Dragon dragons[];
	Hero hero;
	Exit exit;
	Sword sword;
	Shield shield;
	Darts darts[];
	boolean end;
	Maze maze;
	int dragonChoice;
	int mazeChoice;

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
		end = false;
		dragons = new Dragon[1];
		darts = new Darts[1];
		hero = new Hero(0, 0, 'H');
		sword = new Sword(0, 0, 'E');
		shield = new Shield(0, 0, 'P');
		exit = new Exit(0, 0, 'S');
		maze = new Maze(0);

	}

	public Dragon[] getDragons() {
		return dragons;
	}

	//TODO: mudar para "i"
	public void Init_Numbers() {
		dragons = new Dragon[Main.random(maze.getMaze().length / 2)];
		darts = new Darts[Main.random(maze.getMaze().length / 2)];
	}

	public void initDragons() {
		for (int i = 0; i < dragons.length; i++)
			maze.generateCharacter(dragons[i]);

	}

	public void setDragons(Dragon[] dragons) {
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

	public Darts[] getDarts() {
		return darts;
	}

	public void setDarts(Darts[] darts) {
		this.darts = darts;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
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
		maze.getMaze()[this.hero.getX()][this.hero.getX()] = 'H';

	}

	public void setSwordPos(int x, int y) {
		if (!Main.insideBoardEle(x, y, maze.getMaze()))
			return;
		// maze.getMaze()[this.sword.getX()][this.sword.getX()] = ' ';
		this.sword.setX(x);
		this.sword.setY(y);
		maze.getMaze()[this.sword.getX()][this.sword.getX()] = 'E';

	}

	public void setExitPos(int x, int y) {

		// maze.getMaze()[this.exit.getX()][this.exit.getX()] = ' ';
		this.exit.setX(x);
		this.exit.setY(y);
		maze.getMaze()[this.exit.getX()][this.exit.getY()] = 'S';

	}

	public void setShieldPos(int x, int y) {
		if (!Main.insideBoardEle(x, y, maze.getMaze()))
			return;
		// maze.getMaze()[this.shield.getX()][this.shield.getX()] = ' ';
		this.shield.setX(x);
		this.shield.setY(y);
		maze.getMaze()[this.shield.getX()][this.shield.getX()] = 'P';
	}

	// Só usar quando só houver UM dragão
	public void setDragonPos(int x, int y) {
		if (!Main.insideBoardEle(x, y, maze.getMaze()))
			return;
		// maze.getMaze()[dragons[0].getX()][dragons[0].getY()] = ' ';
		dragons[0].setX(x);
		dragons[0].setY(y);
		maze.getMaze()[dragons[0].getX()][dragons[0].getY()] = dragons[0].getFigure();
	}

	public void initDragon(char figure) {
		for (int i = 0; i < dragons.length; i++) {
			dragons[i] = new Dragon(0, 0, figure);
		}
	}

	public boolean dragonsAlive(){
		for (int i = 0; i < dragons.length; i++){
			if(dragons[i].isDragonAlive())
				return true;   // se ha algum vivo, da true
		}
		return false; // se nao houve nenhum vivo, e false
	}
	
	public void heroDarts() {

		for (int i = 0; i < darts.length; i++) {
			if (!darts[i].isPickedUp()) {
				if (darts[i].getX() == hero.getX() && darts[i].getY() == hero.getY()) {

					darts[i].setPickedUp(true);
					hero.incDarts();
				}
			}
		}
	}

	public void initDarts(char figure) {
		for (int i = 0; i < darts.length; i++) {
			darts[i] = new Darts(0, 0, figure);
		}

	}

	public void throwDart(char direction) {
		hero.decDarts();
		switch (direction) {
		case 'w':
		case 'W':
			for (int i = 0; i < dragons.length; i++) {
				if (hero.getY() == dragons[i].getY() && hero.getX() > dragons[i].getX()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons[i].getX(), dragons[i].getY())) {
						dragons[i].setDragonAlive(false);
						maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = ' ';
					}
					break;
				}

			}
			break;
		case 'a':
		case 'A':
			for (int i = 0; i < dragons.length; i++) {
				if (hero.getX() == dragons[i].getX() && hero.getY() > dragons[i].getY()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons[i].getX(), dragons[i].getY())) {
						dragons[i].setDragonAlive(false);
						maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = ' ';
					}
					break;
				}
			}
			break;
		case 's':
		case 'S':
			for (int i = 0; i < dragons.length; i++) {
				if (hero.getY() == dragons[i].getY() && hero.getX() < dragons[i].getX()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons[i].getX(), dragons[i].getY())) {
						dragons[i].setDragonAlive(false);
						maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = ' ';
					}
					break;
				}
			}
			break;
		case 'd':
		case 'D':
			for (int i = 0; i < dragons.length; i++) {
				if (hero.getX() == dragons[i].getX() && hero.getY() < dragons[i].getY()) {
					if (!obstacles(hero.getX(), hero.getY(), dragons[i].getX(), dragons[i].getY())) {
						dragons[i].setDragonAlive(false);
						maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = ' ';
					}
					break;
				}
			}
			break;
		}

	}

	public boolean distance(int x, int y, int x1, int y1, int dist) {
		return ((x == x1 && y1 >= (y - dist) && y1 <= (y + dist)) || (y1 == y && x1 >= (x - dist) && x1 <= (x + dist)));
	}

	public boolean obstacles(int x, int y, int x1, int y1) {

		int dist;
		if (x == x1) {
			dist = Math.abs(y1 - y);
			if (y < y1) {

				for (int i = 0; i < dist; i++) {
					if (maze.getMaze()[x][y + i] == 'X') {
						System.out.println(true);
						return true;
					}
				}
				return false;

			} else {

				for (int i = 0; i < dist; i--) {
					if (maze.getMaze()[x][y + i] == 'X') {
						System.out.println(true);
						return true;
					}
				}
				return false;

			}

		} else if (y == y1) {
			dist = Math.abs(x1 - x);
			if (x < x1) {
				for (int i = 0; i < dist; i++) {
					if (maze.getMaze()[x + i][y] == 'X') {
						System.out.println(true);
						return true;
					}
				}
				return false;

			} else {
				for (int i = 0; i < dist; i--) {
					if (maze.getMaze()[x + i][y] == 'X') {
						System.out.println(true);
						return true;
					}
				}
				return false;

			}

		}

		return false;
	}

	public void generate() {
		Main.fillMaze(maze.getMaze(), 'X');
		maze.generateMaze(Main.randomOddNumber(maze.getMaze().length), Main.randomOddNumber(maze.getMaze().length));
		maze.generateExit(this);
		maze.generateCharacter(hero);
		maze.generateCharacter(sword);
		maze.generateCharacter(shield);
		Init_Numbers();
		// call a function which depending on the size of the board will "count"
		// the number of dragons
		initDragon('D');
		//TODO: substituir por funcao?
		for (int i = 0; i < dragons.length; i++)
			maze.generateCharacter(dragons[i]);
		initDarts('-');
		for (int i = 0; i < darts.length; i++)
			maze.generateCharacter(darts[i]);

	}

	public void updateBoard(char inputChar) {

		switch (inputChar) {
		case 'A':
		case 'a':
			move_hero(hero.getX(), hero.getY() - 1);
			break;

		case 'W':
		case 'w':
			move_hero(hero.getX() - 1, hero.getY());
			break;

		case 'D':
		case 'd':
			move_hero(hero.getX(), hero.getY() + 1);
			break;

		case 'S':
		case 's':
			move_hero(hero.getX() + 1, hero.getY());
			break;
		}

		// DRAGON MOVEMENT STUB
		for (int i = 0; i < dragons.length; i++)
			if (getDragonChoice() == 1) {
				// OPTION 1 -> STATIC DRAGON
			} else if (getDragonChoice() == 2) {
				// OPTION 2 -> DRAGON WITH RANDOM MOVEMENT
				move_dragon(dragons[i]);
			} else {

				// OPTION 3 -> DRAGON WITH RANDOM MOVEMENT AND SLEEP
				int number = Main.random(10);
				if (dragons[i].isDragonAlive()) {
					if (dragons[i].isAsleep()) { // IF ALREADY ASLEEP,
													// HAS
													// 20% CHANCE TO WAKE UP
													// AND
													// MOVE
						if (number == 0 || number == 1) {
							dragons[i].setAsleep(false);
							dragons[i].setFigure('D');
							maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = 'D';
							move_dragon(dragons[i]);
						}
					} else { // IF NOT ASLEEP, HAS 10% CHANCE TO GO ASLEEP AND
								// 90%
								// CHANCE TO MOVE
						if (number == 0) {
							dragons[i].setAsleep(true);
							dragons[i].setFigure('Z');
							maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = 'Z';
						} else
							move_dragon(dragons[i]);
					}

				}
			}
		update_status();

	}

	// No need to adaptar,alterou-se a chamada, mandar apenas o heroi como
	// argumento
	public boolean move_hero(int newX, int newY) {

		if (maze.getMaze()[newX][newY] == 'X' || maze.getMaze()[newX][newY] == 'Z') {// ver
			return false;
		}

		else {

			if (maze.getMaze()[newX][newY] == 'E') {
				// encontrou espada
				hero.setArmed(true);

				if (hero.isHasShield()) {
					hero.setFigure('K');
					maze.getMaze()[newX][newY] = 'K';
				} else {
					hero.setFigure('A');
					maze.getMaze()[newX][newY] = 'A';
				}
			}

			if (maze.getMaze()[newX][newY] == 'P')// need to change this
			{
				// encontrou escudo
				hero.setHasShield(true);

				if (hero.isArmed()) {
					hero.setFigure('K');
					maze.getMaze()[newX][newY] = 'K';
				} else {
					hero.setFigure('p');
					maze.getMaze()[newX][newY] = 'p';

				}
			}

			if (hero.isArmed() && hero.isHasShield()) {
				hero.setFigure('K');
				maze.getMaze()[newX][newY] = 'K';
			} else if (hero.isHasShield() && (!hero.isArmed())) {
				if (maze.getMaze()[newX][newY] == 'D')// check dragon and stuff
					return false;
				hero.setFigure('p');
				maze.getMaze()[newX][newY] = 'p';
			}
			if (hero.isArmed() && (!hero.isHasShield())) { // Esta
				hero.setFigure('A'); // armado
				maze.getMaze()[newX][newY] = 'A';
			} else {
				if (maze.getMaze()[newX][newY] == 'S') {
					return false;
				}

				if (!(hero.isArmed() || hero.isHasShield())) {
					hero.setFigure('H');
					maze.getMaze()[newX][newY] = 'H';
				}
			}

			maze.getMaze()[hero.getX()][hero.getY()] = ' ';
			hero.setX(newX);
			hero.setY(newY);
			heroDarts();
			return true;
		}

	}

	// adaptar para classes
	public boolean move_dragon(Dragon dragon) {

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
		} while (maze.getMaze()[new_x][new_y] != ' ' || (new_x == sword.getX() && new_y == sword.getY()));
		if (maze.getMaze()[new_x][new_y] == 'E') {
			// -> If the
			// dragon
			// lands on the
			// sword
			maze.getMaze()[new_x][new_y] = 'F';
			maze.getMaze()[dragon.getX()][dragon.getY()] = ' ';
			dragon.setSwordDragon(true);
		} else { // -> If the dragon doesn't land on the sword
			dragon.setFigure('D');
			maze.getMaze()[new_x][new_y] = 'D';// check
			if (dragon.isSwordDragon()) { // -> Was on the sword's place and
											// left it
				dragon.setFigure('E');
				maze.getMaze()[dragon.getX()][dragon.getY()] = 'E';
			} else {
				maze.getMaze()[dragon.getX()][dragon.getY()] = ' ';
			}
			dragon.setSwordDragon(false);
		}

		dragon.setX(new_x);
		dragon.setY(new_y);

		return true;
	}

	// Updates status - is only called after the coordinates change(after the
	// hero and dragon move

	private void update_status() {

		for (int i = 0; i < getDragons().length; i++) {

			if (dragons[i].isDragonAlive()) {

				// only worth checking if the hero is 3<= distance from the
				// dragon if doesn´t have the shield
				if (!hero.isHasShield()) {

					if (!dragons[i].isAsleep()) {
						if (distance(hero.getX(), hero.getY(), dragons[i].getX(), dragons[i].getY(), 3)) {
							if (!obstacles(hero.getX(), hero.getY(), dragons[i].getX(), dragons[i].getY())) {
								hero.setHeroAlive(false);
								maze.getMaze()[hero.getX()][hero.getY()] = ' ';
							}
						}
					}
				}
				// else if (s.getHero().isHasShield()) {
				if (distance(hero.getX(), hero.getY(), dragons[i].getX(), dragons[i].getY(), 1)) {

					// System.out.println("Dragao adjacente");
					if (hero.isArmed()) {
						dragons[i].setDragonAlive(false);
						dragons[i].setFigure(' ');
						maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = ' ';
						if (hero.isHasShield()) {
							hero.setFigure('K');
							maze.getMaze()[hero.getX()][hero.getY()] = 'K';
						} else {
							hero.setFigure('A');
							maze.getMaze()[hero.getX()][hero.getY()] = 'A';
						}
					} else {
						if (!dragons[i].isAsleep()) { // IF DRAGON IS
														// AWAKE, HERO
														// DIES
							hero.setHeroAlive(false);
							dragons[i].setFigure('D');
							hero.setFigure(' ');
							maze.getMaze()[dragons[i].getX()][dragons[i].getY()] = 'D';
							maze.getMaze()[hero.getX()][hero.getY()] = ' ';
						}
						if (dragons[i].isAsleep()) {
							// if dragon is asleep, nothing happens to the
							// player
						}

					}
				}
				// }
			}
		}

	}
}