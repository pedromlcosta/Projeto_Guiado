package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import maze.logic.Darts;
import maze.logic.Dragon;
import maze.logic.Status;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JMazeOptions extends JDialog implements MouseMotionListener {
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
	JLabel detailsLabel;
	int mouseX = 1;
	int mouseY = 1;

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
			sizeField.setBounds(312, 38, 86, 20);
			sizeField.setColumns(10);
		}
		{
			label = new Label("Dragons number");
			label.setBounds(10, 76, 94, 22);
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

		detailsLabel = new JLabel("Details");
		detailsLabel.setBounds(10, 136, 289, 81);
		contentPanel.add(detailsLabel);
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
						if (mazeChoice != 1 && mazeChoice != 2)
							mazeChoice = 1;
						mazeSize = Integer.parseInt(sizeField.getText());
						if (mazeSize % 2 == 0)
							mazeSize++;
						dragonChoice = Integer.parseInt(dragonChoiceField.getText());
						if (dragonChoice != 1 && dragonChoice != 2 && dragonChoice != 3)
							dragonChoice = 1;

						dartsSize = Integer.parseInt(dartsNumber.getText());
						if (dartsSize >= mazeSize * 0.75)
							dartsSize = mazeSize / 4;
						dragonsSize = Integer.parseInt(dragonsNumber.getText());

						if (dragonsSize >= mazeSize * 0.75)
							dragonsSize = mazeSize / 4;
					} catch (Exception e) {

					}
					setVisible(false);

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
						dragonChoice = 1;
						mazeChoice = 1;
						dartsSize = 0;
						dragonsSize = 1;
						mazeSize = 11;

					}
				}
			});
		}
		addMouseMotionListener(this);
	}

	public void mouseDragged(MouseEvent arg0) {

	}

	public void Details() {

		if (Math.abs(label.getX() - mouseX) < label.getWidth() && Math.abs(mouseY - label.getY()) < label.getHeight())
			detailsLabel.setText("<html>Details:<br>" + "Pick the number of dragons <br>" + "(It must be smaller than 75% of the Maze Size)</html>");
		else if (Math.abs(label_1.getX() - mouseX) < label_1.getWidth() && Math.abs(mouseY - label_1.getY()) < label_1.getHeight())
			detailsLabel.setText("<html>Deatails:<br>" + "1 - Default Maze<br>" + "2 - Random Generated Maze");
		else if (Math.abs(label_2.getX() - mouseX) < label_2.getWidth() && Math.abs(mouseY - label_2.getY()) < label_2.getHeight())
			detailsLabel.setText("<html>Details:<br>" + "1 - Static Dragon\n" + "2 - Random Moving Dragon\n" + "3 - Random Moving Dragon w/ sleep time\n</html>");
		else if (Math.abs(label_3.getX() - mouseX) < label_3.getWidth() && Math.abs(mouseY - label_3.getY()) < label_3.getHeight())
			detailsLabel.setText("<html>Details:<br>" + " Pick the number of darts \n(It must be smaller than 75% of the Maze Size)</html>");
		else if (Math.abs(label_4.getX() - mouseX) < label_4.getWidth() && Math.abs(mouseY - label_4.getY()) < label_4.getHeight())
			detailsLabel.setText("<html>Details:\n Pick the size of the Maze (the size must be an odd number)</html>");
		repaint();
	}

	public void mouseMoved(MouseEvent arg0) {
		System.out.println(arg0.getX() + "  " + arg0.getY());
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		Details();

	}
}
