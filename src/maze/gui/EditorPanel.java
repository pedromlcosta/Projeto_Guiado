package maze.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import maze.logic.Status;

@SuppressWarnings("serial")
class EditorPanel extends JPanel implements MouseListener, MouseMotionListener,
		KeyListener {

	public static enum Element {
		Walls, Hero, Dragon, Sword, Shield, Darts, Exit
	}

	Element selectedElement; // Default value is the wall
	JComboBox<Element> elementBox;

	Status s;
	int cursorX = 0, cursorY = 0;
	int selectedMazeX;
	int selectedMazeY;

	boolean heroSet = false; // Has put a hero yet
	boolean swordSet = false; // Has put a sword yet
	boolean shieldSet = false; // Has put a shield yet
	boolean exitSet = false; // Has put an exit yet

	int size = 1;
	int offsetY = 0;
	int offsetX = 0;
	int KEY_RIGHT;
	int KEY_UP;
	int KEY_LEFT;
	int KEY_DOWN;

	public EditorPanel(JMazeOptions options) {

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		selectedElement = Element.Walls;
		s = new Status();

		s.getMaze().setMaze(new char[options.mazeSize][options.mazeSize]);
		s.createMazeWalls();

		s.setDragonChoice(options.dragonChoice);
		s.setMazeChoice(2);

		s.getMaze().setMaze(new char[options.mazeSize][options.mazeSize]);
		s.createMazeWalls();

		this.grabFocus();

	}

	public Element getElement() {
		return selectedElement;
	}

	public void setElement(Element element) {
		selectedElement = element;
	}

	public void removeDart(int x, int y) {
		//If there is a dart on the location, attempt to remove from arrayList
		if (s.getMaze().getMaze()[y][x] == '-') {
			for (int i = 0; i < s.getDarts().size(); i++) {
				if(s.getDarts().get(i).getX() == x && s.getDarts().get(i).getY() == y){
					//Found the dart we wanted to remove, at index i
					s.getDarts().remove(i);
				}
			}
		}
	}

	public void removeDragon(int x, int y) {
		//If there is a dragon on the location, attempt to remove from arrayList
		if (s.getMaze().getMaze()[y][x] == 'D') {
			for (int i = 0; i < s.getDragons().size(); i++) {
				if(s.getDragons().get(i).getX() == x && s.getDarts().get(i).getY() == y){
					//Found the dragon we wanted to remove, at index i
					s.getDragons().remove(i);
				}
			}

		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLUE);

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

		g.drawString(offsetX + " " + offsetY + " " + size + " " + cursorX + " "
				+ cursorY, 20, 50);
	}

	public void mouseMoved(MouseEvent e) {
		cursorX = e.getX();
		cursorY = e.getY();
		repaint();

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

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent arg0) {

		cursorX = arg0.getX();
		cursorY = arg0.getY();
		selectedMazeX = (arg0.getX() - offsetX) / size;
		selectedMazeY = (arg0.getY() - offsetY) / size;
		int mazeSize = s.getMaze().getMaze().length;

		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		// Will only enter the switch if the X and Y are inside the maze
		if (selectedMazeX < mazeSize && selectedMazeX >= 0
				&& selectedMazeY < mazeSize && selectedMazeY >= 0) {
			if (SwingUtilities.isLeftMouseButton(arg0)) {
				switch (selectedElement) {
				case Walls:
					if (selectedMazeX < mazeSize - 1 && selectedMazeX > 0
							&& selectedMazeY < mazeSize - 1
							&& selectedMazeY > 0) { // inside
													// acceptable
													// boundaries
						if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
							s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'X';
						}
					}
					break;
				case Hero:
					break;
				case Dragon:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'D';
						// ADICIONAR A S.DRAGONS
					}
					break;
				case Sword:
					break;
				case Shield:
					break;
				case Darts:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = '-';
						// ADICIONAR A S.DARTS
					}
					break;

				case Exit:
					break;
				}

				repaint();
			} else if (SwingUtilities.isRightMouseButton(arg0)) {
				
				//Attempts to remove dart or dragon, if the user clicked on any of them
				removeDart(selectedMazeX, selectedMazeY);
				removeDragon(selectedMazeX, selectedMazeY);
				
				if (selectedMazeX < mazeSize - 1 && selectedMazeX > 0
						&& selectedMazeY < mazeSize - 1
						&& selectedMazeY > 0) {
					s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = ' ';
				}

				repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		cursorX = arg0.getX();
		cursorY = arg0.getY();
		selectedMazeX = (arg0.getX() - offsetX) / size;
		selectedMazeY = (arg0.getY() - offsetY) / size;
		int mazeSize = s.getMaze().getMaze().length;
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		// Will only enter the switch if the X and Y are inside the maze
		if (selectedMazeX < mazeSize && selectedMazeX >= 0
				&& selectedMazeY < mazeSize && selectedMazeY >= 0) {

			if (arg0.getButton() == MouseEvent.BUTTON1) { // LEFT MOUSE BUTTON

				switch (selectedElement) {
				case Walls:
					if (selectedMazeX < mazeSize - 1 && selectedMazeX > 0
							&& selectedMazeY < mazeSize - 1
							&& selectedMazeY > 0) { // inside
													// acceptable
													// boundaries
						if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
							s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'X';
						}
					}
					break;
				case Hero:
					break;
				case Dragon:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'D';
						// ADICIONAR A S.DRAGONS
					}
					break;
				case Sword:
					break;
				case Shield:
					break;
				case Darts:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = '-';
						// ADICIONAR A S.DARTS
					}
					break;
				case Exit:
					break;
				}

				repaint();
			} else if (arg0.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE
																	// BUTTON
				//Attempts to remove dart or dragon, if the user clicked on any of them
				removeDart(selectedMazeX, selectedMazeY);
				removeDragon(selectedMazeX, selectedMazeY);
				
				if (selectedMazeX < mazeSize - 1 && selectedMazeX > 0
						&& selectedMazeY < mazeSize - 1
						&& selectedMazeY > 0) {
					s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = ' ';
				}


				repaint();
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
