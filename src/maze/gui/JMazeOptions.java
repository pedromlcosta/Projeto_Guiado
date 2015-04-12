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
	JDialog window;
	JPanel contentPanel = new JPanel();
	JPanel buttonPane = new JPanel();
	JButton cancelButton;
	JButton doneButton;
	Label label_1;
	Label label_2;
	Label label_3;
	Label label_4;
	Label label;
	JSpinner Darts;
	JSpinner sizeSpinner;
	JSpinner dragonNumber;
	Choice dragonChoices;
	Choice mazeChoices;

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
	// }
	// }

	/**
	 * Create the dialog.
	 */
	public JMazeOptions() {
		// JMazeOptions dialog = new JMazeOptions();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);

		// Default Values
		dragonChoice = 1;
		mazeChoice = 1;
		dartsSize = 0;
		dragonsSize = 1;
		mazeSize = 11;
		//

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 31, 500, 250);
		this.setBounds(0, 0, 500, 250);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			label_1 = new Label("Maze choice");
			label_1.setBounds(10, 9, 75, 22);
		}
		{
			label_2 = new Label("Dragon choice");
			label_2.setBounds(10, 81, 84, 22);
		}
		{
			label_3 = new Label("Darts number");
			label_3.setBounds(238, 42, 79, 22);
		}
		{
			label_4 = new Label("Size");
			label_4.setBounds(239, 9, 35, 22);
		}
		{
			label = new Label("Dragons number");
			label.setBounds(10, 42, 94, 22);
		}
		contentPanel.setLayout(null);
		contentPanel.add(label_1);
		contentPanel.add(label_2);
		contentPanel.add(label_3);
		contentPanel.add(label_4);
		contentPanel.add(label);
		{
			Darts = new JSpinner();
			Darts.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			Darts.setBounds(333, 44, 66, 22);
			// Darts.
			contentPanel.add(Darts);
		}
		{
			sizeSpinner = new JSpinner();
			sizeSpinner.setModel(new SpinnerNumberModel(new Integer(11), new Integer(5), null, new Integer(2)));
			sizeSpinner.setBounds(331, 11, 68, 20);
			contentPanel.add(sizeSpinner);
		}
		{
			dragonNumber = new JSpinner();
			dragonNumber.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
			dragonNumber.setBounds(110, 42, 66, 22);
			contentPanel.add(dragonNumber);
		}

		dragonChoices = new Choice();
		dragonChoices.setBounds(109, 83, 208, 20);
		dragonChoices.add("Static Dragon");
		dragonChoices.add("Random Moving Dragon");
		dragonChoices.add("Random Moving Dragon w/ sleep");
		contentPanel.add(dragonChoices);
		{
			mazeChoices = new Choice();
			mazeChoices.add("Default");
			mazeChoices.add("Random");
			mazeChoices.setBounds(107, 9, 122, 22);
			contentPanel.add(mazeChoices);
		}

		{
			buttonPane.setBounds(0, 0, 434, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				doneButton = new JButton("Done");
				doneButton.setBounds(109, 5, 73, 23);
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
						// mazeChoices;

					} catch (Exception e) {

					}
					setVisible(false);

				}
			});
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBounds(245, 5, 83, 23);
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
