package maze.gui;

import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class JMazeOptions extends JDialog {
	
	final int defaultRandomSize = 13;
	
//	int dragonsSize;
//	int dartsSize;
//	int mazeSize;
//	int mazeChoice;
//	int dragonChoice;
//	int KEY_UP;
//	int KEY_DOWN;
//	int KEY_LEFT;
//	int KEY_RIGHT;
	
	public static GameOptions gameOpt;
	
	JPanel contentPanel;
	JPanel buttonPane;
	JButton cancelButton;
	JButton doneButton;
	Label labelMazeChoice;
	Label labelDragonChoice;
	Label labelDartsNumber;
	Label labelMazeSize;
	Label labelDragonNumber;
	JSpinner Darts;
	JSpinner sizeSpinner;
	JSpinner dragonNumber;
	Choice dragonChoices;
	Choice mazeChoices;
	JButton buttomKeyUp;
	JButton buttomKeyRight;
	JButton buttomKeyLeft;
	JButton buttomKeyDown;
	
	KeyBinding key;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// JMazeOptions dialog = new JMazeOptions();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }}

	/**
	 * Create the dialog.
	 */
	public JMazeOptions() {
		// JMazeOptions dialog = new JMazeOptions();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
		contentPanel = new JPanel();
		buttonPane = new JPanel();
		
		// Default Values
		gameOpt = new GameOptions();

		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 31, 484, 292);
		this.setBounds(0, 0, 500, 350);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			labelMazeChoice = new Label("Maze choice");
			labelMazeChoice.setBounds(10, 35, 75, 22);
		}
		{
			labelDragonChoice = new Label("Dragon choice");
			labelDragonChoice.setBounds(10, 133, 84, 22);
		}
		{
			labelDartsNumber = new Label("Darts number");
			labelDartsNumber.setBounds(239, 79, 79, 22);
		}
		{
			labelMazeSize = new Label("Size");
			labelMazeSize.setBounds(242, 35, 35, 22);
		}
		{
			labelDragonNumber = new Label("Dragons number");
			labelDragonNumber.setBounds(10, 79, 94, 22);
		}
		contentPanel.setLayout(null);
		contentPanel.add(labelMazeChoice);
		contentPanel.add(labelDragonChoice);
		contentPanel.add(labelDartsNumber);
		contentPanel.add(labelMazeSize);
		contentPanel.add(labelDragonNumber);
		{
			Darts = new JSpinner();
			Darts.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			Darts.setBounds(324, 79, 66, 22);
			// Darts.
			contentPanel.add(Darts);
		}
		{
			sizeSpinner = new JSpinner();
			sizeSpinner.setModel(new SpinnerNumberModel(new Integer(11), new Integer(5), null, new Integer(2)));
			sizeSpinner.setBounds(322, 37, 68, 20);
			contentPanel.add(sizeSpinner);
		}
		{
			dragonNumber = new JSpinner();
			dragonNumber.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
			dragonNumber.setBounds(110, 79, 66, 22);
			contentPanel.add(dragonNumber);
		}
		dragonChoices = new Choice();
		
		dragonChoices.setBounds(100, 135, 208, 20);
		dragonChoices.add("Random Moving Dragon w/ sleep");
		dragonChoices.add("Random Moving Dragon");
		dragonChoices.add("Static Dragon");

		contentPanel.add(dragonChoices);
		{
			mazeChoices = new Choice();

			mazeChoices.add("Random");
			mazeChoices.add("Default");
			mazeChoices.setBounds(91, 37, 122, 22);
			contentPanel.add(mazeChoices);
		}
		{
			buttomKeyUp = new JButton("Move Up: ");
			buttomKeyUp.setBounds(33, 185, 199, 22);
			contentPanel.add(buttomKeyUp);
		}
		{
			buttomKeyRight = new JButton("Move Right: ");
			buttomKeyRight.setBounds(263, 218, 199, 23);
			contentPanel.add(buttomKeyRight);
		}
		{
			buttomKeyLeft = new JButton("Move Left: ");
			buttomKeyLeft.setBounds(263, 185, 199, 23);
			contentPanel.add(buttomKeyLeft);
		}
		{
			buttomKeyDown = new JButton("Move Down: ");
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

						gameOpt.mazeSize = (int) sizeSpinner.getValue();
						if (gameOpt.mazeSize % 2 == 0)
							gameOpt.mazeSize++;

						gameOpt.dartsSize = (int) Darts.getValue();
						if (gameOpt.dartsSize >= gameOpt.mazeSize * 0.75)
							gameOpt.dartsSize = gameOpt.mazeSize / 4;

						gameOpt.dragonsSize = (int) dragonNumber.getValue();
						if (gameOpt.dragonsSize >= gameOpt.mazeSize * 0.75)
							gameOpt.dragonsSize = gameOpt.mazeSize / 4;

						switch (mazeChoices.getSelectedItem()) {
						case "Default":
							gameOpt.mazeChoice = 1;
							break;
						case "Random":
							gameOpt.mazeChoice = 2;
							break;
						default:
							break;
						}
						switch (dragonChoices.getSelectedItem()) {
						case "Static Dragon":
							gameOpt.dragonChoice = 1;
							break;
						case "Random Moving Dragon":
							gameOpt.dragonChoice = 2;
							break;
						case "Random Moving Dragon w/ sleep":
							gameOpt.dragonChoice = 3;
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
						gameOpt.dragonChoice = 1;
						gameOpt.mazeChoice = 1;
						gameOpt.dartsSize = 0;
						gameOpt.dragonsSize = 1;
						gameOpt.mazeSize = 11;

					}
				}
			});
			buttomKeyUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(gameOpt.KEY_UP);
					key.setVisible(true);
					gameOpt.KEY_UP = key.keyToBeRead;
					buttomKeyUp.setText("Move Up: " + key.letter);
				}
			});
			buttomKeyRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(gameOpt.KEY_RIGHT);
					key.setVisible(true);
					gameOpt.KEY_RIGHT = key.keyToBeRead;
					buttomKeyRight.setText("Move Right: " + key.letter);
				}
			});
			buttomKeyLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(gameOpt.KEY_LEFT);
					key.setVisible(true);
					gameOpt.KEY_LEFT = key.keyToBeRead;
					buttomKeyLeft.setText("Move Left: " + key.letter);
				}
			});
			buttomKeyDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					key = new KeyBinding(gameOpt.KEY_DOWN);
					key.setVisible(true);
					gameOpt.KEY_DOWN = key.keyToBeRead;
					buttomKeyDown.setText("Move Down: " + key.letter);
				}
			});
		}

	}

	public int getKeyDown() {
		return gameOpt.KEY_DOWN;
	}

	public int getKeyUp() {
		return gameOpt.KEY_UP;
	}

	public int getKeyRight() {
		return gameOpt.KEY_RIGHT;
	}

	public int getKeyLeft() {
		return gameOpt.KEY_LEFT;
	}
}
