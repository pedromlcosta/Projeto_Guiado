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

@SuppressWarnings({ "serial", "unused" })
public class MazeGUI extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

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

	/**
	 * Launch the application.
	 */

	public MazeGUI() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
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

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new JFrame("Maze ");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setPreferredSize(new Dimension(400, 400));
				MazeGUI panel = new MazeGUI();
				f.getContentPane().add(panel);
				f.pack();
				panel.setRequestFocusEnabled(true);
				panel.grabFocus(); // para receber eventos do teclado
				panel.requestFocus();
				f.setVisible(true);
			}
		});
	}

	public void paintComponent(Graphics g) {
		if (s.isGameOver())
			return;// erro de o dragão poder estar em cima da espada
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
			changeBufferedImage("hero", "images\\dragon.png");
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

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			s.updateBoard('a');
			// s.move_hero(s.getHero().getX(), s.getHero().getY() - 1);
			repaint();
			break;

		case KeyEvent.VK_RIGHT:
			s.updateBoard('d');
			// s.move_hero(s.getHero().getX(), s.getHero().getY() + 1);
			repaint();
			break;

		case KeyEvent.VK_UP:
			s.updateBoard('w');
			// s.move_hero(s.getHero().getX() - 1, s.getHero().getY());
			repaint();
			break;

		case KeyEvent.VK_DOWN:
			s.updateBoard('s');
			// s.move_hero(s.getHero().getX() + 1, s.getHero().getY());
			repaint();
			break;

		default: {

			repaint();
		}
			break;
		}

	}

	// Mais eventos do teclado, que neste caso não interessam
	public void mousePressed(MouseEvent e) {
		System.out.println("Default");
		x2 = x1 = e.getX();
		y2 = y1 = e.getY();
		repaint();
	}

	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
