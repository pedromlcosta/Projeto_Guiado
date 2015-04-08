package maze.gui;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import maze.logic.Status;

public class GameIO {
	ObjectOutputStream out;
	ObjectInputStream in;
	Status status;

	public GameIO(Status s) {
		out = null;
		in = null;
		status = s;
	}

	public void saveGame() {
		
		try {
			// Serialize data object to a file
			out = new ObjectOutputStream(new FileOutputStream("MyObject.ser"));
			out.writeObject(status);
			out.close();

		} catch (IOException e) {
		}
	}

	public void loadGame() {
		
		try {
			FileInputStream stuff = new FileInputStream("name_of_file.sav");
			in = new ObjectInputStream(stuff);
			Status s = new Status();
			s = (Status) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
