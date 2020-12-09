package mygame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import mygame.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {
	
	enum GameState {
		Running, Dead
	}
	
	GameState state = GameState.Running;
	
	private static Robot robot;
	public static Chopper chop, chop2;
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);
	private Image image, currentSprite, character, character2, character3, characterDown, characterJumped, background, chopper, chopper2, chopper3, chopper4, chopper5;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, tiledirt;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;
	private Animation anim, hanim;
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	
	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// Handle exception
		}
		
		// Setup image
		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");
		
		chopper = getImage(base, "data/chopper.png");
		chopper2 = getImage(base, "data/chopper2.png");
		chopper3 = getImage(base, "data/chopper3.png");
		chopper4 = getImage(base, "data/chopper4.png");
		chopper5 = getImage(base, "data/chopper5.png");
		
		
		background = getImage(base, "data/background.png");
		
		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");
		
		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);
		
		hanim = new Animation();
		hanim.addFrame(chopper, 100);
		hanim.addFrame(chopper2, 100);
		hanim.addFrame(chopper3, 100);
		hanim.addFrame(chopper4, 100);
		hanim.addFrame(chopper5, 100);
		hanim.addFrame(chopper4, 100);
		hanim.addFrame(chopper3, 100);
		hanim.addFrame(chopper2, 100);
		
		
		currentSprite = anim.getImage();
		
	}

	@Override
	public void start() {
		bg1 = new Background(0,0);
		bg2 = new Background(2160, 0);
		robot = new Robot();
		
		try {
			loadMap("data/map1.txt");
		} catch(IOException err) {
			err.printStackTrace();
		}
		
		chop = new Chopper(340, 360);
		chop2 = new Chopper(700, 360);
		
		Thread thread = new Thread(this);
		thread.start();
	}

	private void loadMap(String filename) throws IOException{
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while(true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}
			
			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height = lines.size();
		
		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for(int i = 0; i < width; i++){
				System.out.println(i + " is i");
				
				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}
			}
		}
		
	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void run() {
		if (state == GameState.Running) {
			while (true) {
				robot.update();
				if(robot.isJumped()){
					currentSprite = characterJumped;
				} else if (robot.isJumped() == false && robot.isDucked() == false) {
					currentSprite = anim.getImage();
				}
				
				ArrayList projectiles = robot.getProjectiles();
				for (int i = 0; i < projectiles.size(); i++){
					Projectile p = (Projectile) projectiles.get(i);
					if (p.isVisible() == true) {
						p.update();
					} else {
						projectiles.remove(i);
					}
				}
				updateTiles();
				chop.update();
				chop2.update();
				bg1.update();
				bg2.update();
				
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (robot.getCenterY() > 500) {
					state = GameState.Dead;
				}
			}
		}
	}
	
	@Override
	public void update(Graphics g) {
		if (image == null){
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		if (state == GameState.Running) {
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
			paintTiles(g);
			
			ArrayList projectiles = robot.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++){
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}
			//g.drawRect((int)robot.rect.getX(), (int)robot.rect.getY(), (int)robot.rect.getWidth(), (int)robot.rect.getHeight());
			//g.drawRect((int)robot.rect2.getX(), (int)robot.rect2.getY(), (int)robot.rect2.getWidth(), (int)robot.rect2.getHeight());
			g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
			g.drawImage(hanim.getImage(),  chop.getCenterX() - 48,  chop.getCenterY() - 48, this);
			g.drawImage(hanim.getImage(), chop2.getCenterX() - 48, chop2.getCenterY() - 48, this);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
		}
	}
	
	private void updateTiles() {
		for(int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}
	
	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (robot.isJumped() == false) {
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;
		case KeyEvent.VK_SPACE:
			robot.jump();
			break;
		case KeyEvent.VK_CONTROL:
			if (robot.isDucked() == false && robot.isJumped() == false) {
				robot.shoot();
				robot.setReadyToFire(false);
			}
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			robot.setDucked(false);
			break;
		case KeyEvent.VK_LEFT:
			robot.stop();
			break;
		case KeyEvent.VK_RIGHT:
			robot.stop();
			break;
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_CONTROL:
			robot.setReadyToFire(true);
			break;
		}
	}
	
	public static Background getBg1() {
		return bg1;
	}
	
	public static Background getBg2() {
		return bg2;
	}
	
	public static Robot getRobot() {
		return robot;
	}
	
	public void animate() {
		anim.update(10);
		hanim.update(50);
	}
}
