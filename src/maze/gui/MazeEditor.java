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
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import maze.gui.EditorPanel.Element;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class MazeEditor extends JDialog {

	private static final long serialVersionUID = 1L;

	JMaze gamePanel; // Panel where to introduce the new maze after it is
						// finished
	JPanel buttonPanel;
	EditorPanel editor;
	JComboBox<EditorPanel.Element> elementBox;

	int dragonChoice = 3; // Defaults to 3
	int mazeSize = 21; // Defaults to 21

	int cursorX = 0, cursorY = 0;

	public MazeEditor(JMazeOptions options, JMaze game) {
		// Panel and other design stuff
		setTitle("Maze Editor");
		setModal(true);
		this.setMinimumSize(new Dimension(100, 100));
		this.setSize(new Dimension(500, 500));

		buttonPanel = new JPanel();
		editor = new EditorPanel(options);

		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(editor, BorderLayout.CENTER);

		elementBox = new JComboBox<EditorPanel.Element>();
		

		elementBox.setModel(new DefaultComboBoxModel<EditorPanel.Element>(EditorPanel.Element.values()));
		elementBox.setSelectedIndex(0);

		elementBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				editor.setElement(elementBox.getItemAt(elementBox.getSelectedIndex()));
			}
		});
		buttonPanel.add(elementBox);
		
		elementBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setElement(elementBox.getItemAt(elementBox.getSelectedIndex()));
			}
		});

		// Saving the JMaze game reference to pass the status when editing is
		// finished
		gamePanel = game;

		// ACTION LISTENERS

	}

}
