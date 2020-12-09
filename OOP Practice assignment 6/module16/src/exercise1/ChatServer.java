package exercise1;

import java.net.*;
import java.io.*;

public class ChatServer {
    public static void main(String args[]) {
        ServerSocket s;
        try {
            s = new ServerSocket(2000);
            Socket connection = s.accept();
            if(connection.isConnected()){
                System.out.println("Connected.");
            }
            // Run the listen/accept loop forever
            while (true) {
                try {
                    PrintWriter serverOut = new PrintWriter(
                            connection.getOutputStream(),
                            true);
                    BufferedReader severIn = new BufferedReader(
                                            new InputStreamReader(
                                    connection.getInputStream()));
                    serverOut.println(severIn.readLine());
                    serverOut.println(severIn.readLine());
                    serverOut.println(severIn.readLine());
                } catch (IOException e) {
                    // ignore
                }
            }
        } catch (IOException e) {
            // ignore
        }
    }
}
