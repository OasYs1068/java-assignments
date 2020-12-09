package mod03.exercise1;

public class MyPoint {
  public int x;
  public int y;

  public void setX(int paraX) {
	  x = paraX;
  }
  
  public void setY(int paraY) {
	  y = paraY;
  }
  
  public int getX() {
	  return x;
  }
  
  public int getY() {
	  return y;
  }
  
  public String toString() {
    return ("[" + x + "," + y + "]");
  }
}

