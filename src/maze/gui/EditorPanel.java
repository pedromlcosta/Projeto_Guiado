package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JPanel;

import maze.logic.Status;

@SuppressWarnings("serial")
class EditorPanel extends JPanel implements MouseListener, MouseMotionListener,
		KeyListener {
	Status s;
	int cursorX = 0, cursorY = 0;
	
	int size = 1;
	int offsetY = 0;
	int offsetX = 0;
	int KEY_RIGHT;
	int KEY_UP;
	int KEY_LEFT;
	int KEY_DOWN;

	public EditorPanel(JMazeOptions options) {

		s = new Status();
		
		s.getMaze().setMaze(new char[options.mazeSize][options.mazeSize]);
		s.createMazeWalls();
		
		s.setDragonChoice(options.dragonChoice);
		s.setMazeChoice(2);
		
		s.getMaze().setMaze(new char[options.mazeSize][options.mazeSize]);
		s.createMazeWalls();

	}

//	public void newGame(JMazeOptions options) {
//		s = new Status();
//
//		s.setDragonChoice(options.dragonChoice);
//		s.setMazeChoice(options.mazeChoice);
//		s.setDarts(new Darts[options.dartsSize]);
//		s.setDragons(new Dragon[options.dragonsSize]);
//		if (s.getMazeChoice() == 2)
//			s.randomMaze(options.mazeSize, 1);
//		else
//			s.defaultMaze();
//
//		grabFocus();
//		repaint();
//
//	}

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
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
	
	public void mouseMoved(MouseEvent e) {
		cursorX = e.getX();
		cursorY = e.getY();
		repaint();

	}

	public void mouseDragged(MouseEvent e) {
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
