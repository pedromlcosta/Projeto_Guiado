package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import maze.logic.Darts;
import maze.logic.Dragon;
import maze.logic.Status;

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
	JTextField mazeChoiceField;
	JTextField sizeField;
	JTextField dragonChoiceField;
	JTextField dartsNumber;
	JTextField dragonsNumber;
	JButton cancelButton;
	JButton doneButton;
	Label label_1;
	Label label_2;
	Label label_3;
	Label label_4;
	Label label;

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

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 33, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			label_1 = new Label("Maze choice");
			label_1.setBounds(10, 9, 75, 22);
		}
		{
			mazeChoiceField = new JTextField();
			mazeChoiceField.setBounds(112, 9, 86, 20);
			mazeChoiceField.setColumns(10);
		}
		{
			label_2 = new Label("Dragon choice");
			label_2.setBounds(215, 9, 84, 22);
		}
		{
			dragonChoiceField = new JTextField();
			dragonChoiceField.setBounds(312, 11, 86, 20);
			dragonChoiceField.setColumns(10);
		}
		{
			label_3 = new Label("Darts number");
			label_3.setBounds(10, 36, 79, 22);
		}
		{
			dartsNumber = new JTextField();
			dartsNumber.setBounds(112, 40, 86, 20);
			dartsNumber.setColumns(10);
		}
		{
			label_4 = new Label("Size");
			label_4.setBounds(225, 38, 35, 22);
		}
		{
			sizeField = new JTextField();
			sizeField.setBounds(312, 42, 86, 20);
			sizeField.setColumns(10);
		}
		{
			label = new Label("Dragons number");
			label.setBounds(12, 76, 94, 22);
		}
		{
			dragonsNumber = new JTextField();
			dragonsNumber.setBounds(112, 78, 86, 20);
			dragonsNumber.setColumns(10);
		}
		contentPanel.setLayout(null);
		contentPanel.add(label_1);
		contentPanel.add(mazeChoiceField);
		contentPanel.add(label_2);
		contentPanel.add(dragonChoiceField);
		contentPanel.add(label_3);
		contentPanel.add(dartsNumber);
		contentPanel.add(label_4);
		contentPanel.add(sizeField);
		contentPanel.add(label);
		contentPanel.add(dragonsNumber);
		{
			buttonPane.setBounds(0, 0, 434, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				doneButton = new JButton("Done");
				doneButton.setBounds(109, 5, 57, 23);
				buttonPane.add(doneButton);
			}
			
					doneButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
			
								mazeChoice = Integer.parseInt(mazeChoiceField.getText());
								mazeSize = Integer.parseInt(sizeField.getText());
								dragonChoice = Integer.parseInt(dragonChoiceField.getText());
								dartsSize = Integer.parseInt(dartsNumber.getText());
								dragonsSize = Integer.parseInt(dragonsNumber.getText());
								System.out.println("OK");
							} catch (Exception e) {
							}
						}
					});
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBounds(245, 5, 65, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						contentPanel.setVisible(false);
						contentPanel.setVisible(false);

					} catch (Exception e) {
					}
				}
			});
		}
	}

	public void generateMaze(Status status) {
		if (mazeChoice == 1) {
			dragonChoice = 1;
			mazeChoice = 1;
			dartsSize = 0;
			dragonsSize = 1;
			mazeSize = 10;
			status.defaultMaze();
		} else if (mazeChoice == 2) {
			status.setDragonChoice(dragonChoice);
			status.setDarts(new Darts[dartsSize]);
			status.setDragons(new Dragon[dragonsSize]);
			status.randomMaze(defaultRandomSize, 1);
		}
	}

}
