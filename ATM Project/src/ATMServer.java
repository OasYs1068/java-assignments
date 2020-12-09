import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ATMServer {
    private static final String p = "Affirmative";
    private static DataInputStream in;
    private static DataOutputStream out;
    private static RandomAccessFile raf;
    private static String hostName;
    private static int portNumber;
    private static ServerSocket serverSocket;
    private static String usernameText,passwordText;

    private static class ThreadHandler extends Thread {
        private Socket socket;
        StringTokenizer tempToken = null;
        long pinDown = 0;
        String username = null;
        long password = 0;
        double balance = 0;
        String firstName = null, lastName = null, isTransferred = null;

        public ThreadHandler(Socket client) {
            socket = client;
        }

        @Override
        public void run() {
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ignored) {
            }
            while (true) {
                try {
                    String selection = in.readUTF();
                    System.out.println(selection);

                    if (selection.equals("logout")){
                        in = new DataInputStream(socket.getInputStream());
                        out = new DataOutputStream(socket.getOutputStream());
                    }

                    if (selection.equals("client change password")){
                        try {
                            String cardNumber = in.readUTF();
                            String password2 = in.readUTF();
                            raf = new RandomAccessFile("AccountInformation.txt","rw");
                            String temp = null;
                            while((temp = raf.readLine()) != null){
                                if(temp.startsWith("username")&&temp.equals("username "+cardNumber)){
                                    raf.write(("password "+password2).getBytes());
                                    out.writeUTF(p);
                                    break;
                                }
                            }
                        } catch (IOException ignored) {
                        }
                        continue;
                    }

                    if (selection.equals("client transfer")){
                        double amount = Double.parseDouble(in.readUTF());
                        String transferCard = in.readUTF();
                        String cardNumber = in.readUTF();
                        String cardBalance = in.readUTF();
                        raf = new RandomAccessFile("AccountInformation.txt","rw");
                        raf.seek(0);
                        String temp = null;
                        while((temp = raf.readLine()) != null){
                            if(temp.startsWith("username")&&temp.equals("username "+cardNumber)){
                                while(!(temp = raf.readLine()).equals("---")){
                                    if(temp.startsWith("cardNum")){
                                        raf.write(("balance "+cardBalance+"\n").getBytes());
                                        break;
                                    }
                                }
                            }
                        }
                        raf.seek(0);
                        while((temp = raf.readLine()) != null){
                            if(temp.startsWith("username")&&temp.equals("username "+transferCard)){
                                while(!(temp = raf.readLine()).equals("---")){
                                    if(temp.startsWith("cardNum")){// We actually want pointer to be at the start of balance line
                                        long pinDown = raf.getFilePointer();
                                        StringTokenizer nxtLine = new StringTokenizer(raf.readLine());
                                        nxtLine.nextElement();
                                        double currentBalance = Double.parseDouble((String)nxtLine.nextElement());
                                        raf.seek(pinDown);
                                        double balanceAfterTransfer = currentBalance+amount;
                                        raf.write(("balance "+balanceAfterTransfer).getBytes());
                                    }
                                    if(temp.startsWith("lastName")){
                                        raf.write(("isTransferred true+").getBytes());
                                        break;
                                    }
                                }
                                out.writeUTF(p);
                            }
                        }
                        continue;
                    }

                    if (selection.equals("client withdraw")){
                        try {
                            String cardNumber = in.readUTF();
                            String cardBalance = in.readUTF();
                            raf = new RandomAccessFile("AccountInformation.txt","rw");
                            String temp = null;
                            while((temp = raf.readLine()) != null){
                                if(temp.startsWith("username")&&temp.equals("username "+cardNumber)){
                                    while(!(temp = raf.readLine()).equals("---")){
                                        if(temp.startsWith("cardNum")){
                                            raf.write(("balance "+cardBalance+"\n").getBytes());
                                            break;
                                        }
                                    }
                                    out.writeUTF(p);
                                }
                            }
                        } catch (IOException ignored) {
                        }
                        continue;
                    }

                    if (selection.equals("client deposit")){
                        try {
                            String cardNumber = in.readUTF();
                            String cardBalance = in.readUTF();
                            raf = new RandomAccessFile("AccountInformation.txt","rw");
                            String temp = null;
                            while((temp = raf.readLine()) != null){
                                if(temp.startsWith("username")&&temp.equals("username "+cardNumber)){
                                    while(!(temp = raf.readLine()).equals("---")){
                                        if(temp.startsWith("cardNum")){
                                            raf.write(("balance "+cardBalance+"\n").getBytes());
                                            break;
                                        }
                                    }
                                    out.writeUTF(p);
                                }
                            }
                        } catch (IOException ignored) {
                        }
                        continue;
                    }

                    if (selection.equals("request user login")) {
                        usernameText = in.readUTF();
                        passwordText = in.readUTF();
                        System.out.println(usernameText+"\n"+passwordText);
                        raf = new RandomAccessFile("AccountInformation.txt", "rw");
                        String temp = " ";
                        boolean isLoggedIn = false;
                        while ((temp = raf.readLine()) != null) {
                            if (temp.equals("***")) {
                                pinDown = raf.getFilePointer();
                            }
                            else {
                                tempToken = new StringTokenizer(temp);
                                if (temp.startsWith("username")) {
                                    tempToken.nextElement();
                                    username = (String) tempToken.nextElement();
                                }
                                if (temp.startsWith("password")) {
                                    tempToken.nextElement();
                                    password = Long.parseLong((String) tempToken.nextElement());
                                }
                                if(temp.equals("---")){
                                    username = " ";
                                    password = 0;
                                }
                                if (usernameText.equals(username) &&
                                        Long.parseLong(passwordText) == password) {
                                    out.writeUTF("match");
                                    isLoggedIn = true;
                                    raf.seek(pinDown);
                                    while (!(temp = raf.readLine()).equals("---")) {
                                        tempToken = new StringTokenizer(temp);
                                        if (temp.startsWith("balance")) {
                                            tempToken.nextElement();
                                            balance = Double.parseDouble((String) tempToken.nextElement());
                                        }
                                        if (temp.startsWith("firstName")) {
                                            tempToken.nextElement();
                                            firstName = (String) tempToken.nextElement();
                                        }
                                        if (temp.startsWith("lastName")) {
                                            tempToken.nextElement();
                                            lastName = (String) tempToken.nextElement();
                                        }
                                        if (temp.startsWith("isTransferred")) {
                                            tempToken.nextElement();
                                            isTransferred = (String) tempToken.nextElement();
                                        }
                                    }
                                    out.writeUTF(username);
                                    out.writeUTF(String.valueOf(password));
                                    out.writeUTF(String.valueOf(balance));
                                    out.writeUTF(firstName);
                                    out.writeUTF(lastName);
                                    out.writeUTF(isTransferred);
                                    String transferVerification = in.readUTF();
                                    System.out.println(transferVerification);
                                    if(transferVerification.equals(p)){
                                        raf.seek(pinDown);
                                        while (!(temp = raf.readLine()).equals("---")) {
                                            if (temp.startsWith("lastName")) {
                                                raf.write("isTransferred false\n".getBytes());
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if (!isLoggedIn) {
                            out.writeUTF("W u o p");
                        }
                    }

                    if (selection.equals("request admin login")) {
                        usernameText = in.readUTF();
                        passwordText = in.readUTF();
                        System.out.println(usernameText+"\n"+passwordText);
                        if(usernameText.equals("admin") && passwordText.equals("#BuffElizaPlease")){
                            out.writeUTF(p);
                        }
                        else {
                            out.writeUTF("W u o p");
                        }
                        continue;
                    }

                    if (selection.equals("admin add new card")){
                        String newCardNum = in.readUTF();
                        String initialBalance = in.readUTF();
                        String firstName = in.readUTF();
                        String lastName = in.readUTF();
                        BankCard newCard = new BankCard(newCardNum,
                                Double.parseDouble(initialBalance),
                                firstName,
                                lastName);
                        try {
                            raf = new RandomAccessFile("AccountInformation.txt","rw");
                            raf.seek(raf.length());
                            raf.write("\n".getBytes());
                            raf.write("***\n".getBytes());
                            raf.write(("username "+newCard.getCardNum()+"\n").getBytes());
                            raf.write(("password "+newCard.getCardPassword()+"\n").getBytes());
                            raf.write(("cardNum "+newCard.getCardNum()+"\n").getBytes());
                            raf.write(("balance "+newCard.getAccount().getBalance()+"\n").getBytes());
                            raf.write(("firstName "+newCard.getFirstName()+"\n").getBytes());
                            raf.write(("lastName "+newCard.getLastName()+"\n").getBytes());
                            raf.write("isTransferred false\n".getBytes());
                            raf.write("---".getBytes());
                            out.writeUTF("new card added");
                        } catch (IOException ignored) {
                        }
                        continue;
                    }

                    if (selection.equals("admin delete a card")){
                        raf = new RandomAccessFile("AccountInformation.txt","rw");
                        int usernameFlag = 0;
                        String temp;
                        ArrayList<String> cardNumbers = new ArrayList<String>();
                        ArrayList<Long> pinDowns = new ArrayList<Long>();
                        pinDowns.add(raf.getFilePointer());
                        while((temp = raf.readLine()) != null){
                            if(temp.equals("---")){
                                pinDowns.add(raf.getFilePointer());
                            }
                            if(temp.startsWith("username")){
                                StringTokenizer tempToken = new StringTokenizer(temp);
                                tempToken.nextElement();
                                String tempCardNum = (String)tempToken.nextElement();
                                cardNumbers.add(tempCardNum);
                                usernameFlag++;
                            }
                        }
                        out.writeUTF(String.valueOf(usernameFlag));
                        for(int i = 0; i < usernameFlag; i++){
                            out.writeUTF((String) cardNumbers.toArray()[i]);
                        }
                        StringBuffer stringBuffer;
                        String toDelete = in.readUTF();
                        for(int i = 0; i < cardNumbers.size() ; i++){
                            if(toDelete.equals(cardNumbers.toArray()[i])){
                                raf.seek((Long) pinDowns.toArray()[i]);
                                stringBuffer = new StringBuffer();
                                do {
                                    temp = raf.readLine();
                                    stringBuffer.append(temp);
                                }while(!(temp.equals("---")));
                                raf.seek((Long) pinDowns.toArray()[i]);
                                System.out.println(stringBuffer.toString());
                                for (int j = 0 ; j < stringBuffer.toString().getBytes().length+7 ; j++) {
                                    raf.write(" ".getBytes());
                                }
                                break;
                            }
                        }
                        continue;
                    }

                    if (selection.equals("quit")){
                        break;
                    }

                } catch (IOException ignored) {
                }
            }
        }
    }

    public static void main(String[] args) {
        hostName = "localhost";
        portNumber = 4422;
        try{
            serverSocket = new ServerSocket(portNumber);
            ArrayList<Socket> clients = new ArrayList<Socket>();
            while (true) {
                Socket client = serverSocket.accept();
                if(client.isConnected()){
                    System.out.println("A new thread joins the battle!");
                }
                clients.add(client);
                new ThreadHandler(client).start();
            }
        }catch (IOException ignored){
        }
    }
}
