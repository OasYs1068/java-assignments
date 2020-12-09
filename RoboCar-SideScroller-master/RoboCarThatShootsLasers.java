/*
minimap with tiny car at bottom, various weapons with different patterns, weapon upgrade drops that add bullets to the current weapon,
weapon drops, health bar, health pickups, boss

//http://www.java2s.com/Code/Java/2D-Graphics-GUI/Arotatingandscalingrectangle.htm - animations

shield powerup = puts (possibly animated) shiled over the car that deflects lasers and makes them playerlasers

new enemy types - vertical/horizontal up and down enemies with different sprites

add animation for scores - when you kill someone, have it display a +100 above the exlosion, +200 for the lasers, +(laserKillCount-1)*100 for multihits >=3

https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html - music clip
*/

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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.MalformedURLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.geom.Line2D;
import java.lang.Object;
import javafx.scene.shape.Line;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

public class RoboCarThatShootsLasers extends JPanel implements KeyListener,Runnable,MouseListener {

	private int globalX = 0;
	private int spiderPlace = 20000;
	private float angle;
	private int x = 600;    //300, 503 for normal play
	private int y = 503;
	private int imageNum = 0;
	private int moveSpeed = 2;
	private int tempMoveSpeed = moveSpeed;
	private boolean left, right, up, down;
	String lastDirection = "right";
	private int background1X;
	private int background2X;
	private JFrame frame;
	Thread t;
	private boolean gameOn = false;
	private boolean paused = false;
	private boolean victory = false;
	BufferedImage guy;
	BufferedImage image1;
	BufferedImage image2;
	BufferedImage laser, enemyLaser;
	BufferedImage carSheet, reverseCarSheet;
	BufferedImage enemyExplosionSheet;
	BufferedImage healthPickup;
	BufferedImage ammoPickup;
	BufferedImage titleFont;
	BufferedImage enterToStart;
	BufferedImage[] car = new BufferedImage[4];
	BufferedImage[] reverseCar = new BufferedImage[4];
	BufferedImage[] guys=new BufferedImage[11];
	BufferedImage[] flyingGuys = new BufferedImage[2];
	BufferedImage enemySpriteSheet;
	BufferedImage[] enemyExplosion = new BufferedImage[16];
	BufferedImage spiderBossSheet, spiderBossSheetRed, spiderBossSheetOver, spiderBossSheetRedOver;
	BufferedImage spiderString;
	BufferedImage[] spiderBoss = new BufferedImage[4];
	BufferedImage[] spiderBossRed = new BufferedImage[4];
	BufferedImage[] spiderBossOver = new BufferedImage[4];
	BufferedImage[] spiderBossRedOver = new BufferedImage[4];
	BufferedImage[] spiderMinion = new BufferedImage[3];
	BufferedImage[] spiderMinionRed = new BufferedImage[3];
	boolean restart=false;
	int imgCount=0;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Laser> lasers = new ArrayList<Laser>();
	ArrayList<Laser> enemyLasers = new ArrayList<Laser>();
	ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	int health = 10;
	int score = 0;
	int ammo = 30;
	final int healthChanceFINAL = 700;
	int healthChance = healthChanceFINAL;
	final int ammoChanceFINAL = 500;
	int ammoChance = ammoChanceFINAL;
	boolean showInfo = false;
	boolean showTutorial = false;
	boolean mute = false;
	boolean bossBattle = false;
	boolean bossBattleTransition = false;
	boolean bossBattleTransition2 = false;
	boolean regularGame = false;
	boolean tutorial = false;
	boolean title = true;
	double spiderBossX = 1000;
	double spiderBossY = -600;
	double spiderBossTempY = spiderBossY;
	final int spiderBossAnimationCountTimerValue = 20;
	int spiderBossAnimationCountTimer = spiderBossAnimationCountTimerValue;
	int spiderBossAnimationCount = 2;
	int spiderBossAnimationCountChange = -1;
	final int spiderBossRedFramesValue = 10;
	int spiderBossRedFramesCount = 0;
	final int spiderBossMaxHealth = 50;
	int spiderBossHealth = spiderBossMaxHealth;
	Clip carMusicClip;
	Clip bossMusicClip;
	int carMusicClipLocationPaused;
	int bossMusicClipLocationPaused;
	int spawnWaveTimerValue = 300;
	int spawnTimerValue = 46;
	int spawnTimerCount = spawnWaveTimerValue;
	int spawnCounter = 0;
	final int spiderMinionAnimationCountValue = 20;
	int spiderMinionAnimationCount = 1;
	boolean spawnSpiderMinions = true;
	final int spiderMinionsRedFramesValue =  10;
	int timeToDie = 200;
	boolean lostAllHealth = false;
	int spiderTimeToDie = 400;
	boolean spiderLostAllHealth = false;

