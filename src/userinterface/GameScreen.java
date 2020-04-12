package userinterface;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JPanel;

import objectGame.Cactus;
import objectGame.Clouds;
import objectGame.EnemyManager;
import objectGame.Land;
import objectGame.MainCharacter;
import util.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener{
	public static final int GAME_FIRST_STATE =0;
	public static final int GAME_PLAY_STATE =1;
	public static final int GAME_OVER_STATE =2;
	public static final float GRAVITY=0.1f;
	public static final float GROUNDY=120;
	private MainCharacter maincharater;
	private Thread thread;
	private Land land;
	private Clouds clouds;
	private EnemyManager enemyManager;
	
	private int Score=0;
	private int gameState=GAME_FIRST_STATE;
	private BufferedImage imageGameOver;
	private AudioClip scoreup;
 @SuppressWarnings("deprecation")
public GameScreen() {
	 thread= new Thread(this);
	 maincharater = new MainCharacter();
	 maincharater.setX(50);
	 maincharater.setY(50);
	 land = new Land(this);
	 clouds = new Clouds();
	 enemyManager = new EnemyManager(maincharater,this);
	 imageGameOver=Resource.getResourceImage("data/gameover_text.png");
	 try {
	 scoreup = Applet.newAudioClip("data/scoreup.waw");
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
 }
public void startGame() {
	thread.start();
}
@Override
public void run() {
	while(true) {
		//incremented vales of scores
		try {
			update();
			repaint();
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	
}
	}

public void paint(Graphics g) {
	//super.paint(g);
	
	g.setColor(Color.decode("#f7f7f7"));
	g.fillRect(0, 0, getWidth(), getHeight());
	//g.setColor(Color.RED);
	//g.drawLine(0,(int)GROUNDY,getWidth(),(int)GROUNDY);
	
	switch(gameState) {
	case (int)GAME_FIRST_STATE:
		maincharater.draw(g);
		break;
	case (int) GAME_PLAY_STATE:
		
		clouds.draw(g);
		land.draw(g);
		maincharater.draw(g);
		enemyManager.draw(g);
		g.drawString("HI  "+String.valueOf(Score), 500, 20);
		break;
	case (int) GAME_OVER_STATE:
		clouds.draw(g);
		land.draw(g);
		maincharater.draw(g);
		enemyManager.draw(g);
g.drawImage(imageGameOver,200,50,null);
		break;
	}
	
}


public void update() {
	switch (gameState) {
	case GAME_PLAY_STATE:
		maincharater.update();
		land.update();
		clouds.update();
		enemyManager.update();
		if(!maincharater.getAlive()) {
			gameState=GAME_OVER_STATE;

		}		break;
	}
	
}

public void restartGame() {
	maincharater.setAlive(true);
	maincharater.setX(50);
	maincharater.setY(50);
	enemyManager.reset();
	Score=0;
}
@Override
public void keyPressed(KeyEvent e) {
}
@Override
public void keyReleased(KeyEvent e) {
	switch(e.getKeyCode()) {
	case KeyEvent.VK_SPACE:
		if(gameState==GAME_FIRST_STATE) {
			gameState=GAME_PLAY_STATE;
		}else if(gameState== GAME_PLAY_STATE) {
			maincharater.jump();
		}else if(gameState==GAME_OVER_STATE) {
			restartGame();
			gameState=GAME_PLAY_STATE;
			
		}
	}
}
@Override
public void keyTyped(KeyEvent e) {
	
}
public void plusScore(int i) {
	Score+=i;
	scoreup.play();
}
 

}
