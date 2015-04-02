package maze.teste;

import maze.cli.*;
import maze.logic.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class teste {

	@Test
	public void moveTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		// MazeInterface.print_maze(s.getMaze().getMaze());
		s.updateBoard('d');
		assertEquals(2, s.getHero().getY());
		s.updateBoard('d');
		s.updateBoard('d');
		s.updateBoard('a');
		s.updateBoard('s');
		s.updateBoard('w');

	}

	@Test
	public void standStillTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.updateBoard('a');
		s.updateBoard('w');
		assertEquals(1, s.getHero().getY());

	}

	@Test
	public void swordTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.move_hero(3, 4);
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
		s.move_hero(3, 4);
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
		s.move_hero(3, 4);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());
		s.move_hero(1, 1);
		s.updateBoard('s');
		assertEquals(false, s.dragonsAlive());
		s.move_hero(5, 8);
		// MazeInterface.print_maze(s.getMaze().getMaze());

		s.updateBoard('d');
		assertEquals(true, s.isGameOver());

	}

	@Test
	public void noExit() { // Can only exit if he HAS SWORD(1) && DRAGON(S) ARE DEAD(2)

		// TRIES TO LEAVE WITHOUT(!1) SWORD && DRAGON(S) ALIVE(!2)
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		s.move_hero(5, 8);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

		// TRIES TO LEAVE WITHOUT SWORD(!1) && DRAGON(S) DEAD(2) (if, for example, the hero kills him with darts)
		s.getDragons()[0].setDragonAlive(false);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());
		
		// GETS SWORD
		s.move_hero(3, 4);
		s.updateBoard('s');
		assertEquals(true, s.getHero().isArmed());

		// TRIES TO LEAVE WITH SWORD(1) && DRAGON(S) ALIVE(!2)
		s.getDragons()[0].setDragonAlive(true); // HE RESSURRECTED, OMG!!!!
		s.move_hero(5, 8);
		s.updateBoard('d');
		assertEquals(false, s.isGameOver());

	}
	
}
