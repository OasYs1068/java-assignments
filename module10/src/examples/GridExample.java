package examples;

import java.awt.*;

public class GridExample {
  private Frame f;
  private Button b1, b2, b3, b4, b5, b6;

  public GridExample() {
    f = new Frame("Grid Example");
    b1 = new Button("1");
    b2 = new Button("2");
    b3 = new Button("3");
    b4 = new Button("4");
    b5 = new Button("5");
    b6 = new Button("6");
  }

  public void launchFrame() {
    f.setLayout (new GridLayout(3,2));

    f.add(b1);
    f.add(b2);
    f.add(b3);
    f.add(b4);
    f.add(b5);
    f.add(b6);

    f.pack();
    f.setVisible(true);
  }

  public static void main(String args[]) {
    GridExample grid = new GridExample();
    grid.launchFrame();
  }
}
