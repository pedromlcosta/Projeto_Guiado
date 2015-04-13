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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Status;

@SuppressWarnings("serial")
class JMaze extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	Status s;
	int cursorX = 0, cursorY = 0;

	static BufferedImage aim;

	Toolkit kit;
	Cursor customCursor;
	int size = 1;
	int offsetY = 0;
	int offsetX = 0;
	int K_UP;
	int K_DOWN;
	int K_LEFT;
	int K_RIGHT;

	public JMaze() {

		s = new Status();

		try {
			kit = Toolkit.getDefaultToolkit();

			aim = ImageIO.read(new File("images\\aim.png"));
			customCursor = kit.createCustomCursor(aim, new Point(16, 16), "myCursor");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newGame(JMazeOptions options) {
		s = new Status();
		this.setEnabled(true);
		s.setDragonChoice(MazeGUI.gameOpt.dragonChoice);
		s.setMazeChoice(MazeGUI.gameOpt.mazeChoice);
		s.setDragonSize(MazeGUI.gameOpt.dragonsSize);
		s.setDartsSize(MazeGUI.gameOpt.dartsSize);
		if (s.getMazeChoice() == 2)
			s.randomMaze(MazeGUI.gameOpt.mazeSize, 1);
		else
			s.defaultMaze();

		grabFocus();
		repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLUE);
		grabFocus();

		if (s.getMaze().getMaze().length == 0) {
			this.setEnabled(false);
			return;
		}

		try {
			if (getWidth() > getHeight()) {
				size = getHeight() / ((s.getMaze().getMaze().length));
				offsetY = 0;
			} else {
				size = getWidth() / ((s.getMaze().getMaze().length));
				offsetY = (getHeight() - (s.getMaze().getMaze().length * size)) / 2;
			}

			offsetX = (getWidth() - size * s.getMaze().getMaze().length) / 2;

			MazeGraphics.paintMaze(g, offsetX, offsetY, size, s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == K_LEFT) {
			s.updateBoard('a');
			// s.move_hero(s.getHero().getX(), s.getHero().getY() - 1);
			repaint();
		} else if (e.getKeyCode() == K_RIGHT) {
			s.updateBoard('d');
			// s.move_hero(s.getHero().getX(), s.getHero().getY() + 1);
			repaint();
		} else if (e.getKeyCode() == K_UP) {
			s.updateBoard('w');
			// s.move_hero(s.getHero().getX() - 1, s.getHero().getY());
			repaint();
		} else if (e.getKeyCode() == K_DOWN) {
			s.updateBoard('s');
			// s.move_hero(s.getHero().getX() + 1, s.getHero().getY());
			repaint();
		} else {
			repaint();
		}

		if (s.isGameOver()) {
			this.setEnabled(false);
			if (!s.getHero().isHeroAlive())
				JOptionPane.showMessageDialog(this, "You lost!", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION);
			else {
				JOptionPane.showMessageDialog(this, "You Won!", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION);

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseMoved(MouseEvent e) {
		cursorX = e.getX();
		cursorY = e.getY();
		repaint();

	}

	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
