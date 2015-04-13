package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	JButton DoneButton;
	JButton cancelButton;

	public MazeEditor(JMazeOptions options, JMaze game) {
		// Panel and other design stuff
		setTitle("Maze Editor");
		setModal(true);
		this.setMinimumSize(new Dimension(100, 100));
		this.setSize(new Dimension(500, 500));
		gamePanel = game;
		buttonPanel = new JPanel();
		editor = new EditorPanel(options);

		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(editor, BorderLayout.CENTER);

		elementBox = new JComboBox<EditorPanel.Element>();

		elementBox.setModel(new DefaultComboBoxModel<EditorPanel.Element>(EditorPanel.Element.values()));
		elementBox.setSelectedIndex(1);

		elementBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				editor.setElement(elementBox.getItemAt(elementBox.getSelectedIndex()));
			}
		});
		buttonPanel.add(elementBox);

		DoneButton = new JButton("Done");
		buttonPanel.add(DoneButton);

		cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);

		// ACTION LISTENERS

		elementBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setElement(elementBox.getItemAt(elementBox.getSelectedIndex()));
			}
		});
		DoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Saving the JMaze game reference to pass the status when
				// editing is
				// finished
				int i;
				i = JOptionPane.showConfirmDialog(buttonPanel, "You will loose your current progress. Are you sure?", "Create a Maze", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == JOptionPane.YES_OPTION) {
					gamePanel.s = editor.s;
					gamePanel.setEnabled(true);
					setVisible(false);
				}
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}

}
