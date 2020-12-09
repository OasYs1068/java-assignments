import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Enemy {

	private int x;
	private int y;
	private int xMoveSpeed;
	private int yMoveSpeed;
	private BufferedImage image;
	private int globalPos, health, redFramesCount, animationFrames;
	private boolean active;

	public Enemy(int x, int y, int xMoveSpeed, int yMoveSpeed, BufferedImage image, int globalPos, int health, int redFramesCount, int animationFrames, boolean active) {
		this.x= x;
		this.y = y;
		this.xMoveSpeed = xMoveSpeed;
		this.yMoveSpeed = yMoveSpeed;
		this.image = image;
		this.globalPos = globalPos;
		this.health = health;
		this.redFramesCount = redFramesCount;
		this.animationFrames = animationFrames;
		this.active = active;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getXMoveSpeed() { return xMoveSpeed; }
	public int getYMoveSpeed() { return yMoveSpeed; }
	public BufferedImage getImage() { return image; }
	public int getGlobalPos() { return globalPos; }
	public int getHealth() {return health;}
	public int getRedFramesCount() {return redFramesCount;}
	public int getAnimationFrames() {return animationFrames;}
	public boolean getActive() {return active;}

	public void setX(int changeTo) { x = changeTo; }
	public void setY(int changeTo) { y = changeTo; }
	public void setXMoveSpeed(int changeTo) {xMoveSpeed = changeTo;}
	public void setYMoveSpeed(int changeTo) {yMoveSpeed = changeTo;}
	public void setImage(BufferedImage changeTo) {image = changeTo;}
	public void setGlobalPos(int changeTo) {globalPos = changeTo;}
	public void setHealth(int changeTo) {health = changeTo;}
	public void setRedFramesCount(int changeTo) {redFramesCount = changeTo;}
	public void decreaseRedFramesCount() {redFramesCount--;}
	public void setAnimationFrames(int changeTo) {animationFrames = changeTo;}
	public void deactivate() {active = false;}
}