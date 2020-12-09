package examples;

import java.awt.*;

public class TestDrawingPanel {
  private Frame f;
  private DrawingPanel drawPanel;

  public TestDrawingPanel() {
    f = new Frame("Drawing Shapes");
    drawPanel = new DrawingPanel();
  }

  public void launchFrame() {
    f.add(drawPanel);
    f.pack();
    f.setSize(new Dimension(DrawingPanel.PANEL_WIDTH,
			    DrawingPanel.PANEL_HEIGHT));
    f.setVisible(true);
  }

  public static void main(String args[]) {
    TestDrawingPanel gui = new TestDrawingPanel();
    gui.launchFrame();
  }
}
