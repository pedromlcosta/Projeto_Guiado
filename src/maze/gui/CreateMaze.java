package maze.gui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Status;

public class CreateMaze extends JPanel {

	char mazeEditor[][];
	boolean swordSet = false; // Has put a sword yet
	boolean shieldSet = false; // Has put a shield yet
	boolean exitSet = false; // Has put an exit yet

	Status s;

	Toolkit kit;
	Cursor customCursor;
	int cursorX = 0, cursorY = 0;

	BufferedImage floor;
	BufferedImage empty;
	BufferedImage dragon;
	BufferedImage hero;
	BufferedImage sword;
	BufferedImage darts;
	BufferedImage shield;
	BufferedImage exit;
	BufferedImage aim;

	public CreateMaze(JMazeOptions options) {
		initMaze(options);
		loadImages();
	}
	
	public void initMaze(JMazeOptions options){
		s = new Status();
		s.setDragonChoice(options.dragonChoice);
		s.getMaze().setMaze(new char[options.mazeSize][options.mazeSize]);
		// mazeEditor = new char[options.mazeSize][options.mazeSize];
	}
	
	public void loadImages() {
		try {
			kit = Toolkit.getDefaultToolkit();
			floor = ImageIO.read(new File("images\\wall.png"));
			empty = ImageIO.read(new File("images\\empty.png"));
			dragon = ImageIO.read(new File("images\\dragon.png"));
			hero = ImageIO.read(new File("images\\hero.png"));
			sword = ImageIO.read(new File("images\\sword.png"));
			darts = ImageIO.read(new File("images\\dart.png"));
			shield = ImageIO.read(new File("images\\shield.png"));
			exit = ImageIO.read(new File("images\\exit_closed.png"));
			aim = ImageIO.read(new File("images\\aim.png"));
			customCursor = kit.createCustomCursor(aim, new Point(16, 16),
					"myCursor");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
