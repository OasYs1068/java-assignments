public class Powerup {

	private int x, y;
	private String effect;

	public Powerup(int x, int y, String effect) {
		this.x = x;
		this.y = y;
		this.effect = effect;
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public String getEffect() {return effect;}
	public void setX(int changeTo) {x = changeTo;}
	public void setY(int changeTo) {y = changeTo;}
}