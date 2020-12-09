package exercise2;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI {
  // Buttons and a place to put them.
  private Button key0, key1, key2, key3, key4;
  private Button key5, key6, key7, key8, key9;
  private Button keyequal, keyplus, keyminus;
  private Button keyperiod, keymult, keydiv;
  private Panel buttonArea;

  // Where answer will be displayed.
  private Label answer;

  public CalculatorGUI() {
    answer = new Label("0.0",Label.RIGHT);

    key0 = new Button("0");
    key1 = new Button("1");
    key2 = new Button("2");
    key3 = new Button("3");
    key4 = new Button("4");
    key5 = new Button("5");
    key6 = new Button("6");
    key7 = new Button("7");
    key8 = new Button("8");
    key9 = new Button("9");
    keyequal = new Button("=");
    keyplus = new Button("+");
    keyminus = new Button("-");
    keymult = new Button("*");
    keydiv = new Button("/");
    keyperiod = new Button(".");
    buttonArea = new Panel();
  }

  public void go() {
    buttonArea.setLayout(new GridLayout(4,4));

    buttonArea.add(key7);
    buttonArea.add(key8);
    buttonArea.add(key9);
    buttonArea.add(keyplus);
    buttonArea.add(key4);
    buttonArea.add(key5);
    buttonArea.add(key6);
    buttonArea.add(keyminus);
    buttonArea.add(key1);
    buttonArea.add(key2);
    buttonArea.add(key3);
    buttonArea.add(keymult);
    buttonArea.add(key0);
    buttonArea.add(keyperiod);
    buttonArea.add(keyequal);
    buttonArea.add(keydiv);

    // Create a Frame
    JFrame f = new JFrame("Calculator");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(200, 200);

    f.add(answer, BorderLayout.NORTH);
    f.add(buttonArea, BorderLayout.CENTER);
    f.setVisible (true);
  }

  public static void main(String args[]) {
    CalculatorGUI calcGUI = new CalculatorGUI();
    calcGUI.go();
  }


}
