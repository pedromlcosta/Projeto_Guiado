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
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Status;

import javax.swing.SwingConstants;

public class MazeGUI implements MouseListener, MouseMotionListener, KeyListener {

	JFrame mainFrame; // Main frame where the game runs, with 2 panels
	JPanel buttonPanel; // Button panel
	JMaze gamePanel; // Maze game panel

	JButton newGameButton;
	JButton exitButton;
	JButton loadButton;
	JButton saveButton;
	JButton optionsButton;
	JButton mazeEditorButton;

	GameIO gameInputOutput; // Associated with the load and save buttons
	JMazeOptions gameOptions; // Associated with the options button
	MazeEditor mazeEditorPanel;// Associated with the maze editor button

	// int sizeX = 1;
	// int sizeY = 1;

	/**
	 * Launch the application.
	 */

	public MazeGUI() {
		init();
		gamePanel.addMouseListener(this);
		gamePanel.addMouseMotionListener(this);
		gamePanel.addKeyListener(this);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGraphics.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MazeGUI gui = new MazeGUI();
				gui.mainFrame.setVisible(true);
				gui.buttonPanel.setVisible(true);
				gui.gamePanel.setVisible(true);
			}
		});
	}

	public void init() {

		// CREATING MAIN FRAME AND ITS 2 PANELS
		mainFrame = new JFrame("Ultra Cool Maze Game With Dragons!");
		buttonPanel = new JPanel();
		gamePanel = new JMaze();
		gameOptions = new JMazeOptions();
		gameInputOutput = new GameIO(gamePanel.s);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(700, 500));
		mainFrame.pack();

		mainFrame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		mainFrame.getContentPane().add(gamePanel, BorderLayout.CENTER);

		gamePanel.setRequestFocusEnabled(true);
		gameOptions.setVisible(false);
		gameOptions.setAutoRequestFocus(false);
		gamePanel.setVisible(true);
		gamePanel.grabFocus(); // para receber eventos do teclado
		// this.requestFocus();

		// CREATING THE BUTTONS
		newGameButton = new JButton("New Game");
		loadButton = new JButton("Load Game");
		saveButton = new JButton("Save Game");
		optionsButton = new JButton("Options");
		mazeEditorButton = new JButton("Maze Editor");

		// ADDING THE BUTTONS TO THE PANEL
		saveButton.setVerticalAlignment(SwingConstants.TOP);
		buttonPanel.add(newGameButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(optionsButton);
		buttonPanel.add(mazeEditorButton);
		exitButton = new JButton("Exit");
		exitButton.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonPanel.add(exitButton);
		
				exitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		
						int i;
						i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "Exit game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (i == JOptionPane.YES_OPTION)
							System.exit(0);
		
					}
				});

		// ADDING ACTION LISTENERS TO THE BUTTONS
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					gamePanel.setVisible(true);
					gamePanel.newGame(gameOptions);
				}
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
						gamePanel.s = temp;
					}
				}
			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "This action will replace your current save. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					gameInputOutput.saveGame(gamePanel.s);
				}
			}
		});

		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				gameOptions.setModal(true);
				gameOptions.setVisible(true);
				gameOptions.buttonPane.setVisible(true);
				gameOptions.setAutoRequestFocus(true);
				gameOptions.contentPanel.setVisible(true);
				gameOptions.requestFocusInWindow();
				// gamePanel.wa
				// gamePanel.setFocusable(false);
				// gameOptions.paint(gameOptions.getGraphics());

			}
		});

		mazeEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "Create a Maze", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					MazeEditor editor = new MazeEditor(gameOptions, gamePanel);
					editor.setModal(true);
					editor.setVisible(true);
				}
			}
		});

		gamePanel.grabFocus();
	}

	public void paintComponent(Graphics g) {
		gamePanel.paintComponent(g); // TODO: background image
	}

	public void mouseMoved(MouseEvent arg0) {
		// System.out.print(arg0.getX());
		// System.out.print(" ");
		// System.out.println(arg0.getY());
		gamePanel.mouseMoved(arg0);
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
		gamePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if (gamePanel.s.getHero().getnDarts() > 0) {
			gamePanel.s.getHero().decDarts();
			// System.out.println("Cursorx: " + gamePanel.cursorX + " Cursory:"
			// +
			// gamePanel.cursorY);
			for (int i = 0; i < gamePanel.s.getDragons().size(); i++) {
				if (gamePanel.s.getDragons().get(i).isDragonAlive()) {
					int drx = gamePanel.s.getDragons().get(i).getX();
					int dry = gamePanel.s.getDragons().get(i).getY();
					int hx = gamePanel.s.getHero().getX();
					int hy = gamePanel.s.getHero().getY();
					// System.out.println("dr: " + drx * size + " " + dry *
					// size);

					if (Math.abs(gamePanel.cursorX - gamePanel.offsetX - drx * gamePanel.size) <= gamePanel.size) {
						if (Math.abs(gamePanel.cursorY - gamePanel.size + gamePanel.offsetY - dry * gamePanel.size) <= gamePanel.size) {
							if (gamePanel.s.insideRange(hx, hy, drx, dry)) {
								if (!gamePanel.s.obstacles(hx, hy, drx, dry)) {
									// System.out.println("Dead");
									gamePanel.s.getMaze().getMaze()[dry][drx] = ' ';
									gamePanel.s.getDragons().get(i).setDragonAlive(false);
									gamePanel.s.getHero().decDarts();
									gamePanel.repaint();
								}
							}
						}
					}
				}
			}
		}

	}

	public void mousePressed(MouseEvent e) {

		gamePanel.setCursor(gamePanel.customCursor);
	}

	public void mouseDragged(MouseEvent e) {

		gamePanel.mouseDragged(e);

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		gamePanel.keyPressed(e);

	}

}
