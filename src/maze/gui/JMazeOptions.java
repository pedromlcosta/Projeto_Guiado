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
	int dragonsSize;
	int dartsSize;
	int mazeSize;
	int mazeChoice;
	int dragonChoice;
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
	private JButton buttomKeyUp;
	private JButton buttomKeyRight;
	private JButton buttomKeyLeft;
	private JButton buttomKeyBack;

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
		dragonChoice = 1;
		mazeChoice = 1;
		dartsSize = 0;
		dragonsSize = 1;
		mazeSize = 11;
		//

		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 31, 500, 400);
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
		dragonChoices.add("Static Dragon");
		dragonChoices.add("Random Moving Dragon");
		dragonChoices.add("Random Moving Dragon w/ sleep");
		contentPanel.add(dragonChoices);
		{
			mazeChoices = new Choice();
			mazeChoices.add("Default");
			mazeChoices.add("Random");
			mazeChoices.setBounds(91, 37, 122, 22);
			contentPanel.add(mazeChoices);
		}
		{
			buttomKeyUp = new JButton("New button");
			buttomKeyUp.setBounds(83, 185, 120, 22);
			contentPanel.add(buttomKeyUp);
		}
		{
			buttomKeyRight = new JButton("New button");
			buttomKeyRight.setBounds(298, 218, 121, 23);
			contentPanel.add(buttomKeyRight);
		}
		{
			buttomKeyLeft = new JButton("New button");
			buttomKeyLeft.setBounds(298, 185, 121, 23);
			contentPanel.add(buttomKeyLeft);
		}
		{
			buttomKeyBack = new JButton("New button");
			buttomKeyBack.setBounds(84, 218, 119, 23);
			contentPanel.add(buttomKeyBack);
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

						mazeSize = (int) sizeSpinner.getValue();
						if (mazeSize % 2 == 0)
							mazeSize++;

						dartsSize = (int) Darts.getValue();
						if (dartsSize >= mazeSize * 0.75)
							dartsSize = mazeSize / 4;

						dragonsSize = (int) dragonNumber.getValue();
						if (dragonsSize >= mazeSize * 0.75)
							dragonsSize = mazeSize / 4;

						switch (mazeChoices.getSelectedItem()) {
						case "Default":
							mazeChoice = 1;
							break;
						case "Random":
							mazeChoice = 2;
							break;
						default:
							break;
						}
						switch (dragonChoices.getSelectedItem()) {
						case "Static Dragon":
							dragonChoice = 1;
							break;
						case "Random Moving Dragon":
							dragonChoice = 2;
							break;
						case "Random Moving Dragon w/ sleep":
							dragonChoice = 3;
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
						contentPanel.setVisible(false);
						contentPanel.setVisible(false);

					} catch (Exception e) {
						dragonChoice = 1;
						mazeChoice = 1;
						dartsSize = 0;
						dragonsSize = 1;
						mazeSize = 11;

					}
				}
			});
		}
	}
}
