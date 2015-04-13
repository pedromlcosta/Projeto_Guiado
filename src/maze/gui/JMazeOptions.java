package maze.gui;

import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class JMazeOptions extends JDialog {

	final int defaultRandomSize = 13;

	JPanel contentPanel;
	JPanel buttonPane;

	JButton cancelButton;
	JButton doneButton;
	JButton buttomKeyUp;
	JButton buttomKeyRight;
	JButton buttomKeyLeft;
	JButton buttomKeyDown;

	Label labelMazeChoice;
	Label labelDragonChoice;
	Label labelDartsNumber;
	Label labelMazeSize;
	Label labelDragonNumber;

	JSpinner Darts;
	JSpinner sizeSpinner;
	JSpinner dragonNumber;

	Choice mazeChoices;

	JComboBox<String> dragonChoiceBox;
	JComboBox<String> mazeChoiceBox;

	KeyBinding key;

	/**
	 * Create the dialog.
	 */
	public JMazeOptions() {
		// JMazeOptions dialog = new JMazeOptions();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel = new JPanel();
		buttonPane = new JPanel();
		getContentPane().setLayout(null);

		// Creates Panels
		contentPanel.setBounds(0, 31, 484, 292);
		this.setBounds(0, 0, 500, 350);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			labelMazeChoice = new Label("Maze choice");
			labelMazeChoice.setBounds(10, 35, 84, 22);
		}
		{
			labelDragonChoice = new Label("Dragon choice");
			labelDragonChoice.setBounds(10, 133, 84, 22);
		}
		{
			labelDartsNumber = new Label("Darts number");
			labelDartsNumber.setBounds(252, 79, 79, 22);
		}
		{
			labelMazeSize = new Label("Size");
			labelMazeSize.setBounds(252, 35, 35, 22);
		}
		{
			labelDragonNumber = new Label("Dragons number");
			labelDragonNumber.setBounds(10, 79, 94, 22);
		}
		contentPanel.setLayout(null);
		// Adds labels to contentPanel
		contentPanel.add(labelMazeChoice);
		contentPanel.add(labelDragonChoice);
		contentPanel.add(labelDartsNumber);
		contentPanel.add(labelMazeSize);
		contentPanel.add(labelDragonNumber);
		{
			// Creates Spinner for Darts
			Darts = new JSpinner();
			Darts.setModel(new SpinnerNumberModel(MazeGUI.gameOpt.dartsSize, new Integer(0), null, new Integer(1)));
			Darts.setBounds(340, 79, 66, 22);
			// Darts.
			contentPanel.add(Darts);
		}
		{
			sizeSpinner = new JSpinner();
			sizeSpinner.setModel(new SpinnerNumberModel(MazeGUI.gameOpt.mazeSize, new Integer(5), null, new Integer(2)));
			sizeSpinner.setBounds(340, 35, 68, 20);
			contentPanel.add(sizeSpinner);
		}
		{
			dragonNumber = new JSpinner();
			dragonNumber.setModel(new SpinnerNumberModel(MazeGUI.gameOpt.dragonsSize, new Integer(0), null, new Integer(1)));
			dragonNumber.setBounds(110, 79, 66, 22);
			contentPanel.add(dragonNumber);
		}

		// COMBO BOXES

		// DRAGON CHOICE BOX
		dragonChoiceBox = new JComboBox<String>();
		dragonChoiceBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Static Dragon", "Random Dragon Movement", "Random Dragon Movement w/ Sleep" }));
		dragonChoiceBox.setBounds(100, 133, 208, 22);
		// dragon choice is from 1 to 3, index is 0 to 2
		dragonChoiceBox.setSelectedIndex(MazeGUI.gameOpt.dragonChoice - 1);

		contentPanel.add(dragonChoiceBox);

		// MAZE CHOICE BOX

		mazeChoiceBox = new JComboBox<String>();
		mazeChoiceBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Default Maze", "Random Maze w Options", "Random Maze" }));
		// maze choice is from 1 to 2, index is 0 to 1
		mazeChoiceBox.setSelectedIndex(MazeGUI.gameOpt.mazeChoice - 1);
		mazeChoiceBox.setBounds(100, 35, 146, 22);
		contentPanel.add(mazeChoiceBox);

		// KEY BUTTONS
		{
			buttomKeyUp = new JButton("Move Up: " + KeyEvent.getKeyText(MazeGUI.gameOpt.KEY_UP));
			buttomKeyUp.setBounds(33, 185, 199, 22);
			contentPanel.add(buttomKeyUp);
		}

		{
			buttomKeyRight = new JButton("Move Right: " + KeyEvent.getKeyText(MazeGUI.gameOpt.KEY_RIGHT));
			buttomKeyRight.setBounds(263, 218, 199, 23);
			contentPanel.add(buttomKeyRight);
		}
		{
			buttomKeyLeft = new JButton("Move Left: " + KeyEvent.getKeyText(MazeGUI.gameOpt.KEY_LEFT));
			buttomKeyLeft.setBounds(263, 185, 199, 23);
			contentPanel.add(buttomKeyLeft);
		}
		{
			buttomKeyDown = new JButton("Move Down: " + KeyEvent.getKeyText(MazeGUI.gameOpt.KEY_DOWN));
			buttomKeyDown.setBounds(33, 218, 199, 22);
			contentPanel.add(buttomKeyDown);
		}

		{
			buttonPane.setBounds(0, 0, 434, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				doneButton = new JButton("Done");
				doneButton.setBounds(134, 5, 83, 23);
				buttonPane.add(doneButton);
			}

			doneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {

						MazeGUI.gameOpt.mazeSize = (int) sizeSpinner.getValue();
						if (MazeGUI.gameOpt.mazeSize % 2 == 0)
							MazeGUI.gameOpt.mazeSize++;

						MazeGUI.gameOpt.dartsSize = (int) Darts.getValue();
						if (MazeGUI.gameOpt.dartsSize >= MazeGUI.gameOpt.mazeSize * 0.75) {
							MazeGUI.gameOpt.dartsSize = MazeGUI.gameOpt.mazeSize / 4;
							Darts.setValue(MazeGUI.gameOpt.dartsSize);
						}

						MazeGUI.gameOpt.dragonsSize = (int) dragonNumber.getValue();
						if (MazeGUI.gameOpt.dragonsSize >= MazeGUI.gameOpt.mazeSize * 0.75) {
							MazeGUI.gameOpt.dragonsSize = MazeGUI.gameOpt.mazeSize / 4;
							dragonNumber.setValue(MazeGUI.gameOpt.dragonsSize);
						}

						switch (mazeChoiceBox.getSelectedIndex()) {
						case 0:
							MazeGUI.gameOpt.mazeChoice = 1;
							break;
						case 1:
							MazeGUI.gameOpt.mazeChoice = 2;
							break;
						case 2:
							MazeGUI.gameOpt.mazeChoice = 3;
						default:

							break;
						}

						switch (dragonChoiceBox.getSelectedIndex()) {

						case 0:
							MazeGUI.gameOpt.dragonChoice = 1;
							break;
						case 1:
							MazeGUI.gameOpt.dragonChoice = 2;
							break;
						case 2:
							MazeGUI.gameOpt.dragonChoice = 3;
							break;
						default:
							break;
						}
					} catch (Exception e) {

					}
					setVisible(false);

				}
			});
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBounds(237, 5, 83, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {

						setVisible(false);

					} catch (Exception e) {
						MazeGUI.gameOpt.dragonChoice = 1;
						MazeGUI.gameOpt.mazeChoice = 1;
						MazeGUI.gameOpt.dartsSize = 0;
						MazeGUI.gameOpt.dragonsSize = 1;
						MazeGUI.gameOpt.mazeSize = 11;

					}
				}
			});
			buttomKeyUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(MazeGUI.gameOpt.KEY_UP);
					key.setVisible(true);
					MazeGUI.gameOpt.KEY_UP = key.keyToBeRead;
					buttomKeyUp.setText("Move Up: " + key.letter);
				}
			});
			buttomKeyRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(MazeGUI.gameOpt.KEY_RIGHT);
					key.setVisible(true);
					MazeGUI.gameOpt.KEY_RIGHT = key.keyToBeRead;
					buttomKeyRight.setText("Move Right: " + key.letter);
				}
			});
			buttomKeyLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(MazeGUI.gameOpt.KEY_LEFT);
					key.setVisible(true);
					MazeGUI.gameOpt.KEY_LEFT = key.keyToBeRead;
					buttomKeyLeft.setText("Move Left: " + key.letter);
				}
			});
			buttomKeyDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(MazeGUI.gameOpt.KEY_DOWN);
					key.setVisible(true);
					MazeGUI.gameOpt.KEY_DOWN = key.keyToBeRead;
					buttomKeyDown.setText("Move Down: " + key.letter);
				}
			});
		}

	}

	public int getKeyDown() {
		return MazeGUI.gameOpt.KEY_DOWN;
	}

	public int getKeyUp() {
		return MazeGUI.gameOpt.KEY_UP;
	}

	public int getKeyRight() {
		return MazeGUI.gameOpt.KEY_RIGHT;
	}

	public int getKeyLeft() {
		return MazeGUI.gameOpt.KEY_LEFT;
	}
}
