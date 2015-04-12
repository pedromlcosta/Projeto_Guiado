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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import maze.logic.Status;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class MazeEditor extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Elements {
	    Hero, Dragon, Sword, Shield, Darts, Walls, Exit 
	}
	
	JMaze gamePanel;        //Panel where to introduce the new maze after it is finished
	JPanel buttonPanel;
	JPanel editorPanel;
	JComboBox comboBox;
	
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

	 public MazeEditor(JMazeOptions options, JMaze game) {
		 //Panel and other design stuff
		setTitle("Maze Editor");
		setModal(true);
		this.setMinimumSize(new Dimension(100,100));
	    this.setSize(new Dimension(500,500));
		
		buttonPanel = new JPanel();
		editorPanel = new JPanel();
		
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(editorPanel, BorderLayout.CENTER);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Elements.values()));
		buttonPanel.add(comboBox);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), buttonPanel, comboBox, editorPanel}));

		//Game Editor initialization
		gamePanel = game;    //gamePanel
		initMaze(options);
		loadImages();
		

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
			kit = Toolkit.getDefaultToolkit();
			floor = ImageIO.read(new File("images\\wall.png"));
			empty = ImageIO.read(new File("images\\empty.png"));
			dragon = ImageIO.read(new File("images\\dragon.png"));
			hero = ImageIO.read(new File("images\\hero.png"));
			sword = ImageIO.read(new File("images\\sword.png"));
			darts = ImageIO.read(new File("images\\dart.png"));
			shield = ImageIO.read(new File("images\\shield.png"));
			exit = ImageIO.read(new File("images\\exit_closed.png"));
			aim = ImageIO.read(new File("images\\aim.png"));
			customCursor = kit.createCustomCursor(aim, new Point(16, 16),
					"myCursor");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ACTION LISTENERS 
	

}
