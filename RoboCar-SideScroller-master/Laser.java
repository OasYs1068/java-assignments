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

public class Laser {

	private int killCount;
	private double angle, xMoveSpeed, yMoveSpeed, x, y, moveSpeed, endX, endY;
	private boolean playerLaser, active;

	public Laser(double x, double y, double angle, double moveSpeed, boolean playerLaser) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.moveSpeed = moveSpeed;
		this.playerLaser = playerLaser;
		xMoveSpeed = moveSpeed*Math.cos(Math.toRadians(angle));
		yMoveSpeed = moveSpeed*Math.sin(Math.toRadians(angle));
		if (yMoveSpeed > 20) {  //max cap on vertical laser movement speed
			yMoveSpeed = 20;
		}
		if (y <= 496) {
			yMoveSpeed *= -1;
		}
		endX = x + (63.0)*Math.cos(Math.toRadians(angle));
		endY = y - (63.0)*Math.sin(Math.toRadians(angle));

		killCount = 0;
		active = true;
	}

	public double getX() {return x;}
	public double getY() {return y;}
	public void setX(double changeTo) {x = changeTo;}
	public void setY(double changeTo) {y = changeTo;}
	public double getAngle() {return angle;}
	public double getMoveSpeed() {return moveSpeed;}
	public void setMoveSpeed(int changeTo) {moveSpeed = changeTo;}
	public String toString() {return "Laser: x:" + x + ", y:" + y + ", angle:" + angle + ", speed: " + moveSpeed + ",xMove: " + xMoveSpeed + ", yMove:" + yMoveSpeed;}
	public double getXMoveSpeed() {return xMoveSpeed;}
	public double getYMoveSpeed() {return yMoveSpeed;}
	public double getEndX() {return endX;}
	public double getEndY() {return endY;}
	public void setEndX(double changeTo) {endX = changeTo;}
	public void setEndY(double changeTo) {endY = changeTo;}
	public boolean getPlayerLaser() {return playerLaser;}
	public void increaseKillCount() {killCount ++;}
	public int getKillCount() {return killCount;}
	public boolean getActive() {return active;}
	public void deactivate() {active = false;}
}