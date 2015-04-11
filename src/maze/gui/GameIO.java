package maze.gui;

import java.io.File;
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
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void saveGame(Status s) {
		
		try {
			// Serializing data object to a file
			out = new ObjectOutputStream(new FileOutputStream("Maze.sav"));
			out.writeObject(s);
			out.close();
			
			// Updating gameIO status
			this.status = s;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
                        
	public Status loadGame() {
		
		try {
			File test = new File("Maze.sav");
			if(!test.exists()){
				System.out.println("lelelel derped");
				return null;
			}
			FileInputStream stuff = new FileInputStream("Maze.sav");
			in = new ObjectInputStream(stuff);
			this.status = (Status) in.readObject();
			return status;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return status;
		
	}
}
