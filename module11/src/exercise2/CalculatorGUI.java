package exercise2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CalculatorGUI implements ActionListener{
  // Buttons and a place to put them.
  private JButton[] key;
  private JButton keyequal, keyplus, keyminus;
  private JButton keyperiod, keymult, keydiv;
  private JPanel buttonArea;
  private Calculator calculator;

  private enum Mode{
    normal, newNumber
  }
  private Mode mode = Mode.normal;

  private String resultStr;
  private double resultDouble;
  private JLabel answer;

  public CalculatorGUI() {
    calculator = new Calculator();
    answer = new JLabel("0",Label.RIGHT);
    resultStr = new String("");
    key = new JButton[10];

    for(int i=0;i<10;i++){
      key[i] = new JButton(Integer.toString(i));
      key[i].addActionListener(this);
    }

    keyequal = new JButton("=");
    keyequal.addActionListener(this);
    keyplus = new JButton("+");
    keyplus.addActionListener(this);
    keyminus = new JButton("-");
    keyminus.addActionListener(this);
    keymult = new JButton("*");
    keymult.addActionListener(this);
    keydiv = new JButton("/");
    keydiv.addActionListener(this);
    keyperiod = new JButton(".");
    keyperiod.addActionListener(this);
    buttonArea = new JPanel();
  }

  public void initiate() {
    buttonArea.setLayout(new GridLayout(4,4));

    buttonArea.add(key[7]);
    buttonArea.add(key[8]);
    buttonArea.add(key[9]);
    buttonArea.add(keyplus);
    buttonArea.add(key[4]);
    buttonArea.add(key[5]);
    buttonArea.add(key[6]);
    buttonArea.add(keyminus);
    buttonArea.add(key[1]);
    buttonArea.add(key[2]);
    buttonArea.add(key[3]);
    buttonArea.add(keymult);
    buttonArea.add(key[0]);
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
    calcGUI.initiate();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    for(int i = 0;i<10;i++){
      if(mode==Mode.newNumber){
        resultStr = "0";
        mode = Mode.normal;
      }
      if(key[i]==e.getSource()&&answer.getText().length()<12){
        resultStr += key[i].getText();
      }
    }

    if(e.getSource() == keyperiod){
      if(!resultStr.contains(".")){
        resultStr += ".";
      }
    }

    if(e.getSource() == keydiv){
      try{
        calculator.divide(resultDouble);
      }catch(ArithmeticException e1){
        answer.setText("Error");
      }finally {
        mode = Mode.newNumber;
      }
    }

    if(e.getSource() == keyminus){
      calculator.minus(resultDouble);
      mode = Mode.newNumber;
    }
    if(e.getSource() == keyplus){
      calculator.plus(resultDouble);
      mode = Mode.newNumber;
    }
    if(e.getSource() == keymult){
      calculator.multiply(resultDouble);
      mode = Mode.newNumber;
    }
    if(e.getSource() == keyequal){
      calculator.equal(resultDouble);
      mode = Mode.newNumber;
      resultStr = Double.toString(calculator.num);
      calculator.num = 0;
    }

    resultDouble = Double.parseDouble(resultStr);
    answer.setText(Double.toString(resultDouble));
  }
}
