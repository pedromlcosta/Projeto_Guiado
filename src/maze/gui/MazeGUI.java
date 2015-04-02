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

import javax.swing.JToolBar;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class MazeGUI extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private JFrame frame;
	Status s;
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	BufferedImage floor;
	BufferedImage empty;
	BufferedImage dragon;

	/**
	 * Launch the application.
	 */

	public MazeGUI() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		s = new Status();
		s.setDragonChoice(1);
		MazeInterface.randomMaze(s, 25);
		try {
			floor = ImageIO.read(new File("images\\wall.png"));
			empty = ImageIO.read(new File("images\\empty.png"));
			dragon = ImageIO.read(new File("images\\dragon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro");
			// e.printStackTrace();
		}
		// initialize();
	}

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MazeGUI window = new MazeGUI();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	//
	// }
	public static void main(String[] args) {
		JFrame f = new JFrame("Maze ");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(400, 400));
		JPanel panel = new MazeGUI();
		f.getContentPane().add(panel);
		f.pack();
		f.setVisible(true);
		panel.requestFocus(); // para receber eventos do teclado

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo ...
		g.setColor(Color.BLUE);
		paintMaze(g);
	} 

	public void paintMaze(Graphics g) {

		int sizeX = getWidth() / ((s.getMaze().getMaze().length));
		int sizeY = getHeight() / ((s.getMaze().getMaze().length));
		int x1 = 0;
		int y1 = sizeY;
		for (int j = 0; j < s.getMaze().getMaze().length; j++) {
			for (int i = 0; i < s.getMaze().getMaze().length; i++) {
				if (s.getMaze().getMaze()[j][i] == 'X') {
					g.drawImage(floor, x1, y1, sizeX, sizeY, null);
				} else if (s.getMaze().getMaze()[j][i] == ' ') {
					g.drawImage(empty, x1, y1, sizeX, sizeY, null);
				} else {
					g.drawImage(dragon, x1, y1, sizeX, sizeY, null);
				}
				x1 += sizeX;
			}
			y1 += sizeY;

			x1 = 0;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unused")
	private void initialize() {
		frame = new JFrame("Maze");
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		// JPanel panel = null;
		// panel.requestFocus(); // para receber eventos do teclado
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			System.out.println("key left;");
			repaint();
			break;

		case KeyEvent.VK_RIGHT:

			repaint();
			break;

		case KeyEvent.VK_UP:

			repaint();
			break;

		case KeyEvent.VK_DOWN:

			repaint();
			break;

		default:
			repaint();
			break;
		}

	}

	// Mais eventos do teclado, que neste caso não interessam
	public void mousePressed(MouseEvent e) {
		x2 = x1 = e.getX();
		y2 = y1 = e.getY();
		repaint();
	}

	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
