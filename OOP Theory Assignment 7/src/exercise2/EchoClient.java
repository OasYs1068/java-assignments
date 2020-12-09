package exercise2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
public class EchoClient implements Runnable{
    private JFrame frame;
    private JLabel label;
    private JTextField textField;//input the number of lines in this textfield
    private JButton send;
    private JTextArea textArea;
    private Socket socket;
    private PrintStream clientOut;
    private BufferedReader clientIn;
    private boolean flag = false;//if EOF is reached, it becomes true

    public EchoClient() { }

    public void launchGUI(){
        frame = new JFrame("Product System");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        label = new JLabel("Please input the number of lines to get from server: ");
        c.gridwidth = 4;
        frame.add(label, c);

        textField = new JTextField(10);
        c.weightx = 1;
        c.gridwidth = 1;
        frame.add(textField, c);

        send = new JButton("Send to Server");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(textField.getText());
                clientOut.println(number);
                textField.setText("");
                try {
                    String tmp = clientIn.readLine();
                    if (!flag && !tmp.contains("no more sentences")) {
                        textArea.append("Got " + number + " sentence(s) from the server\n");
                    }
                    textArea.append(tmp + "\n");
                    if (tmp.contains("no more sentences")) {
                        textArea.append("No more sentences can be input\n");
                        flag = true;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        c.weighty = 0.1;
        c.gridwidth = 0;
        c.weightx = 0;
        frame.add(send, c);

        textArea = new JTextArea(40, 20);
        textArea.setEditable(false);
        c.weighty = 1;
        c.weightx = 1;
        frame.add(textArea, c);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 400));

        frame.setVisible(true);

    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 4448);
            clientOut = new PrintStream(socket.getOutputStream());
            clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            launchGUI();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed connection", "You tried lol", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        client.run();
    }


}
