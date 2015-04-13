package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import maze.logic.*;

import javax.imageio.ImageIO;

public class MazeGraphics {

	static BufferedImage floor;
	static BufferedImage empty;
	static BufferedImage dragon;
	static BufferedImage dragonSleep;
	static BufferedImage dragonSword;
	static BufferedImage hero;
	static BufferedImage heroShield;
	static BufferedImage heroSword;
	static BufferedImage heroSwordShield;
	static BufferedImage sword;
	static BufferedImage darts;
	static BufferedImage shield;
	static BufferedImage exit;
	static BufferedImage dragonSwordSleep;
	
	public static void load() throws IOException {
		floor = ImageIO.read(new File("images\\wall.png"));
		empty = ImageIO.read(new File("images\\empty.png"));
		dragon = ImageIO.read(new File("images\\dragon.png"));
		hero = ImageIO.read(new File("images\\hero.png"));
		sword = ImageIO.read(new File("images\\sword.png"));
		darts = ImageIO.read(new File("images\\dart.png"));
		shield = ImageIO.read(new File("images\\shield.png"));
		exit = ImageIO.read(new File("images\\exit_open.png"));
		dragonSleep = ImageIO.read(new File("images\\dragonSleeping.png"));
		dragonSword = ImageIO.read(new File("images\\swordDragon.png"));
		heroShield = ImageIO.read(new File("images\\heroShield.png"));
		heroSword = ImageIO.read(new File("images\\heroArmed.png"));
		dragonSwordSleep = ImageIO.read(new File("images\\swordDragonSleeping.png"));
		heroSwordShield = ImageIO.read(new File("images\\heroArmedShield.png"));
	}
	
	public static void paintMaze(Graphics g, int offsetX, int offsetY,int size, Status s) throws IOException {
		int x1 = 0 + offsetX;
		int y1 = offsetY;
		for (int j = 0; j < s.getMaze().getMaze().length; j++) {
			for (int i = 0; i < s.getMaze().getMaze().length; i++) {
				if (s.getMaze().getMaze()[j][i] == 'X') {
					g.drawImage(floor, x1, y1, size, size, null);
				} else if (s.getMaze().getMaze()[j][i] == ' ') {
					g.drawImage(empty, x1, y1, size, size, null);
				} else {
					printCharacter(s.getMaze().getMaze()[j][i], x1, y1, size, size, g);
				}
				x1 += size;
			}
			y1 += size;

			x1 = 0 + offsetX;
		}
	}

	public static void printCharacter(char character, int x1, int y1, int sizeX, int sizeY, Graphics g) throws IOException {

		switch (character) {
		case 'H':
			g.drawImage(hero, x1, y1, sizeX, sizeY, null);
			break;
		case 'p':
			g.drawImage(heroShield, x1, y1, sizeX, sizeY, null);
			break;
		case 'A':
			g.drawImage(heroSword, x1, y1, sizeX, sizeY, null);
			break;
		case 'K':
			g.drawImage(heroSwordShield, x1, y1, sizeX, sizeY, null);
			break;
		case 'D':
			g.drawImage(dragon, x1, y1, sizeX, sizeY, null);
			break;
		case 'S':
			g.drawImage(exit, x1, y1, sizeX, sizeY, null);
			break;
		case 'E':
			g.drawImage(sword, x1, y1, sizeX, sizeY, null);
			break;
		case 'F':
			g.drawImage(dragonSword, x1, y1, sizeX, sizeY, null);
			break;
		case 'Z':
			g.drawImage(dragonSleep, x1, y1, sizeX, sizeY, null);
			break;
		case 'P':
			g.drawImage(shield, x1, y1, sizeX, sizeY, null);
			break;
		case '-':
			g.drawImage(darts, x1, y1, sizeX, sizeY, null);
			break;
		case 'f':
			g.drawImage(dragonSwordSleep, x1, y1, sizeX, sizeY, null);
			break;
		default:
			g.drawImage(empty, x1, y1, sizeX, sizeY, null);

		}

	}
}
