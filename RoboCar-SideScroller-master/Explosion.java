public class Explosion {

	private int count, x, y;
	public Explosion(int x, int y) {
		count = 0;
		this.x = x;
		this.y = y;



	}
	public int getCount() {return count;}
	public void increment() {count ++;}
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int changeTo) {x = changeTo;}
	public void setY(int changeTo) {y = changeTo;}
}