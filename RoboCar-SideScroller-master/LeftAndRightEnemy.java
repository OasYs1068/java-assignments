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

public class LeftAndRightEnemy extends Enemy{

	private int startX;
	private int endX;

	public LeftAndRightEnemy(int startX, int endX, int y, int xMoveSpeed, int yMoveSpeed, BufferedImage image, int globalPos, int health, int redFramesCount, int animationFrames, boolean active) {
		super (startX, y, xMoveSpeed, yMoveSpeed, image, globalPos, health, redFramesCount, animationFrames, active);
		this.startX = startX;
		this.endX = endX;
	}
	public int getStartX() {return startX;}
	public int getEndX() {return endX;}
	public void setStartX(int changeTo) {startX = changeTo;}
	public void setEndX(int changeTo) {endX = changeTo;}
	public void reverseX() {setXMoveSpeed(-1*getXMoveSpeed());}
}