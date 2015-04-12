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

	JFrame mainFrame;        //Main frame where the game runs, with 2 panels
	JPanel buttonPanel;      //Button panel
	JMaze jMaze;             //Maze game panel
	
	JButton newGameButton;
	JButton exitButton;
	JButton loadButton;
	JButton saveButton;
	JButton optionsButton;
	JButton mazeEditorButton;
	
	GameIO gameInputOutput;    //Associated with the load and save buttons
	JMazeOptions jMazeOptions; //Associated with the options button
	MazeEditor mazeEditorPanel;//Associated with the maze editor button

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
		gameInputOutput = new GameIO(jMaze.s);
		mazeEditorPanel = new MazeEditor(jMazeOptions);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(500, 500));
		mainFrame.pack();

		mainFrame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		mainFrame.getContentPane().add(jMaze, BorderLayout.CENTER);
		
		jMaze.setRequestFocusEnabled(true);
		jMazeOptions.setVisible(false);
		jMazeOptions.setAutoRequestFocus(false);
		jMaze.setVisible(true);
		jMaze.grabFocus(); // para receber eventos do teclado
		// this.requestFocus();
		
		//CREATING THE BUTTONS
		newGameButton = new JButton("New Game");
		exitButton = new JButton("Exit");
		loadButton = new JButton("Load Game");
		saveButton = new JButton("Save Game");
		optionsButton = new JButton("Options");
		mazeEditorButton = new JButton ("Maze Editor");

		//ADDING THE BUTTONS TO THE PANEL
		saveButton.setVerticalAlignment(SwingConstants.TOP);
		exitButton.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonPanel.add(newGameButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(optionsButton);
		buttonPanel.add(mazeEditorButton);
		
		//ADDING ACTION LISTENERS TO THE BUTTONS
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					jMaze.setVisible(true);
					jMaze.newGame(jMazeOptions);
				}
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "Exit game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION)
					System.exit(0);

			}
		});

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
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "This action will replace your current save. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					gameInputOutput.saveGame(jMaze.s);
				}
			}
		});
		
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				jMazeOptions.setVisible(true);
				jMazeOptions.buttonPane.setVisible(true);
				jMazeOptions.setAutoRequestFocus(true);
				jMazeOptions.contentPanel.setVisible(true);
				jMazeOptions.requestFocusInWindow();
//				jMaze.wa
//				jMaze.setFocusable(false);
				// jMazeOptions.paint(jMazeOptions.getGraphics());

			}
		});
		
		mazeEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "Create a Maze", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					mazeEditorPanel.setVisible(true);
					mazeEditorPanel.refreshEditor(jMazeOptions);
				}
			}
		});
		
		jMaze.grabFocus();
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
