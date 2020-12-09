package exercise2;

// FileServer.java
// TCP/IP Server - opens and reads a requested file and
// send the file. The client will request a file to read,
// open it and send the file over the socket, or an error.

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// This is the lab template - fill in the two methods that
// get the filename from the requesting client, and send
// the file

class FileServer {


  // Method for receiving the file name from the client
  // Takes the socket created upon accept and returns the
  // filename as a String

  public static String getFileName(Socket s1) throws IOException {
    // FILL IN THIS METHOD - NOTE: The return type is String
    BufferedReader serverIn = new BufferedReader(new InputStreamReader(s1.getInputStream()));
    return serverIn.readLine();
  }


  // Send file method - opens the file name requested by
  // the client and checks if:
  // a) the file exists, and
  // b) if the server has permission to read the file,
  //    then returns the result or the file to the client

  public static void sendFileToClient(Socket s1, String sfile) throws IOException {

    // FILL IN THIS METHOD - NOTE: the code below is
    // provided as an example of returning a error result
    // to the client.  The \n is required for the client
    // to properly read the string returned

    // Open the file for reading
    // Use file to determine if permissions are correct
    File f = new File(sfile);
    OutputStream s1out = s1.getOutputStream();
    // Does the file exist?
    if (!(f.exists())) {
      String error = "File " + sfile + " does not exist...\n";
      int len = error.length();
      for (int i = 0; i < len; i++) {
        s1out.write((int)error.charAt(i));
      }
      System.out.println(error);
      return;
    }

    int tmp;
    FileInputStream fin = new FileInputStream(f);
    while ((tmp = fin.read()) != -1) {
      s1out.write(tmp);
    }

    s1out.close();
  }

  // You need to fill in the listen/accept loop below to
  // establish the requested connection and then call
  // the sendFileToClient() method.

  public static void main(String args[]) {
    ServerSocket s = (ServerSocket)null;
    Socket s1;
    // what's its usage?
    byte inbuf[] = new byte[100];
    String fileName;

    // Setup our server on socket 4321
    // (wait for 30 seconds before timing out connections)
    try {
      s = new ServerSocket(4321, 1);
    } catch (IOException e) {
      System.out.println("\nServer timed out!");
      System.exit(-1);
    }

    // Run the listen/accept loop forever
    while (true) {
      try {
        s1 = s.accept();
        if(s1.isConnected()){
          System.out.println("Connected.");
        }
        fileName = getFileName(s1);
        sendFileToClient(s1, fileName);

        // Wait here and listen for a connection
        // After the connection is made, send the
        // file to the client

      } catch (IOException e) {
        System.out.println("Error - " + e.toString());
      }
    }
  }
}