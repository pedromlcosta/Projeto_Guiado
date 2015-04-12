package maze.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
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
import javax.swing.JPanel;

import maze.logic.*;

@SuppressWarnings("serial")
class JMaze extends JPanel implements MouseListener, MouseMotionListener,
		KeyListener {
	Status s;
	int cursorX = 0, cursorY = 0;

	static BufferedImage aim;

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
		s.randomMaze(21, 0);

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

			aim = ImageIO.read(new File("images\\aim.png"));
			customCursor = kit.createCustomCursor(aim, new Point(16, 16),
					"myCursor");
			// this.setCursor(customCursor);
			// Cursor cursor =
			// Toolkit.getDefaultToolkit().createCustomCursor(aim, new
			// Point(16, 16), "blank cursor");
			// this.setCursor(cursor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newGame(JMazeOptions options) {
		s = new Status();

		s.setDragonChoice(options.dragonChoice);
		s.setMazeChoice(options.mazeChoice);
		s.setDarts(new Darts[options.dartsSize]);
		s.setDragons(new Dragon[options.dragonsSize]);
		if (s.getMazeChoice() == 2)
			s.randomMaze(options.mazeSize, 1);
		else
			s.defaultMaze();

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

		}// TODO: erro de o dragão poder estar em cima da espada
		super.paintComponent(g); // limpa fundo ...
		g.setColor(Color.BLUE);
		grabFocus();
		try {
			if (getWidth() > getHeight()) {
				size = getHeight() / ((s.getMaze().getMaze().length));
			} else {
				size = getWidth() / ((s.getMaze().getMaze().length));
			}
			offsetY = size;
			offsetX = (getWidth() - size * s.getMaze().getMaze().length) / 2;

			MazeGraphics.paintMaze(g,offsetX,offsetY,size,s);
		} catch (IOException e) {
			e.printStackTrace();
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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
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
