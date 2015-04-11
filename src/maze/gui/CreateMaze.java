package maze.gui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.logic.Status;

public class CreateMaze extends JPanel implements MouseListener, MouseMotionListener, KeyListener{

	
	
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
