package maze.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.*;

@SuppressWarnings("serial")
class JMaze extends JPanel {
	Status s;
	int cursorX = 0, cursorY = 0;
	BufferedImage floor;
	BufferedImage empty;
	BufferedImage dragon;
	BufferedImage dragonSleep;
	BufferedImage dragonSword;
	BufferedImage hero;
	BufferedImage heroShield;
	BufferedImage heroSword;
	BufferedImage heroSwordShield;
	BufferedImage sword;
	BufferedImage darts;
	BufferedImage shield;
	BufferedImage exit;
	BufferedImage aim;
	BufferedImage dragonSwordSleep;

	Toolkit kit;
	Cursor customCursor;
	int size = 1;
	int offsetY = 0;
	int offsetX = 0;
	int KEY_RIGHT;
	int KEY_UP;
	int KEY_LEFT;
	int KEY_DOWN;

	public JMaze() {

		s = new Status();
		s.setDragonChoice(3);
		s.setMazeChoice(2);
		s.randomMaze(21, 1);

		// KEY_RIGHT = KeyEvent.VK_RIGHT;
		// KEY_UP = KeyEvent.VK_UP;
		// KEY_LEFT = KeyEvent.VK_LEFT;
		// KEY_DOWN = KeyEvent.VK_DOWN;
		// // s = new Status();
		// s.setDragonChoice(1);
		// s.setMazeChoice(2);
		// MazeInterface.defaultMaze(s);
		// // MazeInterface.randomMaze(s, 21);

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
			dragonSleep = ImageIO.read(new File("images\\dragonSleeping.png"));
			dragonSword = ImageIO.read(new File("images\\swordDragon.png"));
			heroShield = ImageIO.read(new File("images\\heroShield.png"));
			heroSword = ImageIO.read(new File("images\\heroArmed.png"));
			dragonSwordSleep = ImageIO.read(new File("images\\swordDragonSleeping.png"));
			heroSwordShield = ImageIO.read(new File("images\\heroArmedShield.png"));
			aim = ImageIO.read(new File("images\\aim.png"));
			customCursor = kit.createCustomCursor(aim, new Point(16, 16), "myCursor");
			// this.setCursor(customCursor);
			// Cursor cursor =
			// Toolkit.getDefaultToolkit().createCustomCursor(aim, new
			// Point(16, 16), "blank cursor");
			// this.setCursor(cursor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void NewGame() {
		s = new Status();

		// if (mazeChoice == 1) {
		// s.defaultMaze();
		// s.setMazeChoice(1);
		// } else if (mazeChoice == 2) {
		// s.setDragonChoice(dragonChoice);
		// s.setMazeChoice(2);
		// s.setDarts(new Darts[dartsSize]);
		// s.setDragons(new Dragon[dragonsSize]);
		// s.randomMaze(size);
		// s.getMaze().getMaze()[s.getDragons()[0].getY()][s.getDragons()[0].getX()]
		// = ' ';
		// s.getDragons()[0].setX(1);
		// s.getDragons()[0].setY(1);
		// s.getMaze().getMaze()[1][1] = 'D';
		// }
		grabFocus();
		repaint();

	}

	public void mouseMoved(MouseEvent e) {
		cursorX = e.getX();
		cursorY = e.getY();
		repaint();

	}

	public void mouseDragged(MouseEvent e) {
	}

	public void paintComponent(Graphics g) {
		if (s.isGameOver()) {

		}// TODO: erro de o drag�o poder estar em cima da espada
		super.paintComponent(g); // limpa fundo ...
		g.setColor(Color.BLUE);
		grabFocus();
		try {
			paintMaze(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintMaze(Graphics g) throws IOException {
		// sizeX = getWidth() / ((s.getMaze().getMaze().length));
		// sizeY = getHeight() / ((s.getMaze().getMaze().length));
		if (getWidth() > getHeight()) {
			size = getHeight() / ((s.getMaze().getMaze().length));
		} else {
			size = getWidth() / ((s.getMaze().getMaze().length));
		}
		offsetY = size;
		offsetX = (getWidth() - size * s.getMaze().getMaze().length) / 2;

		g.drawString("Darts: " + s.getHero().getnDarts(), 20, 20);
		int x1 = 0 + offsetX;
		int y1 = size - offsetY;
		for (int j = 0; j < s.getMaze().getMaze().length; j++) {
			for (int i = 0; i < s.getMaze().getMaze().length; i++) {
				if (s.getMaze().getMaze()[j][i] == 'X') {
					g.drawImage(floor, x1, y1, size, size, null);
				} else if (s.getMaze().getMaze()[j][i] == ' ') {
					g.drawImage(empty, x1, y1, size, size, null);
				} else {
					printCharacter(s.getMaze().getMaze()[j][i], x1, y1, size, size, g);
				}
				x1 += size;
			}
			y1 += size;

			x1 = 0 + offsetX;
		}
	}

	public void printCharacter(char character, int x1, int y1, int sizeX, int sizeY, Graphics g) throws IOException {

		switch (character) {
		case 'H':
			g.drawImage(hero, x1, y1, sizeX, sizeY, null);
			break;
		case 'p':
			g.drawImage(heroShield, x1, y1, sizeX, sizeY, null);
			break;
		case 'A':
			g.drawImage(heroSword, x1, y1, sizeX, sizeY, null);
			break;
		case 'K':
			g.drawImage(heroSwordShield, x1, y1, sizeX, sizeY, null);
			break;
		case 'D':
			g.drawImage(dragon, x1, y1, sizeX, sizeY, null);
			break;
		case 'S':
			g.drawImage(exit, x1, y1, sizeX, sizeY, null);
			break;
		case 'E':
			g.drawImage(sword, x1, y1, sizeX, sizeY, null);
			break;
		case 'F':
			g.drawImage(dragonSword, x1, y1, sizeX, sizeY, null);
			break;
		case 'Z':
			g.drawImage(dragonSleep, x1, y1, sizeX, sizeY, null);
			break;
		case 'P':
			g.drawImage(shield, x1, y1, sizeX, sizeY, null);
			break;
		case '-':
			g.drawImage(darts, x1, y1, sizeX, sizeY, null);
			break;
		case 'f':
			g.drawImage(dragonSwordSleep, x1, y1, sizeX, sizeY, null);
			break;
		default:
			g.drawImage(empty, x1, y1, sizeX, sizeY, null);

		}

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			s.updateBoard('a');
			// s.move_hero(s.getHero().getX(), s.getHero().getY() - 1);
			repaint();
			break;

		case KeyEvent.VK_RIGHT:
			s.updateBoard('d');
			// s.move_hero(s.getHero().getX(), s.getHero().getY() + 1);
			repaint();
			break;

		case KeyEvent.VK_UP:
			s.updateBoard('w');
			// s.move_hero(s.getHero().getX() - 1, s.getHero().getY());
			repaint();
			break;

		case KeyEvent.VK_DOWN:
			s.updateBoard('s');
			// s.move_hero(s.getHero().getX() + 1, s.getHero().getY());
			repaint();
			break;

		default: {
			repaint();
		}
		}
	}
}