package maze.teste;

import java.util.Random;

import maze.cli.*;
import maze.logic.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Teste {

	@Test
	public void moveTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		MazeInterface.print_maze(s.getMaze().getMaze());
		s.updateBoard('d');
		assertEquals(2, s.getHero().getX());
		s.updateBoard('a');
		s.updateBoard('s');
		s.updateBoard('w');
		// assertEquals(2, s.getHero().getX());
		s.updateBoard('e');

	}

	@Test
	public void standStillTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.updateBoard('a');
		s.updateBoard('w');
		assertEquals(1, s.getHero().getY());
		assertEquals(1, s.getHero().getX());

	}

	@Test
	public void swordTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.move_hero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		// MazeInterface.print_maze(s.getMaze().getMaze());

	}

	@Test
	public void death() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.updateBoard('s');
		assertEquals(false, s.getHero().isHeroAlive());

	}

	@Test
	public void killDragon() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.move_hero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		s.move_hero(1, 1);
		s.updateBoard('s');
		assertEquals(false, s.dragonsAlive());
	}

	@Test
	public void randomTest() {
		Status s = new Status();
		MazeInterface.randomMaze(s, 13);
		s.updateBoard('d');
		s.updateBoard('d');
		s.updateBoard('a');
		s.updateBoard('s');
		s.updateBoard('w');

	}

	@Test
	public void victory() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.move_hero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		s.move_hero(1, 1);
		s.updateBoard('s');
		assertEquals(false, s.dragonsAlive());
		s.move_hero(8, 5);
		s.updateBoard('d');
		assertEquals(true, s.isGameOver());

	}

	@Test
	public void noExit() { // Can only exit if he HAS SWORD(1) && DRAGON(S) ARE
							// DEAD(2)

		// TRIES TO LEAVE WITHOUT(!1) SWORD && DRAGON(S) ALIVE(!2)
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.move_hero(8, 5);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

		// TRIES TO LEAVE WITHOUT SWORD(!1) && DRAGON(S) DEAD(2) (if, for
		// example, the hero kills him with darts)
		s.getDragons()[0].setDragonAlive(false);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

		// GETS SWORD
		s.move_hero(4, 3);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());

		// TRIES TO LEAVE WITH SWORD(1) && DRAGON(S) ALIVE(!2)
		s.getDragons()[0].setDragonAlive(true); // HE RESSURRECTED, OMG!!!!
		s.move_hero(8, 5);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

	}

	@Test
	public void darts() {
		Status status = new Status();
		char maze[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		status.setMazeChoice(2);
		status.setDragonChoice(1);
		status.getMaze().setMaze(maze);
		status.setHeroPos(1, 1);
		status.setExitPos(9, 5);
		status.setSwordPos(4, 4);
		status.initDragon('D');
		status.setDragonPos(8, 8);
		int nDarts = 0;
		boolean error = false;

		while (nDarts == 0)
			nDarts = Main.random(maze.length / 2);

		Darts[] darts = new Darts[nDarts];
		status.setDarts(darts);
		status.initDarts('-');
		for (int i = 0; i < darts.length; i++)
			status.getMaze().generateCharacter(darts[i]);

		assertEquals(nDarts, status.getDarts().length);
		assertEquals('-', status.getDarts()[0].getFigure());
		for (int i = 0; i < darts.length;) {
			if (status.move_hero(status.getDarts()[i].getX(),
					status.getDarts()[i].getY() - 1)) {
				// MazeInterface.print_maze(status.getMaze().getMaze());
				status.updateBoard('s');
				// MazeInterface.print_maze(status.getMaze().getMaze());
				assertEquals(1, status.getHero().getnDarts());
				if (!status.getHero().isHeroAlive())
					status.getHero().setHeroAlive(true); // just in case
				status.move_hero(1, 1);
				error = false;
				break;
			} else {
				// Movimento não válido
				i++;
				error = true;
				assertEquals(0, status.getHero().getnDarts());
			}
		}
		if (!error) {
			status.move_hero(8, 5);
			status.throwDart('a');
			assertEquals(status.dragonsAlive(), true);
			status.throwDart('d');
			assertEquals(status.dragonsAlive(), true);
			status.throwDart('w');
			assertEquals(status.dragonsAlive(), true);
			status.getHero().incDarts();
			status.throwDart('s');
			assertEquals(status.dragonsAlive(), false);
			assertEquals(0, status.getHero().getnDarts());
		}
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
		System.out.println("entrou");
		if (i < 0 || i >= m.length || j < 0 || j >= m.length){
			System.out.println("saiu?");
			return;
		}
		if (m[i][j] == 'X' || m[i][j] == 'V'){
			System.out.println("saiu?2");
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
		
		System.out.println("FECK");
		MazeInterface.print_maze(m);
		System.out.println("FECK2");

		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m.length; j++)
				if (m[i][j] != 'X' && m[i][j] != 'V')
					return false;

		return true;
	}

	// Checks if all the arguments (in the variable arguments list) are not
	// null and distinct
	private <T> boolean notNullAndDistinct(T... args) {
		for (int i = 0; i < args.length - 1; i++)
			for (int j = i + 1; j < args.length; j++)
				if (args[i] == null || args[j] == null
						|| args[i].equals(args[j]))
					return false;
		return true;
	}

	@Test
	public void testRandomMazeGenerator() throws Exception {
		int numMazes = 1;
		int maxSize = 101; // can change to any odd number >= 5

		char[][] badWalls = { { 'X', 'X', 'X' }, { 'X', 'X', 'X' },
				{ 'X', 'X', 'X' } };
		char[][] badSpaces = { { ' ', ' ' }, { ' ', ' ' } };
		char[][] badDiag1 = { { 'X', ' ' }, { ' ', 'X' } };
		char[][] badDiag2 = { { ' ', 'X' }, { 'X', ' ' } };

		Random rand = new Random();
		for (int i = 0; i < numMazes; i++) {
			int size = maxSize == 5 ? 5 : 5 + 2 * rand
					.nextInt((maxSize - 5) / 2);

			size = 13;
			Status s = new Status();
			s.setDragonChoice(1);
			s.setMazeChoice(2);
			MazeInterface.randomMaze(s, size);
			Maze m = s.getMaze();

			MazeInterface.print_maze(m.getMaze());

			assertTrue("Invalid maze boundaries in maze:\n" + m,
					checkBoundaries(m));
			assertTrue("Maze exit not reachable in maze:\n" + m,
					checkExitReachable(s, m));
			assertNotNull("Invalid walls in maze:\n" + m,
					!hasSquare(m, badWalls));
			assertNotNull("Invalid spaces in maze:\n" + m,
					!hasSquare(m, badSpaces));
			assertNotNull("Invalid diagonals in maze:\n" + m,
					!hasSquare(m, badDiag1));
			assertNotNull("Invalid diagonals in maze:\n" + m,
					!hasSquare(m, badDiag2));
			assertTrue(
					"Missing or overlapping objects in maze:\n" + m,
					notNullAndDistinct(s.getExit(), s.getHero(),
							s.getDragons()[0], s.getSword()));
		}
	}
}