	public RoboCarThatShootsLasers() {
		frame=new JFrame();
		background1X = 0;
		background2X = 1499;
		gameOn=true;

		//IMAGE STUFF
		try {
			image1 = ImageIO.read(new File("background.jpg"));
			image2 = ImageIO.read(new File("background.jpg"));

			laser = ImageIO.read(new File("laser3.png"));
			enemyLaser = ImageIO.read(new File("laser3Enemy.png"));

			carSheet = ImageIO.read(new File("carSheet.png"));
			reverseCarSheet = ImageIO.read(new File("reverseCarSheet.png"));
			for (int i = 0; i < 4; i ++) {
				car[i] = carSheet.getSubimage(0, i*80, 256, 80);
				reverseCar[i] = reverseCarSheet.getSubimage(0, i*80, 256, 80);
			}

			enemyExplosionSheet = ImageIO.read(new File("enemyExplosion.png"));
			//x: 0-64, 64-128, 128-192, 192-256
			int count = 0;
			for (int row = 0; row < 4; row ++) {
				for (int col = 0; col < 4; col ++) {
					enemyExplosion[count] = enemyExplosionSheet.getSubimage(80*col, 80*row, 80, 80);
					count ++;
				}
			}

			spiderBossSheet = ImageIO.read(new File("spiderBossSheet.png"));
			spiderBossSheetRed = ImageIO.read(new File("spiderBossSheetRed.png"));
			spiderBossSheetOver = ImageIO.read(new File("spiderBossSheetOver.png"));
			spiderBossSheetRedOver = ImageIO.read(new File("spiderBossSheetRedOver.png"));
			for (int i = 0; i < 4; i ++) {
				BufferedImage temp = spiderBossSheet.getSubimage(i*229, 0, 229, 263);
				spiderBoss[i] = resize(temp, 400, 468);
				temp = spiderBossSheetRed.getSubimage(i*229, 0, 229, 263);
				spiderBossRed[i] = resize(temp, 400, 468);
				temp = spiderBossSheetOver.getSubimage(i*229, 0, 229, 263);
				spiderBossOver[i] = resize(temp, 400, 468);
				temp = spiderBossSheetRedOver.getSubimage(i*229, 0, 229, 263);
				spiderBossRedOver[i] = resize(temp, 400, 468);
			}
			BufferedImage temp = spiderBoss[3];            //swap the order of the spider boss sprites to make stuff easier
			spiderBoss[3] = spiderBoss[0];
			spiderBoss[0] = temp;
			temp = spiderBossRed[3];                       //do the same for the set of red pictures
			spiderBossRed[3] = spiderBossRed[0];
			spiderBossRed[0] = temp;
			temp = spiderBossOver[3];                      //do same thing for "over" set
			spiderBossOver[3] = spiderBossOver[0];
			spiderBossOver[0] = temp;
			temp = spiderBossRedOver[3];                   //and also for "redOver" set
			spiderBossRedOver[3] = spiderBossRedOver[0];
			spiderBossRedOver[0] = temp;

			spiderMinion[0] = spiderBossSheet.getSubimage(0, 277, 90, 54);    //identify the spiderMinions and do the same for the red versions
			spiderMinion[1] = spiderBossSheet.getSubimage(90, 278, 74, 49);
			spiderMinion[2] = spiderBossSheet.getSubimage(165, 279, 59, 51);
			spiderMinionRed[0] = spiderBossSheetRed.getSubimage(0, 277, 90, 54);
			spiderMinionRed[1] = spiderBossSheetRed.getSubimage(90, 278, 74, 49);
			spiderMinionRed[2] = spiderBossSheetRed.getSubimage(165, 279, 59, 51);

			spiderString = ImageIO.read(new File("rope.png"));

			guy = ImageIO.read(new File("st1.png"));
			for(int x=0;x<11;x++) {
				guys[x]=guy.getSubimage(x*81,81,85,85);
			}

			enemySpriteSheet = ImageIO.read(new File("enemySprites.png"));
			flyingGuys[0] = enemySpriteSheet.getSubimage(0, 764, 91, 109);
			flyingGuys[1] = enemySpriteSheet.getSubimage(0, 327, 91, 109);

			healthPickup = resize(ImageIO.read(new File("healthHeart.jpg")), 30, 30);
			ammoPickup = resize(ImageIO.read(new File("ammoCrate.png")), 30, 39);

			titleFont = ImageIO.read(new File("titleFont.png"));
			enterToStart = ImageIO.read(new File("enterToStart.png"));
		}
		catch (IOException e) {
		}

		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.add(this);
		frame.setSize(1500,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();

		//MUSIC
		try {
			//AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("ItsMyLifeMusic.wav"));       //better music tbh
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("carMusic.wav"));
			carMusicClip = AudioSystem.getClip();
			if (!mute) {
				carMusicClip.open(inputStream);
			}
			carMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (Exception e) {
			System.out.println("ERROR");
		}


	}

	public void run()
	{
		while(true)
		{
			if(gameOn && !paused)
			{
				//Math happens here!

				if (title) {
					ammo = 100;
					tempMoveSpeed = 0;
					if (lastDirection.equals("right")) {
						if (background1X <= -1499) {
							background1X = background2X+1499;   //1499
						}
						if (background2X <= -1499) {
							background2X = background1X+1499;
						}
						tempMoveSpeed = -1 * moveSpeed;
					}
					if (lastDirection.equals("left")) {
						if (background1X >= 1499) {            //1499
							background1X = background2X-1499;  //1499
						}
						if (background2X >= 1499) {
							background2X = background1X-1499;
						}
						tempMoveSpeed = moveSpeed;
					}

					background1X += tempMoveSpeed;
					background2X += tempMoveSpeed;
					imageNum ++;
					if (imageNum == 4) {
						imageNum = 0;
					}

					//move lasers during title
					for (int i = lasers.size()-1; i >= 0; i --) {
						Laser tempLaser = lasers.get(i);
						tempLaser.setX(tempLaser.getX() + tempLaser.getXMoveSpeed());
						tempLaser.setY(tempLaser.getY() + tempLaser.getYMoveSpeed());
						tempLaser.setEndX(tempLaser.getEndX() + tempLaser.getXMoveSpeed());
						tempLaser.setEndY(tempLaser.getEndY() + tempLaser.getYMoveSpeed());

						//remove far-offscreen lasers
						if (tempLaser.getX() < -200 || tempLaser.getX() > 1700 || tempLaser.getY() < -200 || tempLaser.getY() > 1000) {
							lasers.remove(i);
						}
					}
				}

				if (!title && !tutorial && !regularGame) {  //the transition between title and tutorial
					tempMoveSpeed = 0;
					if (background1X <= -1499) {
						background1X = background2X+1499;   //1499
					}
					if (background2X <= -1499) {
						background2X = background1X+1499;
					}
					tempMoveSpeed = -1 * moveSpeed;

					background1X += tempMoveSpeed;
					background2X += tempMoveSpeed;
					imageNum ++;
					if (imageNum == 4) {
						imageNum = 0;
					}

					x -= 5;                         //move car backwards
					if (x <= 300) {
						x = 300;
						tutorial = true;
						moveSpeed = 7;
					}

					//move lasers during transition
					for (int i = lasers.size()-1; i >= 0; i --) {
						Laser tempLaser = lasers.get(i);
						tempLaser.setX(tempLaser.getX() + tempLaser.getXMoveSpeed());
						tempLaser.setY(tempLaser.getY() + tempLaser.getYMoveSpeed());
						tempLaser.setEndX(tempLaser.getEndX() + tempLaser.getXMoveSpeed());
						tempLaser.setEndY(tempLaser.getEndY() + tempLaser.getYMoveSpeed());

						//remove far-offscreen lasers
						if (tempLaser.getX() < -200 || tempLaser.getX() > 1700 || tempLaser.getY() < -200 || tempLaser.getY() > 1000) {
							lasers.remove(i);
						}
					}
				}


				//boss battle transition things
				if (!bossBattle && !bossBattleTransition && !bossBattleTransition2 && globalX >= spiderPlace) {
					bossBattleTransition = true;
				}

				if (bossBattleTransition) {                  					//stuff unique to bossBattleTransition
					carMusicClip.stop();
					if (enemies.size() == 0 && enemyLasers.size() == 0) {               //switch to actual bossBattle stage after all enemies are gone
						bossBattleTransition = false;
						bossBattleTransition2 = true;
					}
				}

				if (bossBattleTransition2) {

					health = 10;
					ammo = 100;

					if (bossMusicClip == null) {
						try {
							AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("skyrim.wav"));
							bossMusicClip = AudioSystem.getClip();
							if (!mute) {
								bossMusicClip.open(inputStream);
							}
							bossMusicClip.setMicrosecondPosition(38900000);
							bossMusicClip.setLoopPoints(2913190, 4699190);
							bossMusicClip.loop(Clip.LOOP_CONTINUOUSLY);

						} catch(Exception e) {
							System.out.println("BOSS ERROR");
						}
					}

					//spider animation
					spiderBossAnimationCountTimer--;                                          //decrease the 20-frame timer on boss animation ticks
					if (spiderBossAnimationCountTimer == 0) {                                 //if timer runs out
						spiderBossAnimationCountTimer = spiderBossAnimationCountTimerValue;   //reset the timer
						spiderBossAnimationCount += spiderBossAnimationCountChange;   		  //change the sprite
						if (spiderBossAnimationCount == -1) {								  //reset the sprite if it goes over limit, both up and down
							spiderBossAnimationCountChange *= -1;
							spiderBossAnimationCount = 1;
						}
						if (spiderBossAnimationCount == 3) {
							spiderBossAnimationCountChange *= -1;
							spiderBossAnimationCount = 1;
						}
					}
					if (spiderBossY < 0) {                   //spiderboss comes down
						spiderBossTempY += 0.75;			 //usually 0.8
						spiderBossY = (int)spiderBossTempY;
					}

					if (spiderBossY >= 0) {                  //end spiderBossTransition2 when spider fully comes down
						spiderBossY = 0;
						bossBattleTransition2 = false;
						bossBattle = true;
					}
				}

				if (bossBattleTransition || bossBattleTransition2 || bossBattle) {          //different car movement for bossbattletransitions and bossbattle
					if (right) {
						x += moveSpeed;
						if (x > 1230) //dont go offscreen right
							x = 1230;
					}
					if (left) {
						x -= moveSpeed;
						if (x < 0)    //dont go offscreen left
							x = 0;
					}
					if (left || right) {
						imageNum ++;
						if (imageNum == 4) {
							imageNum = 0;
						}
					}
				}


				if (bossBattle) {  //if currently in boss battle
					powerups.clear();

					//move lasers during bossbattle
					ammo = 100;
					for (int i = lasers.size()-1; i >= 0; i --) {
						Laser tempLaser = lasers.get(i);
						tempLaser.setX(tempLaser.getX() + tempLaser.getXMoveSpeed());
						tempLaser.setY(tempLaser.getY() + tempLaser.getYMoveSpeed());
						tempLaser.setEndX(tempLaser.getEndX() + tempLaser.getXMoveSpeed());
						tempLaser.setEndY(tempLaser.getEndY() + tempLaser.getYMoveSpeed());

						//remove far-offscreen lasers
						if (tempLaser.getX() < -200 || tempLaser.getX() > 1700 || tempLaser.getY() < -200 || tempLaser.getY() > 1000) {
							lasers.remove(i);
						}

						//laser hits spiderBoss
						if (tempLaser.getActive() && (new Rectangle((int)spiderBossX + 140, (int)spiderBossY + 160, 120, 80)
							.contains(tempLaser.getX(), tempLaser.getY()) ||
								new Rectangle((int)spiderBossX + 140, (int)spiderBossY + 160, 120, 80)
									.contains(tempLaser.getEndX(), tempLaser.getEndY()))) {

							spiderBossRedFramesCount = spiderBossRedFramesValue;
							tempLaser.deactivate();
							spiderBossHealth--;
						}

						//if laser hits spiderMinion
						for (int j = enemies.size()-1; j >= 0; j --) {
							if (tempLaser.getActive() && (new Rectangle(enemies.get(j).getX(), enemies.get(j).getY(), spiderMinion[enemies.get(j).getGlobalPos()].getWidth(), spiderMinion[enemies.get(j).getGlobalPos()].getHeight())
								.contains(tempLaser.getEndX(), tempLaser.getEndY()) ||
									new Rectangle(enemies.get(j).getX(), enemies.get(j).getY(), spiderMinion[enemies.get(j).getGlobalPos()].getWidth(), spiderMinion[enemies.get(j).getGlobalPos()].getHeight())
										.contains(tempLaser.getEndX(), tempLaser.getEndY()))) {

								enemies.get(j).setRedFramesCount(spiderMinionsRedFramesValue);
								tempLaser.deactivate();
								enemies.get(j).setHealth(enemies.get(j).getHealth()-1);
								if (enemies.get(j).getHealth() == 0) {
									explosions.add(new Explosion(enemies.get(j).getX(), enemies.get(j).getY()));
									enemies.remove(j);
									score += 200;
									if (!mute) {
										try {
											playSound("explosion.wav");
										} catch (MalformedURLException ex) {
										} catch (LineUnavailableException ex) {
										} catch (UnsupportedAudioFileException ex) {
										} catch (IOException ex) {
										}
									}
								}
							}
						}
					}

					//spider animation
					spiderBossAnimationCountTimer--;                                          //decrease the 20-frame timer on boss animation ticks
					if (spiderBossAnimationCountTimer == 0) {                                 //if timer runs out
						spiderBossAnimationCountTimer = spiderBossAnimationCountTimerValue;   //reset the timer
						spiderBossAnimationCount += spiderBossAnimationCountChange;   		  //change the sprite
						if (spiderBossAnimationCount == -1) {								  //reset the sprite if it goes over limit, both up and down
							spiderBossAnimationCountChange *= -1;
							spiderBossAnimationCount = 1;
						}
						if (spiderBossAnimationCount == 3) {
							spiderBossAnimationCountChange *= -1;
							spiderBossAnimationCount = 1;
						}
					}

					//spawn spiderminions
					if (enemies.size() == 0) {
						spawnSpiderMinions = true;
					}
					if (spawnSpiderMinions) {
						if (spawnTimerCount == 0) {
							spawnTimerCount = spawnTimerValue;
							int tempXMoveSpeed = 2;
							if (x < spiderBossX+168) {
								tempXMoveSpeed *= -1;
							}
							enemies.add(new Enemy((int)spiderBossX+168, (int)spiderBossY+370, tempXMoveSpeed, 3, spiderMinion[2], 2, 2, 0, 1, true));
							spawnCounter++;
							if (spawnCounter == 3) {
								spawnTimerCount = spawnWaveTimerValue;
								spawnCounter = 0;
								spawnSpiderMinions = false;
							}
						}
						else {
							spawnTimerCount--;
						}
					}

					//move spiderMinions
					for (int i = enemies.size()-1; i >= 0; i --) {
						Enemy tempEnemy = enemies.get(i);
						if (tempEnemy.getY() < 532) {                                                 //move downwards until it hits the ground
							tempEnemy.setY(tempEnemy.getY() + tempEnemy.getYMoveSpeed());
						}
						if (tempEnemy.getY() > 450 && tempEnemy.getY() < 532) {						  //switch sprite as falling
							tempEnemy.setImage(spiderMinion[1]);
							tempEnemy.setGlobalPos(1);
						}
						if (tempEnemy.getY() >= 532) {							//start moving horizontally once on ground and change sprite - basically all regular functions
							tempEnemy.setY(532);
							tempEnemy.setX(tempEnemy.getX() + tempEnemy.getXMoveSpeed());

							//spiderMinion animation while moving forward
							tempEnemy.setAnimationFrames(tempEnemy.getAnimationFrames()-1);
							if (tempEnemy.getAnimationFrames() == 0) {
								tempEnemy.setAnimationFrames(spiderMinionAnimationCountValue);
								if (tempEnemy.getGlobalPos() == 0) {                                  //using globalPosition to keep track of which sprite (0 or 1) its at
									tempEnemy.setGlobalPos(1);										  //because i'm lazy and all enemies keep track of their global position
									tempEnemy.setImage(spiderMinion[tempEnemy.getGlobalPos()]);       //but spiderMinion's global position doesn't ever change and is never
								}																	  //used so why not
								else if (tempEnemy.getGlobalPos() == 1) {
									tempEnemy.setGlobalPos(0);
									tempEnemy.setImage(spiderMinion[tempEnemy.getGlobalPos()]);
								}
							}
						}

						//spiderMinion redFrames stuff
						if (tempEnemy.getRedFramesCount() > 0) {
							tempEnemy.setImage(spiderMinionRed[tempEnemy.getGlobalPos()]);
							tempEnemy.decreaseRedFramesCount();
						}
						else if (tempEnemy.getRedFramesCount() == 0) {
							tempEnemy.setImage(spiderMinion[tempEnemy.getGlobalPos()]);
						}

						//hits player
						if (tempEnemy.getActive() && new Rectangle(x, y, car[imageNum].getWidth(), car[imageNum].getHeight()).intersects(new Rectangle(tempEnemy.getX(), tempEnemy.getY(), tempEnemy.getImage().getWidth(), tempEnemy.getImage().getHeight()))) {
							tempEnemy.deactivate();
							health--;
							if (!mute) {
								try {
									playSound("explosionGetHit.wav");
								} catch (MalformedURLException ex) {
								} catch (LineUnavailableException ex) {
								} catch (UnsupportedAudioFileException ex) {
								} catch (IOException ex) {
								}
							}
						}

						//remove offscreen spiders immediately - no point in keeping them longer since the camara doesnt move
						if (tempEnemy.getX() < -200 || tempEnemy.getX() > 1700 || tempEnemy.getY() < -200 || tempEnemy.getY() > 1000) {
							enemies.remove(i);
						}
					}

					//spiderBoss death animation
					if ((spiderBossHealth <= 0 || spiderLostAllHealth) && spiderTimeToDie > 0) {
						enemies.clear();
						if (spiderBossY < 300) {
							spiderBossY += 0.6;
						}

						spiderLostAllHealth = true;
						victory = true;
						for (int i = 0; i < 3; i ++) {
							explosions.add(new Explosion((int)spiderBossX + (int)(Math.random()*398) - 40, (int)spiderBossY + (int)(Math.random()*446) - 0));
						}
						if (spiderTimeToDie % 20 == 0) {
							if (!mute) {
								try {
									playSound("explosion.wav");
								} catch (MalformedURLException ex) {
								} catch (LineUnavailableException ex) {
								} catch (UnsupportedAudioFileException ex) {
								} catch (IOException ex) {
								}
							}
						}

						spiderTimeToDie--;

						if (spiderTimeToDie == 10) {
							gameOn = false;
						}
					}


				} //end if bossBattle



				if (regularGame) {
					tempMoveSpeed = 0;
					if (!bossBattleTransition && !bossBattleTransition2 && !bossBattle) {     //move normally if not transitioning to bossBattle
						if (right) {
							if (background1X <= -1499) {
								background1X = background2X+1499;   //1499
							}
							if (background2X <= -1499) {
								background2X = background1X+1499;
							}
							tempMoveSpeed = -1 * moveSpeed;
						}
						if (left) {
							if (background1X >= 1499) {            //1499
								background1X = background2X-1499;  //1499
							}
							if (background2X >= 1499) {
								background2X = background1X-1499;
							}
							tempMoveSpeed = moveSpeed;
						}
						if (left || right) {
							background1X += tempMoveSpeed;
							background2X += tempMoveSpeed;
							imageNum ++;
							if (imageNum == 4) {
								imageNum = 0;
							}
						}
					}

					//move global position
					globalX += (-1*tempMoveSpeed);

					//ammo edits
					if (ammo > 100) {
						ammo = 100;
					}

					//move enemies
					if (!bossBattle) {
						for (int i = 0; i < enemies.size(); i ++) {
							Enemy tempEnemy = enemies.get(i);
							tempEnemy.setX(tempEnemy.getX() + tempMoveSpeed + tempEnemy.getXMoveSpeed());
							tempEnemy.setY(tempEnemy.getY() + tempEnemy.getYMoveSpeed());

							//turn up-and-down enemies around
							if (tempEnemy instanceof UpAndDownEnemy) {
								UpAndDownEnemy tempUpDown = (UpAndDownEnemy)tempEnemy;
								if (tempUpDown.getY() >= tempUpDown.getEndY()) {
									tempUpDown.reverseY();
								}
								if (tempUpDown.getY() <= tempUpDown.getStartY()) {
									tempUpDown.reverseY();
								}
							}

							//turn left-and-right enemies around
							if (tempEnemy instanceof LeftAndRightEnemy) {
								LeftAndRightEnemy tempLeftRight = (LeftAndRightEnemy)tempEnemy;
								tempLeftRight.setStartX(tempLeftRight.getStartX() + tempMoveSpeed);
								tempLeftRight.setEndX(tempLeftRight.getEndX() + tempMoveSpeed);
								if (tempLeftRight.getX() >= tempLeftRight.getEndX()) {
									tempLeftRight.reverseX();
								}
								if (tempLeftRight.getX() <= tempLeftRight.getStartX()) {
									tempLeftRight.reverseX();
								}
							}

							//remove really far offscreen enemies
							if (!bossBattleTransition && !bossBattleTransition2) {
								if (tempEnemy.getX() < -3000 || tempEnemy.getX() > 4000 || tempEnemy.getY() < -500 || tempEnemy.getY() > 1500) {
									enemies.remove(i);
								}
							}

							if (bossBattleTransition) {   //remove enemies that are offscreen ONLY if transitioning to bossbattle
								if (tempEnemy.getX() < -200 || tempEnemy.getX() > 1700 || tempEnemy.getY() < -200 || tempEnemy.getY() > 1000) {
									enemies.remove(i);
								}
							}
						}
					}

					//move lasers
					if (!bossBattle) {
						//move lasers
						for (int i = lasers.size()-1; i >= 0; i --) {
							Laser tempLaser = lasers.get(i);
							tempLaser.setX(tempLaser.getX() + tempLaser.getXMoveSpeed() + tempMoveSpeed);
							tempLaser.setY(tempLaser.getY() + tempLaser.getYMoveSpeed());
							tempLaser.setEndX(tempLaser.getEndX() + tempLaser.getXMoveSpeed() + tempMoveSpeed);
							tempLaser.setEndY(tempLaser.getEndY() + tempLaser.getYMoveSpeed());

							//remove far-offscreen lasers
							if (tempLaser.getX() < -200 || tempLaser.getX() > 1700 || tempLaser.getY() < -200 || tempLaser.getY() > 1000) {
								if (tempLaser.getKillCount() >= 3) {
									score += (tempLaser.getKillCount()-1)*100;
									ammo += (tempLaser.getKillCount()-2);
								}
								lasers.remove(i);
							}

							//kill enemies
							for (int j = enemies.size()-1; j >= 0; j--) {   //checks if any lasers are intersecting any enemies
								if (new Rectangle(enemies.get(j).getX(), enemies.get(j).getY(), enemies.get(j).getImage().getWidth(), enemies.get(j).getImage().getHeight())
									.contains(tempLaser.getX(), tempLaser.getY()) ||
										new Rectangle(enemies.get(j).getX(), enemies.get(j).getY(), enemies.get(j).getImage().getWidth(), enemies.get(j).getImage().getHeight())
											.contains(tempLaser.getEndX(), tempLaser.getEndY())) {    //if a laser intersects an enemy

									//create explosion + sound effect
									explosions.add(new Explosion(enemies.get(j).getX(), enemies.get(j).getY()));
									if (!mute) {
										try {
											playSound("explosion.wav");
										} catch (MalformedURLException ex) {
										} catch (LineUnavailableException ex) {
										} catch (UnsupportedAudioFileException ex) {
										} catch (IOException ex) {
										}
									}

									enemies.remove(j);
									tempLaser.increaseKillCount();
									score += 100;
								}
							}

							//kill enemy lasers
							Point2D.Double tempStart = new Point2D.Double(tempLaser.getX(), tempLaser.getY());
							Point2D.Double tempEnd = new Point2D.Double(tempLaser.getEndX(), tempLaser.getEndY());
							Line2D.Double laserLine = new Line2D.Double(tempStart, tempEnd);
							Rectangle2D laserBounds = laserLine.getBounds2D();

							for (int j = enemyLasers.size()-1; j>=0; j--) {
								Point2D.Double enemyTempStart = new Point2D.Double(enemyLasers.get(j).getX(), enemyLasers.get(j).getY());
								Point2D.Double enemyTempEnd = new Point2D.Double(enemyLasers.get(j).getEndX(), enemyLasers.get(j).getEndY());
								Line2D.Double enemyLaserLine = new Line2D.Double(enemyTempStart, enemyTempEnd);

								if (laserBounds.intersectsLine(enemyLaserLine)) {
									enemyLasers.remove(j);
									score += 200;
								}
							}
						}
					}

					//move enemy lasers
					for (int i = enemyLasers.size()-1; i >= 0; i --) {
						Laser enemyTempLaser = enemyLasers.get(i);
						enemyTempLaser.setX(enemyTempLaser.getX() + enemyTempLaser.getXMoveSpeed() + tempMoveSpeed);
						enemyTempLaser.setY(enemyTempLaser.getY() + enemyTempLaser.getYMoveSpeed());
						enemyTempLaser.setEndX(enemyTempLaser.getEndX() + enemyTempLaser.getXMoveSpeed() + tempMoveSpeed);
						enemyTempLaser.setEndY(enemyTempLaser.getEndY() + enemyTempLaser.getYMoveSpeed());

						//remove far-offscreen enemy lasers
						if (enemyTempLaser.getX() < -200 || enemyTempLaser.getX() > 1700 || enemyTempLaser.getY() < -200 || enemyTempLaser.getY() > 1000) {
							enemyLasers.remove(i);
						}

						//if enemy laser hits car
						if (enemyTempLaser.getActive()) {
							Point2D.Double enemyTempStart = new Point2D.Double(enemyTempLaser.getX(), enemyTempLaser.getY());
							Point2D.Double enemyTempEnd = new Point2D.Double(enemyTempLaser.getEndX(), enemyTempLaser.getEndY());
							Line2D.Double enemyLaserLine = new Line2D.Double(enemyTempStart, enemyTempEnd);
							if (new Rectangle(x, y, 256, 80).intersectsLine(enemyLaserLine)) {
								health--;
								enemyTempLaser.deactivate();
								enemyLasers.remove(i);
								if (!mute) {
									try {
										playSound("explosionGetHit.wav");
									} catch (MalformedURLException ex) {
									} catch (LineUnavailableException ex) {
									} catch (UnsupportedAudioFileException ex) {
									} catch (IOException ex) {
									}
								}
							}
						}
					}

					//increment and move explosions
					for (int i = explosions.size()-1; i >= 0; i --) {
						Explosion tempExplosion = explosions.get(i);
						tempExplosion.setX(tempExplosion.getX() + tempMoveSpeed);

						tempExplosion.increment();
						if (tempExplosion.getCount() == 16) {
							explosions.remove(i);
						}
					}

					//spawn enemies
					if (!bossBattle && !bossBattleTransition && !bossBattleTransition2) { //spawn enemies in normal play, not when transitioning or the actual bossBattle
						//spawn enemies
						int rand = (int)(Math.random()*100)+1;
						if (rand <= 3) { //if spawn an enemy
							int randSide = (int)(Math.random()*2)+1; //1 = left, 2 = right
							int randColor = (int)(Math.random()*2);
							if (randSide == 1) {
								enemies.add(new Enemy(-100, (int)(Math.random()*300), (int)(Math.random()*4)+1, 0, flip(flyingGuys[randColor]), 0, 1, 0, 0, true));
							}
							else if (randSide == 2) {
								enemies.add(new Enemy(1500, (int)(Math.random()*300), -1 * ((int)(Math.random()*4)+1), 0, flyingGuys[randColor], 0, 1, 0, 0, true));
							}
							//each enemy class has a bunch of variables that do absolutely nothing because I needed extra variables for the spiderMinions but I didnt make an actual subclass for the spiderminions
							//cause im an idiot so yea sorry
						}
					}

					//enemies shoot lasers
					if (!bossBattle) {
						int randomChanceShoot = (int)(Math.random()*100)+1;
						if (randomChanceShoot <= 1) {
							//construct an arraylist of enemies that are allowed to shoot at you
							ArrayList<Enemy> visibleEnemies = new ArrayList<Enemy>();
							for (int i = 0; i < enemies.size(); i ++) {
								if (enemies.get(i).getX() > 0 && enemies.get(i).getX() < 1500) {
									visibleEnemies.add(enemies.get(i));
								}
							}
							if (visibleEnemies.size() > 0) {

								//create, angle, and shoot the laser
								Enemy shooterEnemy = visibleEnemies.get((int)(Math.random()*visibleEnemies.size()));
								double mouseX = 403;   //car: 403, 496    OR    450, 496
								double mouseY = 496;

								double spawnPosX = shooterEnemy.getX();
								double spawnPosY = shooterEnemy.getY();
								if (lastDirection.equals("left")) {
									mouseX = 450;
								}

								double angle = Math.toDegrees(Math.atan((spawnPosY - mouseY)/(mouseX-spawnPosX)));
								System.out.println("Initial Angle: " + angle);
								if (angle < 0) {
									angle += 180;
								}
								if (angle == -0.0) {
									angle += 180;
								}
								if (mouseY > 496) {
									angle += 180;
								}
								angle += 180;
								System.out.println("Final angle: " + angle);

								enemyLasers.add(new Laser((int)spawnPosX, (int)spawnPosY, angle, 5, false));
							}
						}
					}

					//spawn powerups
					if (!bossBattleTransition && !bossBattleTransition2 && !bossBattle) { //spawn powerups in normal play, not when transitioning to boss battle
						//spawn powerups
						if (health >= 27 || powerups.size() > 15) {
							healthChance = healthChanceFINAL * 2;
						}
						else if (health < 27 && health > 6) {
							healthChance = healthChanceFINAL;
						}
						else {
							healthChance = healthChanceFINAL / 2;
						}
						if (ammo >= 100 || powerups.size() > 15) {
							ammoChance = ammoChanceFINAL * 2;
						}
						else if (ammo < 100 && ammo > 20) {
							ammoChance = ammoChanceFINAL;
						}
						else {
							ammoChance = ammoChanceFINAL / 2;
						}


						int randHealth = (int)(Math.random()*healthChance)+1;
						if (randHealth == 5) {
							powerups.add(new Powerup((int)(Math.random()*1500), -50, "health"));
						}

						int randAmmo = (int)(Math.random()*ammoChance)+1;
						if (randAmmo == 5) {
							powerups.add(new Powerup((int)(Math.random()*1500), -50, "ammo"));
						}
					}

					//move powerups
					for (int i = powerups.size()-1; i >= 0; i --) {
						powerups.get(i).setX(powerups.get(i).getX() + tempMoveSpeed);
						if (powerups.get(i).getY() < 550) {
							powerups.get(i).setY(powerups.get(i).getY() + 2);
						}

						if (new Rectangle(x, y, 256, 80).intersects(new Rectangle(powerups.get(i).getX(), powerups.get(i).getY(), 30, 30))) {
							if (!mute) {
								try {
									playSound("pickupSound.wav");
								} catch (MalformedURLException ex) {
								} catch (LineUnavailableException ex) {
								} catch (UnsupportedAudioFileException ex) {
								} catch (IOException ex) {
								}
							}
							activatePowerup(powerups.get(i));
							powerups.remove(i);
						}

					}
					for (int i = powerups.size()-1; i >= 0; i --) {		//remove off-screen powerups ONLY IF transitioning to boss battle
						if (bossBattleTransition) {
							if (powerups.size() > 0) {
								if (powerups.get(i).getX() < 0 || powerups.get(i).getX() > 1500) {
									powerups.remove(i);
								}
							}
						}
						else {											//remove really far off-screen powerups normally
							if (powerups.size() > 0) {
								if (powerups.get(i).getX() < -1000 || powerups.get(i).getX() > 2500) {
									powerups.remove(i);
								}
							}
						}
					}

					//death animation for playerCar
					if ((health <= 0 || lostAllHealth) && timeToDie > 0) {
						lostAllHealth = true;
						explosions.add(new Explosion(x + (int)(Math.random()*256) - 40, y + (int)(Math.random()*80) - 50));
						if (timeToDie % 20 == 0) {
							if (!mute) {
								try {
									playSound("explosion.wav");
								} catch (MalformedURLException ex) {
								} catch (LineUnavailableException ex) {
								} catch (UnsupportedAudioFileException ex) {
								} catch (IOException ex) {
								}
							}
						}

						timeToDie--;
					}
					if (timeToDie == 10) {
						gameOn = false;
					}

				} // end if regularGame

			} //end ifGameON
			if(restart) {
				restart=false;
				gameOn=true;
			}

			try {
				t.sleep(9);    //usually 10
			} catch(InterruptedException e) {

			}
			repaint();
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting happens here!

		//background
		g2d.drawImage(image1, background2X+750, 0, null);
		g2d.drawImage(image1, background1X+750, 0, null);
		g2d.drawImage(image1,background1X,0,null);
		g2d.drawImage(image2,background2X,0,null);

		if (title) {
			g2d.drawImage(resize(titleFont, 1430, 45), 27, 130, null);
			g2d.drawImage(resize(enterToStart, 500, 20), 470, 680, null);
		}

		//tutorial
		if ((tutorial || showTutorial) && !title) {
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 50));
			g2d.setColor(Color.BLACK);
			g2d.drawString("Tutorial: ", 870, 130);
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 14));
			g2d.drawString("Shoot laser          -          Left Mouse", 830, 180);
			g2d.drawString("Move left              -          A", 830, 200);
			g2d.drawString("Move right            -          D", 830, 220);
			g2d.drawString("Increase speed    -          W", 830, 240);
			g2d.drawString("Decrease speed  -          S", 830, 260);
			g2d.drawString("Show dev info       -          I", 830, 280);
			g2d.drawString("Mute                     -         M", 830, 300);
			g2d.drawString("Toggle tutorial     -          T", 830, 320);
			g2d.drawString("Reset game         -         R", 830, 340);

			g2d.drawString("Health           =     ", 830, 380);
			g2d.drawString("Ammo            =       ", 830, 420);
			g2d.drawImage(healthPickup, 980, 358, null);
			g2d.drawImage(ammoPickup, 980, 392, null);

			g2d.drawString("Shoot enemies and lasers for points", 830, 460);
			g2d.drawString("Get multi-kills for extra points and ammo", 830, 480);

			g2d.drawString("Kill the evil Robot Spider!", 830, 520);
		}
		if (tutorial && !title) {
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 20));
			g2d.drawString("Press Enter to exit tutorial", 830, 550);
		}

		//info
		if (showInfo && !title) {
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 12));
			g2d.drawString("SpiderX: " + spiderBossX, 50, 100);
			g2d.drawString("SpiderY: " + spiderBossY, 50, 125);
			g2d.drawString("Boss Battle: " + bossBattle, 50, 150);
			g2d.drawString("Transition: " + bossBattleTransition, 50, 175);
			g2d.drawString("Transition2: " + bossBattleTransition2, 50, 200);
			g2d.drawString("Regular Game: " + regularGame, 50, 225);
			g2d.drawString("X: " + x, 50, 250);
			g2d.drawString("Y: " + y, 50, 275);
			g2d.drawString("Background1X: " + background1X, 50, 300);
			g2d.drawString("Background2X: " + background2X, 50, 325);
			g2d.drawString("MoveSpeed: " + moveSpeed, 50, 350);
			g2d.drawString("TempMoveSpeed: " + tempMoveSpeed, 50, 375);
			g2d.drawString("Global: " + globalX, 50, 400);
			g2d.drawString("Enemy Count: " + enemies.size(), 50, 425);
			g2d.drawString("Score: " + score, 50, 450);
			g2d.drawString("Ammo: " + ammo, 50, 475);
		}

		//car
		if (lastDirection.equals("right")) {
			g2d.drawImage(car[imageNum], x, y, null);
		}
		else if (lastDirection.equals("left")) {
			g2d.drawImage(reverseCar[imageNum], x, y, null);
		}

		//enemies
		for (int i = 0; i < enemies.size(); i ++) {
			g2d.drawImage(enemies.get(i).getImage(), enemies.get(i).getX(), enemies.get(i).getY(), null);
		}

		//lasers
		for (int i = lasers.size()-1; i >= 0; i --) {
			AffineTransform backup = g2d.getTransform();
			AffineTransform trans = new AffineTransform();
			trans.rotate( Math.toRadians(360-lasers.get(i).getAngle()), lasers.get(i).getX(), lasers.get(i).getY() ); //the points to rotate around
			g2d.transform( trans );
			g2d.drawImage( laser, (int)lasers.get(i).getX(), (int)lasers.get(i).getY(), null );  // the actual location of the sprite
			g2d.setTransform( backup ); // restore previous transform

			//killstreak
			if (lasers.get(i).getKillCount() >= 3) {
				g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 70));
				g2d.setColor(Color.YELLOW);
				g2d.drawString("" + lasers.get(i).getKillCount() + "x!", 48, 190);
			}
		}

		//enemy lasers
		for (int i = 0; i < enemyLasers.size(); i ++) {
			AffineTransform backup = g2d.getTransform();
			AffineTransform trans = new AffineTransform();
			trans.rotate( Math.toRadians(360-enemyLasers.get(i).getAngle()), enemyLasers.get(i).getX(), enemyLasers.get(i).getY() ); //the points to rotate around
			g2d.transform( trans );
			g2d.drawImage( enemyLaser, (int)enemyLasers.get(i).getX(), (int)enemyLasers.get(i).getY(), null );  // the actual location of the sprite
			g2d.setTransform( backup ); // restore previous transform
		}

		//healthbar
		if (!title) {
			int tempPos = 51;
			for (int i = 0; i < health; i ++) {
				g2d.setColor(Color.RED);
				g2d.fillRect(tempPos, 50, 50, 3);
				tempPos += 52;
			}
		}

		//ammo count
		if (!title) {
			g2d.drawImage(resize(ammoPickup, 40, 52), 51, 70, null);
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 18));
			g2d.setColor(Color.BLACK);
			g2d.drawString("" + ammo, 100, 104);
		}

		//score
		if (!title) {
			g2d.drawString("Score: " + score, 190, 104);
		}

		//powerups
		for (int i = 0; i < powerups.size(); i ++) {
			if (powerups.get(i).getEffect().equals("health")) {
				g2d.drawImage(healthPickup, powerups.get(i).getX(), powerups.get(i).getY(), null);
			}
			else if (powerups.get(i).getEffect().equals("ammo")) {
				g2d.drawImage(ammoPickup, powerups.get(i).getX(), powerups.get(i).getY(), null);
			}
		}

		//"minimap"
		if (!bossBattle && !bossBattleTransition2 && !title && !(!title && !tutorial && !regularGame)) {
			g2d.setColor(Color.BLACK);
			g2d.drawLine(400, 720, 400, 740);
			g2d.drawLine(1100, 720, 1100, 740);
			g2d.drawLine(400, 730, 1100, 730);
			g2d.drawImage(resize(spiderBoss[3], 50, 50), 1076, 675, null);
			if (lastDirection.equals("right")) {
				g2d.drawImage(resize(car[0], 64, 20), (int)(((698 * globalX)/spiderPlace)+374), 700, null);
			}
			else if (lastDirection.equals("left")) {
				g2d.drawImage(resize(reverseCar[0], 64, 20), (int)(((698 * globalX)/spiderPlace)+374), 700, null);
			}
		}


		//bossBattle stuff
		if (bossBattle || bossBattleTransition2) {
			//spider boss
			if (spiderBossRedFramesCount == 0) {
				g2d.drawImage(spiderBoss[spiderBossAnimationCount], (int)spiderBossX, (int)spiderBossY, null);     //draw the spiderBoss layer underneath
				int stringYPos = (int)spiderBossY-170;                                                        //draw the string in between the two spiderBoss layers
				g2d.drawImage(spiderString, (int)spiderBossX+139, stringYPos, null);
				while (stringYPos > 0) { 																 //keep drawing strings until reach top of screen
					stringYPos -= spiderString.getHeight();
					g2d.drawImage(spiderString, (int)spiderBossX+139, stringYPos, null);
				}
				g2d.drawImage(spiderBossOver[spiderBossAnimationCount], (int)spiderBossX, (int)spiderBossY, null); //draw the spiderBoss layer on top of String
			}
			if (spiderBossRedFramesCount > 0) {
				g2d.drawImage(spiderBossRed[spiderBossAnimationCount], (int)spiderBossX, (int)spiderBossY, null);
				int stringYPos = (int)spiderBossY-170;                                                        //keep drawing strings until reaches top of screen
				g2d.drawImage(spiderString, (int)spiderBossX+139, stringYPos, null);
				while (stringYPos > 0) {
					stringYPos -= spiderString.getHeight();
					g2d.drawImage(spiderString, (int)spiderBossX+139, stringYPos, null);
				}
				g2d.drawImage(spiderBossRedOver[spiderBossAnimationCount], (int)spiderBossX, (int)spiderBossY, null);
				spiderBossRedFramesCount--;
			}

			//spider boss health
			if (!bossBattleTransition2) { //dont draw total health bar right away when bossBattleTransition2 starts, want it to be a bit cooler
				int tempPos = 51;
				for (int i = 0; i < spiderBossHealth; i ++) {
					g2d.setColor(Color.BLUE);
					g2d.fillRect(tempPos, 700, (1330/spiderBossMaxHealth), 3);
					tempPos += (1330/spiderBossMaxHealth)+1;
				}
			}
		}

		//explosions
			for (int i = 0; i < explosions.size(); i ++) {
				g2d.drawImage(enemyExplosion[explosions.get(i).getCount()], explosions.get(i).getX(), explosions.get(i).getY(), null);
		}

		//paused, game over, and victory screen
		g2d.setColor(Color.BLACK);
		if (paused && gameOn) {
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 50));
			g2d.drawString("Move mouse onto screen to unpause!", 370, 300);
		}
		if (!gameOn && !victory) {
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 70));
			g2d.setColor(Color.RED);
			g2d.drawString("You Died!", 600, 350);
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 30));
			g2d.drawString("Score: " + score, 650, 450);
			g2d.drawString("Press R to Restart", 637, 490);
		}
		if (!gameOn && victory) {
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 70));
			g2d.setColor(new Color(24, 142, 97));
			g2d.drawString("You Won!", 600, 350);
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 30));
			g2d.drawString("Score: " + score, 650, 450);
			g2d.drawString("Press R to Restart", 637, 490);
		}
	}
	public void keyPressed(KeyEvent key)
	{
		System.out.println(key.getKeyCode());
		if (key.getKeyCode() == 82) { //r
			enemies.clear();
			powerups.clear();
			explosions.clear();
			lasers.clear();
			enemyLasers.clear();

			if (bossMusicClip != null) {
				bossMusicClip.stop();
			}
			bossMusicClip = null;
			carMusicClip.start();
			carMusicClip.loop(Clip.LOOP_CONTINUOUSLY);

			globalX = 0;
			spiderPlace = 20000;
			x = 600;
			y = 503;
			imageNum = 0;
			moveSpeed = 2;
			tempMoveSpeed = moveSpeed;
			lastDirection = "right";
			paused = false;
			victory = false;
			imgCount=0;
			health = 10;
			score = 0;
			ammo = 30;
			healthChance = healthChanceFINAL;
			ammoChance = ammoChanceFINAL;
			showInfo = false;
			showTutorial = false;
			mute = false;
			bossBattle = false;
			bossBattleTransition = false;
			bossBattleTransition2 = false;
			regularGame = false;
			tutorial = false;
			title = true;
			spiderBossX = 1000;
			spiderBossY = -600;
			spiderBossTempY = spiderBossY;
			spiderBossAnimationCountTimer = spiderBossAnimationCountTimerValue;
			spiderBossAnimationCount = 2;
			spiderBossAnimationCountChange = -1;
			spiderBossRedFramesCount = 0;
			spiderBossHealth = spiderBossMaxHealth;
			spawnWaveTimerValue = 300;
			spawnTimerValue = 46;
			spawnTimerCount = spawnWaveTimerValue;
			spawnCounter = 0;
			spiderMinionAnimationCount = 1;
			spawnSpiderMinions = true;
			timeToDie = 200;
			lostAllHealth = false;
			spiderTimeToDie = 400;
			spiderLostAllHealth = false;
			background1X = 0;
			background2X = 1499;
			gameOn=true;
		}

		if (key.getKeyCode() == 10) {    //enter
			if (title) {
				title = false;
			}
			else if (tutorial) {
				tutorial = false;
				regularGame = true;
			}
		}
		if (key.getKeyCode() == 84) { //t
			showTutorial = !showTutorial;
		}
		if (key.getKeyCode() == 79) { //o
			health = 27;
			ammo = 100;
		}
		if (key.getKeyCode() == 76) { //l
			health = 1;
		}
		if(key.getKeyCode()==68) {    //d
			right = true;
			lastDirection = "right";
		}
		if(key.getKeyCode()==82) {
			restart=true;
		}
		if (key.getKeyCode() == 87) { //w
			moveSpeed += 5;
			if (moveSpeed > 12) {
				moveSpeed = 12;
			}
		}
		if (key.getKeyCode() == 83) { //s
			if (moveSpeed - 5 > 0) {
				moveSpeed -= 5;
			}
		}
		if (key.getKeyCode() == 65) { //a
			if (!tutorial) {
				left = true;
				lastDirection = "left";
			}
		}
		if (key.getKeyCode() == 73) { //i
			showInfo = !showInfo;
		}
		if (key.getKeyCode() == 77) { //m
			mute = !mute;
			if (!bossBattleTransition && !bossBattleTransition2 && !bossBattle) {
				if (carMusicClip.isActive()) {
					carMusicClipLocationPaused = carMusicClip.getFramePosition();
					carMusicClip.stop();
				}
				else if (!carMusicClip.isActive()) {
					carMusicClip.start();
					carMusicClip.setFramePosition(carMusicClipLocationPaused);
					carMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
			if (bossBattleTransition2 || bossBattle) {
				if (bossMusicClip.isActive()) {
					bossMusicClipLocationPaused = bossMusicClip.getFramePosition();
					bossMusicClip.stop();
				}
				else if (!bossMusicClip.isActive()) {
					bossMusicClip.start();
					bossMusicClip.setFramePosition(bossMusicClipLocationPaused);
					bossMusicClip.setLoopPoints(2913190, 4699190);
					bossMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		}
	}
	public void keyReleased(KeyEvent key)
	{
		if (key.getKeyCode() == 68) {
			right = false;
		}
		if (key.getKeyCode() == 65) {
			left = false;
		}
	}
	public void keyTyped(KeyEvent key) {
	}

	public void mouseExited(MouseEvent e) {
		if (!title) {
			paused = true;
		}
	}
	public void mouseEntered(MouseEvent e) {
		if (!title) {
			paused = false;
		}
	}
	public void mousePressed(MouseEvent e) {  //x = 300, y = 503
		if (ammo > 0 && !bossBattleTransition2 && !tutorial) {
			double mouseX = (double)e.getX()-9;   //car: 403, 496    OR    450, 496
			double mouseY = (double)e.getY()-35;
			System.out.println("X: " + mouseX + ", Y: " + mouseY);

			double spawnPosX = (double)(x + 103);
			double spawnPosY = (double)(y-7);
			if (lastDirection.equals("left")) {
				spawnPosX = x + 150;
			}

			double angle = Math.toDegrees(Math.atan((spawnPosY - mouseY)/(mouseX-spawnPosX)));
			System.out.println("Initial Angle: " + angle);
			if (angle < 0) {
				angle += 180;
			}
			if (angle == -0.0) {
				angle += 180;
			}
			if (mouseY > 496) {
				angle += 180;
			}
			System.out.println("Final angle: " + angle);

			lasers.add(new Laser((int)spawnPosX, (int)spawnPosY, angle, 20 + (int)Math.abs(tempMoveSpeed), true));
			ammo --;

			System.out.println("" + (lasers.size()-1) + "--" + lasers.get(lasers.size()-1) + "-------------------------------------\n");

			//laser sound effect
			if (!mute) {
				try {
					playSound("laserPew.wav");
				} catch (MalformedURLException ex) {
				} catch (LineUnavailableException ex) {
				} catch (UnsupportedAudioFileException ex) {
				} catch (IOException ex) {
				}
			}
		}
		else if (ammo == 0 && !bossBattleTransition2) {
			if (!mute) {
				try {
					playSound("failSound.wav");
				} catch (MalformedURLException ex) {
				} catch (LineUnavailableException ex) {
				} catch (UnsupportedAudioFileException ex) {
				} catch (IOException ex) {
				}
			}
		}
	}
	public void mouseClicked(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {
	}

	public static void playSound(String fileName) throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException{
	    File url = new File(fileName);
	    Clip clip = AudioSystem.getClip();

	    AudioInputStream ais = AudioSystem.getAudioInputStream( url );
	    clip.open(ais);
	    clip.start();
	}
	public BufferedImage flip(BufferedImage bufferedImage) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-bufferedImage.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
		AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    	bufferedImage = op.filter(bufferedImage, null);
    	return bufferedImage;
	}
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}
	public void activatePowerup(Powerup powerup) {
		if (powerup.getEffect().equals("health")) {
			if (health < 27) {
				health ++;
			}
			else {
				score += 100;
			}
		}
		else if (powerup.getEffect().equals("ammo")) {
			if (ammo < 100) {        //increase ammo by 10, max at 100
				ammo += 10;
				if (ammo > 100) {
					ammo = 100;
				}
			}
			else {
				score += 100;
			}
		}
	}

	public static void main(String args[])
	{
		RoboCarThatShootsLasers app=new RoboCarThatShootsLasers();
	}
}