package userinterface;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import com.sun.org.apache.bcel.internal.classfile.Field;

public class Gamewindow extends JFrame{

	private GameScreen gameScreen;
	
	public Gamewindow() {
		super("JAVA Dino Game");
		setSize(600,175);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameScreen = new GameScreen();
		add(gameScreen);
		addKeyListener(gameScreen);
	}

	public void startGame() {
		gameScreen.startGame();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gamewindow gw = new Gamewindow();
		gw.setVisible(true);
		gw.startGame();
			}

}