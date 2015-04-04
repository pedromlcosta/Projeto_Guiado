package maze.teste;

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
		char maze[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
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
			if (status.move_hero(status.getDarts()[i].getX(), status.getDarts()[i].getY() - 1)) {
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
}
