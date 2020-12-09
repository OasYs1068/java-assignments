package examples;

import java.net.*;
import java.io.*;

public class SimpleClient {
  public static void main(String args[]) {
    try {
      // Open your connection to a server, at port 5432
      // localhost used here
      Socket s1 = new Socket("127.0.0.1", 5432);  

      // Get an input stream from the socket
      InputStream is = s1.getInputStream();
      // Decorate it with a "data" input stream
      DataInputStream dis = new DataInputStream(is);

      // Read the input and print it to the screen
      System.out.println(dis.readUTF());

      // When done, just close the steam and connection
      dis.close();
      s1.close();
    } catch (ConnectException connExc) {
      System.err.println("Could not connect to the server.");
    } catch (IOException e) {
      // ignore
    }
  }
}
