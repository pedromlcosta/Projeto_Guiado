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
 
	public void Init_Numbers() {
		dragons = new Dragon[Main.random(maze.getMaze().length / 4)];
		darts = new Darts[Main.random(maze.getMaze().length / 4)];

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

	public void heroDarts() {

		if (hero.getnDarts() == 0)
			return;
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
}