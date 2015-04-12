package maze.gui;

//public class MazeCreator extends JFrame {
//
//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MazeCreator frame = new MazeCreator();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public MazeCreator() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//	}
//
//}
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.logic.Status;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import maze.gui.MazeEditor.Elements;
import java.awt.Window.Type;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class MazeEditor extends JDialog implements MouseListener, MouseMotionListener, KeyListener{
	
	public enum Elements {
	    Hero, Dragon, Sword, Shield, Darts, Walls, Exit 
	}
	
	JPanel editorPanel;
	JPanel buttonPanel;
	
	boolean swordSet = false; // Has put a sword yet
	boolean shieldSet = false; // Has put a shield yet
	boolean exitSet = false; // Has put an exit yet
	int dragonChoice = 3; // Defaults to 3
	int mazeSize = 21;    // Defaults to 21

	Status s;

	Toolkit kit;
	Cursor customCursor;
	int cursorX = 0, cursorY = 0;

	BufferedImage floor;
	BufferedImage empty;
	BufferedImage dragon;
	BufferedImage hero;
	BufferedImage sword;
	BufferedImage darts;
	BufferedImage shield;
	BufferedImage exit;
	BufferedImage aim;

	public MazeEditor(JMazeOptions options) {
		setTitle("Maze Editor");
		setModal(true);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Elements.values()));
		panel.add(comboBox);
		
		this.setMinimumSize(new Dimension(100,100));
	    this.setSize(new Dimension(500,500));

		
		JPanel panel2 = new JPanel();
		getContentPane().add(panel2, BorderLayout.CENTER);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), panel, comboBox, panel2}));
	
		initMaze(options);
		loadImages();
		//this is only done once, on the constructor and nowhere else

	}
	
	public void initMaze(JMazeOptions options){
		s = new Status();
		
		s.setDragonChoice(dragonChoice);
		s.setMazeChoice(2);
		
		//Creates empty maze and puts walls around it
		s.getMaze().setMaze(new char[options.mazeSize][options.mazeSize]);
		s.createMazeWalls();
		
	}
	
	public void loadImages() {
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
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
