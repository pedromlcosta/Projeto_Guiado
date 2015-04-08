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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings({ "serial", "unused" })
public class MazeGUI extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	JFrame frame;
	JPanel panel;
	JMaze jMaze;
	JButton NewGameButton;
	JButton ExitButton;

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
				gui.frame.setVisible(true);
				gui.panel.setVisible(true);
				gui.jMaze.setVisible(true);
			}
		});
	}

	public void init() {

		frame = new JFrame();
		panel = new JPanel();
		jMaze = new JMaze();

		NewGameButton = new JButton("New Game");
		ExitButton = new JButton("Exit");
		frame.setTitle("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.pack();

		frame.getContentPane().add(panel, BorderLayout.NORTH);
		frame.getContentPane().add(jMaze, BorderLayout.CENTER);
		jMaze.setRequestFocusEnabled(true);
		jMaze.grabFocus(); // para receber eventos do teclado
		// this.requestFocus();

		NewGameButton = new JButton("New Game");
		ExitButton = new JButton("Exit");
		panel.add(NewGameButton);
		panel.add(ExitButton);

		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i;
				i = JOptionPane.showConfirmDialog(panel, "You will loose your current progress. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION)
					System.exit(0);

			}
		});

		NewGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int i;
				i = JOptionPane.showConfirmDialog(panel, "You will loose your current progress. Are you sure?", "New game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					jMaze.setVisible(true);
					jMaze.NewGame();
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
