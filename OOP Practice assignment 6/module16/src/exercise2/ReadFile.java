package exercise2;

// ReadFile.java
// TCP/IP simple client that reads a file on the server
// Accepts at least one argument, the name of a file on
// the server to read. Opens a connection with the server
// and displays the resultant file or an error result

import java.io.*;
import java.net.Socket;

// This is the lab template.
// Fill in the code for the two methods called from main

class ReadFile {

    // This method will send the file name to read to the
    // server. You need to set up your own data output
    // stream to pass the filename to the server (which
    // should be reading a data input stream and expecting
    // a string).

    public static void sendFileName(Socket s, String fileName) throws IOException {
        PrintStream writer = new PrintStream(s.getOutputStream());
        writer.println(fileName);
    }

    // This method will receive the file from the Server,
    // or the result of the attempt to read the file.

    public static void receiveFile(Socket s, String fileName) throws IOException {
        // NOTICE: I have rewritten this method, adding the second arg fileName,
        // to save the received file to the specific location.
        // FILL IN THIS METHOD - Hint: look at the
        // simpleClient code

        // Practically, the two java programs are usually run at the same directory,
        // so add .bak to prevent overwrite the source file.
        File f = new File(fileName + ".txt");// prevent overwriting the original file
        FileOutputStream fOut = new FileOutputStream(f);
        InputStream clientIn = s.getInputStream();
        int temp;
        while ((temp = clientIn.read()) != -1) {
            fOut.write(temp);
            System.out.print((char)temp);
        }
        fOut.close();

    }

    public static void main(String args[]) {
        Socket s;
        int port = 4321;

        // Did we receive the correct number of arguments?
        if (args.length != 2) {
            System.out.println("Usage: java ReadFile <server> <file>");
            System.exit(-1);
        }

        try {

            // Open our connection to args[0]
            s = new Socket(args[0], port);

            // Send the file name to the Server
            sendFileName(s, args[1]);

            // Output the file (or result of the request)
            receiveFile(s, args[1]);

            // When the EOF is reached, just close the
            // connection and exit
            s.close();

        } catch (IOException e) {
            // ignore
        }
    }
}