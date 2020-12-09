package exercise2;

import java.io.*;
import java.net.*;

public class EchoServer implements Runnable {
    ServerSocket serverSocket;
    BufferedReader serverIn;

    public EchoServer() {
        try {
            serverIn = new BufferedReader(new FileReader("multiLines.txt"));
            serverSocket = new ServerSocket(4448);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getLine(int number) {
        StringBuffer result = new StringBuffer();
        try {
            for (int i = 0; i < number; i++) {
                String temp;
                if ((temp = serverIn.readLine()) != null) {
                    result.append(temp).append(" ##" );
                } else {
                    result.append("no more sentences ## ");
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Waiting for client request");
            try {
                Socket socket = serverSocket.accept();
                BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    int number = Integer.parseInt(serverIn.readLine());
                    String result = getLine(number);
                    PrintStream printer = new PrintStream(socket.getOutputStream());
                    printer.println(result);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new EchoServer().run();
    }
}
