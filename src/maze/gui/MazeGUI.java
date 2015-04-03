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
				jMaze.setVisible(true);
				jMaze.NewGame();
			}
		});

	}

	public void paintComponent(Graphics g) {
		jMaze.paintComponent(g);
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

	// Mais eventos do teclado, que neste caso não interessam
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed");
	}

	public void mouseDragged(MouseEvent e) {
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
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		BufferedImage floor;
		BufferedImage empty;
		BufferedImage dragon;
		BufferedImage hero;
		BufferedImage sword;
		BufferedImage darts;
		BufferedImage shield;
		BufferedImage exit;

		public JMaze() {

			s = new Status();
			s.setDragonChoice(3);
			MazeInterface.randomMaze(s, 25);

			try {
				floor = ImageIO.read(new File("images\\wall.png"));
				empty = ImageIO.read(new File("images\\empty.png"));
				dragon = ImageIO.read(new File("images\\dragon.png"));
				hero = ImageIO.read(new File("images\\hero.png"));
				sword = ImageIO.read(new File("images\\sword.png"));
				darts = ImageIO.read(new File("images\\dart.png"));
				shield = ImageIO.read(new File("images\\shield.png"));
				exit = ImageIO.read(new File("images\\exit_closed.png"));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void NewGame() {
			s = new Status();
			s.setDragonChoice(3);
			MazeInterface.randomMaze(s, 25);
			jMaze.grabFocus();
			repaint();

		}

		public void paintComponent(Graphics g) {
			if (s.isGameOver()) {

				// frame.setVisible(false);
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

			int sizeX = getWidth() / ((s.getMaze().getMaze().length));
			int sizeY = getHeight() / ((s.getMaze().getMaze().length));
			int x1 = 0;
			int y1 = sizeY;
			int desvioX = 10;
			int desvioY = -12;

			for (int j = 0; j < s.getMaze().getMaze().length; j++) {
				for (int i = 0; i < s.getMaze().getMaze().length; i++) {
					if (s.getMaze().getMaze()[j][i] == 'X') {
						g.drawImage(floor, x1, y1 + desvioY, sizeX, sizeY, null);
					} else if (s.getMaze().getMaze()[j][i] == ' ') {
						g.drawImage(empty, x1, y1 + desvioY, sizeX, sizeY, null);
					} else {
						printCharacter(s.getMaze().getMaze()[j][i], x1, y1 + desvioY, sizeX, sizeY, g);
					}
					x1 += sizeX;
				}
				y1 += sizeY;

				x1 = 0;
			}
		}

		public void changeBufferedImage(String buff, String newImage) {

			try {
				switch (buff) {

				case "hero":
					hero = ImageIO.read(new File(newImage));
					break;
				case "dragon":
					dragon = ImageIO.read(new File(newImage));
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void printCharacter(char character, int x1, int y1, int sizeX, int sizeY, Graphics g) throws IOException {

			switch (character) {
			case 'H':
				changeBufferedImage("hero", "images\\hero.png");
				g.drawImage(hero, x1, y1, sizeX, sizeY, null);
				break;
			case 'p':
				changeBufferedImage("hero", "images\\heroShield.png");
				g.drawImage(hero, x1, y1, sizeX, sizeY, null);
				break;
			case 'A':
				changeBufferedImage("hero", "images\\heroArmed.png");
				g.drawImage(hero, x1, y1, sizeX, sizeY, null);
				break;
			case 'K':
				changeBufferedImage("hero", "images\\heroArmedShield.png");
				g.drawImage(hero, x1, y1, sizeX, sizeY, null);
				break;
			case 'D':
				changeBufferedImage("dragon", "images\\dragon.png");
				g.drawImage(dragon, x1, y1, sizeX, sizeY, null);
				break;
			case 'S':
				g.drawImage(exit, x1, y1, sizeX, sizeY, null);
				break;
			case 'E':
				g.drawImage(sword, x1, y1, sizeX, sizeY, null);
				break;
			case 'F':
				changeBufferedImage("dragon", "images\\swordDragon.png");
				g.drawImage(dragon, x1, y1, sizeX, sizeY, null);
				break;
			case 'Z':
				changeBufferedImage("dragon", "images\\dragonSleeping.png");
				g.drawImage(dragon, x1, y1, sizeX, sizeY, null);
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
