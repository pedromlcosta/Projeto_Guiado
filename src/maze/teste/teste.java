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
		Main.print_maze(s.getMaze().getMaze());
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
		Main.print_maze(s.getMaze().getMaze());

	}

	@Test
	public void Death() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		Status s2 = new Status();
		MazeInterface.defaultMaze(s2);

	}

	@Test
	public void RandomTest() {
		Status s = new Status();
		MazeInterface.randomMaze(s, 13);
		s.updateBoard('d');
		s.updateBoard('d');
		s.updateBoard('a');
		s.updateBoard('s');
		s.updateBoard('w');

	}
}
