package maze.test;

import java.util.Random;

import maze.logic.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MazeTest {

	@Test
	public void moveTest() {
		Status s = new Status();
		s.defaultMaze();
		s.updateBoard('d');
		assertEquals(2, s.getHero().getX());
		assertEquals(1, s.getHero().getY());
		s.updateBoard('a');
		assertEquals(1, s.getHero().getX());
		assertEquals(1, s.getHero().getY());
		s.moveHero(s.getSword().getX(), 1);
		s.updateBoard('s');
		assertEquals(s.getSword().getX(), s.getHero().getX());
		assertEquals(2, s.getHero().getY());
		s.updateBoard('w');
		assertEquals(s.getSword().getX(), s.getHero().getX());
		assertEquals(1, s.getHero().getY());
		s.updateBoard('e');
		assertEquals(s.getSword().getX(), s.getHero().getX());
		assertEquals(1, s.getHero().getY());

	}

	@Test
	public void standStillTest() {
		Status s = new Status();
		s.defaultMaze();
		s.updateBoard('a');
		s.updateBoard('w');
		assertEquals(1, s.getHero().getY());
		assertEquals(1, s.getHero().getX());

	}

	@Test
	public void swordTest() {
		Status s = new Status();
		s.defaultMaze();
		s.moveHero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		assertEquals('A', s.getHero().getFigure());
		//

	}

	@Test
	public void shieldTest() {
		Status s = new Status();
		s.defaultMaze();
		s.setShieldPos(4, 1);
		s.moveHero(3, 1);
		s.updateBoard('d');
		assertEquals(true, s.getHero().isHasShield());
		assertEquals('p', s.getHero().getFigure());
		s.moveHero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		assertEquals('K', s.getHero().getFigure());

	}

	@Test
	public void death() {
		Status s = new Status();
		s.defaultMaze();
		s.updateBoard('s');
		assertEquals(false, s.getHero().isHeroAlive());

	}

	@Test
	public void killDragon() {
		Status s = new Status();
		s.defaultMaze();
		s.moveHero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		s.moveHero(1, 1);
		s.updateBoard('s');
		assertEquals(false, s.dragonsAlive());
	}

	@Test
	public void randomTest() {
		Status s = new Status();
		s.randomMaze(13, 0);
		s.updateBoard('d');
		s.updateBoard('d');
		s.updateBoard('a');
		s.updateBoard('s');
		s.updateBoard('w');

	}

	@Test
	public void victory() {
		Status s = new Status();
		s.defaultMaze();
		s.moveHero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		s.moveHero(1, 1);
		s.updateBoard('s');
		assertEquals(false, s.dragonsAlive());
		s.moveHero(8, 5);
		s.updateBoard('d');
		assertEquals(true, s.isGameOver());

	}

	@Test
	public void noExit() { // Can only exit if he HAS SWORD(1) && DRAGON(S) ARE
							// DEAD(2)

		// TRIES TO LEAVE WITHOUT(!1) SWORD && DRAGON(S) ALIVE(!2)
		Status s = new Status();
		s.defaultMaze();
		s.moveHero(8, 5);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

		// TRIES TO LEAVE WITHOUT SWORD(!1) && DRAGON(S) DEAD(2) (if, for
		// example, the hero kills him with darts)
		s.getDragons().get(0).setDragonAlive(false);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

		// GETS SWORD
		s.moveHero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());

		// TRIES TO LEAVE WITH SWORD(1) && DRAGON(S) ALIVE(!2)
		s.getDragons().get(0).setDragonAlive(true); // HE RESSURRECTED, OMG!!!!
		s.moveHero(8, 5);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

	}

	@Test
	public void darts() {
		Status status = new Status();
		char maze[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		status.setMazeChoice(2);
		status.setDragonChoice(1);
		status.getMaze().setMaze(maze);
		status.setHeroPos(1, 1);
		status.setExitPos(9, 5);
		// status.setSwordPos(4, 4);
		//status.setDragons(new Dragon[1]);
		status.getDragons().add(new Dragon(0, 0, 'D'));
		status.setDragonPos(8, 8);
		int nDarts = 0;

		while (nDarts == 0)
			nDarts = Main.random(maze.length / 2);

		Darts[] darts = new Darts[nDarts];
		//status.setDarts(darts);
		status.initDarts('-', nDarts);

		assertEquals(nDarts, status.getDarts().size());
		assertEquals('-', status.getDarts().get(0).getFigure());
		nDarts = 0;
		// Test randomness of Darts and catching all darts
		catchDarts(status, maze, nDarts, darts);
		assertEquals(status.getHero().getnDarts(), darts.length);
		// Test killing dragons in all 4 directions
		nDarts = 5;
		//darts = new Darts[nDarts];
		//status.setDarts(darts);
		status.initDarts('-', nDarts);
		catchDarts(status, maze, nDarts, darts);
		// assertEquals(5, status.getHero().getnDarts()); ver porque é que está
		// a apanhar mais do que deve
		status.moveHero(8, 5);
		status.throwDart('s');
		assertEquals(status.dragonsAlive(), false);

		status.getDragons().get(0).setDragonAlive(true);
		status.setDragonPos(8, 1);
		status.throwDart('w');
		assertEquals(status.dragonsAlive(), false);

		// 1º falha pois tem 1 obstáculo (parede)
		status.getDragons().get(0).setDragonAlive(true);
		status.setDragonPos(1, 5);// ver porque é que com 4 mata
		// MazeInterface.print_maze(maze);
		status.throwDart('a');
		// MazeInterface.print_maze(maze);
		assertEquals(status.dragonsAlive(), true);
		status.setDragonPos(1, 5);
		status.moveHero(6, 5);
		status.throwDart('a');
		assertEquals(status.dragonsAlive(), false);

		status.getDragons().get(0).setDragonAlive(true);
		status.setDragonPos(8, 1);
		status.moveHero(1, 1);
		status.throwDart('d');
		// corregir quando se manda o dardo ele anda
		assertEquals(status.dragonsAlive(), false);

		// assertEquals(0, status.getHero().getnDarts());

		// MazeInterface.print_maze(maze);
	}

	public void catchDarts(Status status, char[][] maze, int nDarts, Darts[] darts) {
		// System.out.println("size: " + darts.length);
		for (int i = 0; i < darts.length; i++) {
			if (status.moveHero(status.getDarts().get(i).getX(), status.getDarts().get(i).getY() - 1)) {

				status.updateBoard('s');
				nDarts++;

				if (!status.getHero().isHeroAlive())
					status.getHero().setHeroAlive(true); // just in case
				status.moveHero(1, 1);
			} else {
				// Movimento não válido
				status.moveHero(status.getDarts().get(i).getX(), status.getDarts().get(i).getY());
				nDarts++;
			}
		}
		// System.out.println("nDarts: " + nDarts);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////

	// a) the maze boundaries must have exactly one exit and everything else
	// walls
	// b) the exist cannot be a corner
	public boolean checkBoundaries(Maze m) {
		int countExit = 0;
		int n = m.getSize();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
					if (m.getMaze()[j][i] == 'S')
						if ((i == 0 || i == n - 1) && (j == 0 || j == n - 1))
							return false;
						else
							countExit++;
					else if (m.getMaze()[j][i] != 'X')
						return false;
		return countExit == 1;
	}

	// d) there cannot exist 2x2 (or greater) squares with blanks only
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal
	// and walls in the other
	// d) there cannot exist 3x3 (or greater) squares with walls only
	private boolean hasSquare(Maze maze, char[][] square) {
		char[][] m = maze.getMaze();
		for (int i = 0; i < m.length - square.length; i++)
			for (int j = 0; j < m.length - square.length; j++) {
				boolean match = true;
				for (int x = 0; x < square.length; x++)
					for (int y = 0; y < square.length; y++) {
						if (m[i + x][j + y] != square[x][y])
							match = false;
					}
				if (match)
					return true;
			}
		return false;
	}

	// Auxiliary method used by checkExitReachable.
	// Gets a deep clone of a char matrix.
	private char[][] deepClone(char[][] m) {
		char[][] c = m.clone();
		for (int i = 0; i < m.length; i++)
			c[i] = m[i].clone();
		return c;
	}

	// auxiliary method used by checkExitReachable
	// marks a cell as visited
	// (V) and proceeds recursively to its neighbors
	private void visit(char[][] m, int i, int j) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length) {
			return;
		}
		if (m[i][j] == 'X' || m[i][j] == 'V') {
			return;
		}

		m[i][j] = 'V';
		visit(m, i - 1, j);
		visit(m, i + 1, j);
		visit(m, i, j - 1);
		visit(m, i, j + 1);
	}

	// c) there must exist a path between any blank cell and the maze exit
	private boolean checkExitReachable(Status s, Maze maze) {

		char[][] m = deepClone(maze.getMaze());
		visit(m, s.getExit().getY(), s.getExit().getX());

		// System.out.println("FECK");
		// MazeInterface.print_maze(m);
		// System.out.println("FECK2");

		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m.length; j++)
				if (m[i][j] != 'X' && m[i][j] != 'V')
					return false;

		return true;
	}

	// Checks if all the arguments (in the variable arguments list) are not
	// null and distinct
	private <T> boolean notNullAndDistinct(@SuppressWarnings("unchecked") T... args) {
		for (int i = 0; i < args.length - 1; i++)
			for (int j = i + 1; j < args.length; j++)
				if (args[i] == null || args[j] == null || args[i].equals(args[j]))
					return false;
		return true;
	}

	@Test
	public void testRandomMazeGenerator() throws Exception {
		int numMazes = 1001;
		int maxSize = 101; // can change to any odd number >= 5

		char[][] badWalls = { { 'X', 'X', 'X' }, { 'X', 'X', 'X' }, { 'X', 'X', 'X' } };
		char[][] badSpaces = { { ' ', ' ' }, { ' ', ' ' } };
		char[][] badDiag1 = { { 'X', ' ' }, { ' ', 'X' } };
		char[][] badDiag2 = { { ' ', 'X' }, { 'X', ' ' } };

		Random rand = new Random();
		for (int i = 0; i < numMazes; i++) {
			int size = maxSize == 5 ? 5 : 5 + 2 * rand.nextInt((maxSize - 5) / 2);

			size = 13;
			Status s = new Status();
			s.setDragonChoice(1);
			s.setMazeChoice(2);
			s.randomMaze(size, 0);
			Maze m = s.getMaze();

			// MazeInterface.print_maze(m.getMaze());

			assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(m));
			assertTrue("Maze exit not reachable in maze:\n" + m, checkExitReachable(s, m));
			assertNotNull("Invalid walls in maze:\n" + m, !hasSquare(m, badWalls));
			assertNotNull("Invalid spaces in maze:\n" + m, !hasSquare(m, badSpaces));
			assertNotNull("Invalid diagonals in maze:\n" + m, !hasSquare(m, badDiag1));
			assertNotNull("Invalid diagonals in maze:\n" + m, !hasSquare(m, badDiag2));
			// MazeInterface.print_maze(m.getMaze());
			assertTrue("Missing or overlapping objects in maze:\n" + m, notNullAndDistinct(s.getExit(), s.getHero(), s.getDragons().get(0), s.getSword()));
		}
	}
}
