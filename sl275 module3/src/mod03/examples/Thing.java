package mod03.examples;

public class Thing {
  private int x;

  public Thing() {
    x = 47;
  }
  public Thing(int new_x) {
    x = new_x;
  }

  public int getX() {
    return x;
  }
  public void setX(int new_x) {
    x = new_x;
  }
}
