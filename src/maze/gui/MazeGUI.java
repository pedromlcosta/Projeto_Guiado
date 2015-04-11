package maze.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Status;
import javax.swing.SwingConstants;

@SuppressWarnings({ "serial", "unused" })
public class MazeGUI extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	JFrame mainFrame;
	JPanel buttonPanel;
	JMaze jMaze;
	JButton newGameButton;
	JButton exitButton;
	JButton loadButton;
	JButton saveButton;

	GameIO gameInputOutput;
	JButton optionsButton;
	JMazeOptions jMazeOptions;

	// int sizeX = 1;
	// int sizeY = 1;

	/**
	 * Launch the application.
	 */

	public MazeGUI() {
		init();
		jMaze.addMouseListener(this);
		jMaze.addMouseMotionListener(this);
		jMaze.addKeyListener(this);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MazeGUI gui = new MazeGUI();
				gui.mainFrame.setVisible(true);
				gui.buttonPanel.setVisible(true);
				gui.jMaze.setVisible(true);
			}
		});
	}

	public void init() {

		// CREATING MAIN FRAME AND ITS 2 PANELS
		mainFrame = new JFrame("Ultra Cool Maze Game With Dragons!");
		buttonPanel = new JPanel();
		jMaze = new JMaze();
		jMazeOptions = new JMazeOptions();
		// mainFrame.setTitle("Ultra Cool Maze Game With Dragons!");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(500, 500));
		mainFrame.pack();

		mainFrame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		mainFrame.getContentPane().add(jMaze, BorderLayout.CENTER);
		loadButton = new JButton("Load Game");

		// CREATING THE BUTTONS FOR THE BUTTON PANEL
		newGameButton = new JButton("New Game");

		// ADDING THE BUTTONS TO THE PANNEL
		buttonPanel.add(newGameButton);

		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					jMaze.setVisible(true);
					jMaze.NewGame();
				}
			}
		});
		saveButton = new JButton("Save Game");
		saveButton.setVerticalAlignment(SwingConstants.TOP);
		buttonPanel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "This action will replace your current save. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					gameInputOutput.saveGame(jMaze.s);
				}
			}
		});
		buttonPanel.add(loadButton);

		optionsButton = new JButton("Options");
		buttonPanel.add(optionsButton);
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				jMazeOptions.setVisible(true);
				jMazeOptions.buttonPane.setVisible(true);
				jMazeOptions.contentPanel.setVisible(true);
				// jMazeOptions.paint(jMazeOptions.getGraphics());

			}
		});
		exitButton = new JButton("Exit");
		exitButton.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonPanel.add(exitButton);

		// SETTING ACTIONS FOR EACH BUTTON
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "Exit game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION)
					System.exit(0);

			}
		});
		jMaze = new JMaze();
		mainFrame.getContentPane().add(jMaze, BorderLayout.SOUTH);
		jMaze.setRequestFocusEnabled(true);
		jMaze.setVisible(true);
		jMaze.grabFocus(); // para receber eventos do teclado
		// this.requestFocus();

		// CREATING A IO OBJECT FOR LOADS/SAVES
		gameInputOutput = new GameIO(jMaze.s);

		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				Status temp;

				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "Load game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {

					if ((temp = gameInputOutput.loadGame()) == null) {
						JOptionPane.showMessageDialog(mainFrame, "No save file was found. Load unsuccessful.", "Error Loading", JOptionPane.ERROR_MESSAGE);
					} else {
						jMaze.s = temp;
					}
				}
			}
		});

	}

	public void paintComponent(Graphics g) {
		jMaze.paintComponent(g); // TODO: background image
	}

	public void mouseMoved(MouseEvent arg0) {
		// System.out.print(arg0.getX());
		// System.out.print(" ");
		// System.out.println(arg0.getY());
		jMaze.mouseMoved(arg0);
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent arg0) {
		jMaze.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if (jMaze.s.getHero().getnDarts() > 0) {
			jMaze.s.getHero().decDarts();
			// System.out.println("Cursorx: " + jMaze.cursorX + " Cursory:" +
			// jMaze.cursorY);
			for (int i = 0; i < jMaze.s.getDragons().length; i++) {
				if (jMaze.s.getDragons()[i].isDragonAlive()) {
					int drx = jMaze.s.getDragons()[i].getX();
					int dry = jMaze.s.getDragons()[i].getY();
					int hx = jMaze.s.getHero().getX();
					int hy = jMaze.s.getHero().getY();
					// System.out.println("dr: " + drx * size + " " + dry *
					// size);

					if (Math.abs(jMaze.cursorX - jMaze.offsetX - drx * jMaze.size) <= jMaze.size) {
						if (Math.abs(jMaze.cursorY - jMaze.size + jMaze.offsetY - dry * jMaze.size) <= jMaze.size) {
							if (jMaze.s.insideRange(hx, hy, drx, dry)) {
								if (!jMaze.s.obstacles(hx, hy, drx, dry)) {
									// System.out.println("Dead");
									jMaze.s.getMaze().getMaze()[dry][drx] = ' ';
									jMaze.s.getDragons()[i].setDragonAlive(false);
									jMaze.s.getHero().decDarts();
									jMaze.repaint();
								}
							}
						}
					}
				}
			}
		}

	}

	public void mousePressed(MouseEvent e) {

		jMaze.setCursor(jMaze.customCursor);
	}

	public void mouseDragged(MouseEvent e) {

		jMaze.mouseDragged(e);

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		jMaze.keyPressed(e);

	}

}
