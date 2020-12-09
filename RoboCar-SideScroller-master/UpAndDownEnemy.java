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

public class UpAndDownEnemy extends Enemy{

	private int startY;
	private int endY;

	public UpAndDownEnemy(int x, int startY, int endY, int xMoveSpeed, int yMoveSpeed, BufferedImage image, int globalPos, int health, int redFramesCount, int animationFrames, boolean active) {
		super (x, startY, xMoveSpeed, yMoveSpeed, image, globalPos, health, redFramesCount, animationFrames, active);
		this.startY = startY;
		this.endY = endY;
	}
	public int getStartY() {return startY;}
	public int getEndY() {return endY;}
	public void reverseY() {setYMoveSpeed(-1*getYMoveSpeed());}
}