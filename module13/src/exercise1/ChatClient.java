package exercise1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class ChatClient extends JFrame{
    private JFrame f;
    private JButton send,quit;
    private JPanel panel;
    private JTextArea textArea;
    private JTextField textField;
    private JComboBox<String> userChoice;
    private JLabel userLabel;
    private JMenu file,help;
    private JMenuItem exit,about;
    private JScrollPane scrollPane;
    private String[] userNames;
    private String userName;

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

    public void TextToField(){
        String input = textField.getText();
        String chatContent =
                new String(userNames[userChoice.getSelectedIndex()]+": "
                        +input+"\n");
        if (!(input.equals("")))
        {
            textArea.append(new Date().toString()+"\n");
            textArea.append(chatContent);
            textField.setText("");
        }
    }

    public void launchComponents(){
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10,10,10,10));
        JMenuBar menuBar = new JMenuBar();
        file = new JMenu("File");
        help = new JMenu("Help");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("about");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f,
                        "This chat room is set up especially for various jacks",
                        "About",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(exit);
        help.add(about);
        menuBar.add(file);
        menuBar.add(help);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(menuBar,constraints);

        constraints = new GridBagConstraints();
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

        userNames = new String[7];
        for(int i=0;i<7;i++){
            userNames[i] = "Jack-"+ (i+1);
        }

        userLabel = new JLabel("select user");
        userChoice = new JComboBox<String>(userNames);
        userChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox)e.getSource();
                userName = (String)comboBox.getSelectedItem();
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(userLabel,constraints);
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(userChoice,constraints);

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
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextToField();
            }
        });
        panel.add(send,constraints);


        quit = new JButton("Quit");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.NORTH;
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f,"You have exited chat room");
                System.exit(0);
            }
        });
        panel.add(quit,constraints);

        textField = new JTextField(50);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.weightx = 1.0;
        constraints.weighty = 0.2;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    TextToField();
                }
            }
        };
        textField.addKeyListener(keyListener);
        panel.add(textField,constraints);


    }

    public static void main(String args[]) {
        new ChatClient();
    }
}
