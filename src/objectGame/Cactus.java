package objectGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Resource;

public class Cactus extends Enemy {
	private BufferedImage cactusImage;
	private int posX,posY;
	private Rectangle rect;
	private MainCharacter mainCharacter;
	private boolean isScoreGot= false;
	public Cactus(MainCharacter mainCharacter){
		this.mainCharacter = mainCharacter;
		cactusImage= Resource.getResourceImage("data/cactus1.png");
	
		posX=200;
		posY=80;
		rect = new Rectangle();
//		rect.intersects(r);
//cactusImage= Resource.getResourceImage("data/cactus2.PNG");
	}
	
	@Override
public void draw(Graphics g) {
	g.drawImage(cactusImage,posX,posY,null);
}
@Override
public Rectangle getBound() {
	return rect;
}
public void update() {
	posX-=2;
	rect.x=posX;
	rect.y=posY;
	rect.width=cactusImage.getWidth();
	rect.height=cactusImage.getHeight();
}

public void setX(int x) {
	posX=x;
}
public void setY(int y) {
	posY=y;
}
public void setImage(BufferedImage image) {
	cactusImage=image;
}
@Override
public boolean isOutOfScreen() {
	return(posX + cactusImage.getWidth()<0);
	
}
@Override
public boolean isScoreGot() {
	return isScoreGot;
}
public void setScoreGot(boolean isScoreGot) {
	this.isScoreGot=isScoreGot;
}
@Override
public boolean isOver() {
	return(mainCharacter.getX() > posX);
}
}
