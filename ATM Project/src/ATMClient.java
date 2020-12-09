import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ATMClient {
    private static final String p = "Affirmative";
    private static Socket clientSocket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static String hostName;
    private static int portNumber;
    private static JFrame login,client,admin;
    private static JPanel loginPanel,clientPanel,adminPanel;
    private static JLabel ATMLabel,loginUserName,loginPassword;
    private static GridBagConstraints c;
    private static JTextField loginUserNameField;
    private static JPasswordField loginCardPassword;
    private static JButton loginBtn,quitBtn;
    private static JLabel clientCurrentBalance,clientName,clientCardNum;
    private static JTextArea clientBalanceDisplay,clientNameDisplay,clientCardNumDisplay;
    private static JButton clientDeposit,clientWithdraw,clientTransfer,clientChangePassword,clientLogout;
    private static JLabel adminSystemLabel;
    private static JButton adminAddCard,adminDeleteCard,adminLogout;

    public static void createLoginComponents(){
        loginPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(20,5,20,5);
        ATMLabel = new JLabel("Welcome to ATM system");
        ATMLabel.setFont(new Font(Font.SERIF,Font.BOLD,30));
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(ATMLabel,c);

        c.gridx = 0;
        c.weightx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        loginUserName = new JLabel("username: ");
        loginPanel.add(loginUserName,c);

        c.gridy = 2;
        loginPassword = new JLabel("password: ");
        loginPanel.add(loginPassword,c);

        c.gridx = 1;
        c.gridwidth = 3;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        loginUserNameField = new JTextField();
        loginPanel.add(loginUserNameField,c);

        c.gridy = 2;
        loginCardPassword = new JPasswordField();
        loginPanel.add(loginCardPassword,c);

        c.gridx = 1;
        c.weightx = 1;
        c.gridy = 3;
        loginBtn = new JButton("login");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginUserNameField.getText().equals("admin")) {
                    try {
                        out.writeUTF("request admin login");
                        out.writeUTF(loginUserNameField.getText());
                        out.writeUTF(String.valueOf(loginCardPassword.getPassword()));
                        if (in.readUTF().equals(p)) {
                            createAdminGUI();
                            login.dispose();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(loginUserNameField.getText().matches("[0-9]+")){
                    try {
                        out.writeUTF("request user login");
                        out.writeUTF(loginUserNameField.getText());
                        out.writeUTF(String.valueOf(loginCardPassword.getPassword()));
                        String username = null;
                        long password = 0;
                        double balance = 0;
                        String firstName = null ,lastName = null, isTransferred = null;
                        String reaction = in.readUTF();
                        System.out.println(reaction);
                        if(reaction.equals("match")){
                            username = in.readUTF();
                            System.out.println(username);
                            password = Long.parseLong(in.readUTF());
                            System.out.println(password);
                            balance = Double.parseDouble(in.readUTF());
                            System.out.println(balance);
                            firstName = in.readUTF();
                            System.out.println(firstName);
                            lastName = in.readUTF();
                            System.out.println(lastName);
                            isTransferred = in.readUTF();
                            System.out.println(isTransferred);
                            createClientGUI(new BankCard(username,password,balance,firstName,lastName));
                            login.dispose();
                            if(isTransferred.equals("true+")){
                                JOptionPane.showMessageDialog(null,
                                        "Welcome back, your account has received a transfer",
                                        "Notice",
                                        JOptionPane.PLAIN_MESSAGE,
                                        new ImageIcon("money.png")
                                );
                                out.writeUTF(p);
                            }
                            else{
                                out.writeUTF("nah");
                            }
                        }
                        if(reaction.equals("W u o p")){
                            JOptionPane.showMessageDialog(login,"Wrong username or password","error",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ignored) {
                    }
                }
                else{
                    JOptionPane.showMessageDialog(login,"Wrong username or password.","error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        loginPanel.add(loginBtn,c);

        c.gridy = 4;
        quitBtn = new JButton("quit");
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.dispose();
                try {
                    out.writeUTF("quit");
                } catch (IOException ignored) {
                }
            }
        });
        loginPanel.add(quitBtn,c);
    }

    public static void createLoginGUI() {
        createLoginComponents();
        login = new JFrame("Login");
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.getContentPane().add(loginPanel);
        login.setMinimumSize(new Dimension(600,400));
        login.setVisible(true);
    }

    public static void createClientComponents(BankCard card){
        clientPanel = new JPanel(new GridBagLayout());
        clientPanel.updateUI();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(20,5,25,5);
        c.gridx = 1;
        c.gridy = 0;
        clientCurrentBalance = new JLabel("Client current balance: ");
        clientPanel.add(clientCurrentBalance,c);

        c.gridx = 3;
        clientBalanceDisplay = new JTextArea(String.valueOf(card.getAccount().getBalance()));
        clientBalanceDisplay.setEditable(false);
        clientBalanceDisplay.setBorder(BorderFactory.createBevelBorder(1,Color.WHITE,Color.BLACK));
        clientPanel.add(clientBalanceDisplay,c);

        c.gridx = 1;
        c.gridy = 1;
        clientName = new JLabel("Client Name: ");
        clientPanel.add(clientName,c);

        c.gridx = 3;
        clientNameDisplay = new JTextArea(card.getFirstName()+" "+card.getLastName());
        clientNameDisplay.setEditable(false);
        clientNameDisplay.setBorder(BorderFactory.createBevelBorder(1,Color.WHITE,Color.BLACK));
        clientPanel.add(clientNameDisplay,c);

        c.gridx = 1;
        c.gridy = 2;
        clientCardNum = new JLabel("Client Card Number: ");
        clientPanel.add(clientCardNum,c);

        c.gridx = 3;
        clientCardNumDisplay = new JTextArea(card.getCardNum());
        clientCardNumDisplay.setEditable(false);
        clientCardNumDisplay.setBorder(BorderFactory.createBevelBorder(1,Color.WHITE,Color.BLACK));
        clientPanel.add(clientCardNumDisplay,c);

        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 1;
        c.gridheight = 1;
        clientDeposit = new JButton("Deposit");
        clientDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(client,"Please input the deposit amount：\n","Deposit",JOptionPane.PLAIN_MESSAGE);
                if(!(amountStr.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(client,"please input a number","failure",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    double amount
                            = Double.parseDouble(amountStr);
                    card.getAccount().deposit(amount);
                    clientBalanceDisplay.setText(String.valueOf(card.getAccount().getBalance()));
                    try {
                        out.writeUTF("client deposit");
                        out.writeUTF(clientCardNumDisplay.getText());
                        out.writeUTF(clientBalanceDisplay.getText());
                        String result = in.readUTF();
                        if (result.equals(p)) {
                            JOptionPane.showMessageDialog(client,"Deposit success","success",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(client,"Deposit failure","failure",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ignore) {
                    } catch (NumberFormatException e1){
                        JOptionPane.showMessageDialog(client,"please input a number","failure",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        clientPanel.add(clientDeposit,c);

        c.gridx = 2;
        clientWithdraw = new JButton("Withdraw");
        clientWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(client,"Please input the withdraw amount: \n","WithDraw",JOptionPane.PLAIN_MESSAGE);
                if(!(amountStr.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(client,"please input a number","failure",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    double amount
                            = Double.parseDouble(amountStr);
                    JOptionPane.showMessageDialog(client,card.getAccount().withdraw(amount),"Withdrawal Result",JOptionPane.PLAIN_MESSAGE);
                    clientBalanceDisplay.setText(String.valueOf(card.getAccount().getBalance()));
                    try {
                        out.writeUTF("client withdraw");
                        out.writeUTF(clientCardNumDisplay.getText());
                        out.writeUTF(clientBalanceDisplay.getText());
                        String result = in.readUTF();
                        if (result.equals(p)) {
                            JOptionPane.showMessageDialog(client,"Withdrawal success","success",JOptionPane.PLAIN_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(client,"Withdrawal failure","failure",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                    }
                }
            }
        });
        clientPanel.add(clientWithdraw,c);

        c.gridx = 4;
        clientTransfer = new JButton("Transfer");
        clientTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(client,"Please input the transfer amount: \n","Transfer",JOptionPane.PLAIN_MESSAGE);
                if(!(amountStr.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(client,"please input a number","failure",JOptionPane.ERROR_MESSAGE);
                }
                double amount = Double.parseDouble(amountStr);
                String transferCard = JOptionPane.showInputDialog("Which card to transfer to: \n");
                clientBalanceDisplay.setText(String.valueOf(card.getAccount().getBalance()-amount));
                try {
                    out.writeUTF("client transfer");
                    out.writeUTF(String.valueOf(amount));
                    out.writeUTF(transferCard);
                    out.writeUTF(clientCardNumDisplay.getText());
                    out.writeUTF(clientBalanceDisplay.getText());
                    String result = in.readUTF();
                    if (result.equals(p)) {
                        JOptionPane.showMessageDialog(client,card.getAccount().withdraw(amount),"Transfer Result",JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(client,"Transfer failure","failure",JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException ignored) {
                }
            }
        });
        clientPanel.add(clientTransfer,c);

        c.gridx = 1;
        clientChangePassword = new JButton("Change Password");
        clientChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password1 = JOptionPane.showInputDialog(client,"Please input the new password：\n","Changing Password",JOptionPane.PLAIN_MESSAGE);
                String password2 = JOptionPane.showInputDialog(client,"Please input the new password again：\n","Changing Password",JOptionPane.PLAIN_MESSAGE);
                if(!(password1.equals(password2))){
                    JOptionPane.showMessageDialog(client,"inconsistent passwords, password modification failed.","failed",JOptionPane.ERROR_MESSAGE);
                }else{
                    card.setCardPassword(Long.parseLong(password2));
                    JOptionPane.showMessageDialog(client,"Password changed successfully!","success",JOptionPane.INFORMATION_MESSAGE);
                    try {
                        out.writeUTF("client change password");
                        out.writeUTF(clientCardNumDisplay.getText());
                        out.writeUTF(password2);
                        String result = in.readUTF();
                        System.out.println(result);
                        if (result.equals(p)) {
                            JOptionPane.showMessageDialog(client,"Password change success","success",JOptionPane.PLAIN_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(client,"Password change failure","failure",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                    }
                }
            }
        });
        clientPanel.add(clientChangePassword,c);

        c.gridx = 3;
        clientLogout = new JButton("Logout");
        clientLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    out.writeUTF("logout");
                } catch (IOException ignore) {
                }
                client.dispose();
                createLoginGUI();
                loginUserNameField.setText("");
                loginCardPassword.setText("");
            }
        });
        clientPanel.add(clientLogout,c);

    }

    public static void createClientGUI(BankCard card){
        createClientComponents(card);
        client = new JFrame("Client");
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.getContentPane().add(clientPanel);
        client.setMinimumSize(new Dimension(600,200));
        client.pack();
        client.setVisible(true);
    }

    public static void createAdminComponents(){
        adminPanel = new JPanel(new GridBagLayout());
        c.insets = new Insets(50,5,50,5);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        adminSystemLabel = new JLabel("Welcome, administrator");
        adminSystemLabel.setFont(new Font(Font.SERIF,Font.BOLD,30));
        c.gridx = 2;
        c.gridy = 0;
        adminPanel.add(adminSystemLabel,c);

        c.insets = new Insets(20,5,20,5);
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 3;
        adminAddCard = new JButton("Add");
        adminAddCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCardNum = JOptionPane.showInputDialog(admin,"Please input the new card number","Adding new card",JOptionPane.PLAIN_MESSAGE);
                JTextField firstNameField = new JTextField(5);
                JTextField lastNameField = new JTextField(5);
                JTextField initialBalanceField = new JTextField(5);

                JPanel newCardPanel = new JPanel();
                newCardPanel.add(new JLabel("firstName: "));
                newCardPanel.add(firstNameField);
                newCardPanel.add(Box.createVerticalStrut(15)); // a spacer
                newCardPanel.add(new JLabel("lastName: "));
                newCardPanel.add(lastNameField);
                newCardPanel.add(Box.createVerticalStrut(15)); // a spacer
                newCardPanel.add(new JLabel("Initial Balance: "));
                newCardPanel.add(initialBalanceField);

                int result = JOptionPane.showConfirmDialog(null, newCardPanel,
                        "Creating a new card", JOptionPane.OK_CANCEL_OPTION);
                try {
                    if (result == JOptionPane.OK_OPTION) {
                        out.writeUTF("admin add new card");
                        out.writeUTF(newCardNum);
                        out.writeUTF(initialBalanceField.getText());
                        out.writeUTF(firstNameField.getText());
                        out.writeUTF(lastNameField.getText());

                        String temp = in.readUTF();
                        if(temp.equals("new card added")){
                            JOptionPane.showMessageDialog(admin,"New card successfully added","success",JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                } catch (IOException ignored) {
                }

            }
        });
        adminPanel.add(adminAddCard,c);

        c.gridy = 2;
        adminDeleteCard = new JButton("Delete");
        adminDeleteCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    while (true) {
                        out.writeUTF("admin delete a card");
                        int usernameFlag = Integer.parseInt(in.readUTF());
                        String[] cardNumbers;
                        if (usernameFlag == 0) {
                            JOptionPane.showMessageDialog(admin,"No card in the dataBase","Fail",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        else{
                            cardNumbers = new String[usernameFlag];
                        }
                        for(int i = 0; i < usernameFlag; i++){
                            cardNumbers[i] = in.readUTF();
                        }
                        String toDelete =
                                (String)JOptionPane.showInputDialog(
                                        admin,
                                        "Choose a card to delete",
                                        "Choose a card",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null,
                                        cardNumbers,
                                        cardNumbers[0]);
                        if (toDelete != null) {
                            out.writeUTF(toDelete);
                        }
                        JOptionPane.showMessageDialog(admin,"Operation successfully executed","Success",JOptionPane.PLAIN_MESSAGE);
                        break;
                    }

                } catch (IOException | NumberFormatException ignored) {
                }
            }
        });

        adminPanel.add(adminDeleteCard,c);

        c.gridy = 3;
        adminLogout = new JButton("Logout");
        adminLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    out.writeUTF("logout");
                } catch (IOException ignore) {
                }
                admin.dispose();
                createLoginGUI();
                loginUserNameField.setText("");
                loginCardPassword.setText("");
            }
        });
        adminPanel.add(adminLogout,c);
    }

    public static void createAdminGUI(){
        createAdminComponents();
        admin = new JFrame("Administrator");
        admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admin.getContentPane().add(adminPanel);
        admin.setMinimumSize(new Dimension(400,400));
        admin.pack();
        admin.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost",4422);
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            createLoginGUI();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Connection Failed","Failed",JOptionPane.ERROR_MESSAGE);
        }

    }
}
