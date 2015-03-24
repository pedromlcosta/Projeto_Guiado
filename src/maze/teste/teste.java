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
		Main.updateBoard(s, 'd');
		assertEquals(2, s.getHero().getY());
		Main.updateBoard(s, 'd');
		Main.updateBoard(s, 'd');
		Main.updateBoard(s, 'a');
		Main.updateBoard(s, 's');
		Main.updateBoard(s, 'w');

	}

	@Test
	public void standStillTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		Main.updateBoard(s, 'a');
		Main.updateBoard(s, 'w');
		assertEquals(1, s.getHero().getY());

	}

	@Test
	public void swordTest() {
		Status s = new Status();
		MazeInterface.defaultMaze(s);
		Main.move_hero(s, 3, 4);
		Main.updateBoard(s, 's');
		assertEquals(true, s.getHero().isArmed());
		Main.print_maze(s.getMaze().getMaze());

	}

	@Test
	public void RandomTest() {
		Status s = new Status();
		MazeInterface.randomMaze(s, 13);
		Main.updateBoard(s, 'd');
		Main.updateBoard(s, 'd');
		Main.updateBoard(s, 'a');
		Main.updateBoard(s, 's');
		Main.updateBoard(s, 'w');

		
	}
}
