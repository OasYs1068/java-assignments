package examples;

import java.awt.*;
import java.awt.event.*;

public class CardExample implements MouseListener {
  private Panel p1, p2, p3, p4, p5;
  private Label lb1, lb2, lb3, lb4, lb5;

  // Declare a CardLayout object to call its methods.
  private CardLayout myCard;
  private Frame f;

  public CardExample() {
    f = new Frame ("Card Test");
    myCard = new CardLayout();

    // Create the panels that I want to use as cards.
    p1 = new Panel();
    p2 = new Panel();
    p3 = new Panel();
    p4 = new Panel();
    p5 = new Panel();

    // Create a label to attach to each panel
    lb1 = new Label("This is the first Panel");
    lb2 = new Label("This is the second Panel");
    lb3 = new Label("This is the third Panel");
    lb4 = new Label("This is the fourth Panel");
    lb5 = new Label("This is the fifth Panel");
  }

  public void launchFrame() {
    f.setLayout(myCard);

    // change the color of each panel, so they are
    // easily distinguishable
    p1.setBackground(Color.yellow);
    p1.add(lb1);
    p2.setBackground(Color.green);
    p2.add(lb2);
    p3.setBackground(Color.magenta);
    p3.add(lb3);
    p4.setBackground(Color.white);
    p4.add(lb4);
    p5.setBackground(Color.cyan);
    p5.add(lb5);

    // Set up the event handling here.
    p1.addMouseListener(this);
    p2.addMouseListener(this);
    p3.addMouseListener(this);
    p4.addMouseListener(this);
    p5.addMouseListener(this);

    // Add each panel to my CardLayout
    f.add(p1, "First");
    f.add(p2, "Second");
    f.add(p3, "Third");
    f.add(p4, "Fourth");
    f.add(p5, "Fifth");

    // Display the first panel.
    myCard.show(f, "First");

    f.setSize(200,200);
    f.setVisible(true);
  }

  public void mousePressed(MouseEvent e) {
    myCard.next(f);
  }

  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }

  public static void main (String args[]) {
    CardExample ct = new CardExample();
    ct.launchFrame();
  }
}
