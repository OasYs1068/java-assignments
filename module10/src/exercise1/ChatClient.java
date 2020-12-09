package exercise1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class ChatClient extends JFrame{
    private JFrame f;
    private JButton send,quit;
    private JPanel panel;
    private JTextArea textArea;
    private JTextField textField;
    private JScrollPane scrollPane;

    public ChatClient() {
        launchComponents();

        f = new JFrame("Chat Room");
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(panel);
        f.setMinimumSize(new Dimension(600,400));
        f.pack();
        f.setVisible(true);
    }


    public void launchComponents(){
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10,10,10,10));

        GridBagConstraints constraints = new GridBagConstraints();
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 0.8;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = 3;
        constraints.insets = new Insets(5,5,5,5);
        panel.add(scrollPane,constraints);


        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 0;
        panel.add(new JPanel(), constraints);


        send = new JButton("Send");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.gridheight = 1;
        constraints.gridwidth = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        panel.add(send,constraints);


        quit = new JButton("Quit");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.NORTH;
        panel.add(quit,constraints);

        textField = new JTextField(50);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.weightx = 1.0;
        constraints.weighty = 0.2;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(textField,constraints);


    }

    public static void main(String args[]) {
        new ChatClient();
    }
}
