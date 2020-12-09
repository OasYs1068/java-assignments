package examples;

import java.awt.*;
import java.awt.event.*;

public class TwoListener
     implements MouseMotionListener, 
                MouseListener {
  private Frame f;
  private TextField tf;

  public TwoListener() {
    f = new Frame("Two listeners example");
    tf = new TextField(30);
  }

  public void launchFrame() {
    Label label = new Label("Click and drag the mouse");
    // Add components to the frame
    f.add(label, BorderLayout.NORTH);
    f.add(tf, BorderLayout.SOUTH);
    // Add this object as a listener
    f.addMouseMotionListener(this);
    f.addMouseListener(this);
    // Size the frame and make it visible
    f.setSize(300, 200);
    f.setVisible(true);
  }

  // These are MouseMotionListener events
  public void mouseDragged(MouseEvent e) {
    String s =  "Mouse dragging:  X = " + e.getX()
                + " Y = " + e.getY();
    tf.setText(s);
  }

  public void mouseEntered(MouseEvent e) {
    String s = "The mouse entered";
    tf.setText(s);
  }

  public void mouseExited(MouseEvent e) {
    String s = "The mouse has left the building";
    tf.setText(s);
  }

  // Unused MouseMotionListener method.
  // All methods of a listener must be present in the
  // class even if they are not used.
  public void mouseMoved(MouseEvent e) { }

  // Unused MouseListener methods.
  public void mousePressed(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }

  public static void main(String args[]) {
    TwoListener two = new TwoListener();
    two.launchFrame();
  }
}
