package mygame;

import java.awt.Rectangle;

public class Enemy {
	private int maxHealth, currentHealth, power, speedX, centerX, centerY;
	private Background bg = StartingClass.getBg1();
	private Robot robot = StartingClass.getRobot();
	
	public Rectangle r = new Rectangle(0,0,0,0);
	
	public int health = 5;
	private int movementSpeed;

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public void update() {
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		r.setBounds(centerX - 25, centerY-25, 50, 60);
		
		if(r.intersects(Robot.yellowRed)) {
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if (r.intersects(Robot.rect) || r.intersects(Robot.rect2) || r.intersects(Robot.rect3) || r.intersects(Robot.rect4)) {
			System.out.println("collision");
		}
	}
	
	public void follow() {
		if (centerX < -95 || centerX > 810) {
			movementSpeed = 0;
		} else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		} else {
			if (robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}
	}

	public void die() {

	}

	public void attack() {

	}
}
