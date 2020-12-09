package examples;

import java.awt.*;

public class FlowExample {
  private Frame f;
  private Button button1;
  private Button button2;
  private Button button3;

  public FlowExample() {
    f = new Frame("Flow Layout");
    button1 = new Button("Ok");
    button2 = new Button("Open");
    button3 = new Button("Close");
  }

  public void launchFrame() {
    f.setLayout(new FlowLayout());
    f.add(button1);
    f.add(button2);
    f.add(button3);
    f.setSize(100,100);
    f.setVisible(true);
  }

  public static void main(String args[]) {
    FlowExample guiWindow = new FlowExample();
    guiWindow.launchFrame();
  }
}
