package maze.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import maze.cli.*;
import maze.logic.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings({ "serial", "unused" })
public class MazeGUI extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	JFrame frame;
	JPanel panel;
	JMaze jMaze;
	JButton NewGameButton;
	JButton ExitButton;
	JLabel exitText;
	// int sizeX = 1;
	// int sizeY = 1;
	int size = 1;
	int offset = 0;

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
		exitText = new JLabel();

		NewGameButton = new JButton("New Game");
		ExitButton = new JButton("Exit");
		frame.setTitle("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.pack();

		frame.getContentPane().add(panel, BorderLayout.NORTH);
		frame.getContentPane().add(jMaze, BorderLayout.CENTER);
		exitText.setVisible(false);
		jMaze.setRequestFocusEnabled(true);
		jMaze.grabFocus(); // para receber eventos do teclado
		// this.requestFocus();

		NewGameButton = new JButton("New Game");
		ExitButton = new JButton("Exit");
		panel.add(NewGameButton);
		panel.add(ExitButton);

		exitText.setText("Are you sure you want to leave?");
		panel.add(exitText);

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
		jMaze.paintComponent(g);
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
			System.out.println("x: " + jMaze.cursorX + " y:" + jMaze.cursorY);
			for (int i = 0; i < jMaze.s.getDragons().length; i++) {
				if (jMaze.s.getDragons()[i].isDragonAlive()) {
					int drx = jMaze.s.getDragons()[i].getX();
					int dry = jMaze.s.getDragons()[i].getY();
					int hx = jMaze.s.getHero().getX();
					int hy = jMaze.s.getHero().getY();
					System.out.println("dr: " + drx * size + " " + dry * size);

					if (Math.abs(jMaze.cursorX - offset - drx * size) <= size)
						if (Math.abs(jMaze.cursorY - size + offset - dry * size) <= size)
							if (!jMaze.s.obstacles(hx, hy, drx, dry)) {
								System.out.println("Dead");
								jMaze.s.getMaze().getMaze()[dry][drx] = ' ';
								jMaze.s.getDragons()[i].setDragonAlive(false);
								jMaze.s.getHero().decDarts();
								jMaze.repaint();
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
		Toolkit kit;
		Cursor customCursor;

		public JMaze() {

			s = new Status();
			s.setDragonChoice(1);
			s.setMazeChoice(2);
			s.randomMaze(21);

			// s = new Status();
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
			s.setDragonChoice(1);
			s.setMazeChoice(2);
			s.randomMaze(21);
			// s.getMaze().getMaze()[s.getDragons()[0].getY()][s.getDragons()[0].getX()]
			// = ' ';
			// s.getDragons()[0].setX(1);
			// s.getDragons()[0].setY(1);
			// s.getMaze().getMaze()[1][1] = 'D';
			jMaze.grabFocus();
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
				System.exit(0);
			}// erro de o dragão poder estar em cima da espada
			super.paintComponent(g); // limpa fundo ...
			g.setColor(Color.BLUE);

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
			offset = size;

			int x1 = 0 + offset;
			int y1 = size - offset;
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

				x1 = 0 + offset;
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
			default:
				g.drawImage(empty, x1, y1, sizeX, sizeY, null);

			}

		}

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				jMaze.s.updateBoard('a');
				// s.move_hero(s.getHero().getX(), s.getHero().getY() - 1);
				repaint();
				break;

			case KeyEvent.VK_RIGHT:
				jMaze.s.updateBoard('d');
				// s.move_hero(s.getHero().getX(), s.getHero().getY() + 1);
				repaint();
				break;

			case KeyEvent.VK_UP:
				jMaze.s.updateBoard('w');
				// s.move_hero(s.getHero().getX() - 1, s.getHero().getY());
				repaint();
				break;

			case KeyEvent.VK_DOWN:
				jMaze.s.updateBoard('s');
				// s.move_hero(s.getHero().getX() + 1, s.getHero().getY());
				repaint();
				break;

			default: {
				repaint();
			}
			}
		}
	}

}
