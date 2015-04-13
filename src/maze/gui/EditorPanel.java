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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import maze.logic.Darts;
import maze.logic.Dragon;
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

	boolean shieldSet = false; // Has put a shield yet

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

		s.getMaze().setMaze(new char[MazeGUI.gameOpt.mazeSize][MazeGUI.gameOpt.mazeSize]);
		s.createMazeWalls(); // Everything empty and only walls surrounding

		// The hero, sword and exit always exist
		s.setHeroPos(1, 1); // Puts hero in x=1 y=1 and char = 'H'
		s.setSwordPos(2, 1);
		s.setExitPos(0, 3);

		s.setDragonChoice(MazeGUI.gameOpt.dragonChoice);
		s.setMazeChoice(2);

		this.grabFocus();

	}

	public Element getElement() {
		return selectedElement;
	}

	public void setElement(Element element) {
		selectedElement = element;
	}

	public void removeDart(int x, int y) {
		// If there is a dart on the location, attempt to remove from arrayList
		if (s.getMaze().getMaze()[y][x] == '-') {
			for (int i = 0; i < s.getDarts().size(); i++) {
				if (s.getDarts().get(i).getX() == x
						&& s.getDarts().get(i).getY() == y) {
					// Found the dart we wanted to remove, at index i
					s.getDarts().remove(i);
					// System.out.println("Removeu dardo " +i);
				}
			}
		}
	}

	public void removeDragon(int x, int y) {
		// If there is a dragon on the location, attempt to remove from
		// arrayList
		if (s.getMaze().getMaze()[y][x] == 'D') {
			for (int i = 0; i < s.getDragons().size(); i++) {
				if (s.getDragons().get(i).getX() == x
						&& s.getDarts().get(i).getY() == y) {
					// Found the dragon we wanted to remove, at index i
					s.getDragons().remove(i);
				}
			}

		}
	}

	public void removeShield(int x, int y) {
		if (s.getMaze().getMaze()[y][x] == 'P') {
			// Puts shields coordinates somewhere where the hero can't get (a
			// wall on the corner)
			s.getShield().setX(0);
			s.getShield().setY(0);
			shieldSet = false;
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
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'X';
					}
					break;
				case Hero:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						// removes hero from old position
						s.getMaze().getMaze()[s.getHeroY()][s.getHeroX()] = ' ';
						// Sets position and puts figure on maze
						s.setHeroPos(selectedMazeX, selectedMazeY);

					}
					break;
				case Dragon:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'D';
						s.getDragons().add(
								new Dragon(selectedMazeX, selectedMazeY, 'D'));
					}
					break;
				case Sword:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						// removes hero from old position
						s.getMaze().getMaze()[s.getSwordY()][s.getSwordX()] = ' ';
						// Sets position and puts figure on maze
						s.setSwordPos(selectedMazeX, selectedMazeY);

					}
					break;
				case Shield:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						if (!shieldSet) {
							if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
								// Sets position and puts figure on maze
								s.setShieldPos(selectedMazeX, selectedMazeY);
								shieldSet = true;
							}
						} else {
							// removes shield from old position
							s.getMaze().getMaze()[s.getShield().getY()][s
									.getShield().getX()] = ' ';
							// Sets position and puts figure on maze
							s.setShieldPos(selectedMazeX, selectedMazeY);
						}
					}
					break;
				case Darts:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = '-';
						s.getDarts().add(
								new Darts(selectedMazeX, selectedMazeY, '-'));
					}
					break;

				case Exit:
					if (selectedMazeX == 0 || selectedMazeY == 0
							|| selectedMazeX == mazeSize - 1
							|| selectedMazeY == mazeSize - 1)

						if (!((selectedMazeX == 0 || selectedMazeX == mazeSize - 1) && (selectedMazeY == 0 || selectedMazeY == mazeSize - 1))) {

							// removes old exit
							s.getMaze().getMaze()[s.getExit().getY()][s
									.getExit().getX()] = 'X';
							// sets new exit
							s.setExitPos(selectedMazeX, selectedMazeY);

						}
					break;
				}

				repaint();
			} else if (SwingUtilities.isRightMouseButton(arg0)) {

				if (selectedMazeX < mazeSize - 1 && selectedMazeX > 0
						&& selectedMazeY < mazeSize - 1 && selectedMazeY > 0) {

					// Attempts to remove dart or dragon, if the user clicked on
					// any
					// of them
					removeDart(selectedMazeX, selectedMazeY);
					removeDragon(selectedMazeX, selectedMazeY);
					removeShield(selectedMazeX, selectedMazeY);
					
					// HERO (H) AND SWORD(E) CANNOT BE REMOVED
					// EXIT ALSO CANT BE REMOVED, BUT RIGHT MOUSE CANT EVEN CLICK IT
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] != 'H'
							&& s.getMaze().getMaze()[selectedMazeY][selectedMazeX] != 'E')
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

		System.out.println("X: " + selectedMazeX + " Y: " + selectedMazeY);

		// Will only enter the switch if the X and Y are inside the maze
		if (selectedMazeX < mazeSize && selectedMazeX >= 0
				&& selectedMazeY < mazeSize && selectedMazeY >= 0) {

			if (arg0.getButton() == MouseEvent.BUTTON1) { // LEFT MOUSE BUTTON

				switch (selectedElement) {
				case Walls:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'X';
					}

					break;
				case Hero:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						// removes hero from old position
						s.getMaze().getMaze()[s.getHeroY()][s.getHeroX()] = ' ';
						// Sets position and puts figure on maze
						s.setHeroPos(selectedMazeX, selectedMazeY);

					}
					break;
				case Dragon:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = 'D';
						s.getDragons().add(
								new Dragon(selectedMazeX, selectedMazeY, 'D'));
					}
					break;
				case Sword:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						// removes hero from old position
						s.getMaze().getMaze()[s.getSwordY()][s.getSwordX()] = ' ';
						// Sets position and puts figure on maze
						s.setSwordPos(selectedMazeX, selectedMazeY);

					}
					break;
				case Shield:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						if (!shieldSet) {
							if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
								// Sets position and puts figure on maze
								s.setShieldPos(selectedMazeX, selectedMazeY);
								shieldSet = true;
							}
						} else {
							// removes shield from old position
							s.getMaze().getMaze()[s.getShield().getY()][s
									.getShield().getX()] = ' ';
							// Sets position and puts figure on maze
							s.setShieldPos(selectedMazeX, selectedMazeY);
						}
					}
					break;
				case Darts:
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] == ' ') {
						s.getMaze().getMaze()[selectedMazeY][selectedMazeX] = '-';
						s.getDarts().add(
								new Darts(selectedMazeX, selectedMazeY, '-'));
					}
					break;
				case Exit:
					if (selectedMazeX == 0 || selectedMazeY == 0
							|| selectedMazeX == mazeSize - 1
							|| selectedMazeY == mazeSize - 1)

						if (!((selectedMazeX == 0 || selectedMazeX == mazeSize - 1) && (selectedMazeY == 0 || selectedMazeY == mazeSize - 1))) {

							// removes old exit
							s.getMaze().getMaze()[s.getExit().getY()][s
									.getExit().getX()] = 'X';
							// sets new exit
							s.setExitPos(selectedMazeX, selectedMazeY);

						}
					break;
				}

				repaint();
			} else if (arg0.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE
																	// BUTTON

				if (selectedMazeX < mazeSize - 1 && selectedMazeX > 0
						&& selectedMazeY < mazeSize - 1 && selectedMazeY > 0) {

					// Attempts to remove dart or dragon, if the user clicked on
					// any
					// of them
					removeDart(selectedMazeX, selectedMazeY);
					removeDragon(selectedMazeX, selectedMazeY);
					removeShield(selectedMazeX, selectedMazeY);
					
					// HERO (H) AND SWORD(E) CANNOT BE REMOVED
					// EXIT ALSO CANT BE REMOVED, BUT RIGHT MOUSE CANT EVEN CLICK IT
					if (s.getMaze().getMaze()[selectedMazeY][selectedMazeX] != 'H'
							&& s.getMaze().getMaze()[selectedMazeY][selectedMazeX] != 'E')
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
